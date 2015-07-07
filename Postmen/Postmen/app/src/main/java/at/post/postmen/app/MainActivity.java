package at.post.postmen.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import at.post.postmen.R;
import at.post.postmen.data.AdressDataSource;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private AdressDataSource adressSource;

    private Button newAdressBtn;
    private Button showAdressesBtn;
    private Button resetParcelsBtn;
    private Button resetDbBtn;
    private Button addParcelsBtn;
    private Button goOnTourBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adressSource = new AdressDataSource(this);

        newAdressBtn = (Button)findViewById(R.id.NewAdressBtn);
        newAdressBtn.setOnClickListener(this);

        showAdressesBtn = (Button)findViewById(R.id.ShowAdressesBtn);
        showAdressesBtn.setOnClickListener(this);

        resetParcelsBtn = (Button)findViewById(R.id.resetParcelsBtn);
        resetParcelsBtn.setOnClickListener(this);

        resetDbBtn = (Button)findViewById(R.id.resetDbBtn);
        resetDbBtn.setOnClickListener(this);

        addParcelsBtn = (Button)findViewById(R.id.addParcelsBtn);
        addParcelsBtn.setOnClickListener(this);

        goOnTourBtn = (Button)findViewById(R.id.goOnTourBtn);
        goOnTourBtn.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {
        int clicked = v.getId();

        switch(clicked)
        {
            case R.id.NewAdressBtn:
                Intent intent = new Intent(this, AddAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.ShowAdressesBtn:
                Intent intent2 = new Intent(this, ShowAdressesActivity.class);
                startActivity(intent2);
                break;
            case R.id.resetParcelsBtn:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                adressSource.resetParcels();
                                Toast.makeText(MainActivity.this, getString(R.string.reset_parcels_done), Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }

                };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                break;
            case R.id.resetDbBtn:
                DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                adressSource.resetDb();
                                Toast.makeText(MainActivity.this, getString(R.string.reset_db_done), Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }

                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener2)
                        .setNegativeButton("No", dialogClickListener2).show();

                break;
            case R.id.addParcelsBtn:
                Intent intent3 = new Intent(this, AddParcelsActivity.class);
                startActivity(intent3);
                break;
            case R.id.goOnTourBtn:
                Intent intent4 = new Intent(this, TourActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
