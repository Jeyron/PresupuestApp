package com.example.jeiro.presupuestapp.Datos;

import com.example.jeiro.presupuestapp.Navegacion;
import com.example.jeiro.presupuestapp.entidades.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
            ContentValues  c  = new ContentValues();
            c.put("Tipo", categoria.getTipo());
            c.put("Descripcion", categoria.getDescripcion());
            c.put("Egreso",categoria.getEgreso());
            c.put("Monto", categoria.getMonto());
            c.put("Mes", categoria.getId_mes());
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.insert(tablas_base_datos.tablaCategoria.TABLE_NAME, null,c);
                db.close();
            }
            else
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.update(tablas_base_datos.tablaCategoria.TABLE_NAME, c, "_id=" + categoria.id, null);
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
            c.put("Estado", egreso.getEstado());
            c.put("IdMes",egreso.getId_mes());
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.insert(tablas_base_datos.tablaEgreso.TABLE_NAME, null,c);
                db.close();
            }
            else
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                ArrayList<entidadEgreso> a = obtener_Egreso(context);
                db.update(tablas_base_datos.tablaEgreso.TABLE_NAME, c, "_id=" + egreso.id, null);
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
            ContentValues  c  = new ContentValues();
            c.put("Entidad", tarjeta.getEntidad());
            c.put("Tipo", tarjeta.getTipo());
            c.put("Numero", tarjeta.getNumero());
            c.put("Marca", tarjeta.getMarca());
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
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
            ContentValues  c  = new ContentValues();
            c.put("Nombre", mes.getNombre());
            c.put("Estado", mes.getEstado());
            c.put("Ingreso", mes.getIngreso());
            if(fork)
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.insert(tablas_base_datos.tablaMes.TABLE_NAME, null,c);
                db.close();
            }
            else
            {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.update(tablas_base_datos.tablaMes.TABLE_NAME, c, "_id=" + Navegacion.id_mes, null);
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
            SQLiteDatabase db = helper.getWritableDatabase();
            String whereClause = "_id=?";
            String[] whereArgs = new String[] { String.valueOf(categoria.id + "") };
            db.delete(tablas_base_datos.tablaCategoria.TABLE_NAME, whereClause, whereArgs);
        }
        catch (Exception exc)
        {
            return false;
        }
        return true;
    }

    public boolean eliminar_egreso (entidadEgreso egreso, Context context)
    {
        base_datos helper = new base_datos(context);
        try
        {
            SQLiteDatabase db = helper.getWritableDatabase();
            String whereClause = "_id=?";
            String[] whereArgs = new String[] { String.valueOf(egreso.id + "") };
            db.delete(tablas_base_datos.tablaEgreso.TABLE_NAME, whereClause, whereArgs);
        }
        catch (Exception exc)
        {
            return false;
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

    public ArrayList<entidadCategoria> obtener_categoria (Context context)
    {
        ArrayList datos = new ArrayList<entidadCategoria>();
        base_datos helper = new base_datos(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        try
        {
            Cursor c = db.query(
                    tablas_base_datos.tablaCategoria.TABLE_NAME, // The table to query
                    null, // The columns to return
                    null, // The columns for the WHERE clause
                    null, // The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null // The sort order
            );
            if(c.moveToFirst())
                do
                {
                    entidadCategoria a = new entidadCategoria(c.getString(1), c.getString(2), c.getString(3), c.getInt(4), c.getInt(5));
                    a.id = c.getInt(0);
                    datos.add(a);
                } while(c.moveToNext());
            db.close();
        }
        catch (Exception exc)
        {
            return new ArrayList<entidadCategoria>();
        }
        return datos;
    }

    public ArrayList<entidadMes> obtener_Mes (Context context)
    {
        ArrayList datos = new ArrayList<entidadMes>();
        base_datos helper = new base_datos(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        try
        {
            Cursor c = db.query(
                    tablas_base_datos.tablaMes.TABLE_NAME, // The table to query
                    null, // The columns to return
                    null, // The columns for the WHERE clause
                    null, // The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null // The sort order
            );
            if(c.moveToFirst())
                do
                {
                    entidadMes a = new entidadMes(c.getString(1), c.getInt(2), c.getString(3));
                    a.id = c.getInt(0);
                    datos.add(a);
                } while(c.moveToNext());
            db.close();
        }
        catch (Exception exc)
        {
            return new ArrayList<entidadMes>();
        }
        return datos;
    }

    public entidadMes obtener_id_mes_habilitado (Context context)
    {
        ArrayList<entidadMes> a;
        try
        {
            a = obtener_Mes(context);
            Navegacion.id_mes = a.get(a.size() - 1).id;
        }
        catch (Exception exc)
        {
            return null;
        }
        return a.get(a.size() - 1);
    }

    public ArrayList<entidadEgreso> obtener_Egreso (Context context)
    {
        ArrayList datos = new ArrayList<entidadEgreso>();
        base_datos helper = new base_datos(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        try
        {
            Cursor c = db.query(
                    tablas_base_datos.tablaEgreso.TABLE_NAME, // The table to query
                    null, // The columns to return
                    null, // The columns for the WHERE clause
                    null, // The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null // The sort order
            );
            if(c.moveToFirst())
                do
                {
                    entidadEgreso a = new entidadEgreso(c.getInt(1), c.getString(2), c.getString(3),
                            c.getString(4), c.getString(5), c.getInt(6), c.getString(7), c.getInt(8), c.getString(9),
                            c.getString(10), c.getInt(11));
                    a.id = c.getInt(0);
                    datos.add(a);
                } while(c.moveToNext());
            db.close();
        }
        catch (Exception exc)
        {
            return new ArrayList<entidadEgreso>();
        }
        return datos;
    }
}
