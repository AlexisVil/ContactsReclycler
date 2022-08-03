package com.xtremecorp.contactsreclycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.xtremecorp.contactsreclycler.adaptadores.ListaContactosAdapter;
import com.xtremecorp.contactsreclycler.db.DbContactos;
import com.xtremecorp.contactsreclycler.db.DbHelper;
import com.xtremecorp.contactsreclycler.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaContactos;
    ArrayList<Contactos> listArrayContactos;
    ListaContactosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        DbContactos dbContactos = new DbContactos(MainActivity.this);

        listArrayContactos = new ArrayList<>();
        adapter = new ListaContactosAdapter(dbContactos.visualizarContactos());

        listaContactos.setAdapter(adapter);


        txtBuscar.setOnQueryTextListener(this);

        /**
         BtnView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
        Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(MainActivity.this, "Error al crear DB", Toast.LENGTH_SHORT).show();
        }
        }
        });
         **/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(MainActivity.this, NuevaActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}