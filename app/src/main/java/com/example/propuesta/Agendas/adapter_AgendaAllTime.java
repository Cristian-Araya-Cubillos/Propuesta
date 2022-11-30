package com.example.propuesta.Agendas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class adapter_AgendaAllTime extends BaseAdapter {
    Context context;
    List<paraAgendas> iwi;

    public adapter_AgendaAllTime(Context context, List<paraAgendas> iwi) {
        this.context = context;
        this.iwi = iwi;
    }

    @Override
    public int getCount() {
        return iwi.size();
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
    public View getView(int i, View convertView, ViewGroup parent) {
        return null;
    }
}
