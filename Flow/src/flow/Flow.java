package flow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author krzysiek
 */
public class Flow {

    private Integer[][] arr;
    private Integer[][] residualArr;
    private int size;
    private Map<Vertex, List<Edge>> edgeMap;
    private Map<Integer, Vertex> vertices;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Flow flow = new Flow();
        flow.defineFlow();
    }

    public Flow() throws Exception {
        init();
        populateEdges();
//        displayMatrix(arr);
//        displayMatrix(residualArr);
    }

    public void init() throws Exception {
        this.vertices = new HashMap<Integer, Vertex>();
        this.arr = getArr();
        this.size = arr.length;
        this.residualArr = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            this.residualArr[i] = Arrays.copyOf(arr[i], size);
        }
        this.edgeMap = new HashMap<Vertex, List<Edge>>();
    }

    public void defineFlow() {
        List<Integer> visitedVertices = new ArrayList<>();
        visitedVertices.add(0);
        while (!visitedVertices.isEmpty()) {
            System.out.println(visitedVertices.toString());
            Integer vertexId = visitedVertices.get(0);
            visitedVertices.remove(0);
            Vertex vertex = vertices.get(vertexId);
            for (Edge edge : edgeMap.get(vertex)) {
                if (!edge.isFull() && !vertex.isMarked()) {
                    vertex.setMark(Mark.POSITIVE, vertexId, 2);
                    visitedVertices.add(edge.getTo());
                }
            }

        }

    }

    public void populateEdges() {
        for (int i = 0; i < size; i++) {
            List<Edge> edgeList = new ArrayList<Edge>();
            for (int j = 0; j < size; j++) {
                if (arr[i][j] > 0) {
                    edgeList.add(new Edge(i, j, 0, arr[i][j]));
                }
            }
            Vertex v = new Vertex(i);
            vertices.put(i, v);
            edgeMap.put(v, edgeList);
        }
    }

    public Integer[][] getArr() throws Exception {
        ArrayList<List<Integer>> lst = new ArrayList<>();
        try (InputStream is = Flow.class.getResourceAsStream("graph.txt");
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
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                //System.out.print(String.format("%3s", arr[i][j]));
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
