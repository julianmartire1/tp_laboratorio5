package com.example.alumno.clase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.os.Message;


public class MainActivity extends AppCompatActivity implements MyOnItemClick, Handler.Callback, SearchView.OnQueryTextListener{
    MyAdapter adapter;
    List<ItemModel> items;
    List<ItemModel> otherItems;

    Handler handler;

    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.items = new ArrayList<>();
        this.otherItems = new ArrayList<>();
        this.handler = new Handler(this);

        RecyclerView rvPersona = (RecyclerView) super.findViewById(R.id.rvItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPersona.setLayoutManager(layoutManager);

        Handler handler = new Handler(this);

        adapter = new MyAdapter(items,this,handler);
        rvPersona.setAdapter(adapter);

        /*MyThread hilo = new MyThread(handler,"https://tn.com.ar/rss.xml",TEXTO,0);
        hilo.start();*/

        Catalog catalogo = new Catalog(this);
        catalogo.show(getSupportFragmentManager(),"dialogo");
    }

    public void Cargar(List<String> urls){

        Log.d("urls", urls.toString());
        this.items = new ArrayList<ItemModel>();
        this.otherItems = new ArrayList<ItemModel>();

        adapter.items = new ArrayList<ItemModel>();
        adapter.notifyDataSetChanged();

        for(String url: urls){
            MyThread hilo = new MyThread(handler,url,TEXTO,0);
            hilo.start();
        }
        //MyThread hilo = new MyThread(handler,url,TEXTO,0);
        //hilo.start();
    }

    @Override
    public void onItemClick(int position) {
        ItemModel item = this.items.get(position);

        Intent i = new Intent(this, OtroActivity.class);
        i.putExtra("url",item.getLink());
        i.putExtra("title",item.getTitle());
        this.startActivity(i);

        //Log.d("ITEM",item.toString());
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == TEXTO){
            this.items = this.adapter.setItems((List<ItemModel>) msg.obj, false);
            adapter.notifyDataSetChanged();
            this.otherItems = this.items;
        }
        else{
            this.adapter.setImagenItem((byte[])msg.obj,msg.arg2);
            adapter.notifyItemChanged(msg.arg2);
            //ImageView imagen = (ImageView)super.findViewById(R.id.imagen);
            //imagen.setImageBitmap(BitmapFactory.decodeByteArray((byte[])msg.obj,0,((byte[]) msg.obj).length));
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem mi = menu.findItem(R.id.search);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.showDialog) {
            /*MyDialog dialog = new MyDialog();
            dialog.show(getSupportFragmentManager(), "dialog");*/

            Catalog catalogo = new Catalog(this);
            catalogo.show(getSupportFragmentManager(),"dialogo");
        } else {
            if(item.getItemId() == android.R.id.home) {
                this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("Query",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("Escribo",newText);
        if(newText.length() >= 2) {
            this.filtrar(newText);
            adapter.setItems(this.items, true);
            adapter.notifyDataSetChanged();
        }
        else{
            this.items = this.otherItems;
            adapter.setItems(this.otherItems, true);
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    public void filtrar(String filtro){
        this.items = new ArrayList<ItemModel>();

        for (ItemModel item: this.otherItems) {
            //Log.d("Titulo",item.getTitle());
            if(item.getTitle().toLowerCase().contains(filtro.toLowerCase())){
                //Log.d("Filro","oka");
                this.items.add(item);
            }
        }
    }
}
