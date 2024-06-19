package entities;
import adt.linkedlist.MyList;
public class Cancion {
    private String nombre;
    private MyList<Artista> artistas;
    private String id;
    private double tempo;
    // Constructor
    public Cancion(String nombre, MyList<Artista> artistas, String id, double tempo) {
        this.nombre = nombre;
        this.artistas = artistas;
        this.id = id;
        this.tempo = tempo;
    }
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public MyList<Artista> getArtista() { return artistas; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getTempo() { return tempo; }
    public void setTempo(double tempo) { this.tempo = tempo; }





}