package com.example.jeiro.presupuestapp.entidades;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class entidadEgreso
{
    private int    identificador_categoria;
    private String fecha;
    private String hora;
    private String local;
    private String metodo_pago;
    private int    monto;
    private String descripcion;
    private int    factura;
    private String tarjeta;
    private String estado;
    private int    id_mes;
    public  int    id;

    public entidadEgreso (int identificador_categoria, String fecha, String hora,
                          String local, String metodo_pago, int monto,
                          String descripcion, int factura, String tarjeta, String estado, int id_mes)
    {
        this.identificador_categoria = identificador_categoria;
        this.fecha                   = fecha;
        this.hora                    = hora;
        this.local                   = local;
        this.metodo_pago             = metodo_pago;
        this.monto                   = monto;
        this.descripcion             = descripcion;
        this.factura                 = factura;
        this.tarjeta                 = tarjeta;
        this.estado                  = estado;
        this.id_mes                  = id_mes;

    }

    public int getId_mes() {
        return id_mes;
    }

    public void setId_mes(int id_mes) {
        this.id_mes = id_mes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdentificador_categoria() {
        return identificador_categoria;
    }

    public void setIdentificador_categoria(int identificador_categoria) {
        this.identificador_categoria = identificador_categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
}