package entities;

import adt.linkedlist.MyList;

import java.util.*;
import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;


public class Estadisticas {

    public MyList<Cancion> top10CancionesPorPaisYFecha(Pais pais, String fecha) {
        MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
        if (canciones != null && canciones.size() > 10) {
            return canciones.subList(0, 10);
        }
        return canciones;
    }

    public MyList<Cancion> top5CancionesEnMasTop50(MyList<Pais> paises, String fecha) {
        MyHash<String, Integer> cancionFrecuencia = new MyHashImpl<>();
        int paisesLength = paises.size();
        for (int i = 0; i < paisesLength; i++) {
            MyList<Cancion> canciones = paises.get(i).getTop50PorFecha().get(fecha);
            int cancionesLength = canciones.size();
            if (canciones != null) {
                for (int j = 0; j < cancionesLength; j++) {
                    Cancion cancion = canciones.get(j);
                    if (cancionFrecuencia.contains(cancion.getId())) {
                        cancionFrecuencia.put(cancion.getId(), cancionFrecuencia.get(cancion.getId()) + 1);
                    } else {
                        cancionFrecuencia.put(cancion.getId(), 1);
                    }
                }
            }
        }
        MyList<Cancion> todasCanciones = new MyLinkedListImpl<>();
        for (int i = 0; i < paisesLength; i++) {
            Pais pais = paises.get(i);
            for (int j = 0; j <pais.getTop50PorFecha().get(fecha).size(); j++) {
                todasCanciones.add(pais.getTop50PorFecha().get(fecha).get(j));
            }
        }

        todasCanciones.sort((a, b) -> cancionFrecuencia.get(b.getId()) - cancionFrecuencia.get(a.getId()));
        if (todasCanciones.size() > 5) {
            return todasCanciones.subList(0, 5);
        }
        return todasCanciones;
    }

    public MyList<Artista> top7ArtistasMasAparecen(List<Pais> paises, String fechaInicio, String fechaFin) {
       MyHash<String, Integer> artistaFrecuencia = new MyHashImpl<>();
        for (int i = 0; i < paises.size(); i++) {
            Pais pais = paises.get(i);
            MyList<String> fechas = pais.getTop50PorFecha().keySet();
            for (int j = 0; j < pais.getTop50PorFecha().size(); j++) {
                String fecha = fechas.get(j);
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
                    if (canciones != null) {
                        for (int k = 0; k < canciones.size(); k++) {
                            Cancion cancion = canciones.get(k);
                            if (artistaFrecuencia.contains(cancion.getArtista())) {
                                artistaFrecuencia.put(cancion.getArtista(), artistaFrecuencia.get(cancion.getArtista()) + 1);
                            } else {
                                artistaFrecuencia.put(cancion.getArtista(), 1);
                            }
                        }
                    }
                }
            }
        }

        MyList<Artista> artistas = new MyLinkedListImpl<>();
        for (int i = 0; i < artistaFrecuencia.size(); i++) {
            MyHash.
            artistas.add(new Artista(entry.getKey()));
        }

        artistas.sort((a, b) -> artistaFrecuencia.get(b.getNombre()) - artistaFrecuencia.get(a.getNombre()));
        if (artistas.size() > 7) {
            return artistas.subList(0, 7);
        }
        return artistas;
    }

    public int cantidadVecesArtistaEnTop(Pais pais, String artista, String fecha) {
        MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
        int count = 0;
        if (canciones != null) {
            for (int i = 0; i < canciones.size(); i++) {
                Cancion cancion = canciones.get(i);
                if (cancion.getArtista().equals(artista)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int cantidadCancionesPorTempoYRangoFechas(MyList<Pais> paises, double tempoInicio, double tempoFin, String fechaInicio, String fechaFin) {
        int count = 0;
        for (Pais pais : paises) {
            for (String fecha : pais.getTop50PorFecha().keySet()) {
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
                    if (canciones != null) {
                        for (Cancion cancion : canciones) {
                            if (cancion.getTempo() >= tempoInicio && cancion.getTempo() <= tempoFin) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}