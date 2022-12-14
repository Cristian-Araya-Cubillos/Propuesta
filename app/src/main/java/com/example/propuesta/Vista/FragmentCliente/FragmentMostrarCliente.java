package com.example.propuesta.Vista.FragmentCliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.propuesta.Modelo.Cliente;
import com.example.propuesta.R;

import java.util.ArrayList;


public class FragmentMostrarCliente extends Fragment {
    private int Realizar;
    private Cliente cls;
    private ListView LV_Mostrado;
    private int Suma;

    public FragmentMostrarCliente() {
        // Required empty public constructor
    }
    public FragmentMostrarCliente(int realizar, Cliente cliente) {
        this.Realizar = realizar;
        this.cls = cliente;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_cliente, container, false);
        this.LV_Mostrado = (ListView) v.findViewById(R.id.LV_FCL_MostrarCls);
        if(this.Realizar ==1){
            Cliente clss = this.cls.Ver1Cliente();
            ArrayList<Cliente> client = new ArrayList<>();
            client.add(clss);
            this.LV_Mostrado.setAdapter(new ArrayAdapter<Cliente>(v.getContext(),android.R.layout.simple_selectable_list_item,client));
        }else if(this.Realizar ==2){
            int valor = this.cls.CostoTotalCliente();
            ArrayList<String> Valores = new ArrayList<>();
            Valores.add("El costo total de los servicios empleados al cliente es: "+valor);
            this.LV_Mostrado.setAdapter(new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_selectable_list_item,Valores));
        }else if(this.Realizar == 3){
            String valor = this.cls.SucursalesCernacas();
            ArrayList<String> Valores = new ArrayList<>();
            Valores.add("La Sucursal más cercana es: "+valor);
            this.LV_Mostrado.setAdapter(new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_selectable_list_item,Valores));
        }
        return v;

    }
}