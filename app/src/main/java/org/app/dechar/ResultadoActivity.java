package org.app.dechar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.app.dechar.modelo.Palabra;

import java.util.List;

public class ResultadoActivity extends AppCompatActivity {

    Button btnMenu;
    Button btnReiniciar;
    TextView tvAcertadas;
    TextView tvErradas;
    ListView lvAcertadas;
    ListView lvErradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        btnMenu=(Button)findViewById(R.id.btnMenu);
        btnReiniciar=(Button)findViewById(R.id.btnReiniciar);
        tvAcertadas=(TextView)findViewById(R.id.tvAcertadas);
        tvErradas=(TextView)findViewById(R.id.tvErradas);
        lvAcertadas=(ListView)findViewById(R.id.lvListaAcertada);
        lvErradas=(ListView)findViewById(R.id.lvListaErradas);
        getSupportActionBar().hide();

        Bundle bolsa=getIntent().getExtras();
        final int t=bolsa.getInt("x");
        long[] acertadas= bolsa.getLongArray("acertar");
        long[] error=bolsa.getLongArray("error");

        ContenidoTexto contenidoTexto=new ContenidoTexto();

        List<Palabra> acer=contenidoTexto.getVerificar(t,acertadas);
        List<Palabra> erro=contenidoTexto.getVerificar(t,error);
        lvAcertadas.setAdapter(new ListaTextoAdapter(ResultadoActivity.this,acer,1));
        lvErradas.setAdapter(new ListaTextoAdapter(ResultadoActivity.this,erro,0));

        tvAcertadas.setText("Acertadas: "+String.valueOf(acertadas.length));
        tvErradas.setText("Erradas: "+String.valueOf(error.length));

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultadoActivity.this,JugarActivity.class);
                intent.putExtra("x",t);
                startActivity(intent);
                finish();
            }
        });

    }
}
