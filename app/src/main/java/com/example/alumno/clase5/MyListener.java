package com.example.alumno.clase5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class MyListener implements View.OnClickListener, Dialog.OnClickListener {
    public Activity activity;
    public MainActivity activityMain;
    public List<CheckBox> opciones;

    public MyListener() {}

    public MyListener(MainActivity a, List<CheckBox> o){
        this.activityMain = a;
        this.opciones = o;
    }

    public MyListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        /*if(view.getId() == R.id.btnCat1) {
            Log.d("CATEGORIA", "1");
        }*/
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        SharedPreferences prefs = this.activityMain.getSharedPreferences("catalogo", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();

        if(which == -1){
            List<String> urls = new ArrayList<>();
            for(CheckBox ch : this.opciones){
                if(ch.isChecked()){
                    //Log.d("Check", (String)ch.getText());
                    if(ch.getId() == R.id.TN1) {
                        urls.add("https://tn.com.ar/rss.xml");
                    } else if(ch.getId() == R.id.CL1){
                        urls.add("https://www.clarin.com/rss/lo-ultimo/");
                    }
                    editor.putBoolean((String)ch.getText(),ch.isChecked());
                }
                else{
                    editor.putBoolean((String)ch.getText(),ch.isChecked());
                }
            }
            if(urls.size() > 0)
                Log.d("urls", urls.toString());
                this.activityMain.Cargar(urls);
            editor.commit();
        }
        //Log.d("Boton", Integer.toString(which));
    }
}
