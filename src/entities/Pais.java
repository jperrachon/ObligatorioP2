package entities;

import java.util.*;

public class Pais {
    private String nombre;
    private Map<String, List<Cancion>> top50PorFecha;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.top50PorFecha = new HashMap<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Map<String, List<Cancion>> getTop50PorFecha() { return top50PorFecha; }
    public void setTop50PorFecha(Map<String, List<Cancion>> top50PorFecha) { this.top50PorFecha = top50PorFecha; }

    public void agregarCancion(String fecha, Cancion cancion) {
        this.top50PorFecha.computeIfAbsent(fecha, k -> new LinkedList<>()).add(cancion);
    }
}