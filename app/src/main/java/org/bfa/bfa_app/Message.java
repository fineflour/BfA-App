package org.bfa.bfa_app;
import android.content.Context;
import android.widget.Toast;
/**
 * Created by fineflour on 2/9/2018.
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
