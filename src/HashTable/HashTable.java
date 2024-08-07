import src.HashTable.HashTable;
import src.Business.Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static HashTable hashTable;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hashTable = new HashTable(200000);
        boolean exit = false;

        while (!exit) {
            System.out.println("Opciones:");
            System.out.println("1. Cargar datos (Multiplicacion, Adyacencia)");
            System.out.println("2. Cargar datos (Division, Adyacencia)");
            System.out.println("3. Buscar ID (Multiplicacion, Adyacencia)");
            System.out.println("4. Buscar ID (Division, Adyacencia)");
            System.out.println("5. Cargar datos (Multiplicacion, Java List)");
            System.out.println("6. Cargar datos (Division, Java List)");
            System.out.println("7. Buscar ID (Multiplicacion, Java List)");
            System.out.println("8. Buscar ID (Division, Java List)");
            System.out.println("9. Imprimir colisiones (LinkedList)");
            System.out.println("10. Imprimir colisiones (ArrayList)");
            System.out.println("11. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadData(true, true);
                    break;
                case 2:
                    loadData(false, true);
                    break;
                case 3:
                    searchData(true, true, scanner);
                    break;
                case 4:
                    searchData(false, true, scanner);
                    break;
                case 5:
                    loadData(true, false);
                    break;
                case 6:
                    loadData(false, false);
                    break;
                case 7:
                    searchData(true, false, scanner);
                    break;
                case 8:
                    searchData(false, false, scanner);
                    break;
                case 9:
                    hashTable.printCollisions(true);
                    break;
                case 10:
                    hashTable.printCollisions(false);
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println();
                    System.out.println("Opción no válida.");
                    System.out.println();
                    break;
            }
        }
        scanner.close();
    }

    private static void loadData(boolean useMultiplication, boolean useLinkedList) {
        String line;
        String splitBy = ",";
        long startTime, endTime;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/data/bussines.csv"))) {
            startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Business business = new Business(data[0], data[1], data[2], data[3], data[4]);
                hashTable.insert(business, useMultiplication, useLinkedList);
            }
            endTime = System.nanoTime();
            System.out.println("--------------------------------------------");
            System.out.println("Datos cargados en " + (endTime - startTime) + " ns");
            System.out.println("--------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchData(boolean useMultiplication, boolean useLinkedList, Scanner scanner) {
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Ingrese el ID a buscar:");
        System.out.println("-----------------------");
        System.out.println();
        String searchId = scanner.nextLine();

        long startTime = System.nanoTime();
        Business foundBusiness = hashTable.search(searchId, useMultiplication, useLinkedList);
        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Tiempo de búsqueda: " + (endTime - startTime) + " ns");
        System.out.println("-------------------------------");
        System.out.println();
        if (foundBusiness != null) {
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("Datos encontrados:\n" + foundBusiness);
            System.out.println("-----------------------------------");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("---------------------");
            System.out.println("Datos no encontrados.");
            System.out.println("---------------------");
            System.out.println();
        }
    }
}
