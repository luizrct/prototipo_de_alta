package com.example.prototipodealta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SalaAdapter  extends RecyclerView.Adapter<SalaViewHolder> {


    private List<SalaEntity> mList;
    OnListClick click;
    public SalaAdapter(List<SalaEntity> mList, OnListClick click){
        this.mList = mList;
        this.click = click;
    }
    @NonNull
    @Override
    public SalaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);
        return new SalaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaViewHolder holder, int position) {
        SalaEntity salaEntity = this.mList.get(position);
        holder.bind(salaEntity, this.click);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
