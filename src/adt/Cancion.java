package adt;

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

}
