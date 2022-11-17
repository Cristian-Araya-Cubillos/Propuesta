package com.example.propuesta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.propuesta.administra_Empleado.datosEmpSucursal;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    private static clsConexionPG con=new clsConexionPG();
    public Connection conData = con.conexionBD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.op_Emp:
                Intent mb = new Intent(MainActivity.this, datosEmpSucursal.class);
                startActivity(mb);
                return true;
            case R.id.agregar_erramienta:
                Toast.makeText(this,"A H", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.quitar_erramienta:
                Toast.makeText(this,"Q H", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}