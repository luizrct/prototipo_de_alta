package com.example.prototipodealta;
import android.content.Context;
import android.content.SharedPreferences;
public class UsuarioAtivo {
    private SharedPreferences usuarioAtivo;
    private SharedPreferences.Editor editor;

    public UsuarioAtivo(Context context){
        this.usuarioAtivo = context.getSharedPreferences("usuário_ativo", Context.MODE_PRIVATE);
        this.editor = usuarioAtivo.edit();
    }

    public boolean verificaExistencia(String chave){
        return this.usuarioAtivo.contains(chave);
    }

    public void salvaUsuarioAtivo(String nome){
        this.editor.putString("usuario_ativo", nome).apply();
    }

    public void removeUsuario(String nome){
        this.editor.remove("usuario_ativo");
    }

    public String nomeUsuarioAtivo(){
        String nome = this.usuarioAtivo.getString("usuario_ativo", "");
        return nome;
    }
}
