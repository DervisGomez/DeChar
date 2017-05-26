package org.app.dechar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;

import java.util.List;

public class EditarCategoriaActivity extends AppCompatActivity {

    ListView lvCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvCategoria=(ListView)findViewById(R.id.lvCategoriasEditar);
        cargarLista();
    }

    public void cargarLista(){
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        List<Categoria> categorias=categoriaDao.loadAll();
        lvCategoria.setAdapter(new MisCategoriasAdater(EditarCategoriaActivity.this,categorias));
        lvCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Categoria categoria = (Categoria) lvCategoria.getAdapter().getItem(pos);
                Intent intent=new Intent(EditarCategoriaActivity.this,PalabraCategoriaActivity.class);
                intent.putExtra("categoria",categoria.getId());
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
}
