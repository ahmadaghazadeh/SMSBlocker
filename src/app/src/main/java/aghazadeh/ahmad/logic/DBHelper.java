package aghazadeh.ahmad.logic;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import aghazadeh.ahmad.smsblocke.db.DaoMaster;
import aghazadeh.ahmad.smsblocker.R;


/**
 * Created by 890683 on 2016/06/01.
 */

public class DBHelper extends DaoMaster.OpenHelper {

    private Context context;

    private SQLiteDatabase sqliteDatabase;



    private static String DB_NAME;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.context = context;
        this.DB_NAME = name;

        try {
            createDataBase();
        } catch (Exception ioe) {
            throw new Error("Unable to create database");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    //** Open Database for Use *//*
    public void openDatabase() {
        String databasePath = context.getDatabasePath(DB_NAME).getAbsolutePath() ;
        sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null,
                (SQLiteDatabase.OPEN_READWRITE));
    }

    //** Close Database after use *//*
    @Override
    public synchronized void close() {
        if ((sqliteDatabase != null) && sqliteDatabase.isOpen()) {
            sqliteDatabase.close();
        }
        super.close();
    }

    //** Get database instance for use *//*
    public SQLiteDatabase getSqliteDatabase() {
        return sqliteDatabase;
    }
String TAG="TAG";
    //** Create new database if not present *//*
    public void createDataBase() {
        SQLiteDatabase sqliteDatabase = null;

        if (databaseExists()) {
            Log.e(TAG, "createDataBase: "+"True" );
            //* Check for Upgrade *//*
        } else {
            //* Database does not exists create blank database *//*
            sqliteDatabase = this.getReadableDatabase();
            sqliteDatabase.close();
            Log.e(TAG, "createDataBase: "+"false" );
            copyDataBase();
        }
    }

    //** Check Database if it exists *//*
    private boolean databaseExists() {
        SQLiteDatabase sqliteDatabase = null;
        try {
            String databasePath =  context.getDatabasePath(DB_NAME).getAbsolutePath();
            sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }
        return sqliteDatabase != null ? true : false;
    }

    //**
    //  Copy existing database file in system
     //*
    public void copyDataBase() {

        int length;
        byte[] buffer = new byte[1024];
        String databasePath =  context.getDatabasePath(DB_NAME).getAbsolutePath();
        Log.e(TAG, "copyDataBase: "+"enter" );
        try {
            InputStream databaseInputFile = this.context.getAssets().open(DB_NAME);

            OutputStream databaseOutputFile = new FileOutputStream(databasePath);
try {
    PackageManager m =context. getPackageManager();
    String s =context. getPackageName();
    PackageInfo p = m.getPackageInfo(s, 0);
    s = p.applicationInfo.dataDir;
    Log.e(TAG, s);
}catch (Exception e)
{

}


            Log.e(TAG, "copyDataBase: "+"databaseOutputFile" );
            while ((length = databaseInputFile.read(buffer)) > 0) {
                databaseOutputFile.write(buffer, 0, length);
                databaseOutputFile.flush();
            }
            databaseInputFile.close();
            databaseOutputFile.close();

        } catch (FileNotFoundException e) {
            Log.e(TAG, "copyDataBase: "+"FileNotFoundException  "+e.getMessage() );
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "copyDataBase: "+"IOException"+e.getMessage() );
            e.printStackTrace();
        }

    }
}
