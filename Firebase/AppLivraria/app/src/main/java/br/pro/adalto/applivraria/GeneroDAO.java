package br.pro.adalto.applivraria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public static void inserir(Context context, String nome){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL("INSERT INTO genero (nome) VALUES (  '" + nome + "'  ) " ) ;
        db.close();
    }


    public static List<Genero> getGeneros(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery( "SELECT id, nome FROM genero ORDER BY nome", null );

        List<Genero> lista = new ArrayList<>();

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            do{
                Genero g = new Genero();
                g.setId(  cursor.getInt( 0 ) );
                g.setNome(  cursor.getString( 1 ) );
                lista.add( g );
            }while (cursor.moveToNext());
        }
        return lista;
    }

}
