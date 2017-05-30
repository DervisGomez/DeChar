package org.app.dechar.modelo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by dervis on 08/12/16.
 */
public class DAOApp extends Application {

    static PalabraDao palabraDao;
    static CategoriaDao categoriaDao;
    static TiempoDao tiempoDao;

    public PalabraDao getPalabraDao(){
        return palabraDao;
    }

    public CategoriaDao getCategoriaDao(){
        return categoriaDao;
    }

    public TiempoDao getTiempoDao(){
        return tiempoDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "alice", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        categoriaDao=daoSession.getCategoriaDao();
        palabraDao=daoSession.getPalabraDao();
        tiempoDao=daoSession.getTiempoDao();
    }
}
