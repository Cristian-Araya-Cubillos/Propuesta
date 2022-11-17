package com.example.propuesta.administra_Empleado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.propuesta.MainActivity;
import com.example.propuesta.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class datosEmpSucursal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    List<paraListaEmpleadoS> lst = new ArrayList<>();
    private ArrayList<String> lst_com = new ArrayList<>();
    private ArrayList<Integer> iterador = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_emp_sucursal);
        Spinner spinner = findViewById(R.id.spinner);
        //El ArrayAdaptar es aquel que, en este caso se liga al Menu Desplegable de las Sucursales, generando que esta se actualize en base a las que se agregen
        ArrayAdapter<String> adapter;//(this, android.R.layout.simple_spinner_item);
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,com());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Se despliega la lista de sucursales al apretar la flechita
            spinner.setAdapter(adapter); //Se inserta lo antes mencionado
            spinner.setOnItemSelectedListener(this); //Este servira para redirigir a la informacion que tenga el empleado/etc seleccionado, la funcion esta mas abajo
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<paraListaEmpleadoS> getDatos(int Nombre_Sucursal) throws SQLException {
        lst.clear();
        PreparedStatement a = uwu.conData.prepareCall("SELECT empleado.run, empleado.nombre, empleado.apellido1, empleado.apellido2 from empleado, trabaja_en WHERE Trabaja_en.run_empleado= empleado.run and trabaja_en.id_sucursal = ?");
        a.setInt(1,Nombre_Sucursal);
        rsData = a.executeQuery();
        while(rsData.next()) {
            //Estas variables se usan para obtener los datos que se quieran usar
            String run = rsData.getString("run");
            String nombre1 =rsData.getString("nombre");
            String ape1 = rsData.getString("apellido1");
            String ape2 = rsData.getString("apellido2");
            String nom_ape = nombre1+" "+ape1+" "+ape2;
            lst.add(new paraListaEmpleadoS(run,nom_ape)); //Se almacenan en la lista, la cual, mediante la funcion de la linea 79, crea la lista dinamica
        }
        //System.out.println("Tama;o "+lst.size());
        return lst;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String NameComuna= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),NameComuna,Toast.LENGTH_SHORT).show();
        mostrar= findViewById(R.id.tablaEmpleado); //Se enlaza a la tabla dinamica creada en el xml correspondiente a activity datos emp sucursal
        adapterEmpleados awa = null;
        try {
            awa = new adapterEmpleados(this, getDatos(idesuc(NameComuna)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrar.setAdapter(awa); //Este inserta la lista creada para proceder a mostrarla en pantalla
        mostrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long ide) {
                //En esta funcion, se debera implementar otra interfaz para mostrar mas info sobre los empleados
                paraListaEmpleadoS l = lst.get(i);
                Toast.makeText(getBaseContext(),"Hola",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int idesuc(String asa){
        for(int i=0; i<lst_com.size();i++){
            if(asa.equals(lst_com.get(i).toString())){
                return iterador.get(i);
            }
        }
        return 0;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //Esta funcion es la correspondiente a obtener las sucursales e ID, las cuales se almacenaran en listas diferentes
    //el Array de comunas se usara para el menu del Spinner
    public ArrayList<String> com () throws SQLException{
        PreparedStatement comunas_ac = uwu.conData.prepareCall("Select distinct sucursal.id_sucursal, comuna from empleado, trabaja_en, sucursal");
        rsData = comunas_ac.executeQuery();

        while(rsData.next()){
            int cide = rsData.getInt("id_sucursal");
            String comun = rsData.getString("comuna");
            iterador.add(cide);
            System.out.println(cide);
            lst_com.add(comun);
            System.out.println(comun);
        }
        return lst_com;
    }
}