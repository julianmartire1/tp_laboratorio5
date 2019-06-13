package com.example.alumno.clase5;

public class ItemModel {

    private String title;
    private String description;
    private String link;
    private String image;
    private boolean procesar;
    private byte[] imagenValor;

    public ItemModel(){
        this.procesar = false;
    }
    public ItemModel(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.procesar = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImagenValor(byte[] imagenValor){this.imagenValor = imagenValor;}
    public byte[] getImagenValor(){return  this.imagenValor;}

    public boolean getProcesar() {
        return this.procesar;
    }

    public void setProcesar(boolean procesar) {
        this.procesar = procesar;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", procesar=" + procesar +
                '}';
    }

    public String obetenerFuente() {
        String url = getLink();
        return url.split("//")[1].split("/")[0];
    }
}
