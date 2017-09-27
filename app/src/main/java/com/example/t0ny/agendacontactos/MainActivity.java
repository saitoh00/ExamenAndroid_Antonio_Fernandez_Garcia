package com.example.t0ny.agendacontactos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final Integer ALTA=100;
    public static final Integer BAJA=200;
    public static final Integer LISTAR=300;
    public static final Integer EDITAR=400;
    public ArrayList<Contacto> listaContactos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnalta=(Button)findViewById(R.id.botonadd);
        btnalta.setOnClickListener(this);
        Button btnborrar=(Button)findViewById(R.id.botonborrar);
        btnborrar.setOnClickListener(this);
        Button btnlistar=(Button)findViewById(R.id.botonvertodos);
        btnlistar.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ALTA == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("alta")) {
                 //pruebas  System.out.println("Tamaño antes :" + listaContactos.size());
                    listaContactos.add((Contacto) data.getParcelableExtra("alta"));  //se añade contacto que previamente hemos pasado con un Intent desde la actividad GrabarDatos
                 //pruebas   System.out.println("Tamaño despues :" + listaContactos.size());
                  // pruebas  System.out.println("Tamaño despues :" + listaContactos.get(0));
                }
            }
        } else {
            if (BAJA == requestCode) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("borrar")) {
                       // System.out.println("Tamaño antes :" + listaContactos.size());  //verificacion del tamaño para saber si se hace el borrado/
                        listaContactos.remove((Contacto) data.getParcelableExtra("borrar"));  //se borra contacto que previamente hemos pasado con un Intent desde la actividad BorrarDatos
                      //  System.out.println("Tamaño despues :" + listaContactos.size());

                    }
                }
            }
        /*    else {
                if (LISTAR == requestCode) {
                    if (resultCode == Activity.RESULT_OK) {

                    }

                } else {
                    if (EDITAR == requestCode) {
                        if (resultCode == Activity.RESULT_OK) {

                        }
                    }
                }
            }*/
        }
    }

        @Override
        public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.botonadd:
                intent=new Intent(this, GrabarDatos.class);
                startActivityForResult(intent, ALTA);  //mandamos un intent junto con un Integer para definin la Actividad a la que llamamos
                break;
            case R.id.botonborrar:
                intent=new Intent(this, BorrarDatos.class);
                startActivityForResult(intent, BAJA);
                break;
            case R.id.botonvertodos:
                intent=new Intent(this, ListarDatos.class);
                intent.putExtra("listacontactos", listaContactos);
                startActivityForResult(intent, LISTAR);
                break;
        }
    }
}
