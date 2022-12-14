package com.example.propuesta.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propuesta.Modelo.Cliente;
import com.example.propuesta.R;

import java.util.ArrayList;

public class CRUD_Cliente extends AppCompatActivity implements View.OnClickListener {
    private TextView Title;
    private EditText Run;
    private EditText Nombre;
    private EditText Apellido1;
    private EditText Apellido2;
    private Spinner SpinerComunas;
    private Button btn_1;
    private Button btn_2;
    private int realizar=0;
    private ArrayList<String>Comunas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_cliente);
        EditTextCharge();
        llenarComunas();
        FillSpiner();
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            realizar = parametros.getInt("datos");
            if(realizar==1){
                ActividadCrear();
            }else if(realizar==2){
                Cliente nuevoCl = (Cliente) parametros.getSerializable("Cliente");
                ActividadModificar(nuevoCl);
            }else if(realizar==3){
                Cliente nuevoCl = (Cliente) parametros.getSerializable("Cliente");
                ActividadEliminar(nuevoCl);
            }
        }

    }

    private void ActividadModificar(Cliente nuevoCl) {
        setfields(nuevoCl,1);
    }
    private void disableFields(int i) {
        if(i ==1){
            this.Run.setEnabled(false);
        }else{
            this.Run.setEnabled(false);
            this.Nombre.setEnabled(false);
            this.Apellido1.setEnabled(false);
            this.Apellido2.setEnabled(false);
            this.SpinerComunas.setEnabled(false);
        }
    }
    private void setfields(Cliente nuevoCl, int i) {
        this.Run.setText(nuevoCl.getRun()+"");
        this.Nombre.setText(nuevoCl.getNombre()+"");
        this.Apellido1.setText(nuevoCl.getApellido1()+"");
        this.Apellido2.setText(nuevoCl.getApellido2()+"");
        this.SpinerComunas.setSelection(Comunas.indexOf(nuevoCl.getComuna()));
        if(i==1) {
            this.Title.setText("Modificar Cliente");
            this.btn_1.setText("Modificar Cliente");
            disableFields(1);
        }else{
            this.Title.setText("Eliminar Cliente");
            this.btn_1.setText("Eliminar Cliente");
            disableFields(2);
        }
    }

    private void ActividadEliminar(Cliente nuevoCl) {
        setfields(nuevoCl,2);
    }
    private void ActividadCrear() {

    }

    private void EditTextCharge() {
        this.Title = (TextView) findViewById(R.id.TV_CL_Title);
        this.Run = (EditText) findViewById(R.id.ETV_CL_RUN);
        this.Nombre = (EditText) findViewById(R.id.ETV_CL_Name);
        this.Apellido1 = (EditText) findViewById(R.id.ETV_CL_Apellido1);
        this.Apellido2 = (EditText) findViewById(R.id.ETV_CL_Apellido2);
        this.btn_1 = (Button) findViewById(R.id.btn_CL_agregar);
        this.btn_2 = (Button) findViewById(R.id.btn_CL_cancelar);
        this.btn_1.setOnClickListener(this);
        this.btn_2.setOnClickListener(this);
    }

    private void llenarComunas(){
        Cliente cl = new Cliente();
        this.Comunas = new ArrayList<String>();
        int i =0;
        this.Comunas = (ArrayList) cl.ListadeComunas().clone();
        while(i==this.Comunas.size()){
            System.out.println(this.Comunas.get(i));
            i++;
        }
    }
    private void FillSpiner(){
        this.SpinerComunas = (Spinner) findViewById(R.id.SP_CL_SelectComuna);
        this.SpinerComunas.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,this.Comunas));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // cases applied over different buttons
            case R.id.btn_CL_agregar:
                switch (realizar){
                    case 1:
                        Cliente cl = new Cliente(this.Run.getText().toString(),this.Nombre.getText().toString(),this.Apellido1.getText().toString(),this.Apellido2.getText().toString(),this.SpinerComunas.getSelectedItem().toString());
                        System.out.println(cl.toString());
                        if(cl.AddClient()==false){
                            Toast.makeText(this,"No se ha podido agregar",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this,"Agregado correctamente",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        Cliente cl1 = new Cliente(this.Run.getText().toString(),this.Nombre.getText().toString(),this.Apellido1.getText().toString(),this.Apellido2.getText().toString(),this.SpinerComunas.getSelectedItem().toString());
                        System.out.println(cl1.toString());
                        if(cl1.UpdateClient()==false){
                            Toast.makeText(this,"No se ha podido Modificar",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this,"Modificado correctamente",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        Cliente cl2 = new Cliente(this.Run.getText().toString(),this.Nombre.getText().toString(),this.Apellido1.getText().toString(),this.Apellido2.getText().toString(),this.SpinerComunas.getSelectedItem().toString());
                        System.out.println(cl2.toString());
                        if(cl2.DeleteClient()==false){
                            Toast.makeText(this,"No se ha podido Eliminar",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this,"Eliminado correctamente",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
            case R.id.btn_CL_cancelar:
                System.out.println("Cancelando...");
                break;
        }
    }
}