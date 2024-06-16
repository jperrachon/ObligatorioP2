package entities;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    System.out.println("Bienvenido!");
    System.out.println("Elija la opcion que desea");
    System.out.println("1. Top 10 de canciones en un pais");
    System.out.println("2. Top 5 canciones que mas aparecen en un top 50 en un dia dado");
    System.out.println("3. Top 7 de artistas que mas aparecen en un top 50 en un rango de fechas dado");
    System.out.println("4. Cantidad de veces que aparece un artista en un top 50 en un rango de fechas dado");
    System.out.println("5. Cantidad de canciones con un tempo en un rango especifico para un rango de fechas dado");
    System.out.println("6. Salir");

    Scanner scanner = new Scanner(System.in);
    int opcion = scanner.nextInt();
    if (opcion == 1) {
        System.out.println("Ingrese el pais");
        String pais = scanner.next();
        System.out.println("Top 10 de canciones en " + pais);
    } else if (opcion == 2) {
        System.out.println("Ingrese la fecha");
        String fecha = scanner.next();
        System.out.println("Top 5 canciones que mas aparecen en un top 50 en " + fecha);
    } else if (opcion == 3) {
        System.out.println("Ingrese la fecha de inicio");
        String fechaInicio = scanner.next();
        System.out.println("Ingrese la fecha de fin");
        String fechaFin = scanner.next();
        System.out.println("Top 7 de artistas que mas aparecen en un top 50 en un rango de fechas dado");
    } else if (opcion == 4) {
        System.out.println("Ingrese la fecha de inicio");
        String fechaInicio = scanner.next();
        System.out.println("Ingrese la fecha de fin");
        String fechaFin = scanner.next();
        System.out.println("Ingrese el artista");
        String artista = scanner.next();
        System.out.println("Cantidad de veces que aparece " + artista + " en un top 50 en un rango de fechas dado");
    } else if (opcion == 5) {
        System.out.println("Ingrese la fecha de inicio");
        String fechaInicio = scanner.next();
        System.out.println("Ingrese la fecha de fin");
        String fechaFin = scanner.next();
        System.out.println("Ingrese el tempo minimo");
        int tempoMin = scanner.nextInt();
        System.out.println("Ingrese el tempo maximo");
        int tempoMax = scanner.nextInt();
        System.out.println("Cantidad de canciones con un tempo en un rango especifico para un rango de fechas dado");
    } else if (opcion == 6) {
        System.out.println("Adios!");
    } else {
        System.out.println("Opcion invalida");


    }

    //revisar si esta bien
    }

}