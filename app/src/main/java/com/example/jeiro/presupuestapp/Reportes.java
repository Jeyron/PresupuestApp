package com.example.jeiro.presupuestapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Reportes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Reportes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reportes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button detalle_egreso,balance_categoría_egreso,porcentajes_egresos_categoría,resumen_gastos_tarjeta;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public Reportes() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Reportes newInstance(String param1, String param2) {
        Reportes fragment = new Reportes();
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

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reportes,container,false);
        context = rootView.getContext();

        Button btn_reporte_detalle_egreos = (Button)rootView.findViewById(R.id.btn_reporte_detalle_egreos);
        btn_reporte_detalle_egreos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,Reporte_detalle_egresos.class);
                startActivity(intent);
            }
        });

        Button btn_llamar_balance_categoria_egreso = (Button)rootView.findViewById(R.id.btn_reporte_balance_por_categoria);
        btn_llamar_balance_categoria_egreso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,Reporte_balance_categoria_egreso.class);
                startActivity(intent);
            }
        });

        Button btn_reporte_porcentaje_egreos_x_categoria = (Button)rootView.findViewById(R.id.btn_reporte_porcentaje_egreos_x_categoria);
        btn_reporte_porcentaje_egreos_x_categoria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,Reporte_porcentaje_egresos_categoria.class);
                startActivity(intent);
            }
        });

        Button reporte_resumen_gastos_x_tarjeta = (Button)rootView.findViewById(R.id.reporte_resumen_gastos_x_tarjeta);
        reporte_resumen_gastos_x_tarjeta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context,Reporte_resumen_gastos_tarjeta.class);
                startActivity(intent);
            }
        });
        return rootView;
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
