package br.pro.adalto.applivraria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

}