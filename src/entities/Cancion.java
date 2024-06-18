package entities;

import adt.linkedlist.MyList;

import java.util.Date;
import java.util.Objects;

public class Cancion {
    private String nombre;
    private MyList<Artista> artistas;
    private String id;
    private int puesto;
    private Date fecha;
    private double tempo;
    private Pais pais;



    public Cancion(String nombre, MyList<Artista> artistas, String id, int puesto, Date fecha, double tempo, Pais pais) {
        this.nombre = nombre;
        this.artistas = artistas;
        this.id = id;
        this.puesto = puesto;
        this.fecha = fecha;
        this.tempo = tempo;
        this.pais = pais;

    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public MyList<Artista> getArtista() { return artistas; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getPuesto() { return puesto; }
    public void setPuesto(int puesto) { this.puesto = puesto; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTempo() { return tempo; }
    public void setTempo(double tempo) { this.tempo = tempo; }

    public Pais getPais() { return pais; }




}