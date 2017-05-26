package org.app.dechar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;
import org.app.dechar.modelo.Palabra;
import org.app.dechar.modelo.PalabraDao;

import java.util.ArrayList;
import java.util.List;

public class PalabraCategoriaActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etNuevaPalabra;
    Button btnNueva;
    ListView lvLista;
    Button btnEliminar;
    Button btnGuardar;
    RelativeLayout rlNuevaCategoria;
    EditText etNombreCategoria;
    Button btnSiguiente;
    List<Palabra> palabras=new ArrayList<>();
    Button btnEliminarAgregar;
    Palabra palabra=new Palabra();
    long categoria;
    int edita=-1;
    boolean cerrar=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabra_categoria);
        etNuevaPalabra=(EditText)findViewById(R.id.etNuevaPalbra);
        btnNueva=(Button)findViewById(R.id.btnAgregarPalabra);
        lvLista=(ListView)findViewById(R.id.lvListaPalabras);
        btnEliminar=(Button)findViewById(R.id.btnGuardarCategoria);
        btnGuardar=(Button)findViewById(R.id.btnEliminarCategoria);
        rlNuevaCategoria=(RelativeLayout)findViewById(R.id.rlNuevaCategoria);
        etNombreCategoria=(EditText)findViewById(R.id.etNombreCategoria);
        btnEliminarAgregar=(Button)findViewById(R.id.btnCancelarAgregar);
        btnSiguiente=(Button)findViewById(R.id.btnSiguiente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnNueva.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);
        btnSiguiente.setOnClickListener(this);
        btnEliminarAgregar.setOnClickListener(this);
        Bundle bolsa=getIntent().getExtras();
        btnEliminarAgregar.setVisibility(View.GONE);
        categoria=bolsa.getLong("categoria");
        if (categoria<1){

        }else{
            if (categoria<6){
                btnEliminar.setVisibility(View.GONE);
                btnGuardar.setVisibility(View.GONE);
            }
            buscarCategoria();
        }
    }

    public void cargarLista(){
        DAOApp daoApp = new DAOApp();
        PalabraDao palabraDao=daoApp.getPalabraDao();
        palabras.clear();
        palabras=palabraDao._queryCategoria_Palabra(categoria);
        lvLista.setAdapter(new PalabraCategoriaAdapter(PalabraCategoriaActivity.this,PalabraCategoriaActivity.this,palabras));
    }

    public void buscarCategoria(){
        rlNuevaCategoria.setVisibility(View.GONE);
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        Categoria categori=categoriaDao.load(categoria);
        getSupportActionBar().setTitle(categori.getNombre());
        cargarLista();
    }

    public void editarLista(int x){
        etNuevaPalabra.setText(palabras.get(x).getNombre());
        palabra=palabras.get(x);
        edita=1;
        btnEliminarAgregar.setVisibility(View.VISIBLE);
    }

    public void eliminarPalabra(final int x){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(PalabraCategoriaActivity.this);
        dialogo1.setTitle("Importante!");
        dialogo1.setMessage("¿Desea Eliminar esta categoria junto a las palabras?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                DAOApp daoApp=new DAOApp();
                PalabraDao palabraDao=daoApp.getPalabraDao();
                palabraDao.delete(palabras.get(x));
                cargarLista();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                if (cerrar){
                    finish();
                }else{
                    rlNuevaCategoria.setVisibility(View.GONE);
                    cerrar=true;
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
//		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
        alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }

    public void eliminarCategoria(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(PalabraCategoriaActivity.this);
        dialogo1.setTitle("Importante!");
        dialogo1.setMessage("¿Desea Eliminar esta categoria junto a las palabras?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                DAOApp daoApp=new DAOApp();
                CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                PalabraDao palabraDao=daoApp.getPalabraDao();
                List<Palabra> palabras1=palabraDao._queryCategoria_Palabra(categoria);
                categoriaDao.deleteByKey(categoria);
                palabraDao.deleteInTx(palabras1);
                finish();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    @Override
    public void onClick(View view) {
        DAOApp daoApp=new DAOApp();
        switch (view.getId()){
            case R.id.btnSiguiente:
                cerrar=true;
                String cate=etNombreCategoria.getText().toString();
                if (cate.length()>0){
                    if (categoria<0){
                        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                        Categoria categoria1=new Categoria();
                        categoria1.setNombre(cate);
                        categoria1.setDescripcion("");
                        categoria1.setAdmin(false);
                        categoria=categoriaDao.insert(categoria1);

                    }else{
                        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                        Categoria categoria1=categoriaDao.load(categoria);
                        categoria1.setNombre(cate);
                        categoriaDao.update(categoria1);
                    }
                    getSupportActionBar().setTitle(cate);
                    rlNuevaCategoria.setVisibility(View.GONE);
                }else{
                    showAlertDialog(PalabraCategoriaActivity.this,"Categoria","Debe introducir el nombre de al categoria",false);
                }
                break;
            case R.id.btnEliminarCategoria:

                    eliminarCategoria();

                break;
            case R.id.btnGuardarCategoria:
                cerrar=false;
                rlNuevaCategoria.setVisibility(View.VISIBLE);
                CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                Categoria categoria1=categoriaDao.load(categoria);
                etNombreCategoria.setText(categoria1.getNombre());
                break;
            case R.id.btnAgregarPalabra:
                String palab=etNuevaPalabra.getText().toString();
                if (palab.length()>0){
                    PalabraDao palabraDao=daoApp.getPalabraDao();
                    if (edita<0){
                        Palabra palabra1=new Palabra();
                        palabra1.setNombre(palab);
                        palabra1.setIdCategoriaPalabra(categoria);
                        palabraDao.insert(palabra1);
                    }else{
                        edita=-1;
                        palabra.setNombre(palab);
                        palabraDao.update(palabra);
                        btnEliminarAgregar.setVisibility(View.GONE);
                    }
                    cargarLista();
                    etNuevaPalabra.setText("");
                }else {
                    showAlertDialog(PalabraCategoriaActivity.this,"Categoria","Debe introducir una palabra o frase",false);
                }
                break;
            case R.id.btnCancelarAgregar:
                edita=-1;
                etNuevaPalabra.setText("");
                btnEliminarAgregar.setVisibility(View.GONE);
                break;
        }
    }
}
