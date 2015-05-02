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
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;
import java.util.List;


public class SignupActivity extends Activity {

    private EditText username = null;
    private EditText password = null;
    private EditText confirm_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText)findViewById(R.id.text3);
        password = (EditText)findViewById(R.id.text5);
        confirm_password = (EditText)findViewById(R.id.text7);
        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
    }

   public void signup(View view) {

       String myUsername = username.getText().toString().trim();
       String myPassword = password.getText().toString();
       String myConfirmPassword = confirm_password.getText().toString();

       if (!myUsername.isEmpty() && !myPassword.isEmpty() && !myConfirmPassword.isEmpty()) {

           if (validateString(myUsername) && validateString(myPassword)) {

               if (myPassword.equals(myConfirmPassword)) {

                   ParseUser user = new ParseUser();
                   user.setUsername(myUsername);
                   user.setPassword(myPassword);

                   user.signUpInBackground(new SignUpCallback() {
                       public void done(ParseException e) {

                       }

                       @Override
                       public void done(com.parse.ParseException e) {
                           if (e == null) {

                               Toast.makeText(getApplicationContext(), "signup successful",
                                       Toast.LENGTH_SHORT).show();

                               Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                               startActivity(intent);

                           } else {

                               Toast.makeText(getApplicationContext(), e.getMessage(),
                                       Toast.LENGTH_SHORT).show();

                           }
                       }
                   });

               } else {

                   Toast.makeText(getApplicationContext(), "passwords do not match",
                           Toast.LENGTH_SHORT).show();

               }

           } else if (!validateString(myUsername)) {

               Toast.makeText(getApplicationContext(), "spaces not allowed in username",
                       Toast.LENGTH_SHORT).show();

           } else if (!validateString(myPassword)) {

               Toast.makeText(getApplicationContext(), "spaces not allowed in password",
                       Toast.LENGTH_SHORT).show();
           }

       } else if (myUsername.isEmpty()){

           Toast.makeText(getApplicationContext(), "enter username",
                   Toast.LENGTH_SHORT).show();

       }

       else if (myPassword.isEmpty()) {
           Toast.makeText(getApplicationContext(), "enter password",
                   Toast.LENGTH_SHORT).show();
       }

       else if (myConfirmPassword.isEmpty()) {
           Toast.makeText(getApplicationContext(), "confirm password",
                   Toast.LENGTH_SHORT).show();
       }

    }

    public boolean validateString(String string){
        if (string.length() - string.replaceAll(" ","").length() == 0)
            return true;
        else {
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
