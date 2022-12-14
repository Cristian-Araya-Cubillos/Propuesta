package com.example.propuesta.herramientas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propuesta.R;
import com.example.propuesta.MainActivity;
import com.example.propuesta.herramientas.adapter_Herramientas;
import com.example.propuesta.herramientas.paraHerramientas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class datosHerramientas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    List<paraHerramientas> lst = new ArrayList<>();
    ArrayList<String> lst_com = new ArrayList<>();
    String herra_selec;
    TextView id,nom,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_herramientas);
        Spinner spinner = findViewById(R.id.spn_herramientas);
        inicia();
        ArrayAdapter<String> adapter;
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
        PreparedStatement herramientas_ac = uwu.conData.prepareCall("SELECT nombre FROM herramientas");
        rsData = herramientas_ac.executeQuery();

        while(rsData.next()){
            String herramienta = rsData.getString("nombre");
            lst_com.add(herramienta);
        }
        return lst_com;
    }

    private void inicia(){
        id = findViewById(R.id.txt_idH);
        nom = findViewById(R.id.txt_nomH);
        desc = findViewById(R.id.txt_desH);
    }


    private void getDatos()  {
        //lst.clear();
        try{
            System.out.println(herra_selec);
            PreparedStatement a= uwu.conData.prepareCall("SELECT * from herramientas where nombre= ?");
            a.setString(1,herra_selec);
            rsData = a.executeQuery();
            while(rsData.next()) {
                String herramienta = rsData.getString("id_herramientas");
                String nombre = rsData.getString("nombre");
                String descripcion = rsData.getString("descripcion");
                System.out.println(descripcion);
                id.setText("ID: "+herramienta);
                nom.setText(nombre);
                desc.setText("Descripcion: "+descripcion);
                //lst.add(new paraHerramientas(herramienta,nombre,descripcion));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        herra_selec = parent.getItemAtPosition(position).toString();
        getDatos();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}