package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void irParaPesquisa(View v){
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    public void irParaPerfil(View v){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}