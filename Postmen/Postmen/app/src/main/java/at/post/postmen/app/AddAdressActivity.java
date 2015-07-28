package at.post.postmen.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import at.post.postmen.R;
import at.post.postmen.data.Adress;
import at.post.postmen.data.AdressDataSource;
import at.post.postmen.data.SigRelAutDataSource;
import at.post.postmen.data.SignatureReleaseAuthorisation;

public class AddAdressActivity extends ActionBarActivity implements View.OnClickListener {

    private Button createButton;
    private Button loadOrt7Btn;
    private Button loadLand12Btn;
    private EditText streetEt;
    private EditText numberEt;

    private AdressDataSource adressSource;
    private SigRelAutDataSource sigRelAutDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        streetEt = (EditText) findViewById(R.id.streetEt);
        numberEt = (EditText) findViewById(R.id.numberEt);

        createButton = (Button) findViewById(R.id.bCreate);
        createButton.setOnClickListener(this);

        loadOrt7Btn = (Button) findViewById(R.id.loadOrt7Btn);
        loadOrt7Btn.setOnClickListener(this);

        loadLand12Btn = (Button) findViewById(R.id.loadLand12Btn);
        loadLand12Btn.setOnClickListener(this);

        adressSource = new AdressDataSource(this);
        sigRelAutDataSource = new SigRelAutDataSource(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_adress, menu);
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

        switch (clicked) {
            case R.id.bCreate:
                createAdress();
                break;
            case R.id.loadOrt7Btn:
                new Ort7().execute("");
                break;
            case R.id.loadLand12Btn:
                new Land12().execute("");
                break;
        }
    }


    private void createAdress() {


        Log.d("CreateAdress()", "Got to create Adress before database connection");

        try {
            adressSource.open();
            Log.d("CreateAdress()", "Opened Connection");
            Adress newAdress = adressSource.createAdress(streetEt.getText().toString(), numberEt.getText().toString(), 0);
            Log.d("CreateAdress()", "Wrote new Adress");
            adressSource.close();

            Toast.makeText(this, getString(R.string.Created) + " " + newAdress.getStreet() + " " + newAdress.getNumber(), Toast.LENGTH_LONG).show();
            streetEt.setText("");
            numberEt.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class Ort7 extends AsyncTask<String, Integer, String> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), String.valueOf(values[0]) + "%", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), "Ort 7 geladen", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                adressSource.resetDb();
                publishProgress(25);
                adressSource.open();
                adressSource.createAdress("Europa-Straße", "49");
                adressSource.createAdress("Europa-Straße", "51");
                adressSource.createAdress("Europa-Straße", "53");
                adressSource.createAdress("Europa-Straße", "55");
                adressSource.createAdress("Europa-Straße", "64");
                adressSource.createAdress("Adalbert-Stifter-Gasse", "11");
                adressSource.createAdress("Adalbert-Stifter-Gasse", "9");
                adressSource.createAdress("Adalbert-Stifter-Gasse", "7");
                adressSource.createAdress("Adalbert-Stifter-Gasse", "5");
                adressSource.createAdress("Adalbert-Stifter-Gasse", "3");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "12");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "10");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "8");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "6");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "4");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "2");
                adressSource.createAdress("Südtirolerstraße", "54");
                adressSource.createAdress("Südtirolerstraße", "52");
                adressSource.createAdress("Südtirolerstraße", "50");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "2");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "1");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "3");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "5");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "4");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "6");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "7");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "8");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "9");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "11");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "10");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "12");
                adressSource.createAdress("Franz-Grillparzer-Gasse", "14");
                adressSource.createAdress("Südtirolerstraße", "58");
                adressSource.createAdress("Südtirolerstraße", "61");
                adressSource.createAdress("Europa-Straße", "65");
                adressSource.createAdress("Europa-Straße", "63");
                adressSource.createAdress("Südtirolerstraße", "60");
                adressSource.createAdress("Südtirolerplatz", "12");
                adressSource.createAdress("Südtirolerplatz", "4");
                adressSource.createAdress("Südtirolerplatz", "5");
                adressSource.createAdress("Südtirolerplatz", "6");
                adressSource.createAdress("Südtirolerplatz", "11");
                adressSource.createAdress("Südtirolerplatz", "7");
                adressSource.createAdress("Südtirolerplatz", "8");
                adressSource.createAdress("Südtirolerplatz", "9");
                adressSource.createAdress("Waldhof", "1");
                adressSource.createAdress("Waldhof", "2");
                adressSource.createAdress("Waldhof", "14");
                adressSource.createAdress("Waldhof", "12");
                adressSource.createAdress("Waldhof", "11");
                adressSource.createAdress("Waldhof", "10");
                adressSource.createAdress("Waldhof", "9");
                adressSource.createAdress("Waldhof", "8");
                adressSource.createAdress("Waldhof", "7");
                adressSource.createAdress("Waldhof", "6");
                adressSource.createAdress("Waldhof", "5");
                adressSource.createAdress("Waldhof", "4");
                adressSource.createAdress("Waldhof", "3");
                adressSource.createAdress("Murhof", "10");
                adressSource.createAdress("Murhof", "11");
                adressSource.createAdress("Murhof", "1");
                adressSource.createAdress("Murhof", "2");
                adressSource.createAdress("Murhof", "3");
                adressSource.createAdress("Murhof", "8");
                adressSource.createAdress("Murhof", "9");
                adressSource.createAdress("Murhof", "4");
                adressSource.createAdress("Murhof", "4a");
                adressSource.createAdress("Murhof", "5");
                adressSource.createAdress("Murhof", "6");
                adressSource.createAdress("Murhof", "6a");
                adressSource.createAdress("Murhof", "7");
                adressSource.createAdress("Murhof", "12");
                adressSource.createAdress("Murhof", "13");
                adressSource.createAdress("Murhof", "14");
                adressSource.createAdress("Südtirolerplatz", "10");
                adressSource.createAdress("Stadion-Straße", "7");
                adressSource.createAdress("Stadion-Straße", "8");
                adressSource.createAdress("Stadion-Straße", "10");
                adressSource.createAdress("Stadion-Straße", "11");
                adressSource.createAdress("Stadion-Straße", "17");
                adressSource.createAdress("Stadion-Straße", "15");
                adressSource.createAdress("Stadion-Straße", "13");
                adressSource.createAdress("Stadion-Straße", "23a");
                adressSource.createAdress("Stadion-Straße", "23");
                adressSource.createAdress("Stadion-Straße", "21");
                adressSource.createAdress("Stadion-Straße", "19");
                adressSource.createAdress("Stadion-Straße", "27");
                adressSource.createAdress("Stadion-Straße", "29");
                adressSource.createAdress("Stadion-Straße", "30a");
                adressSource.createAdress("Stadion-Straße", "30b");
                adressSource.createAdress("Stadion-Straße", "30c");
                adressSource.createAdress("Stadion-Straße", "34");
                adressSource.createAdress("Stadion-Straße", "36");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "21");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "19");
                adressSource.createAdress("Stadion-Straße", "33");
                adressSource.createAdress("Stadion-Straße", "31");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "25");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "27");
                adressSource.createAdress("Stadion-Straße", "25");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "15");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "17");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "5");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "9");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "7");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "11");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "11a");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "11b");
                adressSource.createAdress("Ferdinand-Raimund-Gasse", "7a");
                adressSource.createAdress("Pantherstraße", "5");
                adressSource.createAdress("Pantherstraße", "7");
                adressSource.createAdress("Südtirolerplatz", "3");
                adressSource.createAdress("Pantherstraße", "2");
                adressSource.createAdress("Pantherstraße", "4");
                adressSource.createAdress("Pantherstraße", "6");
                adressSource.createAdress("Pantherstraße", "8");
                publishProgress(50);
                adressSource.createAdress("Pantherstraße", "10");
                adressSource.createAdress("Pantherstraße", "12");
                adressSource.createAdress("Pantherstraße", "14");
                adressSource.createAdress("Pantherstraße", "16");
                adressSource.createAdress("Gaubygasse", "21");
                adressSource.createAdress("Gaubygasse", "19");
                adressSource.createAdress("Gaubygasse", "17");
                adressSource.createAdress("Gaubygasse", "15");
                adressSource.createAdress("Gaubygasse", "13");
                adressSource.createAdress("Gaubygasse", "9");
                adressSource.createAdress("Gaubygasse", "7");
                adressSource.createAdress("Gaubygasse", "5");
                adressSource.createAdress("Gaubygasse", "3");
                adressSource.createAdress("Gaubygasse", "1");
                adressSource.createAdress("Gaubygasse", "18");
                adressSource.createAdress("Gaubygasse", "12");
                adressSource.createAdress("Antoneumgasse", "11");
                adressSource.createAdress("Antoneumgasse", "9");
                adressSource.createAdress("Antoneumgasse", "7");
                adressSource.createAdress("Antoneumgasse", "5");
                adressSource.createAdress("Antoneumgasse", "3");
                adressSource.createAdress("Antoneumgasse", "1");
                adressSource.createAdress("Antoneumgasse", "2");
                adressSource.createAdress("Antoneumgasse", "4");
                adressSource.createAdress("Antoneumgasse", "6");
                adressSource.createAdress("Antoneumgasse", "8");
                adressSource.createAdress("Antoneumgasse", "10");
                adressSource.createAdress("Antoneumgasse", "12");
                adressSource.createAdress("Antoneumgasse", "14");
                adressSource.createAdress("Antoneumgasse", "16");
                adressSource.createAdress("Antoneumgasse", "18");
                adressSource.createAdress("Antoneumgasse", "20");
                adressSource.createAdress("Antoneumgasse", "22");
                adressSource.createAdress("Antoneumgasse", "24");
                adressSource.createAdress("Antoneumgasse", "26");
                adressSource.createAdress("Antoneumgasse", "28");
                adressSource.createAdress("Gaubygasse", "8");
                adressSource.createAdress("Styriagasse", "3");
                adressSource.createAdress("Styriagasse", "6");
                adressSource.createAdress("Styriagasse", "8");
                adressSource.createAdress("Styriagasse", "10");
                adressSource.createAdress("Styriagasse", "12");
                adressSource.createAdress("Styriagasse", "7");
                adressSource.createAdress("Styriagasse", "14");
                adressSource.createAdress("Pantherstraße", "22");
                adressSource.createAdress("Pantherstraße", "18");
                adressSource.createAdress("Styriagasse", "16");
                adressSource.createAdress("Styriagasse", "18");
                adressSource.createAdress("Styriagasse", "20");
                adressSource.createAdress("Styriagasse", "15");
                adressSource.createAdress("Styriagasse", "19");
                adressSource.createAdress("Europa-Straße", "50");
                adressSource.createAdress("Europa-Straße", "52");
                adressSource.createAdress("Europa-Straße", "54");
                adressSource.createAdress("Europa-Straße", "61");
                adressSource.createAdress("Gaubygasse", "27");
                adressSource.createAdress("Gaubygasse", "25");
                adressSource.createAdress("Gaubygasse", "26");
                adressSource.createAdress("Gaubygasse", "24");
                adressSource.createAdress("Pantherstraße", "13");
                adressSource.createAdress("Pantherstraße", "15");
                adressSource.createAdress("Pantherstraße", "17");
                adressSource.createAdress("Pantherstraße", "20");
                adressSource.createAdress("Pantherstraße", "19");
                adressSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            publishProgress(75);
            try {
                sigRelAutDataSource.open();
                sigRelAutDataSource.createSigRelAut("Europa-Straße", "64", "Dorfer Christiane");
                sigRelAutDataSource.createSigRelAut("Europa-Straße", "64", "Walder Daniel");
                sigRelAutDataSource.createSigRelAut("Südtirolerstraße", "52", "Suppan Hubert");
                sigRelAutDataSource.createSigRelAut("Südtirolerstraße", "52", "Suppan Hubert jun.");
                sigRelAutDataSource.createSigRelAut("Südtirolerstraße", "52", "Suppan Barbara");
                sigRelAutDataSource.createSigRelAut("Südtirolerstraße", "52", "Grillitsch Daniela");
                sigRelAutDataSource.createSigRelAut("Südtirolerstraße", "50", "Lanzenberger Maria");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "1", "Gaisbachgrabner Karl");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "1", "Gaisbachgrabner Evelyn");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "1", "Gaisbachgrabner Kerstin");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "3", "Leitner Katrin");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "3", "Krammer Michael");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "11", "Ranner Robert");
                sigRelAutDataSource.createSigRelAut("Franz-Grillparzer-Gasse", "11", "Fink Birgit");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "5", "Amberger Cornelia");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "5", "Amberger Raphael");
                sigRelAutDataSource.createSigRelAut("Waldhof", "1", "Pfeifer Dietmar");
                sigRelAutDataSource.createSigRelAut("Waldhof", "14", "Steinwidder Desiree");
                sigRelAutDataSource.createSigRelAut("Waldhof", "11", "Familie Pfeifer");
                sigRelAutDataSource.createSigRelAut("Murhof", "10", "Steiner Melitta");
                sigRelAutDataSource.createSigRelAut("Murhof", "12", "Moschig Erwin");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "10", "Amberger Alfred");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "10", "Amberger Monika");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "23a", "Summer Jochen");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "23", "Kreiter Katrin");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "23", "Hoffellner Eveline");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "23", "Hoffellner Erwin");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "21", "Okrogelnik Petra");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "19", "Bacher Josef");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "19", "Grasser Herbert");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "19", "Vasold Margit");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "30b", "Bucher Jasmin");
                sigRelAutDataSource.createSigRelAut("Stadion-Straße", "30b", "Göttfried David");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "25", "Kreuzer Erika");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "15", "Mostögl Elisabeth");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "15", "Mostögl Michael");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "9", "Stückler Renate");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "11", "Berger Johann");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "11", "Berger Brigitte");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "11b", "Porkristl Walter");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "7a", "Felfer Karl");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "7a", "Hasler Melanie");
                sigRelAutDataSource.createSigRelAut("Ferdinand-Raimund-Gasse", "7a", "Oberchristl Helga");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "3", "Felber Günter");
                sigRelAutDataSource.createSigRelAut("Südtirolerplatz", "3", "Luttenberger Regina");
                sigRelAutDataSource.createSigRelAut("Gaubygasse", "12", "Pfalzer Maria");
                sigRelAutDataSource.createSigRelAut("Antoneumgasse", "11", "Tanner Siegfried");
                sigRelAutDataSource.createSigRelAut("Antoneumgasse", "11", "Tanner Petra");
                sigRelAutDataSource.createSigRelAut("Antoneumgasse", "8", "Lamuth Petra");
                sigRelAutDataSource.createSigRelAut("Antoneumgasse", "28", "Korb Hans-Peter");
                sigRelAutDataSource.createSigRelAut("Gaubygasse", "8", "Wöhry Ines");
                sigRelAutDataSource.createSigRelAut("Gaubygasse", "8", "Eisbacher Kerstin");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "3", "Schett Luzia");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "3", "Steiner Barbara");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "8", "Url Kerstin");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "8", "Url Petra");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "8", "Url Kevin");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "7", "Thurner Gerhard");
                sigRelAutDataSource.createSigRelAut("Styriagasse", "18", "Porkristl Marianne");
                sigRelAutDataSource.createSigRelAut("Pantherstraße", "13", "Schmid Nina");
                sigRelAutDataSource.createSigRelAut("Pantherstraße", "15", "Degold Marko");
                sigRelAutDataSource.createSigRelAut("Pantherstraße", "15", "Degold Natascha");
                sigRelAutDataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            publishProgress(100);
            return null;
        }
    }

    private class Land12 extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), "Land 12 geladen", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), String.valueOf(values[0]) + "%", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                adressSource.resetDb();
                adressSource.open();
                adressSource.createAdress("Landstraße(Fohnsdorf)", "17");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "19 St1");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "19 St2");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "19 St3");
                adressSource.createAdress("Maiweg", "5");
                adressSource.createAdress("Maiweg", "4");
                adressSource.createAdress("Maiweg", "6");
                adressSource.createAdress("Maiweg", "7");
                adressSource.createAdress("Maiweg", "9");
                adressSource.createAdress("Maiweg", "11");
                adressSource.createAdress("Maiweg", "1a");
                adressSource.createAdress("Maiweg", "2");
                adressSource.createAdress("Maiweg", "1");
                adressSource.createAdress("Lorenzistraße", "5");
                adressSource.createAdress("Lorenzistraße", "7");
                adressSource.createAdress("Lorenzistraße", "9");
                adressSource.createAdress("Höhenstraße", "20");
                adressSource.createAdress("Schachthausweg", "10");
                adressSource.createAdress("Schachthausweg", "8");
                adressSource.createAdress("Schachthausweg", "7");
                adressSource.createAdress("Schachthausweg", "4");
                adressSource.createAdress("Schachthausweg", "6");
                adressSource.createAdress("Schachthausweg", "3");
                adressSource.createAdress("Schachthausweg", "2");
                adressSource.createAdress("Schachthausweg", "1");
                adressSource.createAdress("Hügelgasse", "11");
                adressSource.createAdress("Hügelgasse", "13");
                adressSource.createAdress("Hügelgasse", "15");
                adressSource.createAdress("Hügelgasse", "9");
                adressSource.createAdress("Hügelgasse", "4");
                adressSource.createAdress("Hügelgasse", "7");
                adressSource.createAdress("Hügelgasse", "5");
                adressSource.createAdress("Hügelgasse", "3");
                adressSource.createAdress("Hügelgasse", "2");
                publishProgress(25);
                adressSource.createAdress("Enziangasse", "7");
                adressSource.createAdress("Enziangasse", "6");
                adressSource.createAdress("Enziangasse", "3");
                adressSource.createAdress("Enziangasse", "4");
                adressSource.createAdress("Enziangasse", "2");
                adressSource.createAdress("Enziangasse", "1");
                adressSource.createAdress("Höhenstraße", "13");
                adressSource.createAdress("Höhenstraße", "13a");
                adressSource.createAdress("Höhenstraße", "11");
                adressSource.createAdress("Höhenstraße", "9");
                adressSource.createAdress("Höhenstraße", "18");
                adressSource.createAdress("Höhenstraße", "16");
                adressSource.createAdress("Höhenstraße", "7");
                adressSource.createAdress("Höhenstraße", "14");
                adressSource.createAdress("Höhenstraße", "12");
                adressSource.createAdress("Höhenstraße", "10");
                adressSource.createAdress("Traunerweg", "3");
                adressSource.createAdress("Traunerweg", "24");
                adressSource.createAdress("Schachthausweg", "12");
                adressSource.createAdress("Schachthausweg", "11");
                adressSource.createAdress("Schachthausweg", "14");
                adressSource.createAdress("Schachthausweg", "13");
                adressSource.createAdress("Schachthausweg", "15");
                adressSource.createAdress("Schachthausweg", "16");
                adressSource.createAdress("Schachthausweg", "20a");
                adressSource.createAdress("Schachthausweg", "20");
                adressSource.createAdress("Schachthausweg", "17");
                adressSource.createAdress("Schachthausweg", "22");
                adressSource.createAdress("Precheisengasse", "12");
                adressSource.createAdress("Precheisengasse", "22");
                adressSource.createAdress("Precheisengasse", "13a");
                adressSource.createAdress("Precheisengasse", "13");
                adressSource.createAdress("Precheisengasse", "11");
                adressSource.createAdress("Precheisengasse", "20");
                adressSource.createAdress("Precheisengasse", "18");
                adressSource.createAdress("Precheisengasse", "9");
                adressSource.createAdress("Precheisengasse", "7");
                adressSource.createAdress("Precheisengasse", "16");
                adressSource.createAdress("Precheisengasse", "5");
                adressSource.createAdress("Precheisengasse", "14");
                adressSource.createAdress("Precheisengasse", "10");
                adressSource.createAdress("Precheisengasse", "8");
                adressSource.createAdress("Precheisengasse", "3a");
                adressSource.createAdress("Precheisengasse", "6");
                adressSource.createAdress("Precheisengasse", "1");
                adressSource.createAdress("Precheisengasse", "1a");
                adressSource.createAdress("Precheisengasse", "3");
                adressSource.createAdress("Precheisengasse", "4");
                adressSource.createAdress("Precheisengasse", "2");
                adressSource.createAdress("Höhenstraße", "8");
                adressSource.createAdress("Höhenstraße", "6a");
                adressSource.createAdress("Höhenstraße", "6");
                adressSource.createAdress("Höhenstraße", "4");
                adressSource.createAdress("Höhenstraße", "2");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "18");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "18a");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "20");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "22");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "24");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "26");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "28");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "30");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "27");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "39");
                adressSource.createAdress("Landstraße(Fohnsdorf)", "41");
                adressSource.createAdress("Im Reith", "22");
                adressSource.createAdress("Barbaraweg", "1");
                adressSource.createAdress("Barbaraweg", "1a");
                adressSource.createAdress("Barbaraweg", "2");
                adressSource.createAdress("Barbaraweg", "4");
                adressSource.createAdress("Barbaraweg", "3");
                adressSource.createAdress("Barbaraweg", "5");
                adressSource.createAdress("Barbaraweg", "6");
                adressSource.createAdress("Barbaraweg", "8");
                adressSource.createAdress("Barbaraweg", "7");
                adressSource.createAdress("Barbaraweg", "9");
                adressSource.createAdress("Barbaraweg", "11");
                adressSource.createAdress("Barbaraweg", "13");
                adressSource.createAdress("Barbaraweg", "15");
                adressSource.createAdress("Barbaraweg", "10");
                adressSource.createAdress("Barbaraweg", "17");
                adressSource.createAdress("Barbaraweg", "12");
                adressSource.createAdress("Steigergasse", "12");
                adressSource.createAdress("Steigergasse", "10a");
                adressSource.createAdress("Steigergasse", "8");
                adressSource.createAdress("Dinsendorferweg", "13");
                adressSource.createAdress("Altweg", "6");
                adressSource.createAdress("Dinsendorferweg", "7");
                adressSource.createAdress("Imkerweg", "4");
                adressSource.createAdress("Imkerweg", "10");
                adressSource.createAdress("Imkerweg", "8");
                adressSource.createAdress("Imkerweg", "6");
                adressSource.createAdress("Dinsendorferweg", "5");
                adressSource.createAdress("Dinsendorferweg", "8");
                adressSource.createAdress("Dinsendorferweg", "6");
                adressSource.createAdress("Dinsendorferweg", "1 St2");
                adressSource.createAdress("Lorenzistraße", "46");
                adressSource.createAdress("Dinsendorferweg", "4");
                adressSource.createAdress("Dinsendorferweg", "1");
                adressSource.createAdress("Dinsendorferweg", "2");
                adressSource.createAdress("Lorenzistraße", "42");
                adressSource.createAdress("Lorenzistraße", "40");
                adressSource.createAdress("Lorenzistraße", "38");
                adressSource.createAdress("Lorenzistraße", "37");
                adressSource.createAdress("Lorenzistraße", "36");
                adressSource.createAdress("Lorenzistraße", "35");
                adressSource.createAdress("Lorenzistraße", "34");
                adressSource.createAdress("Lorenzistraße", "31");
                adressSource.createAdress("Lorenzistraße", "32");
                adressSource.createAdress("Lorenzistraße", "29");
                adressSource.createAdress("Lorenzistraße", "30");
                adressSource.createAdress("Lorenzistraße", "28");
                adressSource.createAdress("Lorenzistraße", "30a");
                adressSource.createAdress("Lorenzistraße", "28a");
                adressSource.createAdress("Lorenzistraße", "27");
                adressSource.createAdress("Lorenzistraße", "26");
                adressSource.createAdress("Lorenzistraße", "24");
                adressSource.createAdress("Lorenzistraße", "22");
                adressSource.createAdress("Birkenstraße", "4");
                adressSource.createAdress("Birkenstraße", "8");
                adressSource.createAdress("Birkenstraße", "10");
                adressSource.createAdress("Birkenstraße", "6");
                adressSource.createAdress("Birkenstraße", "11");
                adressSource.createAdress("Birkenstraße", "9");
                adressSource.createAdress("Birkenstraße", "13");
                adressSource.createAdress("Birkenstraße", "15");
                adressSource.createAdress("Birkenstraße", "17");
                adressSource.createAdress("Birkenstraße", "19");
                adressSource.createAdress("Birkenstraße", "7");
                adressSource.createAdress("Birkenstraße", "2");
                adressSource.createAdress("Birkenstraße", "5");
                adressSource.createAdress("Birkenstraße", "3");
                adressSource.createAdress("Raiffeisenstraße", "1");
                adressSource.createAdress("Raiffeisenstraße", "9");
                adressSource.createAdress("Raiffeisenstraße", "3");
                adressSource.createAdress("Raiffeisenstraße", "5");
                adressSource.createAdress("Raiffeisenstraße", "7");
                adressSource.createAdress("Raiffeisenstraße", "6");
                adressSource.createAdress("Raiffeisenstraße", "11");
                adressSource.createAdress("Raiffeisenstraße", "8");
                adressSource.createAdress("Raiffeisenstraße", "13");
                adressSource.createAdress("Raiffeisenstraße", "10");
                adressSource.createAdress("Raiffeisenstraße", "12");
                adressSource.createAdress("Raiffeisenstraße", "15");
                adressSource.createAdress("Raiffeisenstraße", "17");
                adressSource.createAdress("Raiffeisenstraße", "19");
                adressSource.createAdress("Raiffeisenstraße", "18");
                adressSource.createAdress("Raiffeisenstraße", "16");
                adressSource.createAdress("Raiffeisenstraße", "14");
                adressSource.createAdress("Raiffeisenstraße", "4");
                adressSource.createAdress("Raiffeisenstraße", "2");
                adressSource.createAdress("Birkenstraße", "1b");
                adressSource.createAdress("Birkenstraße", "1a");
                adressSource.createAdress("Bernsteingasse", "13");
                adressSource.createAdress("Bernsteingasse", "15");
                adressSource.createAdress("Lorenzistraße", "12");
                adressSource.createAdress("Lorenzistraße", "10");
                adressSource.createAdress("Lorenzistraße", "8");
                adressSource.createAdress("Lorenzistraße", "6");
                adressSource.createAdress("Lorenzistraße", "13");
                adressSource.createAdress("Lorenzistraße", "15");
                adressSource.createAdress("Lorenzistraße", "17");
                adressSource.createAdress("Lorenzistraße", "19");
                adressSource.createAdress("Bernsteingasse", "12");
                adressSource.createAdress("Bernsteingasse", "7");
                adressSource.createAdress("Bernsteingasse", "5");
                adressSource.createAdress("Bernsteingasse", "3");
                adressSource.createAdress("Bernsteingasse", "1");
                adressSource.createAdress("Bernsteingasse", "2");
                adressSource.createAdress("Bernsteingasse", "4");
                adressSource.createAdress("Bernsteingasse", "6");
                adressSource.createAdress("Bernsteingasse", "8");
                adressSource.createAdress("Schwarzenbachgasse", "3");
                adressSource.createAdress("Schwarzenbachgasse", "7");
                adressSource.createAdress("Schwarzenbachgasse", "9");
                adressSource.createAdress("Schwarzenbachgasse", "13");
                adressSource.createAdress("Schwarzenbachgasse", "15");
                adressSource.createAdress("Schwarzenbachgasse", "14");
                adressSource.createAdress("Schwarzenbachgasse", "12");
                adressSource.createAdress("Schwarzenbachgasse", "10");
                adressSource.createAdress("Schwarzenbachgasse", "8");
                adressSource.createAdress("Lorenzistraße", "43");
                adressSource.createAdress("Lorenzistraße", "45");
                adressSource.createAdress("Lorenzistraße", "50");
                adressSource.createAdress("Lorenzistraße", "52");
                adressSource.createAdress("Lorenzistraße", "47");
                adressSource.createAdress("Lorenzistraße", "49");
                adressSource.createAdress("Lorenzistraße", "54");
                adressSource.createAdress("Lorenzistraße", "56");
                adressSource.createAdress("Lorenzistraße", "58");
                adressSource.createAdress("Lorenzistraße", "51");
                adressSource.createAdress("Lorenzistraße", "55");
                adressSource.createAdress("Lorenzistraße", "63");
                adressSource.createAdress("Lorenzistraße", "67");
                adressSource.createAdress("Hauptstraße", "28");
                adressSource.createAdress("Hauptstraße", "24");
                adressSource.createAdress("Hauptstraße", "26");
                adressSource.createAdress("Hauptstraße", "20");
                adressSource.createAdress("Hauptstraße", "18");
                adressSource.createAdress("Hauptstraße", "16a");
                adressSource.createAdress("Hauptstraße", "19");
                publishProgress(50);
                adressSource.createAdress("Hauptstraße", "17");
                adressSource.createAdress("Hauptstraße", "13b");
                adressSource.createAdress("Hauptstraße", "15");
                adressSource.createAdress("Hauptstraße", "16");
                adressSource.createAdress("Hauptstraße", "14");
                adressSource.createAdress("Gartenzaunweg", "4");
                adressSource.createAdress("Gartenzaunweg", "1");
                adressSource.createAdress("Gartenzaunweg", "3");
                adressSource.createAdress("Gartenzaunweg", "6");
                adressSource.createAdress("Gartenzaunweg", "8");
                adressSource.createAdress("Gartenzaunweg", "5");
                adressSource.createAdress("Gartenzaunweg", "12");
                adressSource.createAdress("Hauptstraße", "11");
                adressSource.createAdress("Hauptstraße", "9c");
                adressSource.createAdress("Hauptstraße", "9b");
                adressSource.createAdress("Hauptstraße", "9a");
                adressSource.createAdress("Hauptstraße", "10a");
                adressSource.createAdress("Hauptstraße", "10");
                adressSource.createAdress("Hauptstraße", "9");
                adressSource.createAdress("Hauptstraße", "7b");
                adressSource.createAdress("Hauptstraße", "8");
                adressSource.createAdress("Hauptstraße", "4a");
                adressSource.createAdress("Hauptstraße", "7a");
                adressSource.createAdress("Hauptstraße", "4");
                adressSource.createAdress("Hauptstraße", "6");
                adressSource.createAdress("Hauptstraße", "5");
                adressSource.createAdress("Kohlenstraße", "1a");
                adressSource.createAdress("Kohlenstraße", "1b");
                adressSource.createAdress("Kohlenstraße", "3");
                adressSource.createAdress("Kohlenstraße", "5a");
                adressSource.createAdress("Kohlenstraße", "5");
                adressSource.createAdress("Kreuzbichlweg", "3");
                adressSource.createAdress("Kreuzbichlweg", "3a");
                adressSource.createAdress("Kreuzbichlweg", "8");
                adressSource.createAdress("Kreuzbichlweg", "7");
                adressSource.createAdress("Kreuzbichlweg", "6");
                adressSource.createAdress("Kreuzbichlweg", "4");
                adressSource.createAdress("Kreuzbichlweg", "5");
                adressSource.createAdress("Kohlenstraße", "10");
                adressSource.createAdress("Kohlenstraße", "12");
                adressSource.createAdress("Kohlenstraße", "11");
                adressSource.createAdress("Kohlenstraße", "14");
                adressSource.createAdress("Hauptstraße", "2b");
                adressSource.createAdress("Hauptstraße", "3");
                adressSource.createAdress("Hauptstraße", "1a");
                adressSource.createAdress("Hauptstraße", "1b");
                adressSource.createAdress("Hauptstraße", "2");
                adressSource.createAdress("Hauptstraße", "1");
                adressSource.createAdress("Dorfstraße(Sillweg)", "11");
                adressSource.createAdress("Dorfstraße(Sillweg)", "11 St2");
                adressSource.createAdress("Dorfstraße(Sillweg)", "13");
                adressSource.createAdress("Dorfstraße(Sillweg)", "6");
                adressSource.createAdress("Dorfstraße(Sillweg)", "12");
                adressSource.createAdress("Dorfstraße(Sillweg)", "12a");
                adressSource.createAdress("Dorfstraße(Sillweg)", "15a");
                adressSource.createAdress("Dorfstraße(Sillweg)", "19");
                adressSource.createAdress("Dorfstraße(Sillweg)", "18");
                adressSource.createAdress("Bergweg", "1");
                adressSource.createAdress("Bergweg", "4");
                adressSource.createAdress("Bergweg", "6");
                adressSource.createAdress("Bergweg", "3");
                adressSource.createAdress("Bergweg", "2");
                adressSource.createAdress("Kirchweg", "6");
                adressSource.createAdress("Kirchweg", "55");
                adressSource.createAdress("Dorfstraße(Sillweg)", "2");
                adressSource.createAdress("Dorfstraße(Sillweg)", "2a");
                adressSource.createAdress("Dorfstraße(Sillweg)", "2b");
                adressSource.createAdress("Dorfstraße(Sillweg)", "9");
                adressSource.createAdress("Dorfstraße(Sillweg)", "7");
                adressSource.createAdress("Dorfstraße(Sillweg)", "5a");
                adressSource.createAdress("Dorfstraße(Sillweg)", "5");
                adressSource.createAdress("Teichweg", "1");
                adressSource.createAdress("Teichweg", "3");
                adressSource.createAdress("Ziegelofen", "1a");
                adressSource.createAdress("Ziegelofen", "4");
                adressSource.createAdress("Ziegelofen", "3");
                adressSource.createAdress("Ziegelofen", "6");
                adressSource.createAdress("Ziegelofen", "8");
                adressSource.createAdress("Ziegelofen", "5");
                adressSource.createAdress("Ziegelofen", "11");
                adressSource.createAdress("Ziegelofen", "12");
                adressSource.createAdress("Ziegelofen", "10");
                adressSource.createAdress("Ziegelofen", "14");
                adressSource.createAdress("Ziegelofen", "2");
                adressSource.createAdress("Teichweg", "2");
                adressSource.createAdress("Dorfstraße(Sillweg)", "3");
                adressSource.createAdress("Dorfstraße(Sillweg)", "1");
                adressSource.createAdress("Aichfeldstraße", "101");
                adressSource.createAdress("Landstraße(Rattenberg)", "4a");
                adressSource.createAdress("Landstraße(Rattenberg)", "6");
                adressSource.createAdress("Landstraße(Rattenberg)", "6a");
                adressSource.createAdress("Landstraße(Rattenberg)", "1");
                adressSource.createAdress("Landstraße(Rattenberg)", "3");
                adressSource.createAdress("Landstraße(Rattenberg)", "10");
                adressSource.createAdress("Farracherweg", "7");
                adressSource.createAdress("Farracherweg", "9");
                adressSource.createAdress("Farracherweg", "11");
                adressSource.createAdress("Farracherweg", "13");
                adressSource.createAdress("Farracherweg", "2");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "1");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "3");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "7");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "7a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "11");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "15");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "13");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "13a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "19");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "24");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "21");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "21b");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "23a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "23");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "25");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "27");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "29");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "31");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "33");
                adressSource.createAdress("Göttschach", "1");
                adressSource.createAdress("Göttschach", "4");
                adressSource.createAdress("Göttschach", "2");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "38");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "36");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "34b");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "34a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "34");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "30");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "28");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "28a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "28b");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "26");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "26a");
                adressSource.createAdress("Hüttenweg", "1");
                adressSource.createAdress("Hüttenweg", "3");
                adressSource.createAdress("Hüttenweg", "2");
                adressSource.createAdress("Kirchbichlweg", "10");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "14");
                adressSource.createAdress("Eschenweg", "6");
                adressSource.createAdress("Eschenweg", "8");
                adressSource.createAdress("Hüttenweg", "8");
                adressSource.createAdress("Hüttenweg", "10");
                adressSource.createAdress("Hüttenweg", "12");
                adressSource.createAdress("Hüttenweg", "14");
                adressSource.createAdress("Hüttenweg", "4");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "8");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "6");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "4a");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "4");
                adressSource.createAdress("Dorfstraße(Rattenberg)", "2");
                adressSource.createAdress("Landstraße(Rattenberg)", "9");
                adressSource.createAdress("Landstraße(Rattenberg)", "7");
                adressSource.createAdress("Landstraße(Rattenberg)", "18");
                adressSource.createAdress("Landstraße(Rattenberg)", "20");
                adressSource.createAdress("Landstraße(Rattenberg)", "20a");
                adressSource.createAdress("Landstraße(Rattenberg)", "15");
                adressSource.createAdress("Landstraße(Rattenberg)", "15a");
                adressSource.createAdress("Landstraße(Rattenberg)", "22");
                adressSource.createAdress("Landstraße(Rattenberg)", "11");
                adressSource.createAdress("Landstraße(Rattenberg)", "17");
                adressSource.createAdress("Landstraße(Rattenberg)", "19");
                adressSource.createAdress("Landstraße(Rattenberg)", "19a");
                adressSource.createAdress("Landstraße(Rattenberg)", "21");
                adressSource.createAdress("Landstraße(Rattenberg)", "21a");
                adressSource.createAdress("Landstraße(Rattenberg)", "23");
                adressSource.createAdress("Landstraße(Rattenberg)", "5");
                adressSource.createAdress("Landstraße(Rattenberg)", "14");
                publishProgress(70);
                adressSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            publishProgress(75);
            try {
                sigRelAutDataSource.open();
                sigRelAutDataSource.createSigRelAut("Altweg", "6", "Karner Magdalena");
                sigRelAutDataSource.createSigRelAut("Altweg", "6", "Karner Irmgard");
                sigRelAutDataSource.createSigRelAut("Barbaraweg", "13", "Kolland Petra");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "1", "Liebfahrt Markus");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "12", "Hofer Franz");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "12", "Hofer Gernot");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "12", "Hofer Ulrike");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "2", "Ringer Christian");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "2", "Ringer Petra");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "6", "Bacher Angelika");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "6", "Bacher Stefan");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "6", "Bacher Elena");
                sigRelAutDataSource.createSigRelAut("Bernsteingasse", "6", "Bacher Valentina");
                sigRelAutDataSource.createSigRelAut("Birkenstraße", "10", "Regner Ingrid");
                sigRelAutDataSource.createSigRelAut("Birkenstraße", "11", "Moitzi Eva");
                sigRelAutDataSource.createSigRelAut("Birkenstraße", "2", "Kuhn Ingeborg");
                sigRelAutDataSource.createSigRelAut("Dinsendorferweg", "1", "Von Januszkiewicz Petra");
                sigRelAutDataSource.createSigRelAut("Dinsendorferweg", "5", "Pirker Birgit");
                sigRelAutDataSource.createSigRelAut("Dinsendorferweg", "5", "Pirker Patrick");
                sigRelAutDataSource.createSigRelAut("Dinsendorferweg", "7", "Rößler Werner");
                sigRelAutDataSource.createSigRelAut("Dinsendorferweg", "7", "Rößler Helga");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "11", "Sprung Monika");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "11", "Sprung Markus");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "11", "Sprung Manfred");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "11", "Sprung Doris");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "11", "Wilding Monika");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "13", "Gruber Sonja");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "13", "Gruber Alfred");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "20", "Walzl Peter");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "9", "Porkristl Maria");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "9", "Sattler Lambert");
                sigRelAutDataSource.createSigRelAut("Höhenstraße", "9", "Sattler Maria");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "11", "Ehgartner Erika");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "2", "Schober Gerald");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "2", "Schober Anja");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "2", "Schober Heike");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "5", "Schuster Karl");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "5", "Schuster Philipp");
                sigRelAutDataSource.createSigRelAut("Hügelgasse", "5", "Schuster Roswitha");
                sigRelAutDataSource.createSigRelAut("Landstraße(Fohnsdorf)", "19 St2", "Strafner Elfriede");
                sigRelAutDataSource.createSigRelAut("Landstraße(Fohnsdorf)", "19 St2", "Strafner Johann");
                sigRelAutDataSource.createSigRelAut("Landstraße(Fohnsdorf)", "23", "Grünanger Evelyn");
                sigRelAutDataSource.createSigRelAut("Landstraße(Fohnsdorf)", "30", "Steinkellner Rudolf");
                sigRelAutDataSource.createSigRelAut("Landstraße(Fohnsdorf)", "30", "Steinkellner Silvia");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "19", "Reiter Roland");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "19", "Reiter Karin");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "55", "Schönmetzler Anneliese");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "56", "Potocnik Erwin");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "6", "Peinhaupt Barbara");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "63", "Weber Walter");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "63", "Weber Christine");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "67", "Weber Walter");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "67", "Weber Silvia");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "67", "Weber Nicole");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "9", "Antlitzhofer Christian");
                sigRelAutDataSource.createSigRelAut("Lorenzistraße", "9", "Ublein Franz");
                sigRelAutDataSource.createSigRelAut("Maiweg", "1", "Gapp Herbert");
                sigRelAutDataSource.createSigRelAut("Maiweg", "1", "Gapp Irina");
                sigRelAutDataSource.createSigRelAut("Maiweg", "1", "Gapp Gertrude");
                sigRelAutDataSource.createSigRelAut("Maiweg", "2", "Waldhuber Uwe");
                sigRelAutDataSource.createSigRelAut("Maiweg", "2", "Friesser Manuela");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "1", "Vorraber Peter");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "1", "Vorraber Wolfgang");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "1", "Vorraber Birgit");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "22", "Kranz Mario");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "7", "Maresch Wilfried");
                sigRelAutDataSource.createSigRelAut("Precheisengasse", "7", "Maresch Hilde");
                sigRelAutDataSource.createSigRelAut("Raiffeisenstraße", "19", "Spitzer Ulfried");
                sigRelAutDataSource.createSigRelAut("Raiffeisenstraße", "19", "Rieger Christine");
                sigRelAutDataSource.createSigRelAut("Schachthausweg", "4", "Konrad Nicole");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "13", "Dichtl Ute");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "13", "Dichtl Günter");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "13", "Dichtl Sarah");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "15", "Griesenauer Mario");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "15", "Griesenauer Daniela");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "3", "Wieser Gisela");
                sigRelAutDataSource.createSigRelAut("Schwarzenbachgasse", "8", "Köstenberger Erich");
                sigRelAutDataSource.createSigRelAut("Steigergasse", "12", "Gruber Ingrid");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "13a", "Fandl Walter");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "13a", "Fandl Heike");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "2", "Wiesnegger Raimund");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "2", "Wiesnegger Hermine");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "21b", "Reicher Ulrike");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "21b", "Reicher Christoph");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "33", "Fandl Gerhard");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "34", "Temnitzer Christine");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "34", "Temnitzer Christian");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "34", "Temnitzer Alexandra");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "34", "Temnitzer Maria");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Rattenberg)", "34", "Kussegg Michaela");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "11", "Göttfried Monika");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "11", "Göttfried Patrick");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "11", "Göttfried Kersting");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "2", "Pratter Peter");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "2", "Pratter Dominik");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "2", "Pratter Markus");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "2", "Pratter Alexandra");
                sigRelAutDataSource.createSigRelAut("Farracherweg", "2", "Pratter Tamara");
                sigRelAutDataSource.createSigRelAut("Göttschach", "2", "Wildbolz Sonja");
                sigRelAutDataSource.createSigRelAut("Göttschach", "2", "Wildbolz Dominik");
                sigRelAutDataSource.createSigRelAut("Göttschach", "2", "Schneeberger Jennifer");
                sigRelAutDataSource.createSigRelAut("Hüttenweg", "1", "Grasser Eva-Maria");
                sigRelAutDataSource.createSigRelAut("Hüttenweg", "1", "Grasser Tobias");
                sigRelAutDataSource.createSigRelAut("Hüttenweg", "1", "Grasser Johanna");
                sigRelAutDataSource.createSigRelAut("Hüttenweg", "10", "Pollhammer Adina");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "14", "Bölsche Jens");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "14", "Bölsche Claudia");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "14", "Bölsche Katharina");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "14", "Bölsche Lena");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19", "Bölsche Jens");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19", "Bölsche Claudia");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19", "Bölsche Katharina");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19", "Bölsche Lena");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19a", "Rinößl Maria");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19a", "Rinößl Heinz");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19a", "Rinößl Corinna");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "19a", "Rinößl Christian");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "21a", "Staubmann Nicole");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "22", "Eder Ulrike");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "22", "Porkristl Hans-Peter");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "23", "Erdbau und SteinGes.m.b.H.");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "9", "Köck Renate");
                sigRelAutDataSource.createSigRelAut("Landstraße(Rattenberg)", "9", "Köck Martin");
                sigRelAutDataSource.createSigRelAut("Bergweg", "4", "Graßhoff Hippolytus");
                sigRelAutDataSource.createSigRelAut("Bergweg", "4", "Graßhoff Ingrid");
                sigRelAutDataSource.createSigRelAut("Bergweg", "4", "Winter Kevin");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Sillweg)", "12a", "Pickl Hansjörg");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Sillweg)", "12a", "Pickl Eva");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Sillweg)", "15a", "Krenn Siegfried");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Sillweg)", "24", "Rinößl Norbert");
                sigRelAutDataSource.createSigRelAut("Dorfstraße(Sillweg)", "3", "Bärnthaler Renate");
                sigRelAutDataSource.createSigRelAut("Gartenzaunweg", "12", "Messner Elke");
                sigRelAutDataSource.createSigRelAut("Gartenzaunweg", "12", "Messner Bernd");
                sigRelAutDataSource.createSigRelAut("Gartenzaunweg", "12", "Messner Marco");
                sigRelAutDataSource.createSigRelAut("Gartenzaunweg", "4", "Keller Heimo");
                sigRelAutDataSource.createSigRelAut("Gartenzaunweg", "4", "Keller Sabine");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "28", "Frischer Manfred");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "5", "Auer Patrick");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "5", "Auer Dominik");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "6", "Wogin Angelika");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "6", "Wogrin Günther");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "9", "Grasshof Michaela");
                sigRelAutDataSource.createSigRelAut("Hauptstraße", "9a", "Walch Manfred");
                sigRelAutDataSource.createSigRelAut("Kohlenstraße", "5", "Baumgartner Andrea");
                sigRelAutDataSource.createSigRelAut("Kohlenstraße", "5", "Unterweger Mario");
                sigRelAutDataSource.createSigRelAut("Kreuzbichlweg", "3a", "Lerchbacher Marco");
                sigRelAutDataSource.createSigRelAut("Kreuzbichlweg", "3a", "Haingartner Sabine");
                sigRelAutDataSource.createSigRelAut("Teichweg", "1", "Tockner Elfriede");
                sigRelAutDataSource.createSigRelAut("Teichweg", "3", "Steinwider Helene");
                sigRelAutDataSource.createSigRelAut("Ziegelofen", "11", "Pirker Elisabeth");
                sigRelAutDataSource.createSigRelAut("Ziegelofen", "12", "Meklau Helmut");
                sigRelAutDataSource.createSigRelAut("Ziegelofen", "14", "Bärnthaler Martin");
                sigRelAutDataSource.createSigRelAut("Ziegelofen", "2", "Leitner Helmut");
                sigRelAutDataSource.createSigRelAut("Ziegelofen", "2", "Leitner Rosa");
                sigRelAutDataSource.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            publishProgress(100);
            return null;
        }
    }
}
