package com.example.propuesta.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.propuesta.Modelo.Cliente;
import com.example.propuesta.R;
import com.example.propuesta.Vista.FragmentCliente.FragmentMostrarCliente;

public class BuscarCliente extends AppCompatActivity implements View.OnClickListener{
    Spinner SP_Busqueda;
    EditText ET_BuscarRun;
    Button BTN_Buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente);
        InstanciarCampos();
    }

    private void InstanciarCampos() {
        SP_Busqueda = (Spinner) findViewById(R.id.SP_BusquedaCTotal);
        ET_BuscarRun = (EditText) findViewById(R.id.ET_CL_BuscarRUN);
        BTN_Buscar = (Button) findViewById(R.id.BT_CL_Buscar);
        BTN_Buscar.setOnClickListener(this);
    }

    private int SpinerEleccion(){
        return SP_Busqueda.getSelectedItemPosition();
    }
    @Override
    public void onClick(View view) {
        Cliente cl = new Cliente();
        cl.setRun(ET_BuscarRun.getText().toString());
        FragmentMostrarCliente FMC = new FragmentMostrarCliente(SpinerEleccion(),cl);
        FragmentManager FM = getSupportFragmentManager();
        FM.beginTransaction()
            .replace(R.id.FCL_Contenedor1,FMC,null)
            .setReorderingAllowed(true)
            .commit();

    }
}