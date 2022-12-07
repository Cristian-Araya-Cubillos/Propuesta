package com.example.propuesta.Agendas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.propuesta.R;
import com.example.propuesta.paraconsulta2.paraListaComunas;

import java.util.List;

public class adapter_listaAgendas extends BaseAdapter {
    Context context;
    List<paraAgendas> lists;

    public adapter_listaAgendas(Context context, List<paraAgendas> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i){
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TextView run, horario_ac,servicio;
        paraAgendas p = lists.get(i);
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listviwe_agenda,null);
        }
        run=view.findViewById(R.id.id_run);
        servicio=view.findViewById(R.id.id_servicio);
        horario_ac = view.findViewById(R.id.id_dia);
        run.setText("Rut Cliente: "+p.rut_c);
        servicio.setText(p.servicio);
        horario_ac.setText(p.Horario);
        return view;
    }
}
