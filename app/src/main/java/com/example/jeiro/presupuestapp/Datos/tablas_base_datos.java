package com.example.jeiro.presupuestapp.Datos;

import android.provider.BaseColumns;

/**
 * Created by rcrodriguez on 12/9/2017.
 */

public final class tablas_base_datos {
    private tablas_base_datos() {}

    public static class tablaCategoria implements BaseColumns {
        public static final String TABLE_NAME                = "Categoria";
        public static final String COLUMN_NAME_TIPO          = "Tipo";
        public static final String COLUMN_NAME_DESCRIPCION   = "Descripcion";
        public static final String COLUMN_NAME_EGRESO       = "Egreso";
        public static final String COLUMN_NAME_MONTO         = "Monto";
        public static final String COLUMN_NAME_MES           = "Mes";
    }

    public static class tablaMes implements BaseColumns {
        public static final String TABLE_NAME                = "Mes";
        public static final String COLUMN_NAME_NOMBRE        = "Nombre";
        public static final String COLUMN_NAME_ESTADO        = "Estado";
        public static final String COLUMN_NAME_INGRESO       = "Ingreso";
    }

    public static class tablaTarjeta implements BaseColumns {
        public static final String TABLE_NAME                = "Tarjeta";
        public static final String COLUMN_NAME_ENTIDAD       = "Entidad";
        public static final String COLUMN_NAME_TIPO          = "Tipo";
        public static final String COLUMN_NAME_NUMERO        = "Numero";
        public static final String COLUMN_NAME_MARCA         = "Marca";
    }

    public static class tablaEgreso implements BaseColumns {
        public static final String TABLE_NAME                           = "Egreso";
        public static final String COLUMN_NAME_IDENTIFICADOR_CATEGORIA  = "IdentificadorCategoria";
        public static final String COLUMN_NAME_FECHA                    = "Fecha";
        public static final String COLUMN_NAME_HORA                     = "Hora";
        public static final String COLUMN_NAME_LOCAL                    = "Local";
        public static final String COLUMN_NAME_METODO_PAGO              = "MetodoPago";
        public static final String COLUMN_NAME_MONTO                    = "Monto";
        public static final String COLUMN_NAME_DESCRIPCION              = "Descripcion";
        public static final String COLUMN_NAME_FACTURA                  = "Factura";
        public static final String COLUMN_NAME_TARJETA                  = "Tarjeta";
    }
}
