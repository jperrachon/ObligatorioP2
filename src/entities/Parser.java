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
    private MyList<Pais> paises;
    private MyList<Artista> artistas;
    private MyTree<Date,Cancion> arbolCancionesPorFecha;

    public Parser() {
        this.canciones = new MyHashImpl<>();
        this.paises = new MyLinkedListImpl<>();
        this.artistas = new MyLinkedListImpl<>();
        this.arbolCancionesPorFecha = new MyTreeImpl<>();
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
                int duracion = Integer.parseInt(datos[9]); // duration_ms

                MyList<Artista> artistasCancion = new MyLinkedListImpl<>();
                for(String nombre : nombresArtistas){
                    Artista artista = new Artista(nombre);
                    artistas.add(artista);
                    artistasCancion.add(artista);
                }
                if(paisNombre.isEmpty()){
                    paisNombre = "Global";
                }
                String Id = SpotifyId + paisNombre + fechaSnapshot.toString();
                Pais pais = new Pais(paisNombre);
                Cancion cancion = new Cancion(nombreCancion, artistasCancion, SpotifyId, puesto, fechaSnapshot, tempo, pais);

                canciones.put(Id, cancion);
                paises.add(pais);
                arbolCancionesPorFecha.insert(fechaSnapshot, cancion);
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

    public MyList<Pais> getPaises() {
        return paises;
    }

    public MyList<Artista> getArtistas() {
        return artistas;
    }

    public MyTree<Date, Cancion> getArbolCancionesPorFecha() {
        return arbolCancionesPorFecha;
    }
}
