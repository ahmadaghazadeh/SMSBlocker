package aghazadeh.ahmad;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import aghazadeh.ahmad.logic.DBHelper;
import aghazadeh.ahmad.smsblocke.db.DaoMaster;
import aghazadeh.ahmad.smsblocke.db.DaoSession;
import aghazadeh.ahmad.smsblocke.db.RolesDao;


/**
 * Created by 890683 on 5/25/2016.
 */
public class DaoAPP extends Application {
    static RolesDao roles;


    public static RolesDao getRolesDao() {
        return roles;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // Create new Db -------------------------------------------------
            // DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"rules-db.db",null);
           //  SQLiteDatabase db=helper.getWritableDatabase();
            // ----------------------------------------------------

            // Exist Db ------------------------------------------
            DBHelper helper = new DBHelper(this, "rules-db.db", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            //----------------------------------------------------
            DaoMaster daoMaster=new DaoMaster(db);
            DaoSession daoSession=daoMaster.newSession();
            roles=daoSession.getRolesDao();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}
