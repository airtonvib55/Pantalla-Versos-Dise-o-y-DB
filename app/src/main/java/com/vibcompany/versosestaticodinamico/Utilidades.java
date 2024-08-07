package com.vibcompany.versosestaticodinamico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {

    private SQLiteDatabase db;
    private BaseDeDatosHelper dbhelper;

    public Utilidades(Context context) {
        dbhelper = new BaseDeDatosHelper(context);
        try {
            dbhelper.createDatabase(); // Asegúrate de que la base de datos existe
            db = dbhelper.openDatabase(); // Abre la base de datos
        } catch (IOException e) {
            Log.e("Utilidades", "Error al crear la base de datos", e);
        }
    }

    //metodo para consultar
    public List<Versiculo> obtenerVersos(String numCapitulo, String numVerso, String numLibro) {
        //lista para guardar los datos
        List<Versiculo> listaVersos = new ArrayList<>();

        //consulta con parametros
        //String consulta = "select text from texts where chapter_id = ? and chapter_num = ? and position = ?;";
        String[] parametros = {numLibro, numCapitulo};
        String consulta = "select position, text from texts where chapter_id = ? and chapter_num = ? and position BETWEEN 1 AND 25;";

        //ejecutar consulta
        Cursor cursor = db.rawQuery(consulta, parametros);

        //llenar la lista
        while(cursor.moveToNext()){
            //listaVersos.add(cursor.getString(cursor.getColumnIndexOrThrow("text")));
            Versiculo verso = new Versiculo(cursor.getString(cursor.getColumnIndexOrThrow("position")), cursor.getString(cursor.getColumnIndexOrThrow("text")));
            listaVersos.add(verso);
        }
        return listaVersos;
    }
}
