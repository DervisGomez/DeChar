package org.app.dechar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.app.dechar.modelo.Palabra;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class JugarActivity extends AppCompatActivity implements SensorEventListener {

    private float curX = 0, curY = 0, curZ = 0;
    private TextView txtSegundo, txtTexto;
    private RelativeLayout fondo;
    List<Palabra> texto=new ArrayList<>();
    List<Palabra> acertar=new ArrayList<>();
    List<Palabra> error=new ArrayList<>();
    boolean cambiar=true;
    int valor;
    int xxx=0;
    int xx=6;
    boolean iniciar=false;
    int tiempo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        txtSegundo=(TextView)findViewById(R.id.txtSegundo);
        txtTexto=(TextView)findViewById(R.id.txtTexto);
        fondo=(RelativeLayout)findViewById(R.id.llFondo);
        Bundle bolsa=getIntent().getExtras();
        int t=bolsa.getInt("x");
        ContenidoTexto contenidoTexto=new ContenidoTexto();
        texto=contenidoTexto.getTexto(t);
        tiempo=contenidoTexto.getTiempo();
        Button regresar=(Button)findViewById(R.id.btnMenuRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                v.vibrate(100);
            }
        });
        //txtSegundo.setText(String.valueOf(xxx));
        //cambiarTexto();
        //new MiTarea(1).execute();
    }


    @Override
    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);

        }
    }

    @Override
    protected void onStop() {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
        super.onStop();
    }

    public void cambiarTexto(){
        if (texto.size()>0){
            cambiar=false;
            Random r = new Random();
            valor= r.nextInt(texto.size());
            txtTexto.setText(texto.get(valor).getNombre());
            fondo.setBackgroundResource(R.color.colorFondo);
        }else{
            txtTexto.setText("No hay más palabras");
            fondo.setBackgroundResource(R.color.colorFondo);
        }

    }

    public void textoCorrecto(){
        if (texto.size()>0){
            cambiar=true;
            acertar.add(texto.get(valor));
            texto.remove(valor);
            txtTexto.setText("CORRECTO");
            fondo.setBackgroundResource(R.color.colorAcertar);
        }else{
            txtTexto.setText("No hay más palabras");
            fondo.setBackgroundResource(R.color.colorFondo);
        }
    }

    public void textoError(){
        if (texto.size()>0){
            cambiar=true;
            error.add(texto.get(valor));
            texto.remove(valor);
            txtTexto.setText("SIGUIENTE");
            fondo.setBackgroundResource(R.color.colorErrar);
        }else{
            txtTexto.setText("No hay más palabras");
            fondo.setBackgroundResource(R.color.colorFondo);
        }
    }

    public void llamarResultado(){
        Intent intent=new Intent(JugarActivity.this,ResultadoActivity.class);
        Bundle bolsa=getIntent().getExtras();
        int t=bolsa.getInt("x");
        intent.putExtra("x",t);
        long[] acer=new long[acertar.size()];
        for (int x=0;x<acertar.size();x++){
            acer[x]=acertar.get(x).getId();
        }
        intent.putExtra("acertar",acer);
        long[] erro=new long[error.size()];
        for (int x=0;x<error.size();x++){
            erro[x]=error.get(x).getId();
        }
        intent.putExtra("error",erro);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            curX = event.values[0];
            curY = event.values[1];
            curZ = event.values[2];
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            Calendar calendar=Calendar.getInstance();
            int x=calendar.getTime().getSeconds();

            if (x!=xxx){
                if (iniciar){
                    xxx=x;
                    xx--;
                    txtSegundo.setText(String.valueOf(xx));
                    if (xx==0){
                        txtTexto.setText("FIN");
                        iniciar=false;
                    }
                }else{
                    if (xx==0){
                        xx--;
                        llamarResultado();
                    }else{
                        if (xx>0){
                            xxx=x;
                            xx--;
                            txtSegundo.setText("Pon el dispositivo en tu frente");
                            txtTexto.setText(String.valueOf(xx));
                            if (xx==0){
                                xx=tiempo;
                                iniciar=true;
                                cambiarTexto();
                                txtSegundo.setText(String.valueOf(xx));
                            }
                        }
                    }
                }

            }

            if (iniciar){
                if (curZ>9){
                    if (!cambiar){
                        v.vibrate(100);
                        textoCorrecto();
                    }
                }else if(curZ<-7){
                    if (!cambiar){
                        v.vibrate(100);
                        textoError();
                    }
                }else if(curZ>-4&&curZ<6){
                    if (cambiar){
                        //v.vibrate(200);
                        cambiarTexto();
                    }

                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
