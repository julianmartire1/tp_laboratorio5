package com.example.alumno.clase5;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MyListener implements View.OnClickListener {
    Activity activity;
    public  MyListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        /*if(view.getId() == R.id.btnBack) {
            this.activity.finish();
            Log.d("if", "if");
        } else {*/
            Log.d("elseif", "elseif");
        //}

        //mainActivity.personas.add(new PersonaModel("Cosme","Fulanito","6685-4798"));
        //mainActivity.adapter.notifyDataSetChanged();
    }
}
