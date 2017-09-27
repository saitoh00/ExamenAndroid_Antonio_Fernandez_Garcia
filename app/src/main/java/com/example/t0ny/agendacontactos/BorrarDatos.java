package com.example.t0ny.agendacontactos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BorrarDatos extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog ventana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_datos);
        Button btnborrar=(Button)findViewById(R.id.botonborrar);
        btnborrar.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if(ventana==null) {
            ventana = CreateDialog();
        }
        ventana.show();

            //finish();
            // }
       // }

    }

    public AlertDialog CreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estás seguro de que quieres borrar éste contacto?Esto será irreversible");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //
                Contacto contacto=borrarContacto();
                if(contacto!=null) {
                    Intent intent = new Intent();
                    intent.putExtra("borrar", contacto);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        builder.setNegativeButton("cancelar y volver al menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                BorrarDatos.this.finish(); //al finalizar vuelvo a la actividad principal, si solo quiero volver atras para cambiar datos esta línea no es necesaria

            }
        });

        return builder.create();
    }

    private Contacto borrarContacto() {
        EditText nombre=(EditText)findViewById(R.id.bnombre);
        EditText apellidos=(EditText)findViewById(R.id.bapellidos);
        EditText telefono=(EditText)findViewById(R.id.btelefono);
        Contacto contacto =new Contacto(nombre.getText().toString(), apellidos.getText().toString(), telefono.getText().toString());
        return contacto;
    }
}
