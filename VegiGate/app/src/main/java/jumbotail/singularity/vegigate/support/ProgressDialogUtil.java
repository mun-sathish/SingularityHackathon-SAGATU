package jumbotail.singularity.vegigate.support;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Sathish Mun on 04-07-2015.
 */
public class ProgressDialogUtil {

    private static ProgressDialog pDialog;

    public static void showDialog(Context context, String message) {
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage(message);
        pDialog.show();
    }

    public static void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
