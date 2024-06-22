package entities;

import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;

public class Artista {
    private String nombre;
    private MyList<Cancion> canciones;


    public Artista(String nombre) {
        this.nombre = nombre;
        this.canciones = new MyLinkedListImpl<>();

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MyList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(MyList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
