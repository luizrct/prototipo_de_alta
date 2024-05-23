package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView teste;
    EditText userName;
    EditText senha;
    Persistencia p1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teste = findViewById(R.id.cadastroo);
        userName = findViewById(R.id.userName);
        senha = findViewById(R.id.passSenha);
        p1 = new Persistencia(this);
    }


    public void verificarInformacoes(View v){
        String username = userName.getText().toString();
        String password = senha.getText().toString();
        if(!username.equals("") && !password.equals("")){
            if(p1.verificarExistencia(username)){
                if(p1.validandoSenha(username, password)){
                    passsarTela();
                }else{
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Nome de usuário não existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Dados incompletos", Toast.LENGTH_SHORT).show();
        }
    }



    public void passsarTela(){
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        i.putExtra("nome", userName.getText().toString());
        startActivity(i);
        finish();
    }

    public void irParaCadastro(View v){
        Intent i = new Intent(this, MainActivity4.class);
        startActivity(i);
    }


}

