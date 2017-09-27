package com.example.t0ny.agendacontactos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListarDatos extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter adapter;
    private AlertDialog ventana;
    private Contacto copia;
    private ArrayList <Contacto> contactos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);
        Intent intent = getIntent();
        contactos= (ArrayList<Contacto>) intent.getSerializableExtra("listacontactos");
        listView=(ListView)findViewById(R.id.listafinal);
        adapter=new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1,
                (ArrayList)intent.getSerializableExtra("listacontactos"));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(ventana==null){
            ventana=CreateDialog(contactos.get(i));
        }
        ventana.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(MainActivity.EDITAR ==requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if(data.hasExtra("editar")){
                    Contacto nuevo= data.getParcelableExtra("editar");
                    System.out.println("Tamaño despues :" + contactos.get(0));
                    contactos.remove(copia);
                    contactos.add(nuevo);
                    System.out.println("Tamaño despues :" + contactos.get(0));
                    adapter=new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1, contactos);

                    listView.setAdapter(adapter);

                    //System.out.println("Tamaño despues :" + contactos.get(0));  //Prueba por A.Monitor para ver si se ha editado el contacto



                }
            }
        }
    }

    public AlertDialog CreateDialog(final Contacto c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estás seguro de quieres editar este contacto?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent;
                intent=new Intent(ListarDatos.this, EditarDatos.class);
                copia= c;
                intent.putExtra("contacto", c);
                startActivityForResult(intent, MainActivity.EDITAR);
            }
        });

        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        return builder.create();
    }
}
