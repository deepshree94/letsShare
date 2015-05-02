package com.acds.letsshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;

import static com.acds.letsshare.R.*;


public class LoginActivity extends Activity {

    private EditText username = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.text3);
        password = (EditText)findViewById(R.id.text5);
        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
    }



    public void login(View view) {

        String myUsername = username.getText().toString().trim();
        String myPassword = password.getText().toString();

        ParseUser.logInInBackground(myUsername, myPassword, new LogInCallback() {
            public void done(ParseUser parseUser, ParseException e) {

            }

            @Override
            public void done(ParseUser parseUser, com.parse.ParseException e) {

                if (e == null) {
                    Toast.makeText(getApplicationContext(), "login successful",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
