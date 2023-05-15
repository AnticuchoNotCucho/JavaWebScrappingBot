package org.example.models;

public class Producto {


    public int sitiosID;
    public String nombre;
    public float precio;

    public int getSitiosID() {
        return sitiosID;
    }

    public void setSitiosID(int sitiosID) {
        this.sitiosID = sitiosID;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String imagen;

    public String sku;

    public String categoria;

    public Producto(
                    int sitiosID,
                    String nombre,
                    float precio,
                    String imagen,
                    String sku,
                    String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.sku = sku;
        this.categoria = categoria;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
