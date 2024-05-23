package com.example.prototipodealta;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalaViewHolder extends RecyclerView.ViewHolder {
    private TextView t1;
    private ImageView i1;

    public SalaViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = itemView.findViewById(R.id.text_sala);
        i1 = itemView.findViewById(R.id.image_sala);

    }

    public void bind(SalaEntity sala, OnListClick click){
        t1.setText(sala.nome);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(sala.id);
            }
        });
    }


}
