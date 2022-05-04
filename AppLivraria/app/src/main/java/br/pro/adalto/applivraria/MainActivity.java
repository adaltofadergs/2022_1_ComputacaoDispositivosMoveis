package br.pro.adalto.applivraria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spGeneros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spGeneros = findViewById(R.id.spGenero);

        carregarGeneros();

    }

    private void carregarGeneros(){
        Genero fake = new Genero(0, "Selecione o Gênero...");
        List<Genero> lista = GeneroDAO.getGeneros(  this );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista );
        spGeneros.setAdapter( adapter );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Cadastrar Gênero...");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.toString().equals("Cadastrar Gênero...")){
            //          GeneroDAO.inserir(MainActivity.this, "Romance");
            //          carregarGeneros();
            cadastrarGenero();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cadastrarGenero(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Cadastrar Gênero");
        alerta.setIcon(android.R.drawable.ic_input_add);

        EditText etNomeGenero = new EditText(this);
        etNomeGenero.setHint("Digite aqui o nome do gênero...");
        //    alerta.setMessage("Olá");
        alerta.setView( etNomeGenero );

        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = etNomeGenero.getText().toString();
                if( !nome.isEmpty() ){
                    GeneroDAO.inserir(MainActivity.this, nome);
                    carregarGeneros();
                }
            }
        });
        alerta.show();

    }


}