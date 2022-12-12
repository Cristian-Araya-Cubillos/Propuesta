package com.example.propuesta.administra_Empleado;

public class paraListaEmpleadoS {
    public String nombre_com;
    public String run;
    public paraListaEmpleadoS(String nombre_com, String run) {
        this.nombre_com = nombre_com;
        this.run = run;

    }

    public String getNombre_com() {
        return nombre_com;
    }

    public void setNombre_com(String nombre_com) {
        this.nombre_com = nombre_com;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }
}
