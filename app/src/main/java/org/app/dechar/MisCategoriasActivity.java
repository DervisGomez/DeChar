package org.app.dechar;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;

import java.util.List;

public class MisCategoriasActivity extends AppCompatActivity implements View.OnClickListener{
    ListView lvCategorias;
    TextView tvNoAun;
    Button btnNueva;
    Button btnEditar;
    Button btnmenu;
    Button btnConfig;
    Button btnRegresar;
    RelativeLayout rlMiscategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_categorias);
        lvCategorias=(ListView)findViewById(R.id.lvCategorias);
        tvNoAun=(TextView)findViewById(R.id.tvNoHay);
        getSupportActionBar().hide();
        btnNueva=(Button)findViewById(R.id.btnNuevaCategoria);
        btnEditar=(Button)findViewById(R.id.btnEditarCategoria);
        btnRegresar=(Button)findViewById(R.id.btnRegresarMisCategorias);
        btnConfig=(Button)findViewById(R.id.btnConfigMisCategorias);
        btnmenu=(Button)findViewById(R.id.btnVolverMenu);
        rlMiscategoria=(RelativeLayout) findViewById(R.id.rlMisCategorias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnNueva.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
        btnConfig.setOnClickListener(this);
        btnmenu.setOnClickListener(this);
        cargarLista();

    }

    public void cargarLista(){
        ContenidoTexto contenidoTexto=new ContenidoTexto();
        List<Categoria> categorias=contenidoTexto.getMisCategoria();
        if (categorias.size()>0){
            rlMiscategoria.setVisibility(View.GONE);
            btnRegresar.setVisibility(View.VISIBLE);
            lvCategorias.setAdapter(new MisCategoriasAdater(MisCategoriasActivity.this,categorias));
            lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    Categoria categoria = (Categoria) lvCategorias.getAdapter().getItem(pos);
                    Intent intent=new Intent(MisCategoriasActivity.this,JugarActivity.class);
                    intent.putExtra("x",categoria.getId().intValue());
                    Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    v.vibrate(100);
                    startActivity(intent);
                }
            });
        }else {
            rlMiscategoria.setVisibility(View.VISIBLE);
            btnRegresar.setVisibility(View.GONE);
        }
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
        long x=-1;
        switch (view.getId()){
            case R.id.btnNuevaCategoria:
                Intent intent=new Intent(MisCategoriasActivity.this,PalabraCategoriaActivity.class);
                intent.putExtra("categoria",x);
                startActivityForResult(intent,1);
                break;
            case R.id.btnEditarCategoria:
                Intent intent1=new Intent(MisCategoriasActivity.this,EditarCategoriaActivity.class);
                intent1.putExtra("categoria",x);
                startActivityForResult(intent1,1);
                break;
            case R.id.btnVolverMenu:
                finish();
                break;
            case R.id.btnConfigMisCategorias:
                rlMiscategoria.setVisibility(View.VISIBLE);
                break;
            case R.id.btnRegresarMisCategorias:
                rlMiscategoria.setVisibility(View.GONE);
                break;
        }
    }
}
