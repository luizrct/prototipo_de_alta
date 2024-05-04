package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tnome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        tnome = findViewById(R.id.textNome);
        if(extras != null){
            String nome = extras.getString("nome");
            tnome.setText(nome);
        }
    }

    public void irParaPesquisa(View v){
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    public void irParaReservas(View v){
        Intent i = new Intent(this, MainActivity6.class);
        startActivity(i);
    }
}