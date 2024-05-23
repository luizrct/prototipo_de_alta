package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.view.View;

public class MainActivity5 extends AppCompatActivity {
    TextView tsala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tsala = findViewById(R.id.textSala);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String id = extras.getString("idSala");
            tsala.setText("Sala "+id);



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

    public void irParaPerfil(View v){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}