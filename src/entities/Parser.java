package entities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Parser {
    private Map<String, Cancion> canciones;
    private List<Pais> paises;
    private List<Artista> artistas;

    public Parser() {
        this.canciones = new HashMap<>();
        this.paises = new ArrayList<>();
        this.artistas = new ArrayList<>();
    }

    public void parseCsv(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        Set<Pais> paisesSet = new HashSet<>();
        Set<Artista> artistasSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Leer la cabecera y omitirla
            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);

                // Asumiendo el orden de los campos en el CSV:
                String id = datos[0].replace("\"", "");
                String nombreCancion = datos[1].replace("\"", "");
                String nombreArtista = datos[2].replace("\"", "");
                Pais pais = new Pais(datos[3].replace("\"", ""));
                Artista artista = new Artista(nombreArtista, pais);
                int reproducciones = Integer.parseInt(datos[10].replace("\"", ""));

                LocalDate fechaLanzamiento = LocalDate.parse(datos[7].replace("\"", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int duracion = Integer.parseInt(datos[24].replace("\"", ""));
                boolean explicito = Boolean.parseBoolean(datos[9].replace("\"", ""));

                Cancion cancion = new Cancion(id, nombreCancion, artista, fechaLanzamiento, estadisticas, duracion, explicito);

                canciones.put(id, cancion);
                paisesSet.add(pais);
                artistasSet.add(artista);
            }

            this.paises = new ArrayList<>(paisesSet);
            this.artistas = new ArrayList<>(artistasSet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public Map<String, Cancion> getCanciones() {
        return canciones;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }
}
