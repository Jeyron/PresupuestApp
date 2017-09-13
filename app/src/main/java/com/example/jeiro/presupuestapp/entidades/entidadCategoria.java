package com.example.jeiro.presupuestapp.entidades;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class entidadCategoria {

    private String tipo;
    private String descripcion;
    private String egreso;
    private int    monto;
    private int    id_mes;

    public entidadCategoria (String tipo, String descripcion, String egreso, int monto, int id_mes)
    {
        this.tipo        = tipo;
        this.descripcion = descripcion;
        this.egreso      = egreso;
        this.monto       = monto;
        this.id_mes      = id_mes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getId_mes() {
        return id_mes;
    }

    public void setId_mes(int id_mes) {
        this.id_mes = id_mes;
    }
}
