package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    int idSala;
    RecyclerView r1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        List<SalaEntity> list = new SalaRepository().getmList();
        //1) Obtendo o Recycler View
        r1 = findViewById(R.id.recycler_salas);

        OnListClick click = new OnListClick() {
            @Override
            public void onClick(int id) {
                String idSala = String.valueOf(id + 1);
                Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                intent.putExtra("idSala", idSala);
                startActivity(intent);
            }
        };

        //2)Definir um adapter
        SalaAdapter adapter = new SalaAdapter(list, click);
        r1.setAdapter(adapter);


        //3)Definindo um layout para os itens
        r1.setLayoutManager(new LinearLayoutManager(this));
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