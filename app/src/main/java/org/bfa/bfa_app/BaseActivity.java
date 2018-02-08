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
    public Toolbar toolbar;



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
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
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
        Intent newActivity = new Intent(BaseActivity.this, WebviewActivity.class);

        switch (item.getItemId()) {
            case R.id.action_order:
                // User chose the "Settings" item, show the app settings UI...
                Intent intentOrder = new Intent(this, OrderActivity.class);
                startActivity(intentOrder);
                break;

            case R.id.action_download:
                newActivity.putExtra("title", "Download Books");
                newActivity.putExtra("url", "http://biblesforamerica.org/books" );
                startActivity(newActivity);
                break;

            case R.id.action_blble_study:
                newActivity.putExtra("title", "Bible Study");
                newActivity.putExtra("url", "file:///android_asset/html/index.html");
                startActivity(newActivity);
                break;

            case R.id.action_donation:
                newActivity.putExtra("title", "Give to BfA");
                newActivity.putExtra("url", "https://contributions.biblesforamerica.org/support-bfa");
                startActivity(newActivity);
                break;

            case R.id.action_bfa:
                newActivity.putExtra("title", "Bibles for America");
                newActivity.putExtra("url", "http://www.bfa.org");
                startActivity(newActivity);
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
