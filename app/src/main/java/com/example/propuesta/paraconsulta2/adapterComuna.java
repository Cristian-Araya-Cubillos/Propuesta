package com.example.propuesta.paraconsulta2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.propuesta.R;

import java.util.List;

public class adapterComuna extends BaseAdapter {
    Context context;
    List<paraListaComunas> lists;

    public adapterComuna(Context context, List<paraListaComunas> lists) {
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

        TextView nombre_C, runC;
        paraListaComunas p = lists.get(i);
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.listview_comuna,null);
        }
        nombre_C=view.findViewById(R.id.textnomAp);
        runC=view.findViewById(R.id.textRun);
        nombre_C.setText(p.nombre_com);
        runC.setText(p.run);
        return view;
    }

}
