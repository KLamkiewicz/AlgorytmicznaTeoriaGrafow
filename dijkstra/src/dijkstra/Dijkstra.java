package dijkstra;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author krzysiek
 */

public class Dijkstra {

    
    private static final int START_VERTEX = 7;
    private static final int END_VERTEX = 2;
    
    private static Integer[][] arr;
    private static Integer[][] DOut;
    private static List<List<List<Integer>>> Pi;
    private static int arrSize;
    
    public static void main(String[] args) throws Exception {
        init();
        for( int i=0; i<arrSize; i++){
            dijkstraAlg( i );
        }
        
        print();
        getInfo(START_VERTEX, END_VERTEX);

    }

    public static void dijkstraAlg(int source){
        List<List<Integer>> path = new ArrayList<>();
        
        Integer[] distance = new Integer[arrSize];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int[] prev = new int[arrSize];
        
        //Add vertices to 'queue'
        List<Integer> q = new ArrayList<>();
        for(int i=0; i<arrSize; i++){
            q.add(i);
            path.add(new ArrayList<>());
        }
        
        distance[source] = 0;
        
        while( !q.isEmpty() ){
            int uVertex = getMinFromQ(q, distance);
            remove(q, uVertex);
            for( Integer v : getNeighbors(uVertex)){
                if(distance[uVertex]==Integer.MAX_VALUE){
                    continue;
                }
                int alt = distance[uVertex] + arr[uVertex][v];
                
                if( alt < distance[v]){
                    distance[v] = alt;
                    prev[v] = uVertex;
                    path.get(v).add(uVertex);
                }
            }
        }
        for( int i=0; i<distance.length; i++ ){
            DOut[source][i]=distance[i].equals(Integer.MAX_VALUE)?-1:distance[i];
        }
        
        for( int i=0; i<arrSize; i++){
            Pi.get(source).add(new ArrayList<>());
        }
            
        for( int i=0; i<arrSize; i++){
            List<Integer> pathz = new ArrayList<>();
            if( distance[i]==Integer.MAX_VALUE){
                continue;
            }
            int end = i;
            while (end != source  ) {
                pathz.add( prev[end]);
                end = prev[end];
            }
            Collections.reverse(pathz);
            Pi.get(source).get(i).addAll(pathz);
        }
       
        
    }
    
    private static Set<Integer> getNeighbors(int vertex) {
        Set<Integer> lst = new HashSet<>();
        for (int j = 0; j < arrSize; j++) {
            if (arr[vertex][j] > 0) {
                lst.add(j);
            }
        }
        return lst;
    }
    
    private static void remove(List<Integer> lst, int vert) {
        Iterator it = lst.iterator();
        while (it.hasNext()) {
            Integer i = (Integer) it.next();
            if (i == vert) {
                it.remove();
                break;
            }
        }
    }

    private static int getMinFromQ( List<Integer> lst, Integer[] arr ){
        int min = Integer.MAX_VALUE;
        int v = Integer.MAX_VALUE;
        for( int i : lst){
            if(arr[i]<=min){
                min = arr[i];
                v = i;
            }
        }
        return v;
    }
    
    public static void init() throws Exception {
        arr = getArr();
        arrSize = arr.length;
        DOut = new Integer[arrSize][arrSize];
        for (Integer[] dRow : DOut) {
            Arrays.fill(dRow, 0);
        }
        Pi = new ArrayList<>();
        for( int i=0; i<arrSize; i++){
            Pi.add( new ArrayList<>());
        }
    }
    
    private static void getInfo( int i, int j ){
        System.out.println(String.format("Distance %d to %d = %d", i, j, DOut[i][j] ));//Distance from " + i + DOut[i][j]);
        System.out.println(String.format("Path %d to %d = %s", i, j, Pi.get(i).get(j).toString()));
    }
    
    private static void print(){
        for( int i = 0; i<arrSize; i++ ){
            for( int j = 0; j<arrSize; j++ ){
                if(DOut[i][j]==Integer.MAX_VALUE){
                    System.out.print(String.format("%5d",-1));
                }else
                    System.out.print(String.format("%5d",DOut[i][j]));
            }
            System.out.println("");
        }
        
        System.out.println("  \n\n");
        for(List<List<Integer>> l : Pi ){
            for( List<Integer> ls : l){
                System.out.print( String.format("%15s",ls.toString() ));
            }
            System.out.println("");
        }
    }

    public static Integer[][] getArr() throws Exception {
        ArrayList<List<Integer>> lst = new ArrayList<>();
        try (InputStream is = Dijkstra.class.getResourceAsStream("graph.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (String strLine; (strLine = br.readLine()) != null;) {
                List<Integer> row = new ArrayList<>();
                for (String el : Arrays.asList(strLine.split("\\s"))) {
                    row.add(Integer.valueOf(el));
                }
                lst.add(row);
            }
        }
        Integer[][] arr = new Integer[lst.size()][lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            arr[i] = lst.get(i).toArray(new Integer[lst.size()]);
        }

        return arr;
    }

    public static void displayMatrix(Integer[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
