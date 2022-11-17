package com.example.propuesta;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class clsConexionPG {
    Connection conexion;
    static String driver = "org.postgresql.Driver";
    static String dbname = "peluqueria";
    static String url = "jdbc:postgresql://10.4.3.195:5432/" + dbname;
    static String username = "peluqueria";
    static String password = "stL099m";
    //Creamos nuestra funcion para Conectarnos a Postgresql
    public  Connection conexionBD(){
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,username,password);

        }catch (Exception er){
            System.err.println("Error Conexion"+ er.toString());
        }
        return  conexion;
    }

    //Creamos la funcion para Cerrar la Conexion
    protected  void cerrar_conexion(Connection con)throws  Exception {
        con.close();
    }
}
