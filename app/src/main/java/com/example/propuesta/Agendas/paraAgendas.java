package com.example.propuesta.Agendas;

public class paraAgendas {
    public String Horario;
    public String rut_c;
    public String servicio;

    public paraAgendas(String horario, String rut_c, String servicio) {
        this.Horario = horario;
        this.rut_c = rut_c;
        this.servicio = servicio;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getRut_c() {
        return rut_c;
    }

    public void setRut_c(String rut_c) {
        this.rut_c = rut_c;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
}
