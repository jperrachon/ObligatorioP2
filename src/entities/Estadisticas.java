package entities;

import java.util.*;

public class Estadisticas {

    public List<Cancion> top10CancionesPorPaisYFecha(Pais pais, String fecha) {
        List<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
        if (canciones != null && canciones.size() > 10) {
            return canciones.subList(0, 10);
        }
        return canciones;
    }

    public List<Cancion> top5CancionesEnMasTop50(List<Pais> paises, String fecha) {
        Map<String, Integer> cancionFrecuencia = new HashMap<>();
        for (Pais pais : paises) {
            List<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
            if (canciones != null) {
                for (Cancion cancion : canciones) {
                    cancionFrecuencia.put(cancion.getId(), cancionFrecuencia.getOrDefault(cancion.getId(), 0) + 1);
                }
            }
        }

        List<Cancion> todasCanciones = new LinkedList<>();
        for (Pais pais : paises) {
            for (List<Cancion> canciones : pais.getTop50PorFecha().values()) {
                todasCanciones.addAll(canciones);
            }
        }

        todasCanciones.sort((a, b) -> cancionFrecuencia.get(b.getId()) - cancionFrecuencia.get(a.getId()));
        if (todasCanciones.size() > 5) {
            return todasCanciones.subList(0, 5);
        }
        return todasCanciones;
    }

    public List<Artista> top7ArtistasMasAparecen(List<Pais> paises, String fechaInicio, String fechaFin) {
        Map<String, Integer> artistaFrecuencia = new HashMap<>();
        for (Pais pais : paises) {
            for (String fecha : pais.getTop50PorFecha().keySet()) {
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    List<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
                    if (canciones != null) {
                        for (Cancion cancion : canciones) {
                            artistaFrecuencia.put(cancion.getArtista(), artistaFrecuencia.getOrDefault(cancion.getArtista(), 0) + 1);
                        }
                    }
                }
            }
        }

        List<Artista> artistas = new LinkedList<>();
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
        List<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
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

    public int cantidadCancionesPorTempoYRangoFechas(List<Pais> paises, double tempoInicio, double tempoFin, String fechaInicio, String fechaFin) {
        int count = 0;
        for (Pais pais : paises) {
            for (String fecha : pais.getTop50PorFecha().keySet()) {
                if (fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) {
                    List<Cancion> canciones = pais.getTop50PorFecha().get(fecha);
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