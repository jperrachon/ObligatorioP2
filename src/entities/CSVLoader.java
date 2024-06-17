package entities;


import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




public class CSVLoader {

    private MyHash<String, Cancion> canciones;
    private MyHash<String, Artista> artistas;
    private MyHash<String, Pais> paises;

    public CSVLoader() {
        canciones = new MyHashImpl<>();
        artistas = new MyHashImpl<>();
        paises = new MyHashImpl<>();
    }

    public void cargarDatos(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] nextLine = line.split(",");
                // Suponiendo que las columnas del CSV sean: nombreCancion, nombreArtista, nombrePais, fecha, etc.
                String nombreCancion = nextLine[0];
                String nombreArtista = nextLine[1];
                String nombrePais = nextLine[2];
                String fecha = nextLine[3];
                int puesto = Integer.parseInt(nextLine[4]);

                // Crear o obtener el objeto Cancion
                Cancion cancion = canciones.get(nombreCancion);
                if (cancion == null) {
                    cancion = new Cancion(nombreCancion, nombreArtista, nombrePais, fecha, puesto);
                    canciones.put(nombreCancion, cancion);
                }

                // Crear o obtener el objeto Artista
                Artista artista = artistas.get(nombreArtista);
                if (artista == null) {
                    artista = new Artista(nombreArtista);
                    artistas.put(nombreArtista, artista);
                }

                // Crear o obtener el objeto Pais
                Pais pais = paises.get(nombrePais);
                if (pais == null) {
                    pais = new Pais(nombrePais);
                    paises.put(nombrePais, pais);
                }

                // Asociar la canción con el artista y el país
                cancion.addArtista(artista);
                pais.agregarCancion(cancion, fecha, puesto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyHash<String, Cancion> getCanciones() {
        return canciones;
    }

    public MyHash<String, Artista> getArtistas() {
        return artistas;
    }

    public MyHash<String, Pais> getPaises() {
        return paises;
    }
}

