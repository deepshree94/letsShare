package com.acds.letsshare;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;


public class ViewCouponsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coupons);

        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(Coupon.class);

        ParseQuery<Coupon> query = ParseQuery.getQuery("Coupon");
        query.whereEqualTo("owner", ParseUser.getCurrentUser());

        query.findInBackground(new FindCallback<Coupon>() {
            public void done(List<Coupon> coupons, ParseException e) {
            }

            @Override
            public void done(List<Coupon> coupons, com.parse.ParseException e) {


                if (coupons.size() != 0) {

                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.view);

                    View v = new View(ViewCouponsActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5 ));
                    v.setBackgroundColor(Color.parseColor("BLACK"));
                    linearLayout.addView(v);

                    for (int i = 0; i < coupons.size(); i++) {

                        TextView text = new TextView(ViewCouponsActivity.this);
                        text.setText(coupons.get(i).getServiceProvider());
                        text.setPadding(0, 50, 0, 50);

                        linearLayout.addView(text);

                        v = new View(ViewCouponsActivity.this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_coupons, menu);
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
