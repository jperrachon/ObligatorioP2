package entities;

import adt.linkedlist.MyList;

import java.util.*;
import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;


public class Estadisticas {



    private MyHash<Date, MyList<Cancion>> top50PorFechaPorPais(Pais pais, MyHash<String, Cancion> canciones) {
        MyHash<Date, MyList<Cancion>> top50PorFechaPorPais = new MyHashImpl<>();
        if(pais.getTop50PorFecha().isEmpty()) {
            for (int i = 0; i < canciones.size(); i++) {
                Cancion cancion = canciones.get(canciones.keySet().get(i));
                if (cancion.getPais().equals(pais)) {
                    Date fecha = cancion.getFecha();
                    if (top50PorFechaPorPais.contains(fecha)) {
                        top50PorFechaPorPais.get(fecha).add(cancion);
                    } else {
                        MyList<Cancion> cancionesPorFecha = new MyLinkedListImpl<>();
                        cancionesPorFecha.add(cancion);
                        top50PorFechaPorPais.put(fecha, cancionesPorFecha);
                    }
                }
                pais.setTop50PorFecha(top50PorFechaPorPais);
            }
        }
        else {
            top50PorFechaPorPais = pais.getTop50PorFecha();
        }
        return top50PorFechaPorPais;
    }

    public MyList<Cancion> top10CancionesPorPaisYFecha(Pais pais, Date fecha, MyHash<String, Cancion> canciones) {
        MyList<Cancion> cancionesTop10 = top50PorFechaPorPais(pais, canciones).get(fecha);
        if (canciones.size() > 10) {
            return cancionesTop10.subList(0, 10);
        }
        return cancionesTop10;
    }

    public MyList<Cancion> top5CancionesEnMasTop50(MyList<Pais> paises, Date fecha, MyHash<String, Cancion> canciones) {
        MyHash<String, Integer> cancionFrecuencia = new MyHashImpl<>();
        int paisesLength = paises.size();
        for (int i = 0; i < paisesLength; i++) {
            MyList<Cancion> cancionesTop = top50PorFechaPorPais(paises.get(i),canciones).get(fecha);
            int cancionesLength = cancionesTop.size();
            for (int j = 0; j < cancionesLength; j++) {
                Cancion cancion = cancionesTop.get(j);
                if (cancionFrecuencia.contains(cancion.getId())) {
                    cancionFrecuencia.put(cancion.getId(), cancionFrecuencia.get(cancion.getId()) + 1);
                } else {
                    cancionFrecuencia.put(cancion.getId(), 1);
                }
            }
        }
        MyList<Cancion> todasCanciones = new MyLinkedListImpl<>();
        for (int i = 0; i < paisesLength; i++) {
            Pais pais = paises.get(i);
            for (int j = 0; j <pais.getTop50PorFecha().get(fecha).size(); j++) { //en este punto, todos los paises tienen el hash top50PorFecha, entonces podemos llamar el get en lugar de la funcion top50PorFechaPorPais
                todasCanciones.add(pais.getTop50PorFecha().get(fecha).get(j));
            }
        }

        todasCanciones.sort((a, b) -> cancionFrecuencia.get(b.getId()) - cancionFrecuencia.get(a.getId()));
        if (todasCanciones.size() > 5) {
            return todasCanciones.subList(0, 5);
        }
        return todasCanciones;
    }

    public MyList<Artista> top7ArtistasMasAparecen(List<Pais> paises, Date fechaInicio, Date fechaFin, MyHash<String,Cancion> totalCanciones) {
       MyHash<String, Integer> artistaFrecuencia = new MyHashImpl<>();
        for (int i = 0; i < paises.size(); i++) {
            Pais pais = paises.get(i);
            MyList<Date> fechas = top50PorFechaPorPais(pais,totalCanciones).keySet();
            for (int j = 0; j < pais.getTop50PorFecha().size(); j++) {
                Date fecha = fechas.get(j);
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    MyList<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
                    if (canciones != null) {
                        for (int k = 0; k < canciones.size(); k++) {
                            Cancion cancion = canciones.get(k);
                            for (int l = 0; l < cancion.getArtista().size(); l++) {
                                String artista = cancion.getArtista().get(l).getNombre();
                                if (artistaFrecuencia.contains(artista)) {
                                    artistaFrecuencia.put(artista, artistaFrecuencia.get(artista) + 1);
                                } else {
                                    artistaFrecuencia.put(artista, 1);
                                }
                            }
                        }
                    }
                }
            }
        }

        MyList<Artista> artistas = new MyLinkedListImpl<>();
        MyList<String> artistasNombre = artistaFrecuencia.keySet();
        for (int i = 0; i < artistaFrecuencia.size(); i++) {
            artistas.add(new Artista(artistasNombre.get(i)));
        }
        artistas.sort((a, b) -> artistaFrecuencia.get(b.getNombre()) - artistaFrecuencia.get(a.getNombre()));
        if (artistas.size() > 7) {
            return artistas.subList(0, 7);
        }
        return artistas;
    }

    public int cantidadVecesArtistaEnTop(Pais pais, Artista artista, Date fecha, MyHash<String, Cancion> todasCanciones) {
        MyList<Cancion> canciones = top50PorFechaPorPais(pais, todasCanciones).get(fecha);
        int count = 0;
        if (canciones != null) {
            for (int i = 0; i < canciones.size(); i++) {
                Cancion cancion = canciones.get(i);
                if (cancion.getArtista().contains(artista)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int cantidadCancionesPorTempoYRangoFechas(MyHash<String,Cancion> canciones, double tempoInicio, double tempoFin, Date fechaInicio, Date fechaFin) {
        int count = 0;
        MyList<String> cancionesKeys = canciones.keySet();
        for (int i = 0; i < cancionesKeys.size(); i++) {
            String key = cancionesKeys.get(i);
            Cancion cancion = canciones.get(key);
            MyList<String> nombresCancionesContadas = new MyLinkedListImpl<>();
            if (!nombresCancionesContadas.contains(cancion.getNombre()) && cancion.getTempo() >= tempoInicio && cancion.getTempo() <= tempoFin && cancion.getFecha().compareTo(fechaInicio) >= 0 && cancion.getFecha().compareTo(fechaFin) <= 0) {
                count++;
            }
        }
        return count;
    }
}