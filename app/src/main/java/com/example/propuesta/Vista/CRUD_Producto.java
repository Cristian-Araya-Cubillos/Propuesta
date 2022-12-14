package com.example.propuesta.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.propuesta.Modelo.Producto;
import com.example.propuesta.R;

public class CRUD_Producto extends AppCompatActivity {
    int realizar =0;
    EditText ET_id;
    EditText ET_name;
    EditText ET_stock;
    EditText ET_price;
    Button btn_CUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_producto);
        EditTextCharge();
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
             realizar = parametros.getInt("datos");
             if(realizar==1){
                 ActividadCrear();
             }else if(realizar==2){
                 Producto nuevoProd = (Producto) parametros.getSerializable("producto");
                 ActividadModificar(nuevoProd);
             }else if(realizar==3){
                 Producto nuevoProd = (Producto) parametros.getSerializable("producto");
                 ActividadEliminar(nuevoProd);
             }
        }
    }

    private void EditTextCharge() {
        ET_id.findViewById(R.id.EdTxt_ID_CrearProducto);
        ET_name.findViewById(R.id.EdTxt_Nombre_CrearProd);
        ET_price.findViewById(R.id.EdTxt_Precio_CrearProd);
        ET_stock.findViewById(R.id.EdTxt_Cantidad_CrearProd);
        btn_CUD.findViewById(R.id.btn_CrearProd);
    }
    private void ActividadEliminar(Producto nuevoProd) {
        setfields(nuevoProd,2);
    }

    private void disableFields(int i) {
        if(i ==1){
            ET_id.setEnabled(false);
        }else{
            ET_id.setEnabled(false);
            ET_name.setEnabled(false);
            ET_stock.setEnabled(false);
            ET_price.setEnabled(false);
        }
    }
    private void ActividadModificar(Producto nuevoProd) {
        setfields(nuevoProd,1);
    }
    private void setfields(Producto nuevoProd, int i) {
        ET_id.setText(nuevoProd.getId_producto()+"");
        ET_name.setText(nuevoProd.getNombre()+"");
        ET_stock.setText(nuevoProd.getCantidad()+"");
        ET_price.setText(nuevoProd.getPrecio()+"");
        if(i==1) {
            btn_CUD.setText("Modificar un Producto");
            disableFields(1);
        }else{
            btn_CUD.setText("Eliminar un Producto");
            disableFields(2);
        }
    }

    private void ActividadCrear() {
        btn_CUD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Boton Presionado");
            }
        });
    }

}