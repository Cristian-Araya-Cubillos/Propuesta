package com.example.propuesta.administra_Boleta;

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

public class datosBoletaCliente extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    List<paraBoletas> lst = new ArrayList<>();
    ArrayList<String> lst_com = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_boleta);
        Spinner spinner = findViewById(R.id.spn_boleta);
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
        PreparedStatement boletas_ac = uwu.conData.prepareCall("SELECT DISTINCT cliente_run FROM servicio_prestado");
        rsData = boletas_ac.executeQuery();

        while(rsData.next()){
            String boleta = rsData.getString("cliente_run");
            lst_com.add(boleta);
            System.out.println(boleta);
        }
        return lst_com;
    }


    private List<paraBoletas> getDatos(String cliente_run)  {
        lst.clear();
        try{
            PreparedStatement a= uwu.conData.prepareCall("SELECT DISTINCT numero_boleta, empleado_run, cliente_run FROM servicio_prestado WHERE cliente_run = ?");
            a.setString(1,cliente_run);
            rsData = a.executeQuery();
            while(rsData.next()) {
                String numero_boleta = rsData.getString("numero_boleta");
                String fecha = rsData.getString("empleado_run");
                lst.add(new paraBoletas(numero_boleta,fecha));
            }
        }catch(Exception e){

        }

        return lst;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Numero_Boleta = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),Numero_Boleta,Toast.LENGTH_SHORT).show();
        mostrar= findViewById(R.id.tablaBoleta);
        adapterBoleta awa = new adapterBoleta(this, getDatos(Numero_Boleta));
        mostrar.setAdapter(awa);
        mostrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long ide) {
                paraBoletas l = lst.get(i);
                Toast.makeText(getBaseContext(),"Hola",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}