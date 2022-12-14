package com.example.propuesta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propuesta.ManejoEmpleados.CreateEmpActivity;
import com.example.propuesta.Vista.BuscarCliente;
import com.example.propuesta.Vista.CRUD_Cliente;
import com.example.propuesta.Vista.CRUD_Producto;
import com.example.propuesta.Vista.VerClientes;
import com.example.propuesta.adminAgendas.Crea_Agenda;
import com.example.propuesta.administra_Boleta.datosBoletaCliente;
import com.example.propuesta.administra_Empleado.datosEmpSucursal;
import com.example.propuesta.paraconsulta2.ListaComuna;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private  TabLayout tabLayout;
    private ViewPager2 viewPager;
    private static clsConexionPG con=new clsConexionPG();
    public Connection conData = con.conexionBD();
    ListView agendaA,adendaT;
    Button bbb;
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
    BarChart bar;
    ArrayList<BarEntry> entradas;
    ArrayList<BarEntry> dAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Peluqueria Unisex");
        //Hora awa = new Hora();
        u =findViewById(R.id.aaaaaa);
        timer = new Timer();
        startCHorario();
        bar = findViewById(R.id.chara);
        try {
            datosAgendaGraf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bbb = findViewById(R.id.crea_age);

    }

    public void insrt_agenda(View view){
        Intent intent = new Intent(MainActivity.this, Crea_Agenda.class);
        startActivity(intent);
    }

    public void datosAgendaGraf() throws SQLException {
        System.out.println("Entra");
        ResultSet rsData;
        entradas = new ArrayList<>();
        PreparedStatement comunas_ac = conData.prepareCall("Select fecha, count(*) from agenda Group By fecha");
        rsData = comunas_ac.executeQuery();
        int i=0;
        while(rsData.next()){
            String comun = rsData.getString("count");
            BarEntry  barentry = new BarEntry(i,Integer.parseInt(comun));
            entradas.add(barentry);
            System.out.println(comun);
            i++;
        }

        //Inicializo datos de Bar
        BarDataSet barDataSet = new BarDataSet(entradas,"Dias");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //Oculta valores
        barDataSet.setDrawValues(false);

        //inserta datos
        bar.setData(new BarData(barDataSet));

        //Animacion

        bar.animateY(5000);

        //Descripcion y coloR
        bar.getDescription().setText("");
        bar.getDescription().setTextColor(Color.BLUE);

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
                Intent ae = new Intent(MainActivity.this, CRUD_Producto.class);
                startActivity(ae);
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
            case R.id.op_boletas:
                Intent bol = new Intent(MainActivity.this, datosBoletaCliente.class);
                startActivity(bol);
                return true;
            case R.id.agregar_Cliente:
                Intent cl = new Intent(MainActivity.this, CRUD_Cliente.class);
                Bundle bundle = new Bundle();
                bundle.putInt("datos", 1);
                cl.putExtras(bundle);
                startActivity(cl);
                return true;
            case R.id.Ver_Cliente:
                Intent vcl = new Intent(MainActivity.this, VerClientes.class);
                startActivity(vcl);
                return true;
            case R.id.Buscar_Cliente:
                Intent bcl = new Intent(MainActivity.this, BuscarCliente.class);
                startActivity(bcl);
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
