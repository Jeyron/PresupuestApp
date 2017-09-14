package com.example.jeiro.presupuestapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeiro.presupuestapp.entidades.entidadMes;

import java.util.ArrayList;

import static com.example.jeiro.presupuestapp.Navegacion.ad;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Presupuesto_mensual.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Presupuesto_mensual#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Presupuesto_mensual extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View viewroot;

    private OnFragmentInteractionListener mListener;

    public Presupuesto_mensual() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Presupuesto_mensual.
     */
    // TODO: Rename and change types and number of parameters
    public static Presupuesto_mensual newInstance(String param1, String param2) {
        Presupuesto_mensual fragment = new Presupuesto_mensual();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewroot = inflater.inflate(R.layout.fragment_presupuesto_mensual, container, false);

        EditText e = (EditText) viewroot.findViewById(R.id.txt_monto_prespuesto_mensual);
        try
        {
            entidadMes a = ad.obtener_id_mes_habilitado(getActivity());
            if(a.getIngreso() != 0)
            {
                e.setEnabled(false);
                e.setText(a.getIngreso() + "");
            }
        }
        catch (Exception exc){}

        Button b = (Button) viewroot.findViewById(R.id.btn_incluir_ingreso_mensual);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText e = (EditText) viewroot.findViewById(R.id.txt_monto_prespuesto_mensual);
                if (e.getText().length() != 0)
                {
                    try {
                        if (Navegacion.id_mes != 0 && e.isEnabled()) {
                            entidadMes a = ad.obtener_id_mes_habilitado(getActivity());
                            a.setIngreso(Integer.parseInt(e.getText().toString()));
                            ad.agregar_modificar_mes(a, false, getActivity());
                            e.setEnabled(false);
                            e.setText(a.getIngreso() + "");
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

        Button br = (Button) viewroot.findViewById(R.id.btn_reset_presupuesto);
        br.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try
                {
                    entidadMes a = ad.obtener_id_mes_habilitado(getActivity());
                    a.setIngreso(0);
                    ad.agregar_modificar_mes(a,false,getActivity());

                    EditText e = (EditText) viewroot.findViewById(R.id.txt_monto_prespuesto_mensual);
                    e.setText(0 + "");
                    Toast.makeText(getActivity(),"Éxito, valores restaurados", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),"Error, " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
