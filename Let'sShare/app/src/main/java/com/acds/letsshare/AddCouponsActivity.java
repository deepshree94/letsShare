package com.acds.letsshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class AddCouponsActivity extends Activity {

    private EditText serviceProvider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupons);
        serviceProvider = (EditText)findViewById(R.id.text3);
        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(Coupon.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_coupons, menu);
        return true;
    }

    public void addCoupon(View view) {

        String myServiceProvider = serviceProvider.getText().toString().trim();
        Coupon coupon = new Coupon();
        coupon.setServiceProvider(myServiceProvider);
        coupon.setOwner(ParseUser.getCurrentUser());
        coupon.saveInBackground();

        Toast.makeText(getApplicationContext(), "coupon added",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddCouponsActivity.this, CouponsActivity.class);
        startActivity(intent);

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
