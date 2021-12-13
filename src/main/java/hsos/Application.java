package hsos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Application {
    static int[] unsortiert;
    /**
     * Scanner Objekt zum lesen von Konsolen Eingaben.
     */
    //private final Scanner consoleScanner;

    /**
     * Der Application Constructor wird von der main aufgerufen und führt einige
     * beispielhaft Operationen durch. Es wird etwas in der Konsole ausgegeben,
     * eine Datei wird gelesen und Konsolen-Eingabe wird gelesen.
     *
     */

    public Application() {

        // Es ist nicht empfohlen, die Datei "data_100k" einfach in die Konsole
        // auszugeben, da dies recht lange dauert kann.
        readNumbersFromFile("data_5000k.txt");

        // Um Eingaben von der Konsole zu lesen, kann die Scanner Klasse verwendet
        // und System.in als Input Stream übergeben werden. Es darf nur eine Instanz
        // eines Scanners mit System.in im Programm geben!
        //consoleScanner = new Scanner(System.in);
        //readLineFromConsole();
        System.out.println("\n(1) Selectionsort \n(2) Insertionsort \n(3) Quicksort \n(4) Mergesort \n(5) Heapsort \n");
        int auswahl = IO.readInt();
        switch (auswahl){
            case 1:
                long start = new Date().getTime();
                selectionSort();
                long runningTime = new Date().getTime() - start;
                System.out.println("---");
                System.out.println(runningTime + " ms");
                break;
            case 2:
                start = new Date().getTime();
                insertionSort();
                runningTime = new Date().getTime() - start;
                System.out.println("---");
                System.out.println(runningTime + " ms");
                break;
            case 3:
                start = new Date().getTime();
                quickSort(0, unsortiert.length-1);
                runningTime = new Date().getTime() - start;
                System.out.println("---");
                System.out.println(runningTime + " ms");
                break;
            case 4:
                start = new Date().getTime();
                mergeSort(unsortiert, unsortiert.length);
                runningTime = new Date().getTime() - start;
                System.out.println("---");
                System.out.println(runningTime + " ms");
                break;
            case 5:
                start = new Date().getTime();
                heapsort(unsortiert);
                runningTime = new Date().getTime() - start;
                System.out.println("---");
                System.out.println(runningTime + " ms");
                break;
            default:
                System.out.println("Wählen Sie ein Sortieralgorithmus aus.");
                break;
        }


    }

    /**
     * Dies ist eine beispielhafte Methode, die Zeigt, wie die Scanner Instanz benutzt
     * werden kann. Das Programm wartet hier auf die Eingabe einer Zahl.
     */
    //private void readLineFromConsole() {
    // Das Programm wartet hier auf die Eingabe einer Zahl.
    //   int number = consoleScanner.nextInt();
    //   System.out.println("Zahl: " + number);
    //}

    /**
     * TODO: MUSS MODIFIZIERT WERDEN!
     *
     * Diese Funktion liest Zahlen aus Dateien im "resources"-Verzeichnis und
     * gibt sie in der Konsole aus. Sie dient als Vorlage und muss so modifiziert
     * werden, dass die Zahlen zum Sortieren in eine von Ihnen gewählte
     * Datenstruktur geladen wird. Außerdem sollte der Rückgabetyp dahingehend
     * angepasst werden, dass die Funktion die Zahlen auch zurückgibt.
     *
     * @param fileName Der Name der Datei aus dem "resources"-Verzeichnis
     */
    public static void readNumbersFromFile(String fileName) {
        Path path;
        Scanner scanner;
        try {
            // Diese 2 Zeilen können ignoriert werden. Sie dienen lediglich dem Finden des
            // "resources"-Verzeichnisses und dem Auswählen der richtigen Datei.
            path = Paths.get(Objects.requireNonNull(Application.class.getClassLoader().getResource(fileName)).toURI());
            scanner = new Scanner(path, StandardCharsets.UTF_8);

            // Die erste Zahl einer jeden Datei gibt an, wie viele Elemente sich in der
            // Datei befinden. Dabei wird die erste Zahl nicht mitgerechnet.
            int elemAnzahl = scanner.nextInt();
            System.out.println("Anzahl an Elementen: " + elemAnzahl);
            unsortiert = new int[elemAnzahl];
            // Dieser while loop läuft, bis alle Integer in der Datei gelesen wurden und
            // gibt jede einzelne Zahl in der Konsole aus. Die index Variable zählt mit
            // bei welchem Element der loop sich befindet.
            int index = 0;
            while (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                //System.out.println(index + ". Zahl: " + x);
                unsortiert[index] = x;
                ++index;
            }

            scanner.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /*
        Die Ausgabe des Arrays.
     */
    public static void ausgeben(){
        for(int i = 0; i < unsortiert.length; i++){
            System.out.println(unsortiert[i]);
        }
    }

    /**
     * Entry Point für das Progamm. Hier startet alles!
     *
     * @param args Command Line Argumente in einem String[]
     */
    public static void main(String[] args) {
        new Application();
    }

    /**
     *  Selectionsort Algorithmus
     **/
    public static void selectionSort(){
        //durchläuft das Array
        for(int i = 0; i < unsortiert.length; i++){
            int tmp = unsortiert[i];
            //durchläuft das Array
            for(int j = 0; j < unsortiert.length; j++){
                //Vergleich der Werte
                if(tmp < unsortiert[j]) {
                    tmp = unsortiert[j];
                    //Tausch der Werte
                    int tmp2 = unsortiert[j];
                    unsortiert[j] = unsortiert[i];
                    unsortiert[i] = tmp2;
                }
            }
        }
        //gehört nicht mehr zum Algorithmus selbst
        //ausgeben();
    }

    /**
     *  Insertionsort Algorithmus
     **/
    public static void insertionSort(){
        //durchläuft einmal die Zahlenkolone
        for(int a = 0; a < unsortiert.length-1; a++){
            int b = a + 1;
            int tmp = unsortiert[b];
            //Lücke für nächste Zahl wird frei gemacht
            //Bis b an der richtigen Stelle ist.
            while(b > 0 && tmp < unsortiert[b-1]){
                unsortiert[b] = unsortiert[b-1];
                b--;
            }
            //tmp wird an die richtige Stelle b geschrieben
            unsortiert[b] = tmp;
        }
        //Sortiertes array wird ausgegeben
        //gehört nicht mehr zum Algorithmus selbst
        //ausgeben();
    }

    /**
     *  Quicksort Algorithmus
     **/
    public static void quickSort(int l, int r){
        int pivot;
        //Solange der Index des Endes größer als der des Anfangs ist
        if (l < r){
            //Liste wird geteilt
            pivot = teilen(l, r);
            //Sortierfunktion wird mit linkem Teil rekursiv aufgerufen
            quickSort(l, pivot);
            //Sortierfunktion wird mit rechtem Teil rekursiv aufgerufen
            quickSort( pivot + 1, r);
        }
        //ausgeben(); //macht zu viele Ausgaben...
    }

    public static int teilen(int l, int r){
        //unsortierte Liste wird geteilt
        int pivot = unsortiert[(l+r)/2];
        int i, j;
        i = l-1; //index für linken Anteil ohne Pivot Point (-1)
        j = r+1; //index für rechten Anteil ohne Pivot Point (+1)
        while(true){
            do {
                i++;
                //Sucht den Index des nächsten Wertes, welcher kleiner als der Pivotwert ist, von links
            } while(unsortiert[i] < pivot);

            do {
                j--;
                //Sucht den Index des nächsten Wertes, welcher größter als der Pivotwert ist, von rechts
            } while(unsortiert[j] > pivot);

            if(i < j){
                // Tauscht die Werte an den beiden ermittelten Index-Stellen
                int tmp2 = unsortiert[j];
                unsortiert[j] = unsortiert[i];
                unsortiert[i] = tmp2;
            } else {
                return j;
            }
        }
    }

    /**
     *  Mergesort Algorithmus
     **/
    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    //Quelle (Mergesort): https://www.baeldung.com/java-merge-sort


    /**
     *  Heapsort Algorithmus
     */
    public static void heapsort(int arr[]){
        int n = arr.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(int arr[], int n, int i){
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    //Quelle (Heapsort): https://www.geeksforgeeks.org/heap-sort/
}
