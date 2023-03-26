package org.example.models;

public class Producto {
    public String nombre;
    public float precio;
    public String imagen;

    public String sku;

    public String categoria;

    public Producto(String nombre, float precio, String imagen, String sku, String categoria) {
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
