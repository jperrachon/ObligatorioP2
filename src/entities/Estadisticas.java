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
        for (Pais pais : paises) {
            MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
            if (canciones != null) {
                for (Cancion cancion : canciones) {
                    cancionFrecuencia.put(cancion.getId(), cancionFrecuencia.getOrDefault(cancion.getId(), 0) + 1);
                }
            }
        }

        MyList<Cancion> todasCanciones = new MyLinkedListImpl<>();
        for (Pais pais : paises) {
            for (MyList<Cancion> canciones : pais.getTop50PorFecha().values()) {
                todasCanciones.addAll(canciones);
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
        for (Pais pais : paises) {
            for (String fecha : pais.getTop50PorFecha().keySet()) {
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
                    if (canciones != null) {
                        for (Cancion cancion : canciones) {
                            artistaFrecuencia.put(cancion.getArtista(), artistaFrecuencia.getOrDefault(cancion.getArtista(), 0) + 1);
                        }
                    }
                }
            }
        }

        MyList<Artista> artistas = new MyLinkedListImpl<>();
        for (Map.Entry<String, Integer> entry : artistaFrecuencia.entrySet()) {
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
            for (Cancion cancion : canciones) {
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