package com.example.jeiro.presupuestapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeiro.presupuestapp.entidades.entidadCategoria;
import com.example.jeiro.presupuestapp.entidades.entidadEgreso;
import com.example.jeiro.presupuestapp.entidades.entidadMes;

import java.util.ArrayList;

import static com.example.jeiro.presupuestapp.Navegacion.ad;
import static com.example.jeiro.presupuestapp.Navegacion.id_mes;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registrar_extra_ingreso.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registrar_extra_ingreso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registrar_extra_ingreso extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View viewroot;
    private int total;
    private ArrayList<entidadCategoria> datos;

    private OnFragmentInteractionListener mListener;

    public Registrar_extra_ingreso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrar_extra_ingreso.
     */
    // TODO: Rename and change types and number of parameters
    public static Registrar_extra_ingreso newInstance(String param1, String param2) {
        Registrar_extra_ingreso fragment = new Registrar_extra_ingreso();
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

    private void cargar_spinner_categoria ()
    {
        Spinner sp = (Spinner) viewroot.findViewById(R.id.spinner_extra_categoria_egresos);
        String valores[];
        int cont = 0;
        ArrayList<entidadCategoria> temp = new ArrayList<>();
        for (int i = 0; i < datos.size(); i++) if (datos.get(i).getMonto() == 0){ cont++;temp.add(datos.get(i));}
        if(temp.size() == 0)
            valores = new String[]{"No hay categorias"};
        else
        {
            valores = new String[cont];
        }
        for(int i = 0; i < cont; i++)
            valores[i] = temp.get(i).getEgreso();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, valores);
        sp.setAdapter(adapter);
    }

    private void limpiar_tabla()
    {
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
        TableRow tb = (TableRow) getView().findViewById(R.id.cabeceraListaExtra_Ingresos);
        table.removeAllViews();

        table.addView(tb);
        table.requestLayout();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void completar_tabla()
    {
        total = 0;
        TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        TextView tr1 = (TextView) viewroot.findViewById(R.id.Egreso__extra_ingreso);
        TextView tr2 = (TextView) getActivity().findViewById(R.id.Monto_extra_ingreso);

        tr1.setLayoutParams(params);
        tr2.setLayoutParams(params);
        int largo = datos.size();
        for (int i = 0; i < largo; i++)
        {
            if(datos.get(i).getMonto() != 0) {
                TableRow tr = new TableRow(getActivity());
                EditText t1 = new EditText(getActivity());
                EditText t2 = new EditText(getActivity());

                t1.setText(datos.get(i).getEgreso());
                t1.setEnabled(false);
                t1.setClickable(true);
                t1.setKeyListener(null);
                t1.setTextSize(17);
                t1.setBackground(getResources().getDrawable(R.drawable.borde_celda));
                t1.setGravity(Gravity.CENTER);
                t1.setLayoutParams(params);

                t2.setText(datos.get(i).getMonto()+"");
                t2.setEnabled(false);
                t2.setInputType(InputType.TYPE_CLASS_NUMBER);
                t2.setTextSize(17);
                t2.setBackground(getResources().getDrawable(R.drawable.borde_celda));
                t2.setGravity(Gravity.CENTER);
                t2.setLayoutParams(params);

                tr.addView(t1);
                tr.addView(t2);

                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ef = (EditText) v;
                        Toast.makeText(getActivity(), ef.getText(), Toast.LENGTH_SHORT).show();
                        if (ef.getCurrentTextColor() == Color.WHITE)
                            ef.setTextColor(Color.BLACK);
                        else
                            ef.setTextColor(Color.WHITE);
                    }
                });
                table.addView(tr);
                table.requestLayout();

                total += datos.get(i).getMonto();
            }
        }
    }

    private entidadCategoria buscar_categoria (String nombre)
    {
        entidadCategoria ec;
        for (int i = 0; i < datos.size(); i++)
            if(datos.get(i).getEgreso().equals(nombre))return datos.get(i);
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewroot = inflater.inflate(R.layout.fragment_registrar_extra_ingreso, container, false);

        try
        {
            datos = ad.obtener_categoria(getActivity());
            entidadMes a = ad.obtener_id_mes_habilitado(getActivity());
            EditText e = (EditText) viewroot.findViewById(R.id.txt_mostrar_estado_actual_ingreso);
            e.setText(a.getIngreso() + "");
            cargar_spinner_categoria();
            completar_tabla();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button b = (Button)viewroot.findViewById(R.id.btn_extra_agregar_categoria);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try
                {

                    limpiar_tabla();
                    datos  = ad.obtener_categoria(getActivity());
                    completar_tabla();
                    String sp = ((Spinner) viewroot.findViewById(R.id.spinner_extra_categoria_egresos)).getSelectedItem().toString();
                    //Toast.makeText(getActivity(), sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    if(sp.equals("No hay categorias"))
                        Toast.makeText(getActivity(),"Error, debe agregar categorias", Toast.LENGTH_SHORT).show();
                    else
                    {
                        entidadMes mes_actual = ad.obtener_id_mes_habilitado(getActivity());
                        String text = ((EditText) viewroot.findViewById(R.id.txt_extra_monto_categoria_egresos)).getText().toString();
                        //Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                        entidadCategoria ec = buscar_categoria(sp.toString());

                        if (mes_actual.getIngreso() == 0)
                            Toast.makeText(getActivity(), "Error, sin ingreso", Toast.LENGTH_SHORT).show();
                        else if (text.length() == 0 || text.equals("0") || ec == null)
                            Toast.makeText(getActivity(), "Error, espacios vacíos", Toast.LENGTH_SHORT).show();
                        else
                        {
                            ec.setMonto(Integer.parseInt(text));
                            if (ec.getMonto() + total > mes_actual.getIngreso()) {
                                Toast.makeText(getActivity(), "Error, No tiene suficiente credito", Toast.LENGTH_SHORT).show();
                                ec.setMonto(0);
                            }
                            else if (ad.agregar_modificar_categoria(ec, false, getActivity()))
                            {
                                limpiar_tabla();
                                ec.setMonto(Integer.parseInt(text));
                                datos = ad.obtener_categoria(getActivity());
                                cargar_spinner_categoria();
                                completar_tabla();
                                Toast.makeText(getActivity(), "Éxito, monto guardado", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "Error, no ha logrado guardar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    datos = ad.obtener_categoria(getActivity());
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bc = (Button)viewroot.findViewById(R.id.btn_extra_modificar_categoria);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout table = (TableLayout) getView().findViewById(R.id.listaCategoriaEgresos);
                boolean temp = false;
                int total_Actual = 0;
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    TableRow row = (TableRow) table.getChildAt(i);
                    EditText ed = (EditText) row.getChildAt(1);
                    total += Integer.parseInt(ed.getText().toString());
                }
                for(int i = 1; i < table.getChildCount(); i++)
                {
                    TableRow row = (TableRow) table.getChildAt(i);
                    EditText ed = (EditText) row.getChildAt(1);
                    if (ed.isEnabled())
                    {
                        ed.setEnabled(false);
                        temp = true;
                    }
                    else
                        ed.setEnabled(true);

                    if(temp)
                    {
                        try
                        {
                            if(total_Actual <= total) {
                                String Egreso = ((EditText) row.getChildAt(0)).getText().toString();
                                int Monto = (((EditText) row.getChildAt(1)).getText().toString().equals("")) ? datos.get(i-1).getMonto() : Integer.parseInt(((EditText) row.getChildAt(1)).getText().toString());
                                entidadCategoria a = buscar_categoria(Egreso);
                                a.setMonto(Monto);
                                ad.agregar_modificar_categoria(a, false, getActivity());
                            }
                            else
                                Toast.makeText(getActivity(),"Error, ha eccedido el presupuesto", Toast.LENGTH_SHORT).show();
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
                        cargar_spinner_categoria();
                        completar_tabla();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button ba = (Button)viewroot.findViewById(R.id.btn_extra_eliminar_categoria);
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
                            String Egreso = ((EditText)row.getChildAt(0)).getText().toString();
                            entidadCategoria a = buscar_categoria(Egreso);
                            a.setMonto(0);
                            if (ad.agregar_modificar_categoria(a,false,getActivity()))
                            {
                                Toast.makeText(getActivity(), "Éxito, datos eliminados", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getActivity(),"Error, algo ocurrió", Toast.LENGTH_SHORT).show();
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
                        cargar_spinner_categoria();
                        completar_tabla();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button bi = (Button) viewroot.findViewById(R.id.btn_incluir_ingreso_adicional);
        bi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText e = (EditText) viewroot.findViewById(R.id.txt_monto_extra_ingreso_mensual);
                if (e.getText().length() != 0 && !e.getText().equals("0"))
                {
                    try {
                        if (Navegacion.id_mes != 0 && e.isEnabled()) {
                            entidadMes a = ad.obtener_id_mes_habilitado(getActivity());
                            a.setIngreso(a.getIngreso() + Integer.parseInt(e.getText().toString()));
                            ad.agregar_modificar_mes(a, false, getActivity());
                            e.setText("");
                            a = ad.obtener_id_mes_habilitado(getActivity());
                            EditText ef = (EditText) viewroot.findViewById(R.id.txt_mostrar_estado_actual_ingreso);
                            e.setText("");
                            ef.setText(a.getIngreso() + "");
                            Toast.makeText(getActivity(), "Éxito, periodo "+ a.getNombre() +" habilitado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Error, se debe habilitar un periodo sin ingreso", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception exc) {
                        Toast.makeText(getActivity(), "Error, " + exc.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Error, espacios vacíos", Toast.LENGTH_SHORT).show();
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
