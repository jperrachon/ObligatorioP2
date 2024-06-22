package entities;

import adt.linkedlist.MyList;

import java.util.*;
import adt.linkedlist.MyLinkedListImpl;
import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.tree.MyTree;
import adt.tree.MyTreeImpl;


public class Estadisticas {
    private MyTree<Integer, Cancion> topPorFechaPorPais(Pais pais, MyTree<Date, tuplaCancion> tuplasCanciones, Date fecha) {
        MyTree<Integer,Cancion> top50PorFechaPorPais = new MyTreeImpl<>();
        tuplaCancion tupla = tuplasCanciones.getNext();
        while (tupla != null) {
            if (tupla.getPais().equals(pais) && tupla.getFecha().equals(fecha)) {
                top50PorFechaPorPais.insert(tupla.getPuesto(),tupla.getCancion());
            }
            tupla = tuplasCanciones.getNext();
        }

        return top50PorFechaPorPais;
        }


    public MyList<Cancion> top10CancionesPorPaisYFecha(Pais pais, Date fecha, MyTree<Date, tuplaCancion> tuplasCanciones) {
        MyTree<Integer,Cancion> cancionesTop10 = topPorFechaPorPais(pais, tuplasCanciones, fecha);
        MyList<Cancion> cancionesTop10List = cancionesTop10.getValues();

        if (cancionesTop10.size() > 10) {
            return cancionesTop10List.subList(0, 10);
        }
        return cancionesTop10List;
    }

    public MyList<String> top5CancionesEnMasTop50(MyHash<String,Pais> paises, Date fecha, MyTree<Date, tuplaCancion> tuplasCanciones) {
        MyHash<String, Integer> cancionFrecuencia = new MyHashImpl<>();
        MyList<String> paisesKeys = paises.keySet();
        for (int i = 0; i < paisesKeys.size(); i++) {
            Pais pais = paises.get(paisesKeys.get(i));
            MyTree<Integer,Cancion> top50PorFechaPorPais = topPorFechaPorPais(pais, tuplasCanciones, fecha);
            MyList<Cancion> canciones = top50PorFechaPorPais.getValues();
            for (int j = 0; j < canciones.size(); j++) {
                Cancion cancion = canciones.get(j);
                if (cancionFrecuencia.contains(cancion.getNombre())) {
                    cancionFrecuencia.put(cancion.getNombre(), cancionFrecuencia.get(cancion.getNombre()) + 1);
                } else {
                    cancionFrecuencia.put(cancion.getNombre(), 1);
                }
            }
        }
        MyList<String> cancionesNombre = cancionFrecuencia.keySet();
        cancionesNombre.sort((a, b) -> cancionFrecuencia.get(b) - cancionFrecuencia.get(a));
        if (cancionesNombre.size() > 5) {
            return cancionesNombre.subList(0, 5);
        }
        return cancionesNombre;
    }

    public MyList<Artista> top7ArtistasMasAparecen(MyHash<String,Pais> paises, Date fechaInicio, Date fechaFin,  MyTree<Date, tuplaCancion> tuplasCanciones, MyList<Date> fechas) {
        MyHash<String, Integer> artistaFrecuencia = new MyHashImpl<>();
        for (int i = 0; i < fechas.size(); i++) {
            Date fecha = fechas.get(i);
            if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                MyList<String> paisesKeys = paises.keySet();
                for (int j = 0; j < paisesKeys.size(); j++) {
                    Pais pais = paises.get(paisesKeys.get(j));
                    MyTree<Integer,Cancion> top50PorFechaPorPais = topPorFechaPorPais(pais, tuplasCanciones, fecha);
                    MyList<Cancion> canciones = top50PorFechaPorPais.getValues();
                    for (int k = 0; k < canciones.size(); k++) {
                        Cancion cancion = canciones.get(k);
                        MyList<Artista> artistas = cancion.getArtista();
                        for (int l = 0; l < artistas.size(); l++) {
                            Artista artista = artistas.get(l);
                            if (artistaFrecuencia.contains(artista.getNombre())) {
                                artistaFrecuencia.put(artista.getNombre(), artistaFrecuencia.get(artista.getNombre()) + 1);
                            } else {
                                artistaFrecuencia.put(artista.getNombre(), 1);
                            }
                        }
                    }
                }
            }
        }
        MyList<String> artistasNombre = artistaFrecuencia.keySet();
        artistasNombre.sort((a, b) -> artistaFrecuencia.get(b) - artistaFrecuencia.get(a));
        MyList<Artista> top7Artistas = new MyLinkedListImpl<>();
        for (int i = 0; i < artistasNombre.size(); i++) {
            if (i == 7) {
                break;
            }
            top7Artistas.add(new Artista(artistasNombre.get(i)));
        }
        return top7Artistas;

    }

    public int cantidadVecesArtistaEnTop(MyHash<String, Pais> paises ,Artista artista, Date fecha, MyTree<Date, tuplaCancion> tuplasCanciones) {
        int count = 0;
        MyList<String> paisesKeys = paises.keySet();
        for (int i = 0; i < paisesKeys.size(); i++) {
            Pais pais = paises.get(paisesKeys.get(i));
            MyTree<Integer,Cancion> top50PorFechaPorPais = topPorFechaPorPais(pais, tuplasCanciones, fecha);
            MyList<Cancion> canciones = top50PorFechaPorPais.getValues();
            for (int j = 0; j < canciones.size(); j++) {
                Cancion cancion = canciones.get(j);
                MyList<Artista> artistas = cancion.getArtista();
                for (int k = 0; k < artistas.size(); k++) {
                    Artista artistaCancion = artistas.get(k);
                    if (artistaCancion.getNombre().equals(artista.getNombre())) {
                        count++;
                    }
                }
            }
        }
        return count;

    }

    public int cantidadCancionesPorTempoYRangoFechas(double tempoInicio, double tempoFin, Date fechaInicio, Date fechaFin, MyTree<Date, tuplaCancion> tuplasCanciones) {
        int count = 0;
        tuplaCancion tupla = tuplasCanciones.getNext();
        while (tupla != null) {
            if (tupla.getFecha().compareTo(fechaInicio) >= 0 && tupla.getFecha().compareTo(fechaFin) <= 0) {
                Cancion cancion = tupla.getCancion();
                if (cancion.getTempo() >= tempoInicio && cancion.getTempo() <= tempoFin) {
                    count++;
                }
            }
            tupla = tuplasCanciones.getNext();
        }
        return count;
    }
}