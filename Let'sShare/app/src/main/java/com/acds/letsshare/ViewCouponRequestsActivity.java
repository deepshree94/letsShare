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
import java.util.List;


public class ViewCouponRequestsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coupon_requests);

        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(CouponRequest.class);
        ParseObject.registerSubclass(Coupon.class);

        ParseQuery<CouponRequest> query = ParseQuery.getQuery("CouponRequest");
        query.whereEqualTo("receiver", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<CouponRequest>() {
            public void done(List<CouponRequest> couponRequests, ParseException e) {
            }

            @Override
            public void done(final List<CouponRequest> couponRequests, com.parse.ParseException e) {

                if (couponRequests.size() != 0) {

                    final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.view);

                    View v = new View(ViewCouponRequestsActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                    v.setBackgroundColor(Color.parseColor("BLACK"));
                    linearLayout.addView(v);

                    for (int i = 0; i < couponRequests.size(); i++) {

                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                        final CouponRequest cr = couponRequests.get(i);
                        query.getInBackground(couponRequests.get(i).getSender().getObjectId(), new GetCallback<ParseUser>() {
                            public void done(ParseUser parseUser, ParseException e) {

                            }

                            @Override
                            public void done(final ParseUser parseUser, com.parse.ParseException e) {

                                ParseQuery<Coupon> query = ParseQuery.getQuery("Coupon");
                                query.getInBackground(cr.getCoupon().getObjectId(), new GetCallback<Coupon>() {
                                    public void done(Coupon coupon, ParseException e) {

                                    }

                                    @Override
                                    public void done(final Coupon coupon, com.parse.ParseException e) {

                                        TextView text = new TextView(ViewCouponRequestsActivity.this);
                                        text.setText(parseUser.getUsername() + ": " + coupon.getServiceProvider());
                                        text.setPadding(0, 50, 0, 50);
                                        text.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                coupon.setOwner(parseUser);
                                                coupon.saveInBackground();

                                                cr.deleteInBackground();

                                                Notification notification = new Notification();
                                                notification.setUser(parseUser);
                                                notification.setInformation(ParseUser.getCurrentUser().getUsername() + " accepted your request for a " + coupon.getServiceProvider() + " coupon.");
                                                notification.saveInBackground();

                                                Toast.makeText(getApplicationContext(), "coupon transferred",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        linearLayout.addView(text);

                                        View vi = new View(ViewCouponRequestsActivity.this);
                                        vi.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                                        vi.setBackgroundColor(Color.parseColor("BLACK"));
                                        linearLayout.addView(vi);
                                    }
                                });

                            }
                        });

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "no coupon requests",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_coupon_requests, menu);
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
