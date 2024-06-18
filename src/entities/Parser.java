package entities;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Parser {
    private MyHash<String, Cancion> canciones;
    private MyList<Pais> paises;
    private MyList<Artista> artistas;

    public Parser() {
        this.canciones = new MyHashImpl<>();
        this.paises = new MyLinkedListImpl<>();
        this.artistas = new MyLinkedListImpl<>();
    }

    public void parseCsv(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Leer la cabecera y omitirla
            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);

                String id = datos[0].replace("\"", ""); // spotify_id
                String nombreCancion = datos[1].replace("\"", ""); // name

                String nombreArtista = datos[2].replace("\"", ""); // artists
                //separar nombre artista en varios artistas
                String[] nombresArtistas = nombreArtista.split(",");

                int puesto = Integer.parseInt(datos[3].replace("\"", "")); // daily_rank
                String paisNombre = datos[6].replace("\"", ""); // country
                Date fechaLanzamiento = dateFormat.parse(datos[12].replace("\"", "")); // album_release_date
                double tempo = Double.parseDouble(datos[23].replace("\"", "")); // tempo
                int duracion = Integer.parseInt(datos[10].replace("\"", "")); // duration_ms

                MyList<Artista> artistasCancion = new MyLinkedListImpl<>();
                for(String nombre : nombresArtistas){
                    Artista artista = new Artista(nombre);
                    artistas.add(artista);
                    artistasCancion.add(artista);
                }
                if(paisNombre.isEmpty()){
                    paisNombre = "Global";
                }
                Pais pais = new Pais(paisNombre);
                Cancion cancion = new Cancion(nombreCancion, artistasCancion, id, puesto,  fechaLanzamiento, tempo, pais);

                canciones.put(id, cancion);
                paises.add(pais);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public MyHash<String, Cancion> getCanciones() {
        return canciones;
    }

    public MyList<Pais> getPaises() {
        return paises;
    }

    public MyList<Artista> getArtistas() {
        return artistas;
    }
}
