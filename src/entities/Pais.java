package entities;


import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyList;

import java.util.Date;

public class Pais {
    private String nombre;


    public Pais(String nombre) {
        this.nombre = nombre;

    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }




}