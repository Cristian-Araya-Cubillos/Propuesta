package com.example.propuesta.Vista.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.propuesta.MainActivity;
import com.example.propuesta.Modelo.Cliente;
import com.example.propuesta.R;
import com.example.propuesta.Vista.CRUD_Cliente;
import com.example.propuesta.Vista.VerClientes;


public class SeleccionDialogClientes extends DialogFragment implements View.OnClickListener{
    Activity act;
    ImageButton salir;
    Button btn_Modificar;
    Button btn_Eliminar;
    VerClientes verCl;
    Cliente cl;
    String una_cosa="NO LLEGA NADA";
    public SeleccionDialogClientes() {
        // Required empty public constructor
    }
    public SeleccionDialogClientes(Cliente Cl) {
        cl = Cl;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return CrearDialogo();
    }

    private AlertDialog CrearDialogo() {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_seleccion_dialog_clientes,null);
        build.setView(v);
        salir = v.findViewById(R.id.IB_FCL_Salir);
        btn_Modificar = v.findViewById(R.id.btn_FCL_Modificar);
        btn_Eliminar = v.findViewById(R.id.btn_FCL_Eliminar);
        btn_Modificar.setOnClickListener(this);
        btn_Eliminar.setOnClickListener(this);
        salir.setOnClickListener(this);
        //EventosBotones();
        return build.create();
    }

    /*private void EventosBotones() {
       btn_Modificar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(),"Modificado... ",Toast.LENGTH_SHORT).show();
           }
       });
        btn_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Eliminado...",Toast.LENGTH_SHORT).show();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dismiss();
            }
        });
    }*/
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.act = (Activity) context;
            verCl = (VerClientes) this.act;
        }else {
            throw new RuntimeException(context.toString()+"MUst implements onfragment");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // cases applied over different buttons
            case R.id.btn_FCL_Modificar:
                System.out.println("Modificando... "+this.una_cosa);
                Intent cl = new Intent(getActivity(), CRUD_Cliente.class);
                Bundle bundle = new Bundle();
                bundle.putInt("datos", 2);
                bundle.putSerializable("Cliente", this.cl);
                cl.putExtras(bundle);
                startActivity(cl);
                break;
            case R.id.btn_FCL_Eliminar:
                Intent cl1 = new Intent(getActivity(), CRUD_Cliente.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("datos", 3);
                bundle1.putSerializable("Cliente", this.cl);
                cl1.putExtras(bundle1);
                startActivity(cl1);
                break;
            case R.id.IB_FCL_Salir:
                dismiss();break;
        }
    }
}