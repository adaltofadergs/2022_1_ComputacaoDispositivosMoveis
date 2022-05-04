package com.example.aula01_2022_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etValor;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValor = findViewById( R.id.etValor );
        btnCalcular = findViewById( R.id.btnCalcular );
        tvResultado = findViewById( R.id.tvResultado );

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });

    }

    private void calcular(){
        String sValor = etValor.getText().toString();
        double valor = Double.valueOf( sValor );
        double result = valor * 2;
        tvResultado.setText( String.valueOf( result ) );
    }

}