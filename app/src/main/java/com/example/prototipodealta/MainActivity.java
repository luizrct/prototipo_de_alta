package com.example.prototipodealta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);
    }

    public void passsarTela(View v){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("nome", userName.getText().toString());
        startActivity(i);
    }
}

