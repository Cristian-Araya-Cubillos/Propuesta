package com.example.propuesta.adminAgendas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propuesta.MainActivity;
import com.example.propuesta.R;
import com.example.propuesta.paraconsulta2.paraListaComunas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

public class Crea_Agenda extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText rut, hora;
    TextView fecha;
    Button btn_insertar,btn_volver;
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    Spinner spinner;
    String fecha_pa_usar;
    String srv_slc;
    int id_srv;
    Vector<String> x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_agenda);
        iniciar();

        ArrayAdapter<String> adapter;//(this, android.R.layout.simple_spinner_item);
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos_spin());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        srv_slc = parent.getItemAtPosition(position).toString();


    }

    private void iniciar(){
        spinner = findViewById(R.id.aa_spinner);
        rut = findViewById(R.id.A_run);
        fecha = findViewById(R.id.A_fecha);
        hora = findViewById(R.id.A_hora);
        btn_insertar = findViewById(R.id.insert_agenda);
        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(cliente_registrado()){
                        inserta();
                    }else{
                        Toast.makeText(getBaseContext(),"Cliente No Registrado en la Base de Datos",Toast.LENGTH_SHORT).show();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_volver = findViewById(R.id.o_volver);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crea_Agenda.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public void inserta(){
        try {
            String horario = hora.getText().toString();
            obt_time(horario);
            PreparedStatement a = uwu.conData.prepareCall("Insert into agenda VALUES (?,?,?,?)");
            a.setInt(1,obtnIds());
            a.setString(2,rut.getText().toString());
            //java.sql.Timestamp sqlTime= java.sql.Timestamp.valueOf(hora.getText().toString());
            a.setTime(3, new Time(Integer.parseInt(x.get(0)),
                                                Integer.parseInt(x.get(1)),
                                                    Integer.parseInt(x.get(2))));//Time.valueOf(hora.getText().toString()));
            a.setDate(4,java.sql.Date.valueOf((fecha_pa_usar)));
            int b = a.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void obt_time(String aux){

        StringTokenizer produc = new StringTokenizer(aux,":");
        x = new Vector<>();
        while(produc.hasMoreTokens()) {
            x.addElement(produc.nextToken());
        }
        x.add("00");
    }

    public ArrayList<String> datos_spin()throws SQLException{
        ArrayList<String> lst = new ArrayList<>();
        PreparedStatement pa_spn = uwu.conData.prepareCall("Select distinct nombre from servicio");
        rsData = pa_spn.executeQuery();

        while(rsData.next()){
            String nom = rsData.getString("nombre");
            lst.add(nom);

        }

        return lst;
    }

    public int obtnIds()throws SQLException{
        String id = "";
        PreparedStatement pa_spn = uwu.conData.prepareCall("SELECT id_servicio from servicio where nombre = ?");
        pa_spn.setString(1,srv_slc);
        rsData = pa_spn.executeQuery();
        while(rsData.next()){
            id = rsData.getString("id_servicio");
        }
        return Integer.parseInt(id);
    }

    public void abrirC(View view){
        Calendar  calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int  mes = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog obtener_fecha = new DatePickerDialog(Crea_Agenda.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                fecha_pa_usar = year + "-" + month + "-" +dayOfMonth;
                fecha.setText(fecha_pa_usar);

            }
        },year,mes,day);
        obtener_fecha.show();

    }

    public boolean cliente_registrado() throws SQLException {
        PreparedStatement a= uwu.conData.prepareCall("SELECT rut from clientes where rut= ?");
        a.setString(1,rut.getText().toString());
        rsData = a.executeQuery();
        while(rsData.next()) {
            return true;
        }
        return false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}