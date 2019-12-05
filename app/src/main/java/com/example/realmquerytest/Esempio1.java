package com.example.realmquerytest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Esempio1 extends RealmObject {

    @PrimaryKey
    private int ID;
    @Required
    private String Nome;

    //Getters
    public int getID(){
        return ID;
    }

    public String getNome(){
        return Nome;
    }

    //Setters
    public void setNome(String Nome){
        this.Nome = Nome;
    }

    //Constructors
    public void Esempio1(){}

    public void Esempio1(int ID, String Nome){
        this.ID = ID;
        this.Nome = Nome;
    }

    //toString method to print the data
    public String toString(){
        return "ID: " + getID() + " - Nome: " + getNome();
    }
}
