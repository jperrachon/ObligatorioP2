package entities;
import adt.linkedlist.MyList;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parseCsv("src/dataset.csv");

        //TODO: hacer un main para las funciones de estadisticas
        Estadisticas estadisticas = new Estadisticas();
        while (true) {
            //create unitests menu
            System.out.println("1. Top 10 canciones por pais y fecha");
            System.out.println("2. Top 5 canciones en mas top 50");
            System.out.println("3. Top 7 Artistas que mas aparecen");
            System.out.println("4. Cantidad de veces que aparece un artista en el top");
            System.out.println("5. Exit");
            System.out.println("Elegir opcion: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Top 10 canciones por pais y fecha");

                    Pais pais = seleccionarPais(parser, scanner);
                    Date fecha = seleccionarFecha(parser, scanner);
                    MyList<Cancion> top10 = estadisticas.top10CancionesPorPaisYFecha(pais, fecha, parser.getCanciones(), parser.getTuplasCancionesPorFecha());
                    for (int i = 0; i < top10.size(); i++) {
                        System.out.println(i + ". " + top10.get(i));
                    }
                    break;
                    //TODO: arreglar los paramentros de input
                /*case 2:
                    System.out.println("Top 5 canciones en mas top 50");
                    Date fecha2 = seleccionarFecha(parser, scanner);
                    System.out.println(estadisticas.top5CancionesEnMasTop50(parser.getPaises(), fecha2, parser.getCanciones()));
                    break;
                case 3:
                    System.out.println("Top 7 Artistas que mas aparecen");
                    Date fechaInicio = seleccionarFecha(parser, scanner);
                    Date fechaFin = seleccionarFecha(parser, scanner);
                    System.out.println(estadisticas.top7ArtistasMasAparecen(parser.getPaises(), fechaInicio,fechaFin, parser.getCanciones()));
                    break;*/
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static Date seleccionarFecha(Parser parser, Scanner scanner){
        System.out.println("Seleccionar fecha: ");
        for (int i = 0; i < parser.getFechas().size(); i++) {
            System.out.println(i + ". " + parser.getFechas().get(i));
        }

        int NroFecha = Integer.parseInt(scanner.next());
        return parser.getFechas().get(NroFecha);
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

}