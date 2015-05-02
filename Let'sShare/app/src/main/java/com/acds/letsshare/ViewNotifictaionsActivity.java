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
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;


public class ViewNotifictaionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notifictaions);

        Parse.initialize(this, "BT44WlnEHLEXUDqMwYyAtWyTVZJkxTK3RsXfQ0AU", "YGmQTtw3JqYLF9EVgwypzIRAQrGaBWR5sWaPxo1s");
        ParseObject.registerSubclass(Notification.class);

        ParseQuery<Notification> query = ParseQuery.getQuery("Notification");
        query.whereEqualTo("user", ParseUser.getCurrentUser());

        query.findInBackground(new FindCallback<Notification>() {
            public void done(List<Notification> notifications, ParseException e) {
            }

            @Override
            public void done(List<Notification> notifications, com.parse.ParseException e) {


                if (notifications.size() != 0) {

                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.view);

                    View v = new View(ViewNotifictaionsActivity.this);
                    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5 ));
                    v.setBackgroundColor(Color.parseColor("BLACK"));
                    linearLayout.addView(v);

                    for (int i = 0; i < notifications.size(); i++) {

                        TextView text = new TextView(ViewNotifictaionsActivity.this);
                        final Notification n = notifications.get(i);
                        text.setText(n.getInformation());
                        text.setPadding(0, 50, 0, 50);
                        text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                n.deleteInBackground();

                                Toast.makeText(getApplicationContext(), "notification seen",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        linearLayout.addView(text);


                        v = new View(ViewNotifictaionsActivity.this);
                        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
                        v.setBackgroundColor(Color.parseColor("BLACK"));
                        linearLayout.addView(v);

                    }


                }

                else {
                    Toast.makeText(getApplicationContext(), "no notifications",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_notifictaions, menu);
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
