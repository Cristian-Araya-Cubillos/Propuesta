package com.example.propuesta.Modelo;

import com.example.propuesta.MainActivity;
import com.example.propuesta.clsConexionPG;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Cliente implements Serializable
{
    private String run;
    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String Comuna;

    public Cliente(){}

    public Cliente(String run, String nombre, String apellido1, String apellido2, String Comuna) {
        this.run = run;
        Nombre = nombre;
        Apellido1 = apellido1;
        Apellido2 = apellido2;
        this.Comuna = Comuna;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String apellido1) {
        Apellido1 = apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String apellido2) {
        Apellido2 = apellido2;
    }

    public String getComuna() {
        return this.Comuna;
    }

    public void setComuna(String Comuna) {
        this.Comuna = Comuna;
    }

    public ArrayList<String> ListadeComunas(){
        clsConexionPG con = new clsConexionPG();
        ArrayList<String> Comunas = new ArrayList<String>();
        try {
            Statement st = con.conexionBD().createStatement();
            String SQL = "Select distinct comuna from clientes;";
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Comunas.add(rs.getString("comuna"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Comunas;
    }

    public ArrayList<Cliente> VerClientes(){
        clsConexionPG con = new clsConexionPG();
        ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
        try {
            Statement st = con.conexionBD().createStatement();
            String SQL = "Select * from clientes;";
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setRun(rs.getString("rut"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellido1(rs.getString("apellido1"));
                cl.setApellido2(rs.getString("apellido2"));
                cl.setComuna(rs.getString("comuna"));
                Clientes.add(cl);
            }
            con.cerrar_conexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Clientes;
    }

    public boolean AddClient(){
        boolean correcto = false;
        clsConexionPG con = new clsConexionPG();
        try {
            PreparedStatement st = con.conexionBD().prepareStatement("INSERT INTO clientes VALUES(?,?,?,?,?) ;");
            st.setString(1,this.run);
            st.setString(2,this.Nombre);
            st.setString(3,this.Apellido1);
            st.setString(4,this.Apellido2);
            st.setString(5,this.Comuna);
            int filas = st.executeUpdate();
            st.close();
            correcto = true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }
    public boolean UpdateClient(){
        boolean correcto = false;
        clsConexionPG con = new clsConexionPG();
        try {
            PreparedStatement st = con.conexionBD().prepareStatement(" Update clientes SET nombre = ?," +
                    "apellido1 = ?,apellido2 = ?,comuna = ? WHERE rut = ?;");
            st.setString(1,this.Nombre);
            st.setString(2,this.Apellido1);
            st.setString(3,this.Apellido2);
            st.setString(4,this.Comuna);
            st.setString(5,this.run);
            int filas = st.executeUpdate();
            st.close();
            correcto = true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }
    public boolean DeleteClient(){
        boolean correcto = false;
        clsConexionPG con = new clsConexionPG();
        try {
            PreparedStatement st = con.conexionBD().prepareStatement("DELETE FROM clientes WHERE rut = ?;");
            st.setString(1,this.run);
            int filas = st.executeUpdate();
            st.close();
            correcto = true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }
    public Cliente Ver1Cliente(){
        Cliente cl = new Cliente();
        clsConexionPG con = new clsConexionPG();
        try {
            String SQL = "Select * from clientes where rut=?;";
            PreparedStatement st = con.conexionBD().prepareStatement(SQL);
            st.setString(1,this.run);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                cl.setRun(rs.getString("rut"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellido1(rs.getString("apellido1"));
                cl.setApellido2(rs.getString("apellido2"));
                cl.setComuna(rs.getString("comuna"));
            }
            con.cerrar_conexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(cl);
        return cl;
    }
    public int CostoTotalCliente(){
        clsConexionPG con = new clsConexionPG();
        int valor =0;
        try {
            String SQL = "SELECT sum(servicio.costo) FROM servicio_prestado, servicio " +
                    "WHERE servicio_prestado.id_servicio=servicio.id_servicio and " +
                    "servicio_prestado.cliente_run = ?;";
            PreparedStatement st = con.conexionBD().prepareStatement(SQL);
            st.setString(1,this.run);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                valor = rs.getInt("sum");
                System.out.println(valor);
            }
            con.cerrar_conexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }

    @Override
    public String toString() {
        return "Run: "+this.run+" Nombre: "+this.Nombre+" "+this.Apellido1+" "+this.Apellido2+" Comuna: "+this.Comuna;
    }

    public String SucursalesCernacas() {
        clsConexionPG con = new clsConexionPG();
        String valor ="";
        String comuna ="",calle ="";
        int numero_sucursal =0;
        try {
            String SQL = "SELECT sucursal.comuna,calle, numero_sucursal FROM clientes, sucursal WHERE " +
                    "clientes.comuna=sucursal.comuna AND clientes.rut = ?;";
            PreparedStatement st = con.conexionBD().prepareStatement(SQL);
            st.setString(1,this.run);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                comuna= rs.getString("comuna");
                calle =  rs.getString("calle");
                numero_sucursal = rs.getInt("numero_sucursal");
            }
            valor = comuna+" "+calle+" "+numero_sucursal;
            con.cerrar_conexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }
}
