package com.example.alumno.clase5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by alumno on 06/06/2019.
 */

public class MyDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(this.getContext());
        View v = li.inflate(R.layout.my_dialog, null);

        //Button btn = (Button) v.findViewById(R.id.btnCat1);
        //btn.setOnClickListener(new MyListener());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Titulo");

        builder.setView(v);

        MyListener myListener = new MyListener();
        builder.setNegativeButton("Cancel", myListener);

        return builder.create();
    }
}
