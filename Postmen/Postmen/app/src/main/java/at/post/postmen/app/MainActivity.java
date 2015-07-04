package at.post.postmen.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import at.post.postmen.R;
import at.post.postmen.data.AdressDataSource;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button newAdressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adressSource = new AdressDataSource(this);

        newAdressBtn = (Button)findViewById(R.id.NewAdressBtn);
        newAdressBtn.setOnClickListener(this);

    }

    private AdressDataSource adressSource;

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

        if(clicked == R.id.NewAdressBtn)
        {
            Intent intent = new Intent(this, AddAdressActivity.class);
            startActivity(intent);

        }
    }
}
