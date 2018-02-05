package org.bfa.bfa_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;


    String bfaWebsite = "http://www.bfa.org";
    String bfaDonation = "https://contributions.biblesforamerica.org/support-bfa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Activity Title");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.action_order:
                // User chose the "Settings" item, show the app settings UI...
                Intent intentOrder = new Intent(this, OrderActivity.class);
                startActivity(intentOrder);
                break;

            case R.id.action_download:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            case R.id.action_blble_study:
                Intent intentStudy = new Intent(this, WebviewActivity.class);
                startActivity(intentStudy);
                break;

            case R.id.action_donation:
                Intent browserBfADonation = new Intent(Intent.ACTION_VIEW, Uri.parse(bfaWebsite));
                startActivity(browserBfADonation);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            case R.id.action_bfa:
                Intent browserBfAWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(bfaDonation));
                startActivity(browserBfAWebsite);
                break;

            case R.id.action_signout:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
