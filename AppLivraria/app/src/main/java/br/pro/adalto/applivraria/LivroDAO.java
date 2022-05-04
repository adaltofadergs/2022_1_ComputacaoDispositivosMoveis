package br.pro.adalto.applivraria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public static void inserir(Context context, Livro livro){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", livro.getTitulo() );
        valores.put("autor", livro.getAutor() );
        valores.put("codGenero", livro.getGenero().getId() );

        db.insert("livro", null, valores);

        db.close();
    }


    public static void editar(Context context, Livro livro){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", livro.getTitulo() );
        valores.put("autor", livro.getAutor() );
        valores.put("codGenero", livro.getGenero().getId() );

        db.update("livro",  valores, " id = " + livro.getId(), null);

        db.close();
    }


    public static void excluir(Context context, int idLivro){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("livro", " id = " + idLivro, null);

        db.close();
    }



    public static List<Livro> getLivros(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                " SELECT l.id, l.titulo, l.autor, g.id, g.nome " +
                        " FROM livro l " +
                        " INNER JOIN genero g ON g.id = l.codGenero " +
                        " ORDER BY l.titulo ",
                null );

        List<Livro> lista = new ArrayList<>();

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            do{
                Genero g = new Genero();
                g.setId(  cursor.getInt( 3 ) );
                g.setNome(  cursor.getString( 4 ) );

                Livro l = new Livro();
                l.setId( cursor.getInt(0));
                l.setTitulo( cursor.getString(1));
                l.setAutor( cursor.getString(2));
                l.setGenero( g );

                lista.add( l );
            }while (cursor.moveToNext());
        }
        return lista;
    }

}
