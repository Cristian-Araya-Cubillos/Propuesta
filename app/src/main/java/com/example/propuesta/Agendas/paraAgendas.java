package com.example.propuesta.Agendas;

public class paraAgendas {
    public String Hora;
    public String fecha;
    public String nombre_c;
    public String rut_c;
    public String servicio;

    public paraAgendas(String hora, String fecha, String nombre_c, String rut, String servicio) {
        Hora = hora;
        this.fecha = fecha;
        this.nombre_c = nombre_c;
        this.rut_c = rut;
        this.servicio = servicio;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre_c() {
        return nombre_c;
    }

    public void setNombre_c(String nombre_c) {
        this.nombre_c = nombre_c;
    }

    public String getRut() {
        return rut_c;
    }

    public void setRut(String rut) {
        this.rut_c = rut;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
}
