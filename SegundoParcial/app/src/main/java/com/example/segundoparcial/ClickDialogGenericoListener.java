package com.example.segundoparcial;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClickDialogGenericoListener implements DialogInterface.OnClickListener {

    MainActivity activity;
    private View view;


    public ClickDialogGenericoListener(View view, MainActivity activity){
        this.view=view;
        this.activity= activity;
    }


    @Override
    public void onClick(DialogInterface dialog, int which)
    {


        switch (which)
        {
            case Dialog.BUTTON_NEUTRAL:
                Log.d("Texto", "Se hizo click en NEUTRAL (Cancelar)");
                break;

            case Dialog.BUTTON_NEGATIVE:
                Log.d("Texto", "Se hizo click en NEGATIVO (Guardar)");

                String jsonString = null;
                JSONArray jsonArray = null;


                // OBTENTO LOS EDIT TEXT
                EditText nombre =this.view.findViewById(R.id.nombre);
                EditText telefono =this.view.findViewById(R.id.telefono);


                // LOS PASO A STRING.
                String nombreString = nombre.getText().toString();
                String telefonoString = telefono.getText().toString();

                if ("".equals(nombreString) && "".equals(telefonoString))
                {
                    Log.d("AVISO: ", "LOS ELEMENTOS MARCADOS SON NULOS");
                }

                else
                {

                    // Creo el array.
                    jsonArray = new JSONArray();

                    StringBuilder sb = new StringBuilder();
                    sb.append("{'nombre':'");
                    sb.append(nombreString);
                    sb.append("','telefono':'");
                    sb.append(telefonoString);
                    sb.append("'}");


                    /** NO LO USO PORQUE GUARDA CON ////
                    jsonString = new JSONObject()
                            .put("nombre", nombreString)
                            .put("telefono", telefonoString)
                            .toString();

                     */

                    jsonArray.put(sb);

                    Log.d("array: ", jsonArray.toString());
                    Log.d("sb: ", sb.toString());

                    this.activity.GuardarSharedPreferences(jsonArray.toString());
                    this.activity.RefrescarLista();



                }
                break;
        }





    }








}
