package com.example.propuesta.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.propuesta.Modelo.Cliente;
import com.example.propuesta.R;
import com.example.propuesta.Vista.Dialog.SeleccionDialogClientes;

import java.util.ArrayList;

public class VerClientes extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private ListView ListaClientes;
    private ArrayList<Cliente> Lista;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);
        this.Lista = new ArrayList<Cliente>();
        Cliente cl = new Cliente();
        this.Lista = (ArrayList) cl.VerClientes().clone();
        this.ListaClientes = (ListView) findViewById(R.id.ListV_CL_ListaClientes);
        this.ListaClientes.setAdapter(new ArrayAdapter<Cliente>(getApplicationContext(),android.R.layout.simple_selectable_list_item,this.Lista));
        this.ListaClientes.setOnItemLongClickListener(this);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Presionado"+ Lista.get(i).toString());
        SeleccionDialogClientes FragmentSeleccion =  new SeleccionDialogClientes(Lista.get(i));
        FragmentSeleccion.show(getSupportFragmentManager(),"DialogoSeleccion");
        return true;
    }
}