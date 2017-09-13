package com.example.jeiro.presupuestapp.entidades;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class entidadTarjeta
{
    private String marca;
    private String entidad;
    private String tipo;
    private String numero;

    public entidadTarjeta (String marca, String entidad, String tipo, String numero)
    {
        this.marca   = marca;
        this.entidad = entidad;
        this.tipo    = tipo;
        this.numero  = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}