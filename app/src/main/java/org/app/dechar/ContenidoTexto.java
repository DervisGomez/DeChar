package org.app.dechar;

import org.app.dechar.modelo.Categoria;
import org.app.dechar.modelo.CategoriaDao;
import org.app.dechar.modelo.DAOApp;
import org.app.dechar.modelo.Palabra;
import org.app.dechar.modelo.PalabraDao;
import org.app.dechar.modelo.Tiempo;
import org.app.dechar.modelo.TiempoDao;

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

    public int getTiempo(){
        DAOApp daoApp=new DAOApp();
        TiempoDao tiempoDao=daoApp.getTiempoDao();
        List<Tiempo> tiempos=tiempoDao.loadAll();
        int tiempo;
        if (tiempos.size()>0){
            tiempo=tiempos.get(0).getSegundos();
        }else{
            tiempo=60;
        }
        return tiempo;
    }

    public String[] getAyuda(int x){
        String[] aa;
        switch (x){
            case 1:
                aa= new String[]{"Tutorial de charadas biblica\n\nJugar es muy sencillo y devertido y aqui lo verás\n\ndesliza hacia la izquierda para leer más",
                        "Lo primero que debes hacer es elegir una categoria\n\nluego pon el móvil o tablet en tu frente",
                        "Seguidamente tu familia o amigos tienen que darte pistas de la palabra que esta en tu frente\n\nSi aciertas, inclina el dispositivo hacia arriba",
                        "Si no logras acertar la palabra, tienes que pasar a otra ¡porque el tiempo se agota!\n\nPara hacerlo inclina el dispositivo hacia abajo",
                        "¡Y eso es todo!\n\nAhora lo que tienes que hacer es divertirte y reta a tu familia y/o amigos"
                };
                return aa;
            case 2:
                aa= new String[]{"2Tutorial de charadas biblica\nJues es muy sencillo y devertido y aqui lo verás\n\ndesliza hacia la izquierda para leer más",
                        "Lo primero que debes hacer es elegir una categoria\n\nluego pon el móvil o tablet en tu frente",
                        "Seguidamente tu familia o amigos tienen que darte pistas de la palabra que esta en tu frente\n\nSi aciertas, inclina el dispositivo hacia arriba",
                        "Si no logras acertar la palabra, tienes que pasar a otra ¡porque el tiempo se agota!\n\nPara hacerlo inclina el dispositivo hacia abajo",
                        "¡Y eso es todo!\n\nAhora lo que tienes que hacer es divertirte y reta a tu familia y/o amigos"
                };
                return aa;
            case 3:
                aa= new String[]{"3Tutorial de charadas biblica\nJues es muy sencillo y devertido y aqui lo verás\n\ndesliza hacia la izquierda para leer más",
                        "Lo primero que debes hacer es elegir una categoria\n\nluego pon el móvil o tablet en tu frente",
                        "Seguidamente tu familia o amigos tienen que darte pistas de la palabra que esta en tu frente\n\nSi aciertas, inclina el dispositivo hacia arriba",
                        "Si no logras acertar la palabra, tienes que pasar a otra ¡porque el tiempo se agota!\n\nPara hacerlo inclina el dispositivo hacia abajo",
                        "¡Y eso es todo!\n\nAhora lo que tienes que hacer es divertirte y reta a tu familia y/o amigos"
                };
                return aa;
            default:
                return null;
        }
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
        categorias.add(new Categoria(x,"Lugares","",true));
        x++;
        categorias.add(new Categoria(x,"Palabras","",true));
        x++;
        categorias.add(new Categoria(x,"Actualo","",true));
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
                CategoriaDao categoriaDao=daoApp.getCategoriaDao();
                List<Categoria> categorias=categoriaDao.queryBuilder().where(CategoriaDao.Properties.Admin.in(true)).list();
                for (int i=0;i<categorias.size();i++){
                    List<Palabra> palabras1=palabraDao.queryBuilder().where(PalabraDao.Properties.IdCategoriaPalabra.in(categorias.get(i).getId())).list();
                    palabras.addAll(palabras1);
                }
                palabras=palabraDao.loadAll();
                break;
            case -1:
                j=4;
                palabras=palabraDao._queryCategoria_Palabra(j);
                break;
            default:
                j=x;
                palabras=palabraDao._queryCategoria_Palabra(j);
                break;
        }
        return palabras;
    }
    public List<Categoria> getMisCategoria(){
        DAOApp daoApp=new DAOApp();
        CategoriaDao categoriaDao=daoApp.getCategoriaDao();
        List<Categoria> categorias=categoriaDao.queryBuilder().where(CategoriaDao.Properties.Admin.in(false)).list();
        return categorias;
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

    private void cargarPalabras(){
        DAOApp daoApp=new DAOApp();
        PalabraDao palabraDao=daoApp.getPalabraDao();
        List<Palabra> palabras=new ArrayList<>();
        long c=1;
        long x=1;
        //Personajes de la biblia
        palabras.add(new Palabra(x,"Abrahám","",c));
        x++;
        palabras.add(new Palabra(x,"Adán","",c));
        x++;
        palabras.add(new Palabra(x,"Abel","",c));
        x++;
        palabras.add(new Palabra(x,"Abdías","",c));
        x++;
        palabras.add(new Palabra(x,"Agar","",c));
        x++;
        palabras.add(new Palabra(x,"Amos","",c));
        x++;
        palabras.add(new Palabra(x,"Bernabé","",c));
        x++;
        palabras.add(new Palabra(x,"Caifás","",c));
        x++;
        palabras.add(new Palabra(x,"Caín","",c));
        x++;
        palabras.add(new Palabra(x,"César","",c));
        x++;

        palabras.add(new Palabra(x,"Daniel","",c));
        x++;
        palabras.add(new Palabra(x,"David","",c));
        x++;
        palabras.add(new Palabra(x,"Josué","",c));
        x++;
        palabras.add(new Palabra(x,"Efraím","",c));
        x++;
        palabras.add(new Palabra(x,"Elías","",c));
        x++;
        palabras.add(new Palabra(x,"Eleazar","",c));
        x++;
        palabras.add(new Palabra(x,"Esdras","",c));
        x++;
        palabras.add(new Palabra(x,"Esther","",c));
        x++;
        palabras.add(new Palabra(x,"Eva","",c));
        x++;
        palabras.add(new Palabra(x,"Ezequiel","",c));
        x++;

        palabras.add(new Palabra(x,"Felipe","",c));
        x++;
        palabras.add(new Palabra(x,"Filemón","",c));
        x++;
        palabras.add(new Palabra(x,"Gabriel","",c));
        x++;
        palabras.add(new Palabra(x,"Habacuc","",c));
        x++;
        palabras.add(new Palabra(x,"Herodes","",c));
        x++;
        palabras.add(new Palabra(x,"Ismael","",c));
        x++;
        palabras.add(new Palabra(x,"Israel","",c));
        x++;
        palabras.add(new Palabra(x,"Isaías","",c));
        x++;
        palabras.add(new Palabra(x,"Jacob","",c));
        x++;
        palabras.add(new Palabra(x,"Jeremías","",c));
        x++;

        palabras.add(new Palabra(x,"Jesús","",c));
        x++;
        palabras.add(new Palabra(x,"Job","",c));
        x++;
        palabras.add(new Palabra(x,"Joel","",c));
        x++;
        palabras.add(new Palabra(x,"Jonás","",c));
        x++;
        palabras.add(new Palabra(x,"José","",c));
        x++;
        palabras.add(new Palabra(x,"Josué","",c));
        x++;
        palabras.add(new Palabra(x,"Juan","",c));
        x++;
        palabras.add(new Palabra(x,"Judas","",c));
        x++;
        palabras.add(new Palabra(x,"Leví","",c));
        x++;
        palabras.add(new Palabra(x,"Lucas","",c));
        x++;

        palabras.add(new Palabra(x,"Marcos","",c));
        x++;
        palabras.add(new Palabra(x,"María","",c));
        x++;
        palabras.add(new Palabra(x,"Mateo","",c));
        x++;
        palabras.add(new Palabra(x,"Matías","",c));
        x++;
        palabras.add(new Palabra(x,"Melquisedec","",c));
        x++;
        palabras.add(new Palabra(x,"Miqueas","",c));
        x++;
        palabras.add(new Palabra(x,"Moab","",c));
        x++;
        palabras.add(new Palabra(x,"Judas","",c));
        x++;
        palabras.add(new Palabra(x,"Moisés","",c));
        x++;
        palabras.add(new Palabra(x,"Nahum","",c));
        x++;

        palabras.add(new Palabra(x,"Noé","",c));
        x++;
        palabras.add(new Palabra(x,"Pablo","",c));
        x++;
        palabras.add(new Palabra(x,"Pedro","",c));
        x++;
        palabras.add(new Palabra(x,"Rut","",c));
        x++;
        palabras.add(new Palabra(x,"Salomón","",c));
        x++;
        palabras.add(new Palabra(x,"Samuel","",c));
        x++;
        palabras.add(new Palabra(x,"Santiago","",c));
        x++;
        palabras.add(new Palabra(x,"Timoteo","",c));
        x++;
        palabras.add(new Palabra(x,"Tomás","",c));
        x++;

        palabras.add(new Palabra(x,"Saul","",c));
        x++;
        palabras.add(new Palabra(x,"Zacarías","",c));
        x++;
        palabras.add(new Palabra(x,"Nicodemo","",c));
        x++;
        palabras.add(new Palabra(x,"Nabucodonosor","",c));
        x++;
        palabras.add(new Palabra(x,"Silas","",c));
        x++;
        palabras.add(new Palabra(x,"Isaac","",c));
        x++;
        palabras.add(new Palabra(x,"Zaqueo","",c));
        x++;

        //Libros de la biblia
        c++;
        palabras.add(new Palabra(x,"Génesis","",c));
        x++;
        palabras.add(new Palabra(x,"Éxodo","",c));
        x++;
        palabras.add(new Palabra(x,"Levítico","",c));
        x++;
        palabras.add(new Palabra(x,"Números","",c));
        x++;
        palabras.add(new Palabra(x,"Deuteronomio","",c));
        x++;
        palabras.add(new Palabra(x,"Josué","",c));
        x++;
        palabras.add(new Palabra(x,"Jueces","",c));
        x++;
        palabras.add(new Palabra(x,"Rut","",c));
        x++;
        palabras.add(new Palabra(x,"1 Samuel","",c));
        x++;
        palabras.add(new Palabra(x,"2 Samuel","",c));
        x++;

        palabras.add(new Palabra(x,"1 Reyes","",c));
        x++;
        palabras.add(new Palabra(x,"2 Reyes","",c));
        x++;
        palabras.add(new Palabra(x,"1 Crónicas","",c));
        x++;
        palabras.add(new Palabra(x,"2 Crónicas","",c));
        x++;
        palabras.add(new Palabra(x,"Esdras","",c));
        x++;
        palabras.add(new Palabra(x,"Nehemías","",c));
        x++;
        palabras.add(new Palabra(x,"Ester","",c));
        x++;
        palabras.add(new Palabra(x,"Job","",c));
        x++;
        palabras.add(new Palabra(x,"Juan","",c));
        x++;
        palabras.add(new Palabra(x,"Los Salmos","",c));
        x++;

        palabras.add(new Palabra(x,"Proverbios","",c));
        x++;
        palabras.add(new Palabra(x,"Eclesiastés","",c));
        x++;
        palabras.add(new Palabra(x,"Cantares","",c));
        x++;
        palabras.add(new Palabra(x,"Isaías","",c));
        x++;
        palabras.add(new Palabra(x,"Jeremías","",c));
        x++;
        palabras.add(new Palabra(x,"Lamentaciones","",c));
        x++;
        palabras.add(new Palabra(x,"Ezequiel","",c));
        x++;
        palabras.add(new Palabra(x,"Daniel","",c));
        x++;
        palabras.add(new Palabra(x,"Oseas","",c));
        x++;
        palabras.add(new Palabra(x,"Joel","",c));
        x++;

        palabras.add(new Palabra(x,"Amós","",c));
        x++;
        palabras.add(new Palabra(x,"Jonás","",c));
        x++;
        palabras.add(new Palabra(x,"Miqueas","",c));
        x++;
        palabras.add(new Palabra(x,"Nahúm","",c));
        x++;
        palabras.add(new Palabra(x,"Habacuc","",c));
        x++;
        palabras.add(new Palabra(x,"Sofonías","",c));
        x++;
        palabras.add(new Palabra(x,"Hageo","",c));
        x++;
        palabras.add(new Palabra(x,"Zacarías","",c));
        x++;
        palabras.add(new Palabra(x,"Malaquías","",c));
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
        x++;
        palabras.add(new Palabra(x,"Romanos","",c));
        x++;
        palabras.add(new Palabra(x,"1 Corintios","",c));
        x++;
        palabras.add(new Palabra(x,"2 Corintios","",c));
        x++;
        palabras.add(new Palabra(x,"Gálatas","",c));
        x++;
        palabras.add(new Palabra(x,"Efesios","",c));
        x++;
        palabras.add(new Palabra(x,"Filipenses","",c));
        x++;

        palabras.add(new Palabra(x,"Colosenses","",c));
        x++;
        palabras.add(new Palabra(x,"1 Tesalonicenses","",c));
        x++;
        palabras.add(new Palabra(x,"2 Tesalonicenses","",c));
        x++;
        palabras.add(new Palabra(x,"1 Timoteos","",c));
        x++;
        palabras.add(new Palabra(x,"2 Timoteo","",c));
        x++;
        palabras.add(new Palabra(x,"Tito","",c));
        x++;
        palabras.add(new Palabra(x,"Filemón","",c));
        x++;
        palabras.add(new Palabra(x,"Hebreos","",c));
        x++;
        palabras.add(new Palabra(x,"Santiago","",c));
        x++;
        palabras.add(new Palabra(x,"1 Pedro","",c));
        x++;

        palabras.add(new Palabra(x,"2 Pedro","",c));
        x++;
        palabras.add(new Palabra(x,"1 Juan","",c));
        x++;
        palabras.add(new Palabra(x,"2 Juan","",c));
        x++;
        palabras.add(new Palabra(x,"3 Juan","",c));
        x++;
        palabras.add(new Palabra(x,"Judas","",c));
        x++;
        palabras.add(new Palabra(x,"Apocalipsis","",c));
        x++;

        //Lugares de la biblia
        c++;
        palabras.add(new Palabra(x,"Antioquía","",c));
        x++;
        palabras.add(new Palabra(x,"Aposento Alto","",c));
        x++;
        palabras.add(new Palabra(x,"Aremitea","",c));
        x++;
        palabras.add(new Palabra(x,"Atenas","",c));
        x++;
        palabras.add(new Palabra(x,"Babilonia","",c));
        x++;
        palabras.add(new Palabra(x,"Belén","",c));
        x++;
        palabras.add(new Palabra(x,"Berea","",c));
        x++;
        palabras.add(new Palabra(x,"Bet-el","",c));
        x++;
        palabras.add(new Palabra(x,"Betania","",c));
        x++;
        palabras.add(new Palabra(x,"Calvario","",c));
        x++;

        palabras.add(new Palabra(x,"Capernaum","",c));
        x++;
        palabras.add(new Palabra(x,"Filipo","",c));
        x++;
        palabras.add(new Palabra(x,"Chipre","",c));
        x++;
        palabras.add(new Palabra(x,"Colosas","",c));
        x++;
        palabras.add(new Palabra(x,"Corinto","",c));
        x++;
        palabras.add(new Palabra(x,"Damasco","",c));
        x++;
        palabras.add(new Palabra(x,"Derbe","",c));
        x++;
        palabras.add(new Palabra(x,"Egipto","",c));
        x++;
        palabras.add(new Palabra(x,"Éfeso","",c));
        x++;
        palabras.add(new Palabra(x,"Emaús","",c));
        x++;

        palabras.add(new Palabra(x,"Filistea","",c));
        x++;
        palabras.add(new Palabra(x,"Gaza","",c));
        x++;
        palabras.add(new Palabra(x,"Genesaret","",c));
        x++;
        palabras.add(new Palabra(x,"Getsemaní","",c));
        x++;
        palabras.add(new Palabra(x,"Hebrón","",c));
        x++;
        palabras.add(new Palabra(x,"Jericó","",c));
        x++;
        palabras.add(new Palabra(x,"Jerusalén","",c));
        x++;
        palabras.add(new Palabra(x,"Jope","",c));
        x++;
        palabras.add(new Palabra(x,"Laodicea","",c));
        x++;
        palabras.add(new Palabra(x,"Líbano","",c));
        x++;

        palabras.add(new Palabra(x,"Listra","",c));
        x++;
        palabras.add(new Palabra(x,"Monte Carmelo","",c));
        x++;
        palabras.add(new Palabra(x,"Nazaret","",c));
        x++;
        palabras.add(new Palabra(x,"Nínive","",c));
        x++;
        palabras.add(new Palabra(x,"Pafos","",c));
        x++;
        palabras.add(new Palabra(x,"Palestina","",c));
        x++;
        palabras.add(new Palabra(x,"Patmos","",c));
        x++;
        palabras.add(new Palabra(x,"Siquem","",c));
        x++;
        palabras.add(new Palabra(x,"Samaria","",c));
        x++;
        palabras.add(new Palabra(x,"Siria","",c));
        x++;

        palabras.add(new Palabra(x,"Sodoma","",c));
        x++;
        palabras.add(new Palabra(x,"Gomorra","",c));
        x++;
        palabras.add(new Palabra(x,"Tarso","",c));
        x++;
        palabras.add(new Palabra(x,"Tesalónica","",c));
        x++;
        palabras.add(new Palabra(x,"Tiro","",c));
        x++;
        palabras.add(new Palabra(x,"Troas","",c));
        x++;
        palabras.add(new Palabra(x,"Monte Sinaí","",c));//////////
        x++;
        palabras.add(new Palabra(x,"Monte de los Olivos","",c));
        x++;
        palabras.add(new Palabra(x,"Babel","",c));
        x++;
        palabras.add(new Palabra(x,"Galilea","",c));
        x++;

        palabras.add(new Palabra(x,"Canaan","",c));
        x++;


        c++;
        palabras.add(new Palabra(x,"Orar","",c));
        x++;
        palabras.add(new Palabra(x,"Predicar","",c));
        x++;
        palabras.add(new Palabra(x,"Caminar","",c));
        x++;
        palabras.add(new Palabra(x,"Cantar","",c));
        x++;
        palabras.add(new Palabra(x,"Trabajar","",c));
        x++;
        palabras.add(new Palabra(x,"Amar","",c));
        x++;
        palabras.add(new Palabra(x,"Don","",c));//////////
        x++;
        palabras.add(new Palabra(x,"Alababanza","",c));
        x++;
        palabras.add(new Palabra(x,"Adoracion","",c));
        x++;
        palabras.add(new Palabra(x,"Verdad","",c));
        x++;

        palabras.add(new Palabra(x,"mandamiento","",c));
        x++;
        palabras.add(new Palabra(x,"Pecado","",c));
        x++;
        palabras.add(new Palabra(x,"Santa Cena","",c));
        x++;
        palabras.add(new Palabra(x,"Religioso","",c));
        x++;
        palabras.add(new Palabra(x,"Fe","",c));
        x++;
        palabras.add(new Palabra(x,"Misericordia","",c));
        x++;
        palabras.add(new Palabra(x,"Resurrección","",c));//////////
        x++;
        palabras.add(new Palabra(x,"Cruz","",c));
        x++;
        palabras.add(new Palabra(x,"Vida Eterna","",c));
        x++;
        palabras.add(new Palabra(x,"Bautizo","",c));
        x++;

        c++;
        palabras.add(new Palabra(x,"Orar","",c));
        x++;
        palabras.add(new Palabra(x,"Predicar","",c));
        x++;
        palabras.add(new Palabra(x,"Caminar","",c));
        x++;
        palabras.add(new Palabra(x,"Cantar","",c));
        x++;
        palabras.add(new Palabra(x,"Trabajar","",c));
        x++;
        palabras.add(new Palabra(x,"Amar","",c));
        x++;
        palabras.add(new Palabra(x,"Regalar","",c));//////////
        x++;
        palabras.add(new Palabra(x,"Alababar","",c));
        x++;
        palabras.add(new Palabra(x,"Adorar","",c));
        x++;
        palabras.add(new Palabra(x,"Matar","",c));
        x++;

        palabras.add(new Palabra(x,"Mandar","",c));
        x++;
        palabras.add(new Palabra(x,"Pecar","",c));
        x++;
        palabras.add(new Palabra(x,"Cenar","",c));
        x++;
        palabras.add(new Palabra(x,"Encontrar","",c));
        x++;
        palabras.add(new Palabra(x,"Confiar","",c));
        x++;
        palabras.add(new Palabra(x,"Cambiar","",c));
        x++;
        palabras.add(new Palabra(x,"Resucitar","",c));//////////
        x++;
        palabras.add(new Palabra(x,"Crucificar","",c));
        x++;
        palabras.add(new Palabra(x,"Enviar","",c));
        x++;
        palabras.add(new Palabra(x,"Bautizar","",c));
        x++;

        palabraDao.insertInTx(palabras);

    }
}
