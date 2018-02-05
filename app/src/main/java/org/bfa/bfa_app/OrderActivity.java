package org.bfa.bfa_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import android.widget.TextView;


public class OrderActivity extends BaseActivity  {
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
    private TextView txtMessage;
    private TextView txtOrderResult;

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
    String Message = "";
    String blogSubscription = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle(R.string.app_name);
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress .getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, "Exception in Get IP Address: " + ex.toString());
        }

        txtFirstName = (EditText)findViewById(R.id.txtFirstName);
        txtLastName = (EditText)findViewById(R.id.txtLastName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtAddress1 = (EditText)findViewById(R.id.txtAddress1);
        txtAddress2 = (EditText)findViewById(R.id.txtAddress2);
        txtCity = (EditText)findViewById(R.id.txtCity);
        txtZipcode = (EditText)findViewById(R.id.txtZipcode);
        txtOrderResult = (TextView) findViewById(R.id.txtOrderResult);
        chkBlogSubscribe = (CheckBox) findViewById(R.id.chkBlogSubscribe);
        spnState = (Spinner) findViewById(R.id.spinerStates);
        txtFirstName.requestFocus();

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.strStates,
                        android.R.layout.simple_spinner_item);
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnState.setAdapter(staticAdapter);

        Button btnPlaceOrder  = (Button)findViewById(R.id.btnPlaceOrder);

       btnPlaceOrder.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                new AddNewOrder().execute();
                v.setVisibility(View.INVISIBLE);
                txtOrderResult.setText(R.string.msgOrderSent);

            }
        });

    }

    private class AddNewOrder extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(String... arg) {
            firstName = txtFirstName.getText().toString();
            lastName = txtLastName.getText().toString();
            Email = txtEmail.getText().toString();
            Address1 = txtAddress1.getText().toString();
            Address2 = txtAddress2.getText().toString();
            cityName = txtCity.getText().toString();
            zipCode = txtZipcode.getText().toString();
            stateName = spnState.getSelectedItem().toString();
            if (!chkBlogSubscribe.isChecked()) {
                blogSubscription = "false";
            }
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("firstname", firstName ));
            params.add(new BasicNameValuePair("lastname", lastName));
            params.add(new BasicNameValuePair("email", Email));
            params.add(new BasicNameValuePair("address1", Address1 ));
            params.add(new BasicNameValuePair("address2", Address2 ));
            params.add(new BasicNameValuePair("city", cityName ));
            params.add(new BasicNameValuePair("zip", zipCode ));
            params.add(new BasicNameValuePair("state", stateName));
            params.add(new BasicNameValuePair("products", productId));
            params.add(new BasicNameValuePair("ip_address", ipAddress));
            params.add(new BasicNameValuePair("request_method", requestMethod));
            params.add(new BasicNameValuePair("subscriptions", blogSubscription));


            ServiceHandler serviceClient = new ServiceHandler();
            String json = serviceClient.makeServiceCall(OPS_URL,
                    ServiceHandler.POST, params);
            Log.d("Create Order Request: ", "> " + json);
            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    boolean error = jsonObj.getBoolean("error");
                    // checking for error node in json
                    if (!error) {
                        // new category created successfully
                        Message = "Your order has been sent successfully!";
                        txtMessage.setText(Message);
                        Log.e("Order added :",
                                "> " + jsonObj.getString("message"));

                    } else {
                        Message = "Your request can't be done";
                        txtMessage.setText(Message);
                        Log.e("Add Order Error: ",
                                "> " + jsonObj.getString("message"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "JSON data error!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
