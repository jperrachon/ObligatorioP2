package entities;

import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;

public class Artista {
    private String nombre;


    public Artista(String nombre) {
        this.nombre = nombre;

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
