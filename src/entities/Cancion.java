package entities;

import java.util.Date;
import java.util.Objects;

public class Cancion {
    private String nombre;
    private String artista;
    private String id;
    private int puesto;
    private Date fecha;
    private double tempo;

    public Cancion(String nombre, String artista, String id, int puesto, Date fecha, double tempo) {
        this.nombre = nombre;
        this.artista = artista;
        this.id = id;
        this.puesto = puesto;
        this.fecha = fecha;
        this.tempo = tempo;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getPuesto() { return puesto; }
    public void setPuesto(int puesto) { this.puesto = puesto; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTempo() { return tempo; }
    public void setTempo(double tempo) { this.tempo = tempo; }

    public void addArtista(Artista artista) {
    }
}