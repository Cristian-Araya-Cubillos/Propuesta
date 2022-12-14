package com.example.propuesta.paraconsulta2;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propuesta.R;
import com.example.propuesta.MainActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListaComuna extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    List<paraListaComunas> lst = new ArrayList<>();
    ArrayList<String> lst_com = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comuna);
        getSupportActionBar().setTitle("Clientes por Comuna");
        Spinner spinner = findViewById(R.id.spinner);
        AnimationDrawable animationDrawable = (AnimationDrawable) spinner.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        ArrayAdapter<String> adapter;//(this, android.R.layout.simple_spinner_item);
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,com());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> com () throws SQLException{
        PreparedStatement comunas_ac = uwu.conData.prepareCall("Select distinct comuna from clientes");
        rsData = comunas_ac.executeQuery();

        while(rsData.next()){
            String comun = rsData.getString("comuna");
            lst_com.add(comun);

        }
        return lst_com;
    }


    private List<paraListaComunas> getDatos(String NombredeComuna) throws SQLException {

        //stmData = uwu.conData.createStatement();
        lst.clear();

        PreparedStatement a= uwu.conData.prepareCall("SELECT rut, nombre, apellido1 from clientes where comuna= ?");
        a.setString(1,NombredeComuna);
        rsData = a.executeQuery();
        while(rsData.next()) {
            String run = rsData.getString("Rut");
            String nombre1 =rsData.getString("nombre");
            String ape1 = rsData.getString("apellido1");
            String nom_ape = nombre1+" "+ape1;
            lst.add(new paraListaComunas(run,nom_ape));
        }
        System.out.println("Tama;o "+lst.size());
        return lst;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String NameComuna= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),NameComuna,Toast.LENGTH_SHORT).show();
        mostrar= findViewById(R.id.tablaComunas);
        adapterComuna awa = null;
        try {
            awa = new adapterComuna(this, getDatos(NameComuna));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrar.setAdapter(awa);
        mostrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long ide) {
                paraListaComunas l = lst.get(i);
                Toast.makeText(getBaseContext(),"Hola",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}