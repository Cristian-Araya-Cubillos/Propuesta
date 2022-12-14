package com.example.propuesta.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
    private int id_producto;
    private int precio;
    private int cantidad;
    private String nombre;

    public Producto() {
    }

    public Producto(int id_producto, int precio, int cantidad, String nombre) {
        this.id_producto = id_producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
