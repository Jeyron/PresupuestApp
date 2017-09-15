package com.example.jeiro.presupuestapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeiro.presupuestapp.Datos.acceso_datos;
import com.example.jeiro.presupuestapp.entidades.entidadCategoria;
import java.util.ArrayList;
import static com.example.jeiro.presupuestapp.Navegacion.ad;
import static com.example.jeiro.presupuestapp.Navegacion.id_mes;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Categoria_egreso.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Categoria_egreso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Categoria_egreso extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<entidadCategoria> datos = new ArrayList<>();
    private View viewroot;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinner;

    private OnFragmentInteractionListener mListener;

    public Categoria_egreso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Categoria_egreso.
     */
    // TODO: Rename and change types and number of parameters
    public static Categoria_egreso newInstance(String param1, String param2) {
        Categoria_egreso fragment = new Categoria_egreso();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void cargar_identificardor ()
    {
        acceso_datos ad = new acceso_datos();
        ArrayList<entidadCategoria> datos = ad.obtener_categoria(getActivity());
        EditText ed = (EditText) getView().findViewById(R.id.txt_identificador);
        if (datos.size() == 0)
            ed.setText("1");
        else
            ed.setText(datos.get(datos.size()-1).id + 1 + "");
    }


    private void limpiar_tabla()
    {
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresosCategoria);
        TableRow tb = (TableRow) getView().findViewById(R.id.cabeceraListaCategoriaEgresos);
        table.removeAllViews();

        table.addView(tb);
        table.requestLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void completar_tabla()
    {
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresosCategoria);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        TextView tr1 = (TextView) viewroot.findViewById(R.id.columnaIdentificador);
        TextView tr2 = (TextView) getActivity().findViewById(R.id.columnaEgreso);
        TextView tr3 = (TextView) getActivity().findViewById(R.id.columnaTipo);
        TextView tr4 = (TextView) getActivity().findViewById(R.id.columnaDescripcion);

        tr1.setLayoutParams(params);
        tr2.setLayoutParams(params);
        tr3.setLayoutParams(params);
        tr4.setLayoutParams(params);
        int largo = datos.size();
        for (int i = 0; i < largo; i++)
        {
            TableRow tr = new TableRow(getActivity());
            EditText t1 = new EditText(getActivity());
            EditText t2 = new EditText(getActivity());
            EditText t3 = new EditText(getActivity());
            EditText t4 = new EditText(getActivity());

            t1.setText(datos.get(i).id + "");
            t1.setEnabled(false);
            t1.setClickable(true);
            t1.setKeyListener(null);
            t1.setTextSize(17);
            t1.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t1.setGravity(Gravity.CENTER);
            t1.setLayoutParams(params);

            t2.setText(datos.get(i).getEgreso());
            t2.setEnabled(false);
            t2.setTextSize(17);
            t2.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t2.setGravity(Gravity.CENTER);
            t2.setLayoutParams(params);

            t3.setText(datos.get(i).getTipo());
            t3.setEnabled(false);
            t3.setClickable(true);
            t3.setKeyListener(null);
            t3.setTextSize(17);
            t3.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t3.setGravity(Gravity.CENTER);
            t3.setLayoutParams(params);

            t4.setText(datos.get(i).getDescripcion());
            t4.setEnabled(false);
            t4.setTextSize(17);
            t4.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t4.setGravity(Gravity.CENTER);
            t4.setLayoutParams(params);

            tr.addView(t1);
            tr.addView(t2);
            tr.addView(t3);
            tr.addView(t4);

            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    EditText ef = (EditText) v;
                    Toast.makeText(getActivity(), ef.getText(), Toast.LENGTH_SHORT).show();
                    if (ef.getCurrentTextColor() == Color.WHITE)
                        ef.setTextColor(Color.BLACK);
                    else
                        ef.setTextColor(Color.WHITE);
                }
            });

            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    EditText ef = (EditText) v;
                    String t = ef.getText().toString();
                    if (t.equals("Ahorro"))
                        t = "Gasto";
                    else
                        t = "Ahorro";
                    ef.setText(t);
                }
            });
            table.addView(tr);
            table.requestLayout();
        }
    }

    private boolean categoria_existe (String descripcion)
    {
        for (int i = 0; i < datos.size();i++)
        {
            if(datos.get(i).getDescripcion().equals(descripcion)) return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewroot = inflater.inflate(R.layout.fragment_categoria_egreso, container, false);
        try
        {
            datos = ad.obtener_categoria(getActivity());
            cargar_identificardor();
            completar_tabla();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button b = (Button)viewroot.findViewById(R.id.btn_agregar_categoria);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    entidadCategoria ec = new entidadCategoria(
                            getResources().getStringArray(R.array.categoria)[((Spinner) getView().findViewById(R.id.spinner_tipo_categoria)).getSelectedItemPosition()],
                            ((EditText) viewroot.findViewById(R.id.txt_descripcion_categoria_egreso)).getText().toString(),
                            ((EditText) viewroot.findViewById(R.id.txt_egreso_nombre)).getText().toString(),
                            0, 0);

                    if (ec.getEgreso().length() == 0 || ec.getDescripcion().length() == 0)
                        Toast.makeText(getActivity(), "Error, espacios vacíos", Toast.LENGTH_SHORT).show();
                    else
                    {
                        if (!categoria_existe(ec.getDescripcion()) && ad.agregar_modificar_categoria(ec, true, getActivity()))
                        {
                            limpiar_tabla();
                            datos = ad.obtener_categoria(getActivity());
                            cargar_identificardor();
                            completar_tabla();
                            Toast.makeText(getActivity(), "Éxito, guardado", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getActivity(), "Error, no ha logrado guardar", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bc = (Button)viewroot.findViewById(R.id.btn_modificar_categoria);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresosCategoria);
                boolean temp = false;
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
                    TableRow row = (TableRow) table.getChildAt(i);
                    //This will iterate through the table row.

                    for(int j = 1; j < row.getChildCount(); j++)
                    {
                        EditText ed = (EditText) row.getChildAt(j);
                        if (ed.isEnabled())
                        {
                            ed.setEnabled(false);
                            temp = true;
                        }
                        else
                            ed.setEnabled(true);
                        //Do what you need to do.
                    }

                    if(temp)
                    {
                        try
                        {
                            String Tipo = (((EditText)row.getChildAt(2)).getText().toString().equals(""))? datos.get(i - 1).getTipo() : ((EditText)row.getChildAt(2)).getText().toString();
                            String Descripcion = (((EditText)row.getChildAt(3)).getText().toString().equals(""))? datos.get(i - 1).getDescripcion() : ((EditText)row.getChildAt(3)).getText().toString();
                            String Egreso = (((EditText)row.getChildAt(1)).getText().toString().equals(""))? datos.get(i - 1).getEgreso() : ((EditText)row.getChildAt(1)).getText().toString();
                            entidadCategoria a = new entidadCategoria(
                                    Tipo,
                                    Descripcion,
                                    Egreso, 0, 0);
                            a.id = Integer.parseInt(((EditText)row.getChildAt(0)).getText().toString());
                            if((Descripcion.equals(datos.get(i-1).getDescripcion()) && categoria_existe(Descripcion)) || (!Descripcion.equals(datos.get(i - 1).getDescripcion()) && !categoria_existe(Descripcion)))
                                ad.agregar_modificar_categoria(a,false,getActivity());
                            else
                                Toast.makeText(getActivity(),"Error, categoria existente", Toast.LENGTH_SHORT).show();


                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                if(temp)
                {
                    try
                    {
                        limpiar_tabla();
                        datos = ad.obtener_categoria(getActivity());
                        completar_tabla();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button ba = (Button)viewroot.findViewById(R.id.btn_eliminar_categoria);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresosCategoria);
                boolean temp = false;
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
                    TableRow row = (TableRow) table.getChildAt(i);
                    //This will iterate through the table row.
                    EditText ed = (EditText) row.getChildAt(0);
                    if (ed.isEnabled())
                    {
                        ed.setEnabled(false);
                        temp = true;
                    }
                    else
                        ed.setEnabled(true);

                    if(temp && ed.getCurrentTextColor() == Color.WHITE)
                    {
                        try
                        {
                            if(datos.get(i - 1).getMonto() != 0)
                            {
                                entidadCategoria a = new entidadCategoria(
                                        "",
                                        "",
                                        "", 0, 0);
                                a.id = datos.get(i - 1).id;
                                if (ad.eliminar_categoria(a, getActivity()))
                                    Toast.makeText(getActivity(), "Éxito, datos eliminados", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getActivity(), "Error, algo ocurrió", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getActivity(), "Error, Ya está asociada", Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                if(temp)
                {
                    try
                    {
                        limpiar_tabla();
                        datos = ad.obtener_categoria(getActivity());
                        completar_tabla();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return viewroot;
    }

    // TODO: Rename method, update argument and hook method into UI event
            public void onButtonPressed(Uri uri) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(uri);
                }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    /**
     * Añade la cabecera a la tabla
     * @param recursocabecera Recurso (array) donde se encuentra la cabecera de la tabla
     */
}
