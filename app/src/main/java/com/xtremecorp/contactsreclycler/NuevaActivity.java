package com.xtremecorp.contactsreclycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xtremecorp.contactsreclycler.db.DbContactos;

public class NuevaActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtMail;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtPhone);
        txtMail = findViewById(R.id.txtEmail);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevaActivity.this);
                long id = dbContactos.insertContacto(txtNombre.getText().toString(),
                        txtTelefono.getText().toString(),
                        txtMail.getText().toString());
                if ( id > 0 ) {
                    Toast.makeText(NuevaActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevaActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar(){
        txtMail.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
    }
}