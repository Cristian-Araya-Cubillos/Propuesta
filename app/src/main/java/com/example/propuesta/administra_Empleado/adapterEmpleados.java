package com.example.propuesta.administra_Empleado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.propuesta.R;

import java.util.List;

public class adapterEmpleados extends BaseAdapter {
    Context context;
    List<paraListaEmpleadoS> lists;

    public adapterEmpleados(Context context, List<paraListaEmpleadoS> lists) {
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
        TextView nombre_E, runE;
        paraListaEmpleadoS p = lists.get(i);
        System.out.println(lists.get(i));
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listview_empleados,null);
        }
        //Estos Datos se enlazan con las ID correspondientes a listview empleados, que es la que se encarga de llenar los campos
        nombre_E=view.findViewById(R.id.textnomEmp);
        runE=view.findViewById(R.id.textRun_emp);
        nombre_E.setText(p.nombre_com);
        runE.setText(p.run);
        return view;
    }

}
