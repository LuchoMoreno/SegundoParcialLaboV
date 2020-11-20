package com.example.segundoparcial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNotificarError extends DialogFragment {


    private  String mensaje;
    private  String titulo;
    private Boolean neutral;
    private Boolean negative;
    private Boolean positive;
    private MainActivity mainActivity;

    public DialogNotificarError(MainActivity main,String titulo, String mensaje, Boolean positive, Boolean neutral, Boolean negative) {
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.neutral = neutral;
        this.negative = negative;
        this.positive = positive;
        this.mainActivity = mainActivity;
    }



    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());//obtiene la activity que genere este objeto
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);

        DialogGenerico listener=new DialogGenerico(this.mainActivity);

        if(this.neutral){
            builder.setNeutralButton("OK", null);
        }
        if(this.positive){
            //builder.setPositiveButton();
        }
        if(this.negative){
            // builder.setNegativeButton();
        }

        return builder.create();
    }





}
