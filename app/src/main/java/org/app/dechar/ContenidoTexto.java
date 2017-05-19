package org.app.dechar;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;
import org.app.dechar.modelo.Palabra;
import org.app.dechar.modelo.PalabraDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dervis on 17/05/17.
 */
public class ContenidoTexto {
    private List<String[]> texto=new ArrayList<>();

    public ContenidoTexto(){
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        List<Categoria> categorias=new ArrayList<>();
        categorias=categoriaDao.loadAll();
        if (categorias.size()<1){
            cargarCategorias();
            cargarPalabras();
        }
    }

    private void cargarPalabras(){
        DAOApp daoApp=new DAOApp();
        PalabraDao palabraDao=daoApp.getPalabraDao();
        List<Palabra> palabras=new ArrayList<>();
        long c=1;
        long x=1;
        palabras.add(new Palabra(x,"David","",c));
        x++;
        palabras.add(new Palabra(x,"Salomon","",c));
        x++;
        palabras.add(new Palabra(x,"Elias","",c));
        x++;
        palabras.add(new Palabra(x,"José","",c));
        x++;
        palabras.add(new Palabra(x,"Abrahan","",c));
        x++;
        palabras.add(new Palabra(x,"Pablo","",c));
        x++;
        palabras.add(new Palabra(x,"Pedro","",c));
        x++;
        palabras.add(new Palabra(x,"Juan","",c));
        x++;
        palabras.add(new Palabra(x,"Timoteo","",c));
        x++;
        palabras.add(new Palabra(x,"Lucas","",c));

        x++;
        c++;
        palabras.add(new Palabra(x,"Genesis","",c));
        x++;
        palabras.add(new Palabra(x,"Exodo","",c));
        x++;
        palabras.add(new Palabra(x,"Levitico","",c));
        x++;
        palabras.add(new Palabra(x,"Numeros","",c));
        x++;
        palabras.add(new Palabra(x,"Deutoronomio","",c));
        x++;
        palabras.add(new Palabra(x,"Mateo","",c));
        x++;
        palabras.add(new Palabra(x,"Marcos","",c));
        x++;
        palabras.add(new Palabra(x,"Lucas","",c));
        x++;
        palabras.add(new Palabra(x,"Juan","",c));
        x++;
        palabras.add(new Palabra(x,"Hechos","",c));

        palabraDao.insertInTx(palabras);

    }

    private void cargarCategorias(){
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        List<Categoria> categorias=new ArrayList<>();
        long x=1;
        categorias.add(new Categoria(x,"Personajes","",true));
        x++;
        categorias.add(new Categoria(x,"Libros","",true));
        x++;
        categorias.add(new Categoria(x,"Historias","",true));
        x++;
        categorias.add(new Categoria(x,"Lugares","",true));
        x++;
        categorias.add(new Categoria(x,"Versiculos","",true));
        x++;
        categoriaDao.insertInTx(categorias);
    }
    public List<Palabra> getTexto(int x){
        List<Palabra> palabras=new ArrayList<>();
        DAOApp daoApp=new DAOApp();
        PalabraDao palabraDao=daoApp.getPalabraDao();
        long j;
        switch (x){
            case 0:
                palabras=palabraDao.loadAll();
                break;
            case 1:
                j=1;
                palabras=palabraDao._queryCategoria_Palabra(j);
                break;
            case 2:
                j=2;
                palabras=palabraDao._queryCategoria_Palabra(j);
                break;
            default:
                break;
        }
        return palabras;
    }
    public List<Palabra> getVerificar(int x,long[] item){
        List<Palabra> palabras=new ArrayList<>();
        DAOApp daoApp=new DAOApp();
        PalabraDao palabraDao=daoApp.getPalabraDao();
        for (int i=0;i<item.length;i++){
            palabras.add(palabraDao.load(item[i]));
        }
        return palabras;
    }
}
