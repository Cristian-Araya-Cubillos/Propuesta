package com.example.propuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;

import com.example.propuesta.administra_Empleado.datosEmpSucursal;

import java.sql.SQLException;

public class VentEmergente extends AppCompatActivity {
    TextView ee;
    Button acep, cancel;
    datosEmpSucursal datos = new datosEmpSucursal();
    String ru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vent_emergente);
        ee = findViewById(R.id.holaemp);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Intent intent = getIntent();
        int ancho = dm.widthPixels;//850;//
        int largo = dm.heightPixels;//600;//
        ee.setText(intent.getStringExtra("name"));
        getWindow().setLayout((int)(ancho),(int)(largo));
        ru = intent.getStringExtra("rut");
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
                    datos.elimin(getIntent().getStringExtra("rut"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent ab = new Intent(VentEmergente.this, datosEmpSucursal.class);
                startActivity(ab);
            }
        });

    }

    public void opCancel(View view){
        Intent ab = new Intent(VentEmergente.this, datosEmpSucursal.class);
        startActivity(ab);
    }
}