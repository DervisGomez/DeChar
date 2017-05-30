package app.dechar;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Main {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"org.app.dechar.modelo");
        schema.enableKeepSectionsByDefault();
        createDataBase(schema);
        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema,args[0]);
    }
    public static void createDataBase(Schema schema){
        Entity palabra =schema.addEntity("Palabra");
        palabra.addIdProperty();
        palabra.addStringProperty("nombre");
        palabra.addStringProperty("descripcion");

        Entity categoria =schema.addEntity("Categoria");
        categoria.addIdProperty();
        categoria.addStringProperty("nombre");
        categoria.addStringProperty("descripcion");
        categoria.addBooleanProperty("admin");

        Entity tiempo=schema.addEntity("Tiempo");
        tiempo.addIdProperty();
        tiempo.addIntProperty("segundos");

        Property idCategoriaPalabra = palabra.addLongProperty("idCategoriaPalabra").getProperty();
        ToMany categoriaPalabra= categoria.addToMany(palabra, idCategoriaPalabra);
        categoriaPalabra.setName("Palabra");
    }
}
