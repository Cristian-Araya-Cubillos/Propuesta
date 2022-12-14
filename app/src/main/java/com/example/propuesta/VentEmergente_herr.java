package com.example.propuesta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propuesta.Agendas.AgendaTotaluwu;
import com.example.propuesta.administra_Empleado.datosEmpSucursal;
import com.example.propuesta.herramientas.datosHerramientas;

import java.sql.SQLException;

public class VentEmergente_herr extends AppCompatActivity {
    TextView ee;
    Button acep, cancel;
    datosHerramientas hr = new datosHerramientas();
    String ru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emerg_hrramienta);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int ancho = dm.widthPixels;//850;//
        int largo = dm.heightPixels;//600;//
        getWindow().setLayout((int)(ancho),(int)(largo));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        cancel = findViewById(R.id.noElimin);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(params);
        opcion();

    }

    public void opcion(){
        acep = findViewById(R.id.eliminaEmp);
        acep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                try {
                    int a = hr.elimin_hr(Integer.parseInt(getIntent().getStringExtra("id")));
                    if(a==1){
                        Toast.makeText(VentEmergente_herr.this, "Eliminado Con Exito", Toast.LENGTH_SHORT);
                    }else{
                        Toast.makeText(VentEmergente_herr.this, "Error al eliminar", Toast.LENGTH_SHORT);
                    }
                    Intent ab = new Intent(VentEmergente_herr.this, datosHerramientas.class);
                    startActivity(ab);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public void opCancel(View view){
        Intent ab = new Intent(VentEmergente_herr.this, datosHerramientas.class);
        startActivity(ab);
    }


}