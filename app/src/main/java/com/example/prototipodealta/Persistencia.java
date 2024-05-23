package com.example.prototipodealta;

import android.content.Context;
import android.content.SharedPreferences;



public class Persistencia{
    private SharedPreferences usuarios;
    private SharedPreferences.Editor editor;
    private Context context;

    public Persistencia(Context context) {
        this.usuarios = context.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        this.context = context;
        this.editor = usuarios.edit();
    }




    public boolean verificarExistencia(String chave){
        return this.usuarios.contains(chave);
    }

    public boolean verificarSenha(String senha){
        if(senha.length() < 8){
            return false;
        }else{
            return true;
        }
    }

    public boolean validandoSenha(String nomeUsuario, String senha){
        String pass = this.usuarios.getString(nomeUsuario, "");
        if(pass.equals(senha)){
            return true;
        }else{
            return false;
        }
    }

    public void salvarUsuario(String nome, String senha){
        this.editor.putString(nome, senha).apply();
    }
}
