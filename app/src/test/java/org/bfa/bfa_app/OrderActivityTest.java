package org.bfa.bfa_app;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by fineflour on 1/24/2018.
 */
@RunWith(RobolectricTestRunner.class)

public class OrderActivityTest {
    @Test
    public void testPlaceOrderButton_clickButtonAndExpectInfoText() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        Button button = (Button) activity.findViewById(R.id.btnPlaceOrder);
        TextView results = (TextView) activity.findViewById(R.id.txt_result);
        //Do some other testing afterward
    }

}
