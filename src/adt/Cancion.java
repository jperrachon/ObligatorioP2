package adt;

import java.util.Objects;

public class Cancion {
    private String nombre;
    private String artista;
    private String pais;
    private int posicion;

    public Cancion(String nombre, String artista, String pais, int posicion) {
        this.nombre = nombre;
        this.artista = artista;
        this.pais = pais;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cancion cancion)) return false;
        return getPosicion() == cancion.getPosicion() && Objects.equals(getNombre(), cancion.getNombre()) && Objects.equals(getArtista(), cancion.getArtista()) && Objects.equals(getPais(), cancion.getPais());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getArtista(), getPais(), getPosicion());
    }
}
