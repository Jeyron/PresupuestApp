package com.example.jeiro.presupuestapp.Datos;

import com.example.jeiro.presupuestapp.entidades.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class acceso_datos
{
    public acceso_datos() {}

    public boolean agregar_modificar_categoria (entidadCategoria categoria, boolean fork, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues  c  = new ContentValues();
                c.put("Tipo", categoria.getTipo());
                c.put("Descripcion", categoria.getDescripcion());
                c.put("Egreso",categoria.getEgreso());
                c.put("Monto", categoria.getMonto());
                c.put("Mes", categoria.getId_mes());
                db.insert(tablas_base_datos.tablaCategoria.TABLE_NAME, null,c);
                db.close();
            }
                
        }
        catch (Exception exc)
        {
            return false;
        }
        return true;
    }

    public boolean agregar_modificar_egreso (entidadEgreso egreso, boolean fork, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues  c  = new ContentValues();
                c.put("IdentificadorCategoria", egreso.getIdentificador_categoria());
                c.put("Fecha",egreso.getFecha());
                c.put("Hora",egreso.getHora());
                c.put("Local", egreso.getLocal());
                c.put("MetodoPago", egreso.getMetodo_pago());
                c.put("Monto", egreso.getMonto());
                c.put("Descripcion", egreso.getDescripcion());
                c.put("Factura", egreso.getFactura());
                c.put("Tarjeta", egreso.getTarjeta());
                db.insert(tablas_base_datos.tablaEgreso.TABLE_NAME, null,c);
                db.close();
            }

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean agregar_modificar_tarjeta (entidadTarjeta tarjeta, boolean fork, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues  c  = new ContentValues();
                c.put("Entidad", tarjeta.getEntidad());
                c.put("Tipo", tarjeta.getTipo());
                c.put("Numero", tarjeta.getNumero());
                c.put("Marca", tarjeta.getMarca());
                db.insert(tablas_base_datos.tablaTarjeta.TABLE_NAME, null,c);
                db.close();
            }

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean agregar_modificar_mes (entidadMes mes, boolean fork, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues  c  = new ContentValues();
                c.put("Nombre", mes.getNombre());
                c.put("Estado", mes.getEstado());
                c.put("Ingreso", mes.getIngreso());
                db.insert(tablas_base_datos.tablaMes.TABLE_NAME, null,c);
                db.close();
            }

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean eliminar_categoria (entidadCategoria categoria, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean eliminar_egreso (entidadEgreso egreso, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean eliminar_tarjeta (entidadTarjeta tarjeta, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {

        }
        catch (Exception exc)
        {

        }
        return true;
    }

    public boolean eliminar_mes (entidadMes mes, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {

        }
        catch (Exception exc)
        {

        }
        return true;
    }
}
