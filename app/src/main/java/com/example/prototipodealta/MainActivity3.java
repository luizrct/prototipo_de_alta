package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    int idSala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void passarTela3(View v){
        Intent i3 = new Intent(this, MainActivity5.class);
        i3.putExtra("idSala", v.getTag().toString());
        startActivity(i3);
    }

    public void irParaReservas(View v){
        Intent i = new Intent(this, MainActivity6.class);
        startActivity(i);
    }

    public void irParaMeuPerfil(View v){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

}