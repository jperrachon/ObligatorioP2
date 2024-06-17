package entities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import adt.hash.MyHash;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;
public class Dataset {
    private MyList<Pais> paises;
    private MyHash<String,Cancion> canciones;
    private MyList<Artista> artistas;



    public Dataset() {
        this.paises = new MyLinkedListImpl<>();
    }

    // Getters y Setters
    public MyList<Pais> getPaises() { return paises; }
    public void setPaises(MyList<Pais> paises) { this.paises = paises; }

    public void cargarDatosDesdeCSV(String rutaArchivo) {
            String linea;
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");
                    if (campos.length != 6) {
                        continue; // Ignorar líneas que no tengan el formato esperado
                    }

                    String nombre = campos[0].trim();
                    String artista = campos[1].trim();
                    String id = campos[2].trim();
                    int puesto = Integer.parseInt(campos[3].trim());
                    String fecha = campos[4].trim();
                    double tempo = Double.parseDouble(campos[5].trim());

                    Cancion cancion = new Cancion(nombre, artista, id, puesto, fecha, tempo);

                    boolean encontrado = false;
                    for (Pais pais : paises) {
                        if (pais.getNombre().equals(nombre)) {
                            pais.agregarCancion(fecha, cancion);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        Pais nuevoPais = new Pais(nombre);
                        nuevoPais.agregarCancion(fecha, cancion);
                        paises.add(nuevoPais);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }




    }
}