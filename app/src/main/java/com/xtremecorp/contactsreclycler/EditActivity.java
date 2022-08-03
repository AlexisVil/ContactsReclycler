package com.xtremecorp.contactsreclycler;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xtremecorp.contactsreclycler.db.DbContactos;
import com.xtremecorp.contactsreclycler.entidades.Contactos;

public class EditActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    Contactos contacto;
    boolean correcto = false;
    FloatingActionButton fabEditar, fabEliminar;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        final DbContactos dbContactos = new DbContactos(EditActivity.this);
        contacto = dbContactos.verContacto(id);
        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
        }

        String nombre_string = txtNombre.getText().toString();
        String telefono_string = txtTelefono.getText().toString();
        String correo_string = txtCorreo.getText().toString();

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre_string.equals("") && !telefono_string.equals("")) {
                    correcto = dbContactos.editarContacto(id, nombre_string, telefono_string, correo_string);
                    if (correcto) {
                        Toast.makeText(EditActivity.this, "Registro modificado", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditActivity.this, "Error al modificar registros", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditActivity.this, "Debe llenar los campos obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(EditActivity.this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
