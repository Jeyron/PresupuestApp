package com.example.jeiro.presupuestapp.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public class base_datos extends SQLiteOpenHelper
{
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_CATEGORIA =
            "CREATE TABLE " + 
                    tablas_base_datos.tablaCategoria.TABLE_NAME + " (" +
                    tablas_base_datos.tablaCategoria._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    tablas_base_datos.tablaCategoria.COLUMN_NAME_TIPO + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaCategoria.COLUMN_NAME_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaCategoria.COLUMN_NAME_EGRESO + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaCategoria.COLUMN_NAME_MONTO + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaCategoria.COLUMN_NAME_MES + NUMBER_TYPE + " )";

    private static final String SQL_CREATE_MES =
            "CREATE TABLE " +
                    tablas_base_datos.tablaMes.TABLE_NAME + " (" +
                    tablas_base_datos.tablaMes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    tablas_base_datos.tablaMes.COLUMN_NAME_IDENTIFICADOR + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaMes.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaMes.COLUMN_NAME_INGRESO + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaMes.COLUMN_NAME_ESTADO + TEXT_TYPE + " )";

    private static final String SQL_CREATE_EGRESO =
            "CREATE TABLE " +
                    tablas_base_datos.tablaEgreso.TABLE_NAME + " (" +
                    tablas_base_datos.tablaEgreso._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_IDENTIFICADOR_CATEGORIA + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_FECHA + DATE_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_HORA + DATE_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_LOCAL + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_METODO_PAGO + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_MONTO + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_FACTURA + NUMBER_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaEgreso.COLUMN_NAME_TARJETA + NUMBER_TYPE + " )";

    private static final String SQL_CREATE_TARJETA =
            "CREATE TABLE " +
                    tablas_base_datos.tablaTarjeta.TABLE_NAME + " (" +
                    tablas_base_datos.tablaTarjeta.COLUMN_NAME_MARCA + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaTarjeta.COLUMN_NAME_ENTIDAD + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaTarjeta.COLUMN_NAME_TIPO + TEXT_TYPE + COMMA_SEP +
                    tablas_base_datos.tablaTarjeta.COLUMN_NAME_NUMERO + TEXT_TYPE + " PRIMARY KEY )";

    private static final String SQL_DELETE_CATEGORIA =
            "DROP TABLE IF EXISTS " + tablas_base_datos.tablaCategoria.TABLE_NAME;

    private static final String SQL_DELETE_MES =
            "DROP TABLE IF EXISTS " + tablas_base_datos.tablaMes.TABLE_NAME;

    private static final String SQL_DELETE_EGRESO =
            "DROP TABLE IF EXISTS " + tablas_base_datos.tablaEgreso.TABLE_NAME;

    private static final String SQL_DELETE_TARJETA =
            "DROP TABLE IF EXISTS " + tablas_base_datos.tablaTarjeta.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PresupuestApp.db";
    public base_datos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_EGRESO);
        db.execSQL(SQL_CREATE_MES);
        db.execSQL(SQL_CREATE_TARJETA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CATEGORIA);
        db.execSQL(SQL_DELETE_MES);
        db.execSQL(SQL_DELETE_EGRESO);
        db.execSQL(SQL_DELETE_TARJETA);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}