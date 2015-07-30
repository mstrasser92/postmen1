package at.post.postmen.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.post.postmen.R;
import at.post.postmen.data.Adress;
import at.post.postmen.data.AdressDataSource;

public class AddParcelsActivity extends ActionBarActivity {

    private AdressDataSource adressSource;
    private List<String> streetList;
    private List<String> numberList;
    private String selectedStreet;

    private ListView streetLv;
    private ListView numbersOfStreetLv;
    private TextView streetTv;

    private boolean money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcels);

        Intent i = getIntent();
        money = i.getBooleanExtra("Money", false);

        numberList = new ArrayList<String>();

        streetTv = (TextView) findViewById(R.id.streetTv);
        streetLv = (ListView)findViewById(R.id.streetLv);
        numbersOfStreetLv = (ListView) findViewById(R.id.numbersOfStreetLv);

        adressSource = new AdressDataSource(this);
        try {
            adressSource.open();
            streetList = adressSource.getStreets();
            adressSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> streetListAdapter = new ArrayAdapter<String>(AddParcelsActivity.this, android.R.layout.simple_list_item_1, streetList);
        streetLv.setAdapter(streetListAdapter);
        streetLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStreet = streetListAdapter.getItem(position);
                streetTv.setText(selectedStreet);
                streetTv.setVisibility(View.VISIBLE);
                try {
                    adressSource.open();
                    numberList = adressSource.getNumbersOfStreet(selectedStreet);
                    adressSource.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Log.d("AddParcelsActivity", "Liste ist " + numberList.size());
                streetLv.setVisibility(View.INVISIBLE);
                numbersOfStreetLv.setVisibility(View.VISIBLE);
                final ArrayAdapter<String> numberListAdapter = new ArrayAdapter<String>(AddParcelsActivity.this, android.R.layout.simple_list_item_1, numberList);
                numbersOfStreetLv.setAdapter(numberListAdapter);
            }
        });


        numbersOfStreetLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) numbersOfStreetLv.getAdapter().getItem(position);
                Adress adress = adressSource.getOneAdress(selectedStreet, item);
                if(money){
                    adressSource.updateMoneyNumber(adress);
                    Toast.makeText(AddParcelsActivity.this, "Einen Geldauftrag bei " + selectedStreet + " " + item + " hinzugefügt!", Toast.LENGTH_LONG).show();
                } else {
                    adressSource.updateParcelNumber(adress);
                    Toast.makeText(AddParcelsActivity.this, "Ein Paket bei " + selectedStreet + " " + item + " hinzugefügt!", Toast.LENGTH_LONG).show();
                }
                streetLv.setVisibility(View.VISIBLE);
                numbersOfStreetLv.setVisibility(View.INVISIBLE);
                streetTv.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_parcels, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(streetLv.getVisibility() == View.INVISIBLE)
        {
            streetLv.setVisibility(View.VISIBLE);
            numbersOfStreetLv.setVisibility(View.INVISIBLE);
            streetTv.setVisibility(View.INVISIBLE);
        } else {
            this.finish();
        }
    }
}
