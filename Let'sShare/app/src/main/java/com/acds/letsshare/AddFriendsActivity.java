package com.acds.letsshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.text.ParseException;
import java.util.List;


public class AddFriendsActivity extends Activity {

    private EditText username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        username = (EditText)findViewById(R.id.text3);
        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(PendingRequest.class);
        ParseObject.registerSubclass(Notification.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friends, menu);
        return true;
    }

    public void addFriend(View view) {

        final String friendUsername = username.getText().toString().trim();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", friendUsername);

        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {

            }

            @Override
            public void done(final List<ParseUser> users, com.parse.ParseException e) {

                if (users.size() != 0) {

                    ParseQuery<PendingRequest> query = ParseQuery.getQuery("PendingRequest");
                    query.whereEqualTo("sender", ParseUser.getCurrentUser()).whereEqualTo("receiver", users.get(0));
                    query.findInBackground(new FindCallback<PendingRequest>() {
                        public void done(List<PendingRequest> pendingRequests, ParseException e) {

                        }

                        @Override
                        public void done(List<PendingRequest> pendingRequests, com.parse.ParseException e) {

                            if (pendingRequests.size() != 0) {
                                Toast.makeText(getApplicationContext(), "request already sent",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else {

                                PendingRequest pendingRequest = new PendingRequest();
                                pendingRequest.setSender(ParseUser.getCurrentUser());
                                pendingRequest.setReceiver(users.get(0));
                                pendingRequest.saveInBackground();

                                Notification notification = new Notification();
                                notification.setUser(users.get(0));
                                notification.setInformation(ParseUser.getCurrentUser().getUsername() + " sent you a friend request.");
                                notification.saveInBackground();

                                Toast.makeText(getApplicationContext(), "request sent",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AddFriendsActivity.this, FriendsActivity.class);
                                startActivity(intent);

                            }

                        }
                    });


                }
                else {

                    Toast.makeText(getApplicationContext(), "user " + friendUsername + " does not exist",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });




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
