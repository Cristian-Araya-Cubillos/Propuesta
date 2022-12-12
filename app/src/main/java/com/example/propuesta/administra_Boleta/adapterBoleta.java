package com.example.propuesta.administra_Boleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.propuesta.R;

import java.util.List;

public class adapterBoleta extends BaseAdapter {
    Context context;
    List<paraBoletas> lists;

    public adapterBoleta(Context context, List<paraBoletas> lists) {
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

        TextView Numero_Boleta, Fecha;
        paraBoletas p = lists.get(i);
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listview_boletas,null);
        }
        Numero_Boleta=view.findViewById(R.id.textnum_Boleta);
        Fecha=view.findViewById(R.id.textfecha_Boleta);
        Numero_Boleta.setText("Numero de Boleta: "+p.Numero_Boleta);
        Fecha.setText("RUT empleado que realizo el servicio: "+p.Fecha);
        return view;
    }

}
