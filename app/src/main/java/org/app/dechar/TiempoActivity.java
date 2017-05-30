package org.app.dechar;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import org.app.dechar.modelo.DAOApp;
import org.app.dechar.modelo.Tiempo;
import org.app.dechar.modelo.TiempoDao;

import java.util.List;

public class TiempoActivity extends AppCompatActivity implements View.OnClickListener{

    RadioButton rb30;
    RadioButton rb40;
    RadioButton rb50;
    RadioButton rb60;
    RadioButton rb80;
    Button btnListo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);
        getSupportActionBar().hide();
        rb30=(RadioButton)findViewById(R.id.rb30);
        rb40=(RadioButton)findViewById(R.id.rb40);
        rb50=(RadioButton)findViewById(R.id.rb50);
        rb60=(RadioButton)findViewById(R.id.rb60);
        rb80=(RadioButton)findViewById(R.id.rb80);
        btnListo=(Button)findViewById(R.id.btnListo);
        rb30.setOnClickListener(this);
        rb40.setOnClickListener(this);
        rb50.setOnClickListener(this);
        rb60.setOnClickListener(this);
        rb80.setOnClickListener(this);
        btnListo.setOnClickListener(this);
        ubicarTiempo();
    }

    public void ubicarTiempo(){
        DAOApp daoApp=new DAOApp();
        TiempoDao tiempoDao=daoApp.getTiempoDao();
        List<Tiempo> tiempos=tiempoDao.loadAll();
        if (tiempos.size()>0){
            switch (tiempos.get(0).getSegundos()){
                case 30:
                    rb30.setChecked(true);
                    break;
                case 40:
                    rb40.setChecked(true);
                    break;
                case 50:
                    rb50.setChecked(true);
                    break;
                case 60:
                    rb60.setChecked(true);
                    break;
                case 80:
                    rb80.setChecked(true);
                    break;
            }
        }else{
            rb60.setChecked(true);
        }
    }

    public void asignarTiempo(int seg){
        DAOApp daoApp=new DAOApp();
        TiempoDao tiempoDao=daoApp.getTiempoDao();
        tiempoDao.deleteAll();
        tiempoDao.insert(new Tiempo((long)1,seg));
    }

    @Override
    public void onClick(View view) {
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(100);
        switch (view.getId()){
            case R.id.rb30:
                asignarTiempo(30);
                break;
            case R.id.rb40:
                asignarTiempo(40);
                break;
            case R.id.rb50:
                asignarTiempo(50);
                break;
            case R.id.rb60:
                asignarTiempo(60);
                break;
            case R.id.rb80:
                asignarTiempo(80);
                break;
            case R.id.btnListo:
                finish();
                break;
        }
    }
}
