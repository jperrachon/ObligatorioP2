package entities;


import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyList;

import java.util.Date;

public class Pais {
    private String nombre;
    private MyHash<Date, MyList<Cancion>> top50PorFecha;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.top50PorFecha = new MyHashImpl<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public MyHash<Date, MyList<Cancion>> getTop50PorFecha() { return top50PorFecha; }
    public void setTop50PorFecha(MyHash<Date, MyList<Cancion>> top50PorFecha) { this.top50PorFecha = top50PorFecha; }

    public void agregarCancion(Date fecha, Cancion cancion, int puesto) {
        if (top50PorFecha.contains(fecha)) {
            MyList<Cancion> canciones = top50PorFecha.get(fecha);
            canciones.add(cancion);
            top50PorFecha.remove(fecha);
            top50PorFecha.put(fecha, canciones);
        }
        else {
            MyList<Cancion> canciones = new MyLinkedListImpl<>();
            canciones.add(cancion);
            top50PorFecha.put(fecha, canciones);
        }
    }
}