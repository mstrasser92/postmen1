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
import at.post.postmen.view.AdressAdapter;

public class TourActivity extends ActionBarActivity {

    private AdressDataSource adressSource;
    private List<Adress> remainingParcelsList;
    private ListView remainingParcelsLv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        adressSource = new AdressDataSource(this);
        try {
            adressSource.open();
            remainingParcelsList = adressSource.getRemainingParcels();
            adressSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        remainingParcelsLv = (ListView)findViewById(R.id.remainingParcelsLv);
        //final ArrayAdapter<Adress> remainingParcelsListAdapter2 = new ArrayAdapter<Adress>(TourActivity.this, android.R.layout.simple_list_item_1, remainingParcelsList);
        final AdressAdapter remainingParcelsListAdapter = new AdressAdapter(TourActivity.this, null, remainingParcelsList);
        remainingParcelsLv.setAdapter(remainingParcelsListAdapter);

        remainingParcelsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adress item = remainingParcelsListAdapter.getItem(position);

                if(item.getParcels() > 0){
                    adressSource.updateParcelNumber(item, true);
                    item.setParcels(0);
                } else {
                    adressSource.updateMoneyNumber(item, true);
                    item.setMoney(0);
                }

                if(item.getMoney() == 0)
                    remainingParcelsListAdapter.remove(item);

                remainingParcelsListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tour, menu);
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
