package org.app.dechar.modelo;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import org.app.dechar.modelo.Palabra;
import org.app.dechar.modelo.Categoria;

import org.app.dechar.modelo.PalabraDao;
import org.app.dechar.modelo.CategoriaDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig palabraDaoConfig;
    private final DaoConfig categoriaDaoConfig;

    private final PalabraDao palabraDao;
    private final CategoriaDao categoriaDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        palabraDaoConfig = daoConfigMap.get(PalabraDao.class).clone();
        palabraDaoConfig.initIdentityScope(type);

        categoriaDaoConfig = daoConfigMap.get(CategoriaDao.class).clone();
        categoriaDaoConfig.initIdentityScope(type);

        palabraDao = new PalabraDao(palabraDaoConfig, this);
        categoriaDao = new CategoriaDao(categoriaDaoConfig, this);

        registerDao(Palabra.class, palabraDao);
        registerDao(Categoria.class, categoriaDao);
    }
    
    public void clear() {
        palabraDaoConfig.getIdentityScope().clear();
        categoriaDaoConfig.getIdentityScope().clear();
    }

    public PalabraDao getPalabraDao() {
        return palabraDao;
    }

    public CategoriaDao getCategoriaDao() {
        return categoriaDao;
    }

}
