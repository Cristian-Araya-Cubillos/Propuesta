package com.example.propuesta.administra_Boleta;

public class paraBoletas {
    public String Numero_Boleta;
    public String Fecha;

    public paraBoletas(String Numero_Boleta, String Fecha) {
        this.Numero_Boleta = Numero_Boleta;
        this.Fecha = Fecha;

    }

    public String getNumero_Boleta() {
        return Numero_Boleta;
    }

    public void setNumero_Boleta(String Numero_Boleta) {
        this.Numero_Boleta = Numero_Boleta;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }


}
