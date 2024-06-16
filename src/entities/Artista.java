package entities;

import java.util.LinkedList;
import java.util.List;

public class Artista {
    private String nombre;
    private List<Cancion> canciones;

    public Artista(String nombre) {
        this.nombre = nombre;
        this.canciones = new LinkedList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Cancion> getCanciones() { return canciones; }
    public void setCanciones(List<Cancion> canciones) { this.canciones = canciones; }

    public void agregarCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }
}