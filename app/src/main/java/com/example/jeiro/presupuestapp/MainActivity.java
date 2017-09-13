package com.example.jeiro.presupuestapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.jeiro.presupuestapp.Datos.acceso_datos;
import com.example.jeiro.presupuestapp.Datos.base_datos;
import com.example.jeiro.presupuestapp.entidades.entidadCategoria;
import com.example.jeiro.presupuestapp.entidades.entidadMes;

public class MainActivity extends Activity {
    Button btnSgte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btnSgte = (Button)findViewById(R.id.btn_inicio);
        btnSgte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Navegacion.class);
                startActivity(intent);
            }
        });
    }
}
