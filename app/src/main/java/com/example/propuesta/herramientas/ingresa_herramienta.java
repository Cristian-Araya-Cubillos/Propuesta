package com.example.propuesta.herramientas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propuesta.MainActivity;
import com.example.propuesta.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ingresa_herramienta extends AppCompatActivity {
    EditText id,nom,desc;
    Button btn;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crea_herramienta);
        inicia();

    }

    private void inicia(){
        id = findViewById(R.id.crea_idH);
        nom = findViewById(R.id.crea_nombreH);
        desc = findViewById(R.id.crea_descH);
        btn = findViewById(R.id.herramienta_new);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    private void insertar(){
        try {
            PreparedStatement a = uwu.conData.prepareCall("Insert into herramientas VALUES (?,?,?)");
            a.setInt(1,Integer.parseInt(id.getText().toString()));
            a.setString(2,nom.getText().toString());
            a.setString(3,desc.getText().toString());
            int b = a.executeUpdate();
            if(b ==1){
                Toast.makeText(this, "Ingreso con Exito", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Error Al Crear Herramienta", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}