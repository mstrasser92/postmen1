package at.post.postmen.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.post.postmen.R;
import at.post.postmen.data.Adress;
import at.post.postmen.data.AdressDataSource;

public class ShowAdressesActivity extends ActionBarActivity {

    private AdressDataSource adressSource;
    private List<Adress> adressList;
    private ListView adressListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adresses);

        adressListView = (ListView)findViewById(R.id.adressList);

        adressSource = new AdressDataSource(this);
        try {
            adressSource.open();
            adressList = adressSource.getAllAdresses();
            adressSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<Adress> adapterAdresses = new ArrayAdapter<Adress>(ShowAdressesActivity.this, android.R.layout.simple_list_item_1, adressList);
        adressListView.setAdapter(adapterAdresses);

        adressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adress item = adapterAdresses.getItem(position);
                adressSource.updateParcelNumber(item);
                item.setParcels(item.getParcels() + 1);
                adapterAdresses.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_adresses, menu);
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
}
