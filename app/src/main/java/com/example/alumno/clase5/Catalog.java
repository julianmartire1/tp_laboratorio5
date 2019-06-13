package com.example.alumno.clase5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class Catalog extends DialogFragment {
    public MainActivity activity;
    public Catalog(){

    }
    @SuppressLint("ValidFragment")
    public Catalog(MainActivity activity){

        this.activity = activity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater li = LayoutInflater.from(this.getContext());
        View v = li.inflate(R.layout.my_dialog,null);
        CheckBox TN1 = (CheckBox) v.findViewById(R.id.TN1);
        CheckBox CL1 = (CheckBox) v.findViewById(R.id.CL1);
        List<CheckBox> opciones = new ArrayList<>();

        SharedPreferences prefs = this.activity.getSharedPreferences("catalogo", Context.MODE_MULTI_PROCESS);

        TN1.setChecked(prefs.getBoolean((String)TN1.getText(),false));
        CL1.setChecked(prefs.getBoolean((String)CL1.getText(),false));

        opciones.add(TN1);
        opciones.add(CL1);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Portales");
        MyListener ml = new MyListener(this.activity,opciones);

        builder.setView(v);

        builder.setPositiveButton("Elegir",ml);
        builder.setNegativeButton("Cancelar",ml);

        return builder.create();
    }
}