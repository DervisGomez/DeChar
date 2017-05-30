package org.app.dechar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;
import org.app.dechar.modelo.PalabraDao;

import java.util.List;

public class EditarCategoriaActivity extends AppCompatActivity implements View.OnClickListener{

    ListView lvCategoria;
    Button btnRestablecer;
    Button btnConfi;
    Button btnVolver;
    Button btnRegresar;
    RelativeLayout rlEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);
        getSupportActionBar().hide();
        lvCategoria=(ListView)findViewById(R.id.lvCategoriasEditar);
        btnRestablecer=(Button)findViewById(R.id.btnRestablecer);
        btnConfi=(Button)findViewById(R.id.btnConfigEditarCategoria);
        btnVolver=(Button)findViewById(R.id.btnVolverMenuEditarCategoria);
        btnRegresar=(Button)findViewById(R.id.btnRegresarEditarCategoria);
        btnRestablecer.setOnClickListener(this);
        rlEditar=(RelativeLayout)findViewById(R.id.rlEditarCategoria);
        btnConfi.setOnClickListener(this);
        btnVolver.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
        rlEditar.setVisibility(View.GONE);
        cargarLista();
    }

    public void restablecerCategoria(){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EditarCategoriaActivity.this);
        dialogo1.setTitle("Importante!");
        dialogo1.setMessage("¿Estas seguro de restablecer todas las categoria?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                DAOApp daoApp=new DAOApp();
                PalabraDao palabraDao=daoApp.getPalabraDao();
                CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                palabraDao.deleteAll();
                categoriaDao.deleteAll();
                ContenidoTexto contenidoTexto=new ContenidoTexto();
                //contenidoTexto.getMisCategoria();
                cargarLista();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void cargarLista(){
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        List<Categoria> categorias=categoriaDao.loadAll();
        lvCategoria.setAdapter(new EditarCategoriasAdater(EditarCategoriaActivity.this,categorias));
        lvCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Categoria categoria = (Categoria) lvCategoria.getAdapter().getItem(pos);
                Intent intent=new Intent(EditarCategoriaActivity.this,PalabraCategoriaActivity.class);
                intent.putExtra("categoria",categoria.getId());
                Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                v.vibrate(100);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cargarLista();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(100);
        switch (view.getId()){
            case R.id.btnRestablecer:
                restablecerCategoria();
                break;
            case R.id.btnVolverMenuEditarCategoria:
                finish();
                break;
            case R.id.btnRegresarEditarCategoria:
                rlEditar.setVisibility(View.GONE);
                break;
            case R.id.btnConfigEditarCategoria:
                rlEditar.setVisibility(View.VISIBLE);
                break;
        }
    }
}
