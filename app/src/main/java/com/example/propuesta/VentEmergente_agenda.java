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

import androidx.appcompat.app.AppCompatActivity;

import com.example.propuesta.Agendas.AgendaTotaluwu;
import com.example.propuesta.administra_Empleado.datosEmpSucursal;

import java.sql.SQLException;

public class VentEmergente_agenda extends AppCompatActivity {
    Button acep, cancel;
    AgendaTotaluwu datos_ag = new AgendaTotaluwu();
    String ru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vent_emerg_agenda);
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
        opcion_agend();

    }

    public void opcion_agend(){
        acep = findViewById(R.id.eliminaEmp);
        acep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                try {
                    datos_ag.elimin_ag(getIntent().getStringExtra("rut_ag"),
                                    Integer.parseInt(getIntent().getStringExtra("id_ag")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent ab = new Intent(VentEmergente_agenda.this, MainActivity.class);
                startActivity(ab);
            }
        });

    }

    public void opCancel(View view){
        Intent ab = new Intent(VentEmergente_agenda.this, MainActivity.class);
        startActivity(ab);
    }


}