package entities;



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
        return "Nombre de artista = " + nombre + '\'' ;
    }
}
