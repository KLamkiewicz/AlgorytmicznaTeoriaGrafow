package pl.krzysiek.alg;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import pl.krzysiek.alg.function.GraphFunctions;
import pl.krzysiek.alg.function.GraphReader;
import pl.krzysiek.alg.function.GraphSequence;

/**
 *
 * @author krzysiek
 */
public class Main {

    static String SEQUENCE_FILE = "sequence.txt";
    static final int INVALID_VALUE = -1;
    static final String ERROR_MSG = "Given input is invalid";
    static List<List<Integer>> adjacencyMatrix = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean isRunning = true;
    static GraphFunctions gf = new GraphFunctions(adjacencyMatrix);
    static int i = 0;

    public static void main(String[] args) {

        gf = new GraphFunctions(GraphReader.loadGraphMatrix());
        gf.containsCycleC3();
        while (isRunning) {
            displayMenu();
            i = readInput(scanner);
            switch (i) {
                case 404:
                    isRunning = false;
                    break;
                case 1:
                    gf = new GraphFunctions(GraphReader.loadGraphMatrix());
                    break;
                case 2:
                    gf.displayMatrix();
                    break;
                case 3:
                    System.out.println("Provide vertex id to be removed ");
                    int vertex = readInput(scanner);
                    gf.removeVertex(vertex);
                    break;
                case 4:
                    gf.addVertex();
                    break;
                case 5:
                    System.out.println("Provide start vertex ");
                    int rStart = readInput(scanner);
                    System.out.println("Provide end vertex ");
                    int rEnd = readInput(scanner);
                    gf.removeEdge(rStart, rEnd);
                    break;
                case 6:
                    System.out.println("Provide start vertex ");
                    int aStart = readInput(scanner);
                    System.out.println("Provide end vertex ");
                    int aEnd = readInput(scanner);
                    gf.addEdge(aStart, aEnd);
                    break;
                case 7:
                    gf.printMinAndMaxDegree();
                    break;
                case 8:
                    gf.printEvenAndOddCount();
                    break;
                case 9:
                    gf.printDegreeSequence();
                    break;
                case 10:
                    gf.containsCycleC3();
                    break;
                case 11:
                    GraphSequence.checkGraphicSequence( SEQUENCE_FILE );
                    break;
            }
        }

    }

    static int readInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            System.out.println(ERROR_MSG);
        }
        return INVALID_VALUE;
    }

    static void displayMenu() {
        StringBuilder sb = new StringBuilder()
                .append(" Select : \n")
                .append(" 1 - Read graph from file (Start over)\n")
                .append(" 2 - Display adjacency matrix\n")
                .append(" 3 - Remove vertex\n")
                .append(" 4 - Add vertex\n")
                .append(" 5 - Remove edge\n")
                .append(" 6 - Add edge\n")
                .append(" 7 - Print min and max degree\n")
                .append(" 8 - Print even and odd count degree\n")
                .append(" 9 - Print degree sequence\n")
                .append(" 10 - Check if graph contains C3 cycle\n")
                .append(" 11 - Check graphic sequence from file\n")
                .append(" 404 - Exit \n");

        System.out.println(sb.toString());
    }

}
