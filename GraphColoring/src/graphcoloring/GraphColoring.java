package graphcoloring;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author krzysiek
 */
public class GraphColoring {

    private Integer[][] arr;
    private List<Vertex> vertexList;
    private int size;

    public GraphColoring() throws Exception {

        arr = getArr();
        size = arr.length;
        vertexList = new ArrayList<Vertex>();
        fillVertex();

    }

    private void showColors() {
        for (int i = 0; i < vertexList.size(); i++) {
            System.out.print(" Vertex : " + vertexList.get(i).getVertexId());
            System.out.print(" Degree : " + vertexList.get(i).getVertexDegree());
            System.out.print(" Color : " + vertexList.get(i).getColor() + " \n");
        }
        System.out.println("");
    }

    private void color() {
        for (Vertex vertex : vertexList) {
            List<Integer> neigh = getNeighborsColors(vertex.getVertexId());
            Collections.sort(neigh);
            int max = (neigh.get(neigh.size() - 1) + 1);
            for( int i=-1; i<=max; i++){
                if( !neigh.contains(i+1)){
                    vertex.setColor(i+1);
                    break;
                }
            }
        }
    }

    private List<Integer> getNeighborsColors(int id) {
        List<Integer> colors = new ArrayList<Integer>();
        for (Vertex v : vertexList) {
            if (arr[id][v.getVertexId()] > 0) {
                colors.add(v.getColor());
            }
        }

        Collections.sort(colors, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2) > 0 ? -1 : 1;
            }
        });
        return colors;
    }

    private void fillVertex() {
        for (int i = 0; i < size; i++) {
            int degree = 0;
            vertexList.add(new Vertex());
            for (int j = 0; j < size; j++) {
                degree += arr[i][j] != 0 ? 1 : 0;
            }
            vertexList.get(i).setVertexId(i);
            vertexList.get(i).setVertexDegree(degree);
        }

    }

    private void orderVertices() {
        Collections.sort(vertexList, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.getVertexDegree() > o2.getVertexDegree() ? -1 : 1;
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        GraphColoring gc = new GraphColoring();

        gc.orderVertices();
        gc.color();
        gc.showColors();

    }

    public Integer[][] getArr() throws Exception {
        ArrayList<List<Integer>> lst = new ArrayList<>();
        try (InputStream is = GraphColoring.class.getResourceAsStream("graph.txt");
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

    public void displayMatrix(Integer[][] arr) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
