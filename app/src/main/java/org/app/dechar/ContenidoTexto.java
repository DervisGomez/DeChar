package org.app.dechar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dervis on 17/05/17.
 */
public class ContenidoTexto {
    private List<String[]> texto=new ArrayList<>();

    private void getPersonaje(){
        texto.clear();
        texto.add(new String[]{"0","David"});
        texto.add(new String[]{"1","José"});
        texto.add(new String[]{"2","Moises"});
        texto.add(new String[]{"3","Juan"});
        texto.add(new String[]{"4","Pedro"});
        texto.add(new String[]{"5","Lucas"});
        texto.add(new String[]{"6","María"});
        texto.add(new String[]{"7","Jesús"});
        texto.add(new String[]{"8","Salomon"});
        texto.add(new String[]{"9","Jonas"});
    }
    private void getLibro(){
        texto.clear();
        texto.add(new String[]{"0","Genesis"});
        texto.add(new String[]{"1","Mateo"});
        texto.add(new String[]{"2","Moises"});
        texto.add(new String[]{"3","Marcos"});
        texto.add(new String[]{"4","Lucas"});
        texto.add(new String[]{"5","Juan"});
        texto.add(new String[]{"6","Hechos"});
        texto.add(new String[]{"7","Romanos"});
        texto.add(new String[]{"8","1 Corintios"});
        texto.add(new String[]{"9","2 Corintios"});
    }
    public List<String[]> getTexto(int x){
        switch (x){
            case 1:
                getPersonaje();
                break;
            case 2:
                getLibro();
                break;
            default:
                break;
        }
        return texto;
    }
    public List<String[]> getVerificar(int x,String[] item){
        List<String[]> text=new ArrayList<>();
        switch (x){
            case 1:
                getPersonaje();
                text=extraerLista(item);
                break;
            case 2:
                getLibro();
                text=extraerLista(item);
                break;
            default:
                break;
        }
        return text;
    }
    private List<String[]> extraerLista(String[] item){
        List<String[]> text=new ArrayList<>();
        for (int x=0;x<item.length;x++){
            text.add(texto.get(Integer.valueOf(item[x])));
        }
        return text;
    }
}
