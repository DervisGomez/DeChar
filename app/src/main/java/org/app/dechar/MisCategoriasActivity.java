package org.app.dechar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;

import java.util.List;

public class MisCategoriasActivity extends AppCompatActivity {
    ListView lvCategorias;
    TextView tvNoAun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_categorias);
        lvCategorias=(ListView)findViewById(R.id.lvCategorias);
        tvNoAun=(TextView)findViewById(R.id.tvNoHay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle bolsa=getIntent().getExtras();
        String usua=bolsa.getString("usuario");
        if (usua.equals("0")){
            getMenuInflater().inflate(R.menu.menu_categorias, menu);
        }

        return true;
    }
}
