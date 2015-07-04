package at.post.postmen.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

import at.post.postmen.R;
import at.post.postmen.data.AdressDataSource;

public class AddAdressActivity extends ActionBarActivity implements View.OnClickListener {

    private Button createButton;
    private EditText streetEt;
    private EditText numberEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        streetEt = (EditText)findViewById(R.id.streetEt);
        numberEt = (EditText)findViewById(R.id.numberEt);
        createButton = (Button)findViewById(R.id.bCreate);
        createButton.setOnClickListener(this);
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

        if(clicked == R.id.bCreate)
            createAdress();
    }

    private void createAdress() {
        AdressDataSource adressSource = new AdressDataSource(this);
        try {
            adressSource.open();
            adressSource.createAdress(streetEt.getText().toString(), numberEt.getText().toString(),0);
            adressSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
