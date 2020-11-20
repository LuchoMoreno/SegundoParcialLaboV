package com.example.segundoparcial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogGenerico extends DialogFragment {


    MainActivity activity;

    DialogGenerico(MainActivity activity)
    {
        this.activity = activity;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // EL getActivity es la activity que genere este objeto. Va a guardarse como referencia.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("Nuevo Contacto");


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.nuevo_contacto, null);
        builder.setView(v);

        // EN EL CONSTRUCTOR GUARDO LA VISTA DEL NUEVO CONTACTO. ES DONDE OBTENGO LOS ELEMENTOS
        ClickDialogGenericoListener listenerNuevo = new ClickDialogGenericoListener(v, this.activity);

        builder.setNeutralButton("Cancelar", listenerNuevo);
        builder.setNegativeButton("Guardar", listenerNuevo);

        return builder.create();
    }
}
