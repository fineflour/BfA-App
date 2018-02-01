package org.bfa.bfa_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity  {
    private String OPS_URL = "https://ops.bfa.org/api/v1/orders.json?api_key=AE791F6F9AE5D8E5";
    private Button btnPlaceOrder;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtAddress1;
    private EditText txtAddress2;
    private EditText txtCity;
    private EditText txtZipcode;
    private CheckBox chkBlogSubscribe;
    private Spinner spnState;
    private Toolbar toolbar;

    String firstName = "";
    String lastName = "";
    String Email = "";
    String Address1 = "";
    String Address2 = "";
    String cityName = "";
    String zipCode = "";
    String requestMethod = "android";
    String ipAddress = "";
    String stateName = "";
    String productId = "1";
    String bfaWebsite = "http://www.bfa.org";
    String bfaDonation = "https://contributions.biblesforamerica.org/support-bfa";

    /*
      var data = {
    "email": info.email,
    "api_key": KEY,
    "firstname": info.firstname,
    "lastname": info.lastname,
    "address1": info.address1,
    "address2": info.address2,
    "city": info.city,
    "state": info.state,
    "zip": info.zip,
    "request_method": "web",
    "products": books.join('|'),
    "ip_address": ip,
    "email_lists": email_lists.join('|')
  }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


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
