package entities;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parseCsv("src/dataset.csv");

        Estadisticas estadisticas = new Estadisticas();
        while (true) {
            //create unitests menu
            System.out.println("\n1. Top 10 canciones por pais y fecha");
            System.out.println("2. Top 5 canciones en mas top 50");
            System.out.println("3. Top 7 Artistas que mas aparecen");
            System.out.println("4. Cantidad de veces que aparece un artista en el top");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
            System.out.println("6. Exit");
            System.out.println("Elegir opcion: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Top 10 canciones por pais y fecha");
                    Pais pais = seleccionarPais(parser, scanner);
                    Date fecha = seleccionarFecha(parser, scanner);
                    MyList<Cancion> top10 = estadisticas.top10CancionesPorPaisYFecha(pais, fecha, parser.getTuplasCancionesPorFecha());
                    for (int i = 0; i < top10.size(); i++) {
                        System.out.println(i + ". " + top10.get(i));
                    }
                    break;
                case 2:
                    System.out.println("Top 5 canciones en mas top 50");
                    Date fecha2 = seleccionarFecha(parser, scanner);
                    MyList<String> top5 = estadisticas.top5CancionesEnMasTop50(parser.getPaises(), fecha2, parser.getTuplasCancionesPorFecha());
                    for (int i = 0; i < top5.size(); i++) {
                        System.out.println(i + ". " + top5.get(i));
                    }
                    break;
                case 3:
                    System.out.println("Top 7 Artistas que mas aparecen");
                    Date fechaInicio = seleccionarFecha(parser, scanner);
                    Date fechaFin = seleccionarFecha(parser, scanner);
                    MyList<String> top7 = estadisticas.top7ArtistasMasAparecen(parser.getPaises(), fechaInicio, fechaFin, parser.getTuplasCancionesPorFecha(), parser.getFechas());
                    for (int i = 0; i < top7.size(); i++) {
                        System.out.println(i+1 + ". " + top7.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Cantidad de veces que aparece un artista en el top");
                    Artista artista = seleccionarArtista(parser, scanner);
                    Date fecha3 = seleccionarFecha(parser, scanner);
                    int cantidad = estadisticas.cantidadVecesArtistaEnTop(parser.getPaises(), artista, fecha3, parser.getTuplasCancionesPorFecha());
                    System.out.println("Cantidad de veces que aparece el artista en el top: " + cantidad);
                    break;

                case 5:
                    System.out.println("Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
                    Date fechaInicio2 = seleccionarFecha(parser, scanner);
                    Date fechaFin2 = seleccionarFecha(parser, scanner);
                    System.out.println("Ingresar rango de tempo (min max): ");
                    double min = scanner.nextDouble();
                    double max = scanner.nextDouble();
                    int cantidad2 = estadisticas.cantidadCancionesPorTempoYRangoFechas(min, max,fechaInicio2, fechaFin2, parser.getTuplasCancionesPorFecha());
                    System.out.println("Cantidad de canciones con tempo en rango: " + cantidad2);
                    break;

                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static Date seleccionarFecha(Parser parser, Scanner scanner){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Seleccionar fecha (Formato YYYY-MM-DD): ");
        String FechaTexto = scanner.next();
        try {
            Date fecha= dateFormat.parse(FechaTexto);
            if (parser.getFechas().contains(fecha)){
                return fecha;
            }
            else{
                System.out.println("Fecha no encontrada");
            }
        } catch (Exception e) {
            System.out.println("Fecha invalida");
        }
        return seleccionarFecha(parser, scanner);

    }

    private static Pais seleccionarPais(Parser parser, Scanner scanner){
        System.out.println("Seleccionar pais: ");
        MyList<String> nombresPaises= parser.getPaises().keySet();
        for (int i = 0; i < nombresPaises.size(); i++) {
            System.out.println(i + ". " + nombresPaises.get(i));
        }
        int nroPais = Integer.parseInt(scanner.next());
        return parser.getPaises().get(nombresPaises.get(nroPais));
    }

    private static Artista seleccionarArtista(Parser parser, Scanner scanner){
        System.out.println("Elegir artista deseado: ");
        scanner.nextLine();
        String nombreArtista = scanner.nextLine();
        if (parser.getArtistas().contains(nombreArtista)){
            return parser.getArtistas().get(nombreArtista);
        }
        else{
            System.out.println("Artista no encontrado");
        }
        return seleccionarArtista(parser, scanner);

    }

}