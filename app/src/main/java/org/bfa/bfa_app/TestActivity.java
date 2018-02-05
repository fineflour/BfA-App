package org.bfa.bfa_app;

import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends BaseActivity {

    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txtMessage = (TextView)findViewById(R.id.textView);
        txtMessage.setText("Test Activity");
    }
}
