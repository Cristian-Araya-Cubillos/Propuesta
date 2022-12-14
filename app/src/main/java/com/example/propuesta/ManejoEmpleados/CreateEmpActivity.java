package com.example.propuesta.ManejoEmpleados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.propuesta.MainActivity;
import com.example.propuesta.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEmpActivity extends AppCompatActivity {
    EditText nom,ap1,ap2,rut,pin;
    Button btn;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_emp);
        inicia();

    }

    private void inicia(){
        nom = findViewById(R.id.crea_name);
        ap1 = findViewById(R.id.crea_ape1);
        ap2 = findViewById(R.id.crea_ape2);
        rut = findViewById(R.id.crea_rut);
        pin = findViewById(R.id.create_pin);
        btn = findViewById(R.id.empleado_new);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    private void insertar(){
        try {
            PreparedStatement a = uwu.conData.prepareCall("Insert into empleado VALUES (?,?,?,?,?)");
            a.setString(1,rut.getText().toString());
            a.setInt(2,Integer.parseInt(pin.getText().toString()));
            a.setString(3,nom.getText().toString());
            a.setString(4,ap1.getText().toString());
            a.setString(5,ap2.getText().toString());
            int b = a.executeUpdate();
            if(b ==1){
                Toast.makeText(this, "Ingreso con Exito", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Error Al Crear Empleado", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}