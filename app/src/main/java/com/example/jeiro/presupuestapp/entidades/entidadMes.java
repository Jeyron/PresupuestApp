package com.example.jeiro.presupuestapp.entidades;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class entidadMes
{
    private String nombre;
    private int    ingreso;
    private String estado;

    public entidadMes (String nombre, int ingreso, String estado)
    {
        this.nombre   = nombre;
        this.ingreso  = ingreso;
        this.estado   = estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
