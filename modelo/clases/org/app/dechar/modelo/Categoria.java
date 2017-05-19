package org.app.dechar.modelo;

import java.util.List;
import org.app.dechar.modelo.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table CATEGORIA.
 */
public class Categoria {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean admin;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CategoriaDao myDao;

    private List<Palabra> Palabra;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Categoria() {
    }

    public Categoria(Long id) {
        this.id = id;
    }

    public Categoria(Long id, String nombre, String descripcion, Boolean admin) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.admin = admin;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoriaDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Palabra> getPalabra() {
        if (Palabra == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PalabraDao targetDao = daoSession.getPalabraDao();
            List<Palabra> PalabraNew = targetDao._queryCategoria_Palabra(id);
            synchronized (this) {
                if(Palabra == null) {
                    Palabra = PalabraNew;
                }
            }
        }
        return Palabra;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetPalabra() {
        Palabra = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
