package com.example.propuesta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propuesta.ManejoEmpleados.CreateEmpActivity;
import com.example.propuesta.administra_Empleado.datosEmpSucursal;
import com.example.propuesta.paraconsulta2.ListaComuna;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private  TabLayout tabLayout;
    private ViewPager2 viewPager;
    private static clsConexionPG con=new clsConexionPG();
    public Connection conData = con.conexionBD();
    ListView agendaA,adendaT;
    //--------------------------------------------------
    TextView u;
    Timer timer;
    TimerTask timerTask;
    int segundos =0;
    int minutos =0;
    int hora =0;
    boolean started = false;
    String string_segundos;
    String string_minutos;
    String string_hora;
    //----------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hora awa = new Hora();
        u =findViewById(R.id.aaaaaa);
        timer = new Timer();
        startCHorario();


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
            case R.id.op_Clientes:
                Intent cb = new Intent(MainActivity.this, ListaComuna.class);
                startActivity(cb);
                return true;
            case R.id.ing_emp:
                Intent ho = new Intent(MainActivity.this, CreateEmpActivity.class);
                startActivity(ho);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void startCHorario()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() //Se crea un Hilo pa qe el contador funque xd
                {
                    @Override
                    public void run()
                    {

                        u.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }


    private String getTimerText()
    {

        Calendar c=Calendar.getInstance();
        int hora=c.get(Calendar.HOUR);
        int minutos=c.get(Calendar.MINUTE);
        int segundos=c.get(Calendar.SECOND);
        string_segundos = String.format("%02d", segundos);
        string_minutos = String.format("%02d", minutos);
        string_hora = String.format("%02d", hora);

        return string_hora + " : "+string_minutos + " : " + string_segundos;
    }


}
