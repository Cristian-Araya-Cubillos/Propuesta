package com.example.propuesta.herramientas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.propuesta.R;

import java.util.List;

public class adapter_Herramientas extends BaseAdapter {
    Context context;
    List<paraHerramientas> lists;

    public adapter_Herramientas(Context context, List<paraHerramientas> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        TextView Id_Herramientas,Nombre ;
        paraHerramientas p = lists.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_herramientas, null);
        }
        Id_Herramientas=view.findViewById(R.id.textId_Herramientas);
        Nombre=view.findViewById(R.id.textNombre);
        Id_Herramientas.setText("ID: "+p.id_herramientas);
        Nombre.setText("Nombre de herramienta: "+p.nombre);
        return view;
    }
}