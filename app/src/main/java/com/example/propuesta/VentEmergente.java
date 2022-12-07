package com.example.propuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

public class VentEmergente extends AppCompatActivity {
    TextView ee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vent_emergente);
        ee = findViewById(R.id.holaemp);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Intent intent = getIntent();
        int ancho = dm.widthPixels;//850;//
        int largo = dm.heightPixels;//600;//
        ee.setText(intent.getStringExtra("name"));
        getWindow().setLayout((int)(ancho),(int)(largo));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setAttributes(params);

    }
}