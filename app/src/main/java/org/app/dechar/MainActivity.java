package org.app.dechar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPersonajes;
    Button btnLibros;
    Button btnPalabra;
    Button btnActualo;
    Button btnLugares;
    Button btnTodos;
    Button btnNuevo;
    Button btnCualquiera;
    TextView tvAplicacion;
    TextView tvAyuda;

    Button btnJugar;
    Button btnAgregar;
    Button btnEditar;
    Button btnRegrasarAuda;

    Button btnTiempo;
    Button btnDesarrollador;
    Button btnRegresarAplicacion;

    RelativeLayout rlAyuda;
    RelativeLayout rlAplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnPersonajes=(Button)findViewById(R.id.btnPersonaje);
        btnLibros=(Button)findViewById(R.id.btnLibros);
        btnPalabra=(Button)findViewById(R.id.btnPalabra);
        btnLugares=(Button)findViewById(R.id.btnLugares);
        btnCualquiera=(Button)findViewById(R.id.btnCualquiera);
        btnTodos=(Button)findViewById(R.id.btnTodos);
        btnNuevo=(Button)findViewById(R.id.btnNuevo);
        tvAplicacion=(TextView)findViewById(R.id.tvAplicacion);
        tvAyuda=(TextView)findViewById(R.id.tvAyuda);
        btnJugar=(Button)findViewById(R.id.btnAyudaJugar);
        btnAgregar=(Button)findViewById(R.id.btnAyudaNueva);
        btnEditar=(Button)findViewById(R.id.btnAydaEditar);
        btnRegrasarAuda=(Button)findViewById(R.id.btnAyudaRegresar);
        btnTiempo=(Button)findViewById(R.id.btnAplicacionTiempo);
        btnDesarrollador=(Button)findViewById(R.id.btnAplicacionDesarrollador);
        btnRegresarAplicacion=(Button)findViewById(R.id.btnAplicacionRegresar);
        rlAplicacion=(RelativeLayout)findViewById(R.id.rlAplicacion);
        rlAyuda=(RelativeLayout)findViewById(R.id.rlAyuda);
        btnActualo=(Button)findViewById(R.id.btnActualo);

        btnActualo.setOnClickListener(this);
        btnPersonajes.setOnClickListener(this);
        btnLibros.setOnClickListener(this);
        btnPalabra.setOnClickListener(this);
        btnLugares.setOnClickListener(this);
        btnCualquiera.setOnClickListener(this);
        btnTodos.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);
        tvAplicacion.setOnClickListener(this);
        tvAyuda.setOnClickListener(this);
        btnJugar.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnRegrasarAuda.setOnClickListener(this);
        btnTiempo.setOnClickListener(this);
        btnDesarrollador.setOnClickListener(this);
        btnRegresarAplicacion.setOnClickListener(this);
        ajustarBotones();
        rlAyuda.setVisibility(View.GONE);
        rlAplicacion.setVisibility(View.GONE);
    }

    public void ajustarBotones(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = (display.getWidth()-48)/2;
        int he = (display.getHeight()-48)/2;
        if (width>he){
            width=(display.getWidth()-64)/3;
        }
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
            case R.id.btnPalabra:
                iniciarJuego(4);
                break;
            case R.id.btnActualo:
                iniciarJuego(5);
                break;
            case R.id.btnCualquiera:
                Random r = new Random();
                int valor= r.nextInt(4)+1;
                iniciarJuego(valor);
                break;
            case R.id.btnNuevo:
                Intent intent=new Intent(MainActivity.this,MisCategoriasActivity.class);
                startActivity(intent);
                break;
            case R.id.tvAplicacion:
                rlAplicacion.setVisibility(View.VISIBLE);
                break;
            case R.id.tvAyuda:
                //rlAyuda.setVisibility(View.VISIBLE);
                Intent intent2=new Intent(MainActivity.this,AyudaActivity.class);
                intent2.putExtra("ayuda",1);
                startActivity(intent2);
                break;
            case R.id.btnAyudaJugar:
                /*Intent intent2=new Intent(MainActivity.this,AyudaActivity.class);
                intent2.putExtra("ayuda",1);
                startActivity(intent2);*/
                break;
            case R.id.btnAyudaNueva:
                Intent intent3=new Intent(MainActivity.this,AyudaActivity.class);
                intent3.putExtra("ayuda",2);
                startActivity(intent3);
                break;
            case R.id.btnAydaEditar:
                Intent intent4=new Intent(MainActivity.this,AyudaActivity.class);
                intent4.putExtra("ayuda",3);
                startActivity(intent4);
                break;
            case R.id.btnAyudaRegresar:
                rlAyuda.setVisibility(View.GONE);
                break;
            case R.id.btnAplicacionTiempo:
                break;
            case R.id.btnAplicacionDesarrollador:
                Intent intent1=new Intent(MainActivity.this,DesarrolladorActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnAplicacionRegresar:
                rlAplicacion.setVisibility(View.GONE);
                break;
        }
    }
}
