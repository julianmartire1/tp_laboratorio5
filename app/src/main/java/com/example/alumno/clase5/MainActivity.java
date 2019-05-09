package com.example.alumno.clase5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.os.Message;


public class MainActivity extends AppCompatActivity implements MyOnItemClick, Handler.Callback {
    MyAdapter adapter;
    List<ItemModel> items;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListener listener = new MyListener(this);
        /*Button btn = (Button) super.findViewById(R.id.btn);
        btn.setOnClickListener(listener);*/

        items = new ArrayList<>();

        RecyclerView rvPersona = (RecyclerView) super.findViewById(R.id.rvItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPersona.setLayoutManager(layoutManager);

        Handler handler = new Handler(this);

        adapter = new MyAdapter(items,this,handler);
        rvPersona.setAdapter(adapter);

        MyThread hilo = new MyThread(handler,"https://tn.com.ar/rss.xml",TEXTO,0);
        hilo.start();
    }
    @Override
    public void onItemClick(int position) {
        ItemModel person = this.items.get(position);
        Log.d("Nueva persona",person.toString());
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXTO){
            this.items = this.adapter.SetPersonas((List<ItemModel>) msg.obj);
            adapter.notifyDataSetChanged();
        }
        else{
            this.adapter.SetImagenPer((byte[])msg.obj,msg.arg2);
            adapter.notifyItemChanged(msg.arg2);
            //ImageView imagen = (ImageView)super.findViewById(R.id.imagen);
            //imagen.setImageBitmap(BitmapFactory.decodeByteArray((byte[])msg.obj,0,((byte[]) msg.obj).length));
        }
        return false;
    }
}
