package entities;


import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyList;

public class Pais {
    private String nombre;
    private MyHash<String, MyList<Cancion>> top50PorFecha;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.top50PorFecha = new MyHashImpl<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public MyHash<String, MyList<Cancion>> getTop50PorFecha() { return top50PorFecha; }
    public void setTop50PorFecha(MyHash<String, MyList<Cancion>> top50PorFecha) { this.top50PorFecha = top50PorFecha; }

    public void agregarCancion(String fecha, Cancion cancion, int puesto) {
        this.top50PorFecha.computeIfAbsent(fecha, k -> new MyLinkedListImpl<>()).add(cancion);
    }


}