package com.acds.letsshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class CouponsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coupons, menu);
        return true;
    }

    public void addCoupons(View view) {

        Intent intent = new Intent(CouponsActivity.this, AddCouponsActivity.class);
        startActivity(intent);
    }

    public void viewCoupons(View view) {

        Intent intent = new Intent(CouponsActivity.this, ViewCouponsActivity.class);
        startActivity(intent);

    }

    public void  viewCouponRequests(View view) {
        Intent intent = new Intent(CouponsActivity.this, ViewCouponRequestsActivity.class);
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
