package com.acds.letsshare;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ViewFriendRequestsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend_requests);

        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(PendingRequest.class);
        ParseObject.registerSubclass(Friends.class);
        ParseObject.registerSubclass(Notification.class);

        ParseQuery<PendingRequest> query = ParseQuery.getQuery("PendingRequest");
        query.whereEqualTo("receiver", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<PendingRequest>() {
            public void done(List<PendingRequest> pendingRequests, ParseException e) {
            }

            @Override
            public void done(final List<PendingRequest> pendingRequests, com.parse.ParseException e) {

                if (pendingRequests.size() != 0) {

                    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.view);

                    View v = new View(ViewFriendRequestsActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                    v.setBackgroundColor(Color.parseColor("BLACK"));
                    linearLayout.addView(v);

                    for (int i = 0; i < pendingRequests.size(); i++) {

                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                        query.getInBackground(pendingRequests.get(i).getSender().getObjectId(), new GetCallback<ParseUser>() {
                            public void done(ParseUser parseUser, ParseException e) {

                            }

                            @Override
                            public void done(final ParseUser parseUser, com.parse.ParseException e) {

                                TextView text = new TextView(ViewFriendRequestsActivity.this);
                                text.setText(parseUser.getUsername());
                                text.setPadding(0, 50, 0, 50);
                                text.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        Friends friends = new Friends();
                                        friends.setFriend1(ParseUser.getCurrentUser());
                                        friends.setFriend2(parseUser);
                                        friends.saveInBackground();

                                        friends = new Friends();
                                        friends.setFriend1(parseUser);
                                        friends.setFriend2(ParseUser.getCurrentUser());
                                        friends.saveInBackground();

                                        pendingRequests.get(0).deleteInBackground();

                                        Notification notification = new Notification();
                                        notification.setUser(parseUser);
                                        notification.setInformation(ParseUser.getCurrentUser().getUsername() + " accepted your friend request.");
                                        notification.saveInBackground();

                                        Toast.makeText(getApplicationContext(), "accepted",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                });
                                linearLayout.addView(text);

                                View vi = new View(ViewFriendRequestsActivity.this);
                                vi.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                                vi.setBackgroundColor(Color.parseColor("BLACK"));
                                linearLayout.addView(vi);

                            }
                        });

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "no requests",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_friends, menu);
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
