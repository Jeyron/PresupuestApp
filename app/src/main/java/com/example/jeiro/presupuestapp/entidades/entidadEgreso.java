package com.example.jeiro.presupuestapp.entidades;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class entidadEgreso
{
    private String tipo;
    private String descripcion;
    private String egreso;
    private int    monto;
    private int    id_mes;

    public entidadEgreso (String tipo, String descripcion, String egreso, int monto, int id_mes)
    {
        this.tipo        = tipo;
        this.descripcion = descripcion;
        this.egreso      = egreso;
        this.monto       = monto;
        this.id_mes      = id_mes;
    }
}
