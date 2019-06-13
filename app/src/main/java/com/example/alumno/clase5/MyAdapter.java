package com.example.alumno.clase5;

import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.graphics.BitmapFactory;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<ItemModel> items;
    private MyOnItemClick listener;
    private Handler myHanler;
    public MyAdapter(List<ItemModel> personas,MyOnItemClick listener,Handler myHanler){
        this.items = personas;
        this.listener = listener;
        this.myHanler = myHanler;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy,parent,false);

        MyViewHolder myViewHoleder = new MyViewHolder(v,listener);
        return myViewHoleder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Aca se debe llamar al hilo para obtener la imagen
        ItemModel i = this.items.get(position);

        holder.tvTitle.setText(i.getTitle());
        holder.tvDescription.setText(i.getDescription().replace("/n", ""));
        holder.tvLink.setText(i.obetenerFuente());

        if(!i.getProcesar()){
            MyThread hilo2 = new MyThread(this.myHanler,i.getImage(),2,position);
            i.setProcesar(true);
            hilo2.start();
        }
        else{
            if(i.getImagenValor() != null){
                holder.viewImage.setImageBitmap(BitmapFactory.decodeByteArray(i.getImagenValor(),0,i.getImagenValor().length));
            }
        }

        holder.setPosition(position);
    }

    public List<ItemModel> setItems(List<ItemModel> items, boolean update){
        if(update)
            this.items = items;
        else{
            for(ItemModel item: items){
                this.items.add(item);
            }
        }
        return this.items;
    }
    public void setImagenItem(byte[] imagen,int position){
        ItemModel i = this.items.get(position);
        i.setImagenValor(imagen);
    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }


}
