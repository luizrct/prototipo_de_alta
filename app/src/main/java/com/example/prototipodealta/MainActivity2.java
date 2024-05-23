package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tnome;
    UsuarioAtivo ua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        tnome = findViewById(R.id.textNome);
        ua = new UsuarioAtivo(this);
        if(extras != null){
            String nome = extras.getString("nome");
            if(ua.verificaExistencia("usuario_ativo")){
                ua.removeUsuario("usuario_ativo");
            }
            ua.salvaUsuarioAtivo(nome);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tnome.setText(ua.nomeUsuarioAtivo());
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