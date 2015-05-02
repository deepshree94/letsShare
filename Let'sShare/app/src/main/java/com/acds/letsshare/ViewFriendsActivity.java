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


public class ViewFriendsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friends);

        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(Friends.class);
        ParseObject.registerSubclass(Coupon.class);
        ParseObject.registerSubclass(CouponRequest.class);

        ParseQuery<Friends> query = ParseQuery.getQuery("Friends");
        query.whereEqualTo("friend1", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Friends>() {
            public void done(List<Friends> friendsList, ParseException e) {
            }

            @Override
            public void done(final List<Friends> friendsList, com.parse.ParseException e) {

                if (friendsList.size() != 0) {

                    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.view);

                    View v = new View(ViewFriendsActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                    v.setBackgroundColor(Color.parseColor("BLACK"));
                    linearLayout.addView(v);

                    for (int i = 0; i < friendsList.size(); i++) {

                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                        query.getInBackground(friendsList.get(i).getFriend2().getObjectId(), new GetCallback<ParseUser>() {
                            public void done(ParseUser parseUser, ParseException e) {

                            }

                            @Override
                            public void done(final ParseUser parseUser, com.parse.ParseException e) {

                                TextView text = new TextView(ViewFriendsActivity.this);
                                text.setText(parseUser.getUsername());
                                text.setPadding(0, 50, 0, 50);
                                text.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    setContentView(R.layout.activity_view_coupons);

                                        ParseQuery<Coupon> query = ParseQuery.getQuery("Coupon");
                                        query.whereEqualTo("owner", parseUser);

                                        query.findInBackground(new FindCallback<Coupon>() {
                                            public void done(List<Coupon> coupons, ParseException e) {
                                            }

                                            @Override
                                            public void done(final List<Coupon> coupons, com.parse.ParseException e) {


                                                if (coupons.size() != 0) {

                                                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.view);

                                                    View v = new View(ViewFriendsActivity.this);
                                                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5 ));
                                                    v.setBackgroundColor(Color.parseColor("BLACK"));
                                                    linearLayout.addView(v);

                                                    for (int i = 0; i < coupons.size(); i++) {

                                                        TextView text = new TextView(ViewFriendsActivity.this);
                                                        final Coupon c = coupons.get(i);
                                                        text.setText(c.getServiceProvider());
                                                        text.setPadding(0, 50, 0, 50);
                                                        text.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                CouponRequest cr = new CouponRequest();
                                                                cr.setSender(ParseUser.getCurrentUser());
                                                                cr.setReceiver(parseUser);
                                                                cr.setCoupon(c);
                                                                cr.saveInBackground();

                                                                Notification notification = new Notification();
                                                                notification.setUser(parseUser);
                                                                notification.setInformation(ParseUser.getCurrentUser().getUsername() + " requested you for a "+ c.getServiceProvider() +" coupon.");
                                                                notification.saveInBackground();

                                                                Toast.makeText(getApplicationContext(), "coupon requested",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        });

                                                        linearLayout.addView(text);

                                                        v = new View(ViewFriendsActivity.this);
                                                        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                                                        v.setBackgroundColor(Color.parseColor("BLACK"));
                                                        linearLayout.addView(v);
                                                    }


                                                }

                                                else {
                                                    Toast.makeText(getApplicationContext(), "no coupons",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                                    }
                                });
                                linearLayout.addView(text);

                                View vi = new View(ViewFriendsActivity.this);
                                vi.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                                vi.setBackgroundColor(Color.parseColor("BLACK"));
                                linearLayout.addView(vi);

                            }
                        });

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "no friends",
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
