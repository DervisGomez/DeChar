package org.app.dechar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPersonajes;
    Button btnLibros;
    Button btnHistoria;
    Button btnLugares;
    Button btnVersiculos;
    Button btnTodos;
    Button btnNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnPersonajes=(Button)findViewById(R.id.btnPersonaje);
        btnLibros=(Button)findViewById(R.id.btnLibros);
        btnHistoria=(Button)findViewById(R.id.btnHistorias);
        btnLugares=(Button)findViewById(R.id.btnLugares);
        btnVersiculos=(Button)findViewById(R.id.btnVersiculos);
        btnTodos=(Button)findViewById(R.id.btnTodos);
        btnNuevo=(Button)findViewById(R.id.btnNuevo);
        btnPersonajes.setOnClickListener(this);
        btnLibros.setOnClickListener(this);
        btnHistoria.setOnClickListener(this);
        btnLugares.setOnClickListener(this);
        btnVersiculos.setOnClickListener(this);
        btnTodos.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);
        ajustarBotones();
    }

    public void ajustarBotones(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = (display.getWidth()-48)/2;
        LinearLayout llPresentacion=(LinearLayout)findViewById(R.id.llPresentacion);
        llPresentacion.getLayoutParams().height=(width-16)/2;
        LinearLayout llPersonaje=(LinearLayout)findViewById(R.id.llPersonaje);
        llPersonaje.getLayoutParams().height=width;
        LinearLayout llTodos=(LinearLayout)findViewById(R.id.llTodos);
        llTodos.getLayoutParams().height=width;
        LinearLayout llLibros=(LinearLayout)findViewById(R.id.llLibros);
        llLibros.getLayoutParams().height=width;
        LinearLayout llLugares=(LinearLayout)findViewById(R.id.llLugares);
        llLugares.getLayoutParams().height=width;
        LinearLayout llVersiculos=(LinearLayout)findViewById(R.id.llVericulos);
        llVersiculos.getLayoutParams().height=width;
        LinearLayout llHistorias=(LinearLayout)findViewById(R.id.llHistorias);
        llHistorias.getLayoutParams().height=width;
        LinearLayout llNuevo=(LinearLayout)findViewById(R.id.llNuevo);
        llNuevo.getLayoutParams().height=width;
        LinearLayout llCualquier=(LinearLayout)findViewById(R.id.llCualquiera);
        llCualquier.getLayoutParams().height=width;
        LinearLayout llAyuda=(LinearLayout)findViewById(R.id.llAyuda);
        llAyuda.getLayoutParams().height=(width-16)/2;
    }

    public void iniciarJuego(int x){
        Intent intent=new Intent(MainActivity.this,JugarActivity.class);
        intent.putExtra("x",x);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTodos:
                iniciarJuego(0);
                break;
            case R.id.btnPersonaje:
                iniciarJuego(1);
                break;
            case R.id.btnLibros:
                iniciarJuego(2);
                break;
            case R.id.btnLugares:
                iniciarJuego(3);
                break;
            case R.id.btnHistorias:
                break;
            case R.id.btnVersiculos:
                break;
            case R.id.btnNuevo:
                Intent intent=new Intent(MainActivity.this,MisCategoriasActivity.class);
                startActivity(intent);
                break;
        }
    }
}
