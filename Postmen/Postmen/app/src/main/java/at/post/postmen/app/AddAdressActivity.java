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
    private EditText streetEt;
    private EditText numberEt;

    private AdressDataSource adressSource;
    private SigRelAutDataSource sigRelAutDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        streetEt = (EditText)findViewById(R.id.streetEt);
        numberEt = (EditText)findViewById(R.id.numberEt);

        createButton = (Button)findViewById(R.id.bCreate);
        createButton.setOnClickListener(this);

        loadOrt7Btn = (Button) findViewById(R.id.loadOrt7Btn);
        loadOrt7Btn.setOnClickListener(this);

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
                loadOrt7();
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

    private void loadOrt7() {
        try {
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

    }
}
