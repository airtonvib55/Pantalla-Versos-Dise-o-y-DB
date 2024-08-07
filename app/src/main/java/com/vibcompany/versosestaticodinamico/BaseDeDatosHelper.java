package com.vibcompany.versosestaticodinamico;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaseDeDatosHelper extends SQLiteOpenHelper {

    private static final String db_nombre = "biblia_db.sqlite";
    private static final String db_path = "/data/data/com.vibcompany.versosestaticodinamico/databases/";
    private Context context;
    private static final int db_version = 1;

    public BaseDeDatosHelper(Context context) {
        super(context, db_nombre, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist){
            // Crea el directorio si no existe
            File dbDirectory = new File(db_path);
            if (!dbDirectory.exists()) {
                dbDirectory.mkdirs();
            }

            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e){
                //throw new Error("Error copiando la base de datos");
                Log.e("BaseDeDatosHelper", "Error copiando la base de datos", e);
            }
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = db_path + db_nombre;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){
            //throw new Error("Error al abrir la base de datos");
            Log.e("BaseDeDatosHelper", "Error al abrir la base de datos");
            //La base de datos no existe
        }
        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null;
    }

    public void copyDatabase() throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(db_nombre);
        String outFileName = db_path + db_nombre;
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))>0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public SQLiteDatabase openDatabase() throws SQLException {
        String myPath = db_path + db_nombre;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }
}
