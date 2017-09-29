package com.example.jeiro.presupuestapp;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeiro.presupuestapp.Datos.acceso_datos;
import com.example.jeiro.presupuestapp.entidades.entidadCategoria;
import com.example.jeiro.presupuestapp.entidades.entidadTarjeta;

import java.util.ArrayList;

import static com.example.jeiro.presupuestapp.Navegacion.ad;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro_tarjetas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro_tarjetas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro_tarjetas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View viewroot;
    private int total;
    private ArrayList<entidadTarjeta> datos;

    private OnFragmentInteractionListener mListener;

    public Registro_tarjetas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro_tarjetas.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro_tarjetas newInstance(String param1, String param2) {
        Registro_tarjetas fragment = new Registro_tarjetas();
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

    private void limpiar_tabla()
    {
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
        TableRow tb = (TableRow) getView().findViewById(R.id.cabeceraListaTarjetas);
        table.removeAllViews();

        table.addView(tb);
        table.requestLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void completar_tabla()
    {
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        TextView tr1 = (TextView) viewroot.findViewById(R.id.Marca_tarjeta);
        TextView tr2 = (TextView) getActivity().findViewById(R.id.Entidad_financiera_tarjeta);
        TextView tr3 = (TextView) getActivity().findViewById(R.id.Tipo_tarjeta);
        TextView tr4 = (TextView) getActivity().findViewById(R.id.Numero_tarjeta);

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

            t1.setText(datos.get(i).getMarca());
            t1.setEnabled(false);
            t1.setTextSize(17);
            t1.setClickable(true);
            t3.setKeyListener(null);
            t1.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t1.setGravity(Gravity.CENTER);
            t1.setLayoutParams(params);

            t2.setText(datos.get(i).getEntidad());
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

            t4.setText(datos.get(i).getNumero());
            t4.setEnabled(false);
            t4.setClickable(true);
            t4.setKeyListener(null);
            t4.setTextSize(17);
            t4.setBackground( getResources().getDrawable(R.drawable.borde_celda));
            t4.setGravity(Gravity.CENTER);
            t4.setLayoutParams(params);

            tr.addView(t1);
            tr.addView(t2);
            tr.addView(t3);
            tr.addView(t4);

            t4.setOnClickListener(new View.OnClickListener() {
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
                    if (t.equals("Crédito"))
                        t = "Débito";
                    else
                        t = "Crédito";
                    ef.setText(t);
                }
            });

            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    EditText ef = (EditText) v;
                    String t = ef.getText().toString();
                    if (t.equals("Visa"))
                        t = "MasterCard";
                    else if (t.equals("MasterCard"))
                        t = "Citi";
                    else if (t.equals("Citi"))
                        t = "Santander";
                    else if (t.equals("Santander"))
                        t = "HSBC";
                    else if (t.equals("HSBC"))
                        t = "Bac Credomatic";
                    else if (t.equals("Bac Credomatic"))
                        t = "American Express";
                    else if (t.equals("American Express"))
                        t = "Scotiabank";
                    else if (t.equals("Scotiabank"))
                        t = "otros";
                    else
                        t = "Visa";
                    ef.setText(t);
                }
            });
            table.addView(tr);
            table.requestLayout();
        }
    }

    private boolean numero_existe (String numero)
    {
        for (int i = 0; i < datos.size();i++)
        {
            if(datos.get(i).getNumero().equals(numero)) return true;
        }
        return false;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewroot = inflater.inflate(R.layout.fragment_registro_tarjetas, container, false);
        try
        {
            datos = ad.obtener_Tarjetas(getActivity());
            completar_tabla();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button b = (Button)viewroot.findViewById(R.id.btn_agregar_tarjeta);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    entidadTarjeta ec = new entidadTarjeta(
                            getResources().getStringArray(R.array.marca_tarjeta)[((Spinner) getView().findViewById(R.id.txt_marca_tarjeta)).getSelectedItemPosition()],
                            ((EditText) viewroot.findViewById(R.id.txt_entidad_financiera)).getText().toString(),
                            getResources().getStringArray(R.array.tipo_Tarjeta)[((Spinner) getView().findViewById(R.id.spinner_tipo_tarjeta)).getSelectedItemPosition()],
                            ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_1)).getText() + "-" + ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_2)).getText() + "-" + ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_3)).getText() + "-" + ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_4)).getText());
                    if (ec.getEntidad().length() == 0)// == 0 || ec.getMarca().length() == 0 || ec.getTipo().length() == 0 || ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_1)).getText().toString().length() == 0|| ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_2)).getText().toString().length() == 0|| ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_3)).getText().toString().length() == 0|| ((EditText) viewroot.findViewById(R.id.txt_numero_tarjeta_4)).getText().toString().length() == 0)
                        Toast.makeText(getActivity(), "Error, espacios vacíos", Toast.LENGTH_SHORT).show();
                    else
                    {
                        if (!numero_existe(ec.getNumero()) && ad.agregar_modificar_tarjeta(ec, true, getActivity()))
                        {
                            limpiar_tabla();
                            datos = ad.obtener_Tarjetas(getActivity());
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

        Button bc = (Button)viewroot.findViewById(R.id.btn_modificar_tarjeta);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
                boolean temp = false;
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
                    TableRow row = (TableRow) table.getChildAt(i);
                    //This will iterate through the table row.

                    for(int j = 0; j < row.getChildCount() - 1; j++)
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
                            String Tipo = (((EditText)row.getChildAt(2)).getText().toString().equals(""))? datos.get(i-1).getTipo() : ((EditText)row.getChildAt(2)).getText().toString();
                            String Entidad = (((EditText)row.getChildAt(1)).getText().toString().equals(""))? datos.get(i-1).getEntidad() : ((EditText)row.getChildAt(1)).getText().toString();
                            String Marca = (((EditText)row.getChildAt(3)).getText().toString().equals(""))? datos.get(i-1).getMarca() : ((EditText)row.getChildAt(3)).getText().toString();
                            String Numero = (((EditText)row.getChildAt(0)).getText().toString().equals(""))? datos.get(i-1).getNumero() : ((EditText)row.getChildAt(0)).getText().toString();
                            entidadTarjeta a = new entidadTarjeta(
                                    Marca,
                                    Entidad,
                                    Tipo,
                                    Numero);
                            if((Numero.equals(datos.get(i-1).getNumero()) && numero_existe(Numero)) || (!Numero.equals(datos.get(i-1).getNumero()) && !numero_existe(Numero)))
                                ad.agregar_modificar_tarjeta(a,false,getActivity());
                            else
                                Toast.makeText(getActivity(),"Error, tarjeta existente", Toast.LENGTH_SHORT).show();
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
                        datos = ad.obtener_Tarjetas(getActivity());
                        completar_tabla();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button ba = (Button)viewroot.findViewById(R.id.btn_eliminar_tarjeta);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
                boolean temp = false;
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    //Remember that .getChildAt() method returns a View, so you would have to cast a specific control.
                    TableRow row = (TableRow) table.getChildAt(i);
                    //This will iterate through the table row.
                    EditText ed = (EditText) row.getChildAt(3);
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
                            entidadTarjeta a = new entidadTarjeta(
                                    "",
                                    "",
                                    "",
                                    datos.get(i - 1).getNumero());
                            if (ad.eliminar_tarjeta(a, getActivity()))
                                Toast.makeText(getActivity(), "Éxito, datos eliminados", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getActivity(), "Error, algo ocurrió", Toast.LENGTH_SHORT).show();
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
                        datos = ad.obtener_Tarjetas(getActivity());
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

}
