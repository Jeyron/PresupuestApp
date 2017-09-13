package com.example.jeiro.presupuestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class Navegacion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Categoria_egreso.OnFragmentInteractionListener,Presupuesto_mensual.OnFragmentInteractionListener,
        Habilitar_periodo.OnFragmentInteractionListener,Registro_egreso_mensual.OnFragmentInteractionListener,
        Registrar_extra_ingreso.OnFragmentInteractionListener,Registro_tarjetas.OnFragmentInteractionListener,
        Reportes.OnFragmentInteractionListener{

    Button btn_salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    /* Botón de salida (final de la aplicación)*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .setTitle("Salir")
                    .setMessage("¿Estás seguro?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navegacion, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment= null;
        Boolean fragment_seleccionado= false;
        if (id == R.id.edición_categoría_egreso) {
            fragment = new Categoria_egreso();
            fragment_seleccionado=true;
        }
        else if (id == R.id.detalle_presupuesto_mensual) {
            fragment = new Presupuesto_mensual();
            fragment_seleccionado=true;
        }
        else if (id == R.id.habilitar_período) {
            fragment = new Habilitar_periodo();
            fragment_seleccionado=true;
        }
        else if (id == R.id.registro_extr_ingreso) {
            fragment = new Registrar_extra_ingreso();
            fragment_seleccionado=true;
        }
        else if (id == R.id.registro_tarjetas) {
            fragment = new Registro_tarjetas();
            fragment_seleccionado=true;
        }
        else if (id == R.id.registro_egreso_mensual) {
            fragment = new Registro_egreso_mensual();
            fragment_seleccionado=true;
        }
        else if (id == R.id.reportes) {
            fragment = new Reportes();
            fragment_seleccionado=true;
        }
        if(fragment_seleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onAlertDialog(MenuItem item) {
        AlertDialog alertDialog;
        alertDialog= new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Acerca De");
        alertDialog.setMessage("Este proyecto fue realizado por estudiantes del Instituto Tecnologico de Costa Rica");
        alertDialog.show();
    }
}
