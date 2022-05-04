package com.example.appagenda_2022_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spEstado, spCidade;

    private String[] pr = {"Selecione...", "Curitiba", "Londrina", "Pato Branco"};
    private String[] rs = {"Selecione...", "Alvorada", "Canoas", "Porto Alegre"};
    private String[] sc = {"Selecione...", "Florianópolis", "Praia Grande", "Passo de Torres"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spEstado = (Spinner) findViewById(R.id.spEstado);
        spCidade = findViewById(R.id.spCidade);

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregarCidades();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void carregarCidades(){

        spCidade.setEnabled(true);
        String[] cidades = {"Selecione o estado..."};
        int posicao = spEstado.getSelectedItemPosition();
        switch (posicao){
            case 1:
                cidades = pr;
                break;
            case 2 :
                cidades = rs;
                break;
            case 3:
                cidades = sc;
                break;
            default:
                spCidade.setEnabled(false);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cidades);
        spCidade.setAdapter( adapter );
    }
}









