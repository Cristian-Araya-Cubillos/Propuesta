package com.example.propuesta.Agendas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.propuesta.MainActivity;
import com.example.propuesta.R;
import com.example.propuesta.paraconsulta2.adapterComuna;
import com.example.propuesta.paraconsulta2.paraListaComunas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaDiaActual#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaDiaActual extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgendaDiaActual() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaDiaActual.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaDiaActual newInstance(String param1, String param2) {
        AgendaDiaActual fragment = new AgendaDiaActual();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ListView mostrar;
    MainActivity uwu = new MainActivity();
    Statement stmData;
    ResultSet rsData;
    List<paraAgendas> lst = new ArrayList<>();
    ArrayList<String> lst_com = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_agenda_dia_actual, container, false);
        mostrar = root.findViewById(R.id.ag_dia);
        adapter_listaAgendas awa = null;
        try {
            awa = new adapter_listaAgendas(root.getContext(), getDatos("Santiago"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mostrar.setAdapter(awa);
        mostrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long ide) {
                paraAgendas l = lst.get(i);

            }
        });
        return root;
    }

    private List<paraAgendas> getDatos(String NombredeComuna) throws SQLException {

        //stmData = uwu.conData.createStatement();
        lst.clear();
        //---------------------------------------------------------------------
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());                        //Obtengo Fecha de a;o, mes y dia actuales
        System.out.println(formatter.format(date));
        //------------------------------------------------------------------------
        PreparedStatement a= uwu.conData.prepareCall("SELECT * from agenda, servicio where agenda.id_servicio = servicio.id_servicio and agenda.fecha = ?");
        a.setDate(1, java.sql.Date.valueOf(formatter.format(date))); //
        rsData = a.executeQuery();
        while(rsData.next()) {
            String run = rsData.getString("rut_cliente");
            String serv_id =rsData.getString("nombre");
            String fecha = rsData.getString("fecha");
            String hora_atencion = rsData.getString("hora_atencion");
            String horario = fecha+" a las: "+hora_atencion;
            lst.add(new paraAgendas(horario,run,serv_id));
        }
        System.out.println("Tama;o "+lst.size());
        return lst;
    }
}