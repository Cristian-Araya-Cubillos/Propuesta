package com.example.propuesta.adminAgendas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import com.example.propuesta.MainActivity;
import com.example.propuesta.R;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void iniciar(){
        spinner = findViewById(R.id.aa_spinner);
        rut = findViewById(R.id.A_run);
        fecha = findViewById(R.id.A_fecha);
        hora = findViewById(R.id.A_hora);
    }

    public void inserta() throws SQLException {
        PreparedStatement a = uwu.conData.prepareCall("Insert into agenda VALUES (?,?,?,?)");
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

    public void abrirC(View view){
        Calendar  calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int  mes = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog obtener_fecha = new DatePickerDialog(Crea_Agenda.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha_pa_usar = year + "-" + month + "-" +dayOfMonth;
                fecha.setText(fecha_pa_usar);
                fecha.setHeight(30);
                fecha.setTextSize(20);

            }
        },year,mes,day);
        obtener_fecha.show();

    }


}