package com.example.prototipodealta;

import java.util.ArrayList;
import java.util.List;

public class SalaRepository {
    private List<SalaEntity> mList;

    public SalaRepository(){
        this.mList = new ArrayList<>();

        for(int i = 0; i < 16; i++){
            String nome = "Sala " + (i + 1);
            this.mList.add(new SalaEntity(nome, i));
        }
    }
    public List<SalaEntity> getmList(){
        return this.mList;
    }


    public SalaEntity get(int id){
        return this.mList.get(id);
    }
}
