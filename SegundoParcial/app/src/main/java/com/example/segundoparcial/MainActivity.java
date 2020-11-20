package com.example.segundoparcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // BARRA DE TAREAS.
        // con esto pongo visible el action bar de volver.
        ActionBar actionBar = super.getSupportActionBar();
        // CON ESTO SETEO EL TITULO.
        actionBar.setTitle("Segundo Parcial");
        // CON ESTO LO HAGO VISIBLE.



        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String jsonContactos = preferences.getString("json", null);


        if (jsonContactos==null)
        {
            TextView json = super.findViewById(R.id.jsonConContactos);
            json.setText("Todavía no hay contactos guardados.");
        }

        else
        {
            TextView json = super.findViewById(R.id.jsonConContactos);
            json.setText(jsonContactos);
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // CON ESTO SOBREESCRIBO EL MENU QUE YO TENGO, Y LE PONGO EL NUEVO MENU QUE YO CREÉ
        getMenuInflater().inflate(R.menu.menu, menu); // El primer parametro es nuestro menu XML, el segundo es el parametro de la función

        // CON ESTO MANEJO LA LUPITA.
        MenuItem menuItem = menu.findItem(R.id.opSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.op1:
                Log.d("Click", "Se hizo click en la opcion 1");

                DialogGenerico dialog = new DialogGenerico(this);
                dialog.show(getSupportFragmentManager(), "Etiqueta de OP1");
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {


            String lista = this.ObtenerSharedPreferences();

            boolean encontrado=false;

            List<Persona> auxList=new ArrayList<Persona>();

            for(Persona auxPersona: ObtenerTodas(lista))
            {
                if(auxPersona.getNombre().contains(query))
                {
                    // ACA CREARÍA EL DIALOG QUE LO ENCONTRÓ PERO NO LLEGUÉ A TIEMPO.

                    Toast toast = Toast.makeText(this, "LA PERSONA SE ENCUENTRA AQUI", Toast.LENGTH_SHORT);
                    toast.show();
                    encontrado=true;
                    break;
                }
            }
            if(!encontrado)
            {
                DialogNotificarError dialog= new DialogNotificarError(this,"No encontrada","La persona que busco no esta dentro de la lista",false,true,false);
                dialog.show(getSupportFragmentManager(),"dialog");
            }

            return false;



    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }



    public String ObtenerSharedPreferences()
    {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String jsonContactos = preferences.getString("json", null);

        return jsonContactos;
    }


    public void GuardarSharedPreferences(String valor)
    {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("json", valor);
        editor.commit();
    }



    public void RefrescarLista()
    {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String jsonContactos = preferences.getString("json", null);

            TextView json = super.findViewById(R.id.jsonConContactos);
            json.setText(jsonContactos);

    }


    public List<Persona> ObtenerTodas(String json) {
        List<Persona> personas = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++)
            {
                Persona persona = new Persona();
                JSONObject obj=jsonArray.getJSONObject(i);
                persona.setNombre(obj.getString("nombre"));
                persona.setTelefono(obj.getString("telefono"));
                personas.add(persona);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personas;
    }
}




