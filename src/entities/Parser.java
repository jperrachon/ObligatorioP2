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
    private MyHash<String,Artista> artistas;
    private MyList<Date> fechas;

    public Parser() {
        this.canciones = new MyHashImpl<>();
        this.paises = new MyHashImpl<>();
        this.artistas = new MyHashImpl<>();
        this.tuplasCancionesPorFecha = new MyTreeImpl<>();
        this.fechas = new MyLinkedListImpl<>();
    }

    public void parseCsv(String csvFile) {
        String line = "";
        String cvsSplitBy = "\",\"";
        int count=0;
        int cvsFileLength = 748803;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Leer la cabecera y omitirla
            String header = br.readLine();
            while ((line = br.readLine()) != null ) {

                String[] datos = line.split(cvsSplitBy);

                String SpotifyId = datos[0]; // id
                String nombreCancion = datos[1]; // name
                String nombreArtista = datos[2]; // artists
                //separar nombre artista en varios artistas
                String[] nombresArtistas = nombreArtista.split(",");
                int puesto = Integer.parseInt(datos[3]); // daily_rank
                String paisNombre = datos[6]; // country
                Date fechaSnapshot = dateFormat.parse(datos[7]); // snapshot_date
                double tempo = Double.parseDouble(datos[23]); // tempo
//                int duracion = Integer.parseInt(datos[9]); // duration_ms

                MyList<Artista> artistasCancion = new MyLinkedListImpl<>();
                for(String nombre : nombresArtistas){
                    Artista artista;
                    if(artistas.contains(nombre)){
                        artista = artistas.get(nombre);
                    }
                    else{
                        artista = new Artista(nombre);
                        artistas.put(nombre, artista);
                    }
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
                int barLength = 50; // Longitud de la barra de progreso
                // Calcular el porcentaje de progreso
                double progressPercentage = (double) count / cvsFileLength;

                // Calcular el número de caracteres `#` para mostrar
                int progressChars = (int) (progressPercentage * barLength);

                // Crear la barra de progreso
                StringBuilder progressBar = new StringBuilder();
                progressBar.append("\r[");
                for (int i = 0; i < barLength; i++) {
                    if (i < progressChars) {
                        progressBar.append("#");
                    } else {
                        progressBar.append("-");
                    }
                }
                progressBar.append("] ");
                progressBar.append((int) (progressPercentage * 100));
                progressBar.append("%");

                // Imprimir la barra de progreso
                System.out.print(progressBar.toString());
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

    public MyHash<String,Pais> getPaises() {
        return paises;
    }

    public MyHash<String,Artista> getArtistas() {
        return artistas;
    }

    public MyList<Date> getFechas() {
        return fechas;
    }

    public MyTree<Date, tuplaCancion> getTuplasCancionesPorFecha() {
        return tuplasCancionesPorFecha;
    }
}
