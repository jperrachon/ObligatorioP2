package entities;
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
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Top 10 canciones por pais y fecha");
                    System.out.println("Enter pais: ");
                    String pais = scanner.next();
                    System.out.println("Enter fecha: ");
                    String fecha = scanner.next();
                    System.out.println(estadisticas.top10CancionesPorPaisYFecha(pais, fecha, parser.getCanciones()));
                    break;
                case 2:
                    System.out.println("Top 5 canciones en mas top 50");
                    System.out.println("Enter fecha: ");
                    String fecha2 = scanner.next();
                    System.out.println(estadisticas.top5CancionesEnMasTop50(parser.getPaises(), fecha2, parser.getCanciones()));
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }



    }
}