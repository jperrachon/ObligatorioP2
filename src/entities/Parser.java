package entities;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;
import adt.tree.MyTree;
import adt.tree.MyTreeImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {
    private MyHash<String,Cancion> canciones;
    private MyTree<Date,tuplaCancion> tuplasCancionesPorFecha;
    private MyHash<String,Pais> paises;
    private MyList<Artista> artistas;
    private MyList<Date> fechas;

    public Parser() {
        this.canciones = new MyHashImpl<>();
        this.paises = new MyHashImpl<>();
        this.artistas = new MyLinkedListImpl<>();
        this.tuplasCancionesPorFecha = new MyTreeImpl<>();
        this.fechas = new MyLinkedListImpl<>();
    }

    public void parseCsv(String csvFile) {
        String line = "";
        String cvsSplitBy = "\",\"";
        int count=0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Leer la cabecera y omitirla
            String header = br.readLine();
            while ((line = br.readLine()) != null ) {
                String SpotifyId = line.substring(1, line.indexOf("\","));


                String todo = line.substring(line.indexOf(",")+1);
                String[] datos = todo.split(cvsSplitBy);



                String nombreCancion = datos[0]; // name

                String nombreArtista = datos[1]; // artists
                //separar nombre artista en varios artistas
                String[] nombresArtistas = nombreArtista.split(",");
                int puesto = Integer.parseInt(datos[2]); // daily_rank
                String paisNombre = datos[5]; // country
                Date fechaSnapshot = dateFormat.parse(datos[6]); // snapshot_date
                double tempo = Double.parseDouble(datos[22]); // tempo
//                int duracion = Integer.parseInt(datos[9]); // duration_ms

                MyList<Artista> artistasCancion = new MyLinkedListImpl<>();
                for(String nombre : nombresArtistas){
                    Artista artista = new Artista(nombre);
                    artistas.add(artista);
                    artistasCancion.add(artista);
                }
                if(paisNombre.isEmpty()){
                    paisNombre = "Global";
                }
                String tuplaId = SpotifyId + paisNombre + fechaSnapshot.toString();
                Pais pais;
                if(paises.contains(paisNombre)) {
                    pais = paises.get(paisNombre);
                }
                else {
                    pais = new Pais(paisNombre);
                    paises.put(paisNombre, pais);
                    System.out.println(paisNombre);
                }

                if(!fechas.contains(fechaSnapshot)){
                    fechas.add(fechaSnapshot);
                }
                Cancion cancion;
                if (!canciones.contains(SpotifyId)) {
                    cancion = new Cancion(nombreCancion, artistasCancion, SpotifyId, tempo);
                    canciones.put(SpotifyId, cancion);
                }
                else {
                    cancion = canciones.get(SpotifyId);
                }

                tuplaCancion cancionTupla = new tuplaCancion(pais, fechaSnapshot, puesto, cancion);
                tuplasCancionesPorFecha.insert(fechaSnapshot,  cancionTupla);
                count++;
                System.out.println(count);
            }
            System.out.println(canciones.size());

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

    public MyHash<String,Pais> getPaises() {
        return paises;
    }

    public MyList<Artista> getArtistas() {
        return artistas;
    }

    public MyList<Date> getFechas() {
        return fechas;
    }

    public MyTree<Date, tuplaCancion> getTuplasCancionesPorFecha() {
        return tuplasCancionesPorFecha;
    }
}
