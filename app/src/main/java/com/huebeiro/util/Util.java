package com.huebeiro.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huebeiro.stockontrol.R;

/**
 * Author: adilson
 * Date: 25/06/17
 */

public class Util {

    public static void ask(String message, String title,
                           Context context,
                           final View.OnClickListener onYesOption) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_yesno);
        dialog.setTitle(title);
        ((TextView) dialog.findViewById(R.id.txt_text)).setText(message);

        // if button is clicked, close the custom dialog
        dialog.findViewById(R.id.btnYes)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onYesOption.onClick(v);
                        dialog.dismiss();
                    }
                });
        dialog.findViewById(R.id.btnNo)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //do nothing
                    }
                });
        dialog.show();
    }

    public static void alert(String message, Context context,
                             final DialogInterface.OnClickListener onFinishAction) {
        AlertDialog.Builder bld = new AlertDialog.Builder(context);
        bld.setView(getDialogText(message, context));
        bld.setNeutralButton("OK", onFinishAction);
        bld.create().show();
    }

    private static TextView getDialogText(String message, Context context) {
        TextView myMsg = new TextView(context);
        myMsg.setAutoLinkMask(Linkify.WEB_URLS);
        myMsg.setTextColor(Color.BLACK);
        myMsg.setText(message);
        myMsg.setGravity(Gravity.CENTER);
        myMsg.setTextSize(16.5f);
        return myMsg;
    }
}
