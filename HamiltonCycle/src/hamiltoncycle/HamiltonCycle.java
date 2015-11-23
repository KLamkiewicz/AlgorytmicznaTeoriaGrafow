package hamiltoncycle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author krzysiek
 */
public class HamiltonCycle {

    private static List<Integer> visitedVertices = new ArrayList<>();
    private static int verticesCount;
    private static Integer[][] arr;
    private static final int STARTING_VERTEX = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        arr = getArr();
        verticesCount = arr.length;
        displayMatrix(arr);
        hamiltonianCycle();
    }
    
    
   public static void hamiltonianCycle(){
       visitedVertices.add( STARTING_VERTEX );
       if( hamiltonianCycle( STARTING_VERTEX ) ){
           printHamiltonianCycle();
       }else{
           System.out.println( "Graph does not contain hamiltonian cycle " );
       }
   }
    
   public static boolean hamiltonianCycle( int vertex ){
      
       if( isHamiltonian(vertex) ){
           int first = visitedVertices.get( 0 );
           visitedVertices.add( first );
           return true;
       }
       
       for( int i=0; i<verticesCount; i++ ){
           if( canBeVisited( vertex, i ) ){
               visitedVertices.add( i );         
               if( hamiltonianCycle( i ) ){
                   return true;
               }        
               deleteLast();      
           }
       }
       return false;
   } 
   
   public static boolean isHamiltonian( int lastVertex ){
       int starting = visitedVertices.get( 0 );
       return visitedVertices.size() == verticesCount && arr[lastVertex][starting] == 1;
   }
   
   public static void deleteLast(){
       visitedVertices.remove( visitedVertices.size() - 1 );
   }
   
   public static boolean canBeVisited( int start, int end ){
       if( arr[start][end] != 1 ){
           return false;
       }
       if( wasVisited( end )  ){
           return false;
       }
       return true;
   }
   
   public static boolean wasVisited( int vertex ){
       return visitedVertices.contains( vertex );
   }

    public static void printHamiltonianCycle() {
        System.out.println("Hamiltonian cycle : ");
        int size = visitedVertices.size();
        for( int i=0; i<size; i++ ){
            System.out.print( visitedVertices.get(i) + " " + (i<size-1?"-> ":""));
        }
        System.out.println("");
        
    }
    
   public static Integer[][] getArr() throws Exception{
        ArrayList<List<Integer>> lst = new ArrayList<>();
        try (InputStream is = HamiltonCycle.class.getResourceAsStream("graph.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (String strLine; (strLine = br.readLine()) != null;) {
                List<Integer> row = new ArrayList<>();
                for(String el : Arrays.asList(strLine.split("\\s")) ){
                    row.add(Integer.valueOf(el));
                }
                lst.add(row);
            }
        }
        Integer[][] arr = new Integer[lst.size()][lst.size()];
        for(int i=0; i<lst.size(); i++)
            arr[i] = lst.get(i).toArray(new Integer[lst.size()]);
            
        return arr;
    }
   
    public static void displayMatrix(Integer[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
