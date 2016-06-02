package aghazadeh.ahmad.dbGeneretor;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class Main {
    String TAG="dbGenerator";
    public static void main(String[] args) throws Exception {

            Schema schema=new Schema(1,"aghazadeh.ahmad.smsblocke.db");

            Entity roles=schema.addEntity("Roles");
            roles.addIdProperty();
            roles.addStringProperty("roleName");
            roles.addBooleanProperty("isActive");
            roles.addStringProperty("condition");

            DaoGenerator dg=new DaoGenerator();
            dg.generateAll(schema,"./app/src/main/java");


    }
}
