package pl.krzysiek.alg.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pl.krzysiek.alg.exceptions.GraphEdgeException;

/**
 *
 * @author krzysiek
 */
public class GraphFunctions {

    private final List<List<Integer>> adjacencyMatrix;
    private final static String EDGE_ALREADY_EXISTS = "This edge already exists in this graph";
    private final static String NO_EDGE_BETWEEN = "There is already no edge between provided vertices";
    private final static String SIMPLE_GRAPH = "No loops allowed";
    private final static String VERTEX_NOT_IN_GRAPH = "Provided vertex does not exist in this graph";

    public GraphFunctions(List<List<Integer>> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void displayMatrix() {
        int columnIndex = 1;
        System.out.println("");
        System.out.print("   ");
        
        for( int i = 1; i<=adjacencyMatrix.size(); i++)
        {
            System.out.print("[" + i + "]");
        }
        System.out.println("");
        for (List<Integer> row : adjacencyMatrix) {
            System.out.print("[" + columnIndex++ + "]");
            for (int column : row) {
                System.out.print(" " + column + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void removeEdge(int startVertex, int endVertex) {
        
        try {
            int s = adjacencyMatrix.get(startVertex - 1).get(endVertex - 1);
            int e = adjacencyMatrix.get(endVertex - 1).get(startVertex - 1);
            if( s == 0 || e == 0 )
            {
                throw new GraphEdgeException( NO_EDGE_BETWEEN );
            }
        }catch( GraphEdgeException ex )
        {
            System.out.println( ex.getMessage() );
            return;
        }catch (Exception ex) {
            System.out.println( VERTEX_NOT_IN_GRAPH );
            return;
        }

        adjacencyMatrix.get(startVertex - 1).set(endVertex - 1, 0);
        adjacencyMatrix.get(endVertex - 1).set(startVertex - 1, 0);
    }

    public void addEdge( int startVertex, int endVertex ) {
        try {
            if( startVertex == endVertex)
            {
                throw new GraphEdgeException( SIMPLE_GRAPH );
            }
            
            int s = adjacencyMatrix.get(startVertex - 1).get(endVertex - 1);
            int e = adjacencyMatrix.get(endVertex - 1).get(startVertex - 1);
            if( s != 0 || e != 0 )
            {
                throw new GraphEdgeException( EDGE_ALREADY_EXISTS );
            }  
        } catch( GraphEdgeException ex )
        {
            System.out.println( ex.getMessage() );
            return;
        } catch (Exception ex) {
            System.out.println( VERTEX_NOT_IN_GRAPH );
            return;
        }
        
        adjacencyMatrix.get(startVertex - 1).set(endVertex - 1, 1);
        adjacencyMatrix.get(endVertex - 1).set(startVertex - 1, 1);
    }
    
    public void removeVertex(int vertex) {
        
        try {
            adjacencyMatrix.remove(vertex-1);
            for (List<Integer> row : adjacencyMatrix) {
                row.remove(vertex-1);
            }
        }catch( Exception ex )
        {
            System.out.println( VERTEX_NOT_IN_GRAPH );
        }
    }
    
    public void addVertex() {
        for (List<Integer> row : adjacencyMatrix) {
            row.add(0);
        }
        adjacencyMatrix.add(new ArrayList<>(Collections.nCopies(adjacencyMatrix.size() + 1, 0)));
    }
    
    public void printMinAndMaxDegree() {
        if( adjacencyMatrix.isEmpty() )
        {
            System.out.println("There is no graph");
            return;
        }
        
        Integer[] degrees = new Integer[adjacencyMatrix.size()];
        Arrays.fill( degrees, 0);
        
        for (List<Integer> row : adjacencyMatrix) {
            for (int c = 0; c < row.size(); c++) {
                degrees[c] =  degrees[c] + row.get(c);
            }
        }
        List<Integer> lst = Arrays.asList(degrees);

        System.out.println(" Minimal degree in current graph : " + Collections.min(lst));
        System.out.println(" Maximal degree in current graph : " + Collections.max(lst));
    }
    
    public void printEvenAndOddCount()
    {
        if( adjacencyMatrix.isEmpty() )
        {
            System.out.println("There is no graph");
            return;
        }
        Integer[] degrees = new Integer[adjacencyMatrix.size()];
        Arrays.fill( degrees, 0);
        
        for (List<Integer> row : adjacencyMatrix) {
            for (int c = 0; c < row.size(); c++) {
                degrees[c] =  degrees[c] + row.get(c);
            }
        }
        int odd = 0;
        int even = 0;
        for (int i : degrees) {
            if (i % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        System.out.println( "Number of vertices with even degrees : " + even);
        System.out.println( "Number of vertices with odd degrees : " + odd);
    }
    
    public void printDegreeSequence() {
        if (adjacencyMatrix.isEmpty()) {
            System.out.println("There is no graph");
            return;
        }

        Integer[] degrees = new Integer[adjacencyMatrix.size()];
        Arrays.fill(degrees, 0);

        for (List<Integer> row : adjacencyMatrix) {
            for (int c = 0; c < row.size(); c++) {
                degrees[c] = degrees[c] + row.get(c);
            }
        }
        List<Integer> lst = Arrays.asList(degrees);
        Collections.sort(lst, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 <= o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        System.out.println( " Degree sequence : " + lst.toString());
    }
    
    public void containsCycleC3()
    {
        if (adjacencyMatrix.isEmpty()) {
            System.out.println("There is no graph");
            return;
        }
        Integer[][] adjArr = new Integer[adjacencyMatrix.size()][adjacencyMatrix.size()];
        for(int i=0; i<adjArr.length;i++)
        {
            Arrays.fill(adjArr[i], 0);
        }
        for( int i=0; i<adjacencyMatrix.size(); i++)
        {
            for( int j=0; j<adjacencyMatrix.get(i).size(); j++)
            {
                for( int r=0; r<adjacencyMatrix.get(i).size(); r++)
                {
                    adjArr[i][j] += adjacencyMatrix.get(i).get(r) * adjacencyMatrix.get(r).get(j);
                }
            }
        }
        boolean containsC3 = false;
        for( int i = 0; i<adjArr.length; i++)
        {
            if( adjArr[i][i] == 3)
            {
                containsC3 = true;
            }
        }System.out.println( containsC3 );
        for(int i=0;i<adjArr.length;i++)
        {
            for(int j=0;j<adjArr.length;j++)
            {
                System.out.print( adjArr[i][j] + " ");
                if( adjArr[i][i] == 3)
                {
                    containsC3 = true;
                }
            }
            System.out.println("");
        }
    }
}
