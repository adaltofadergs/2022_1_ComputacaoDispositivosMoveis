package br.pro.adalto.applivraria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class LivrosActivity extends AppCompatActivity {

    private ListView lvLivros;
    private Button btnAdicionar;


    FirebaseDatabase database;
    DatabaseReference reference;

    ChildEventListener childEventListener;
    Query query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);

        //Firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();


        lvLivros = findViewById(R.id.lvLivros);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LivrosActivity.this, MainActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity( intent );
            }
        });


        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Livro livroSelecionado = lista.get( position );

                Intent intent = new Intent(LivrosActivity.this, MainActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idLivro", livroSelecionado.getId() );
                intent.putExtra("titulo", livroSelecionado.getTitulo() );
                intent.putExtra("autor", livroSelecionado.getAutor() );
                startActivity( intent );

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLivros();

        lista.clear();
        query = reference.child("livros").orderByChild("autor");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Livro l = new Livro();
                l.setId( snapshot.getKey() );
                l.setTitulo( snapshot.child("titulo").getValue(String.class) );
                l.setAutor( snapshot.child("autor").getValue(String.class) );
                l.setGenero(snapshot.child("genero").getValue(Genero.class) );
                lista.add( l );
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idLivro = snapshot.getKey();
                for( Livro l : lista){
                    if( l.getId().equals( idLivro) ){
                        l.setTitulo( snapshot.child("titulo").getValue(String.class) );
                        l.setAutor( snapshot.child("autor").getValue(String.class) );
                        l.setGenero(snapshot.child("genero").getValue(Genero.class) );
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String idLivro = snapshot.getKey();
                for( Livro l : lista){
                    if( l.getId().equals( idLivro) ){
                        lista.remove( l );
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        query.addChildEventListener( childEventListener );

    }

    @Override
    protected void onStop() {
        super.onStop();
        query.removeEventListener( childEventListener );
    }

    private void carregarLivros(){
//        List<Livro> lista =  new ArrayList<>();// LivroDAO.getLivros(this);
//
//        if( lista.size() == 0 ){
//            Livro fake = new Livro("Nenhum livro cadastrado", "...", null);
//            lista.add( fake );
//            lvLivros.setEnabled( false );
//        }else {
//            lvLivros.setEnabled( true );
//        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lista);
        lvLivros.setAdapter( adapter );
    }

    private List<Livro> lista = new ArrayList<>();
    private ArrayAdapter adapter;

}