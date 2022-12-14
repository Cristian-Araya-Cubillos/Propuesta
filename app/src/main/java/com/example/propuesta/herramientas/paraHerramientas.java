package com.example.propuesta.herramientas;

public class paraHerramientas {
    public String id_herramientas;
    public String nombre;
    public String descripcion;

    public paraHerramientas(String id_herramientas, String nombre, String descripcion) {
        this.id_herramientas = id_herramientas;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public String getId_herramientas() {
        return id_herramientas;
    }

    public void setId_herramientas(String id_herramientas) {
        id_herramientas = id_herramientas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
