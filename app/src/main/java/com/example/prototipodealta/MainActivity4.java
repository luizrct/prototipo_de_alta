package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    EditText cadastro;
    EditText senha1;
    EditText senha2;
    Persistencia p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        p1 = new Persistencia(this);
        cadastro = findViewById(R.id.cadastro_username);
        senha1 = findViewById(R.id.cadastro_senha1);
        senha2 = findViewById(R.id.cadastro_senha2);

    }

    public void voltar(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    public void criarConta(View v){
        String username = cadastro.getText().toString();
        String senha_1 = senha1.getText().toString();
        String senha_2 = senha2.getText().toString();

        if(!username.equals("") && !senha_1.equals("") && !senha_2.equals("")){
            //preencheu todos
            if(!p1.verificarExistencia(username)){
                //usuario ainda nao existe
                if(senha_1.equals(senha_2)){
                    //senhas iguais, usuário vai ser criado
                    if(senha_1.length() < 8){
                        //senha com menos de 8 digitos
                        Toast.makeText(this, "Escolha uma senha com no mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
                    }else{
                        //Deu tudo certo!
                        p1.salvarUsuario(username, senha_2);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                }else{
                    Toast.makeText(this, "As senhas são diferentes", Toast.LENGTH_SHORT).show();
                    //senhas diferentes
                }
            }else{
                //usuário já existe
                Toast.makeText(this, "Usuário já existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            //dados incompletos
            Toast.makeText(this, "Dados incompletos", Toast.LENGTH_SHORT).show();
        }
    }
}