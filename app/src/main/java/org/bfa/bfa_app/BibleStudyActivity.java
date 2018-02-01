package org.bfa.bfa_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


public class BibleStudyActivity extends AppCompatActivity  {

    private Toolbar toolbar;


    String bfaWebsite = "http://www.bfa.org";
    String bfaDonation = "https://contributions.biblesforamerica.org/support-bfa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_study);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        WebView web_view;
        web_view = (WebView)findViewById(R.id.web_view);
        web_view.loadUrl("file:///android_asset/index.html");
    }
     @Override
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
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;

            case R.id.action_download:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
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
