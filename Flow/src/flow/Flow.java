package flow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;
/**
 *
 * @author krzysiek
 */
public class Flow {

    private Integer[][] arr;
    private Integer[][] residualArr;
    private int size;
    private Map<Vertex, List<Edge>> edgeMap;
    private Map<Vertex, List<Edge>> residualEdgeMap;
    private Map<Integer, Vertex> vertices;
    private final int SINK;
    
    private static final String NO_CONNECTION_EXCEPTION = "There is no connection from source to the sink ";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Flow flow = new Flow();
        Dijkstra dijkstra = new Dijkstra();
        if( !dijkstra.connectionExists(0,  flow.SINK) ){
            throw new Exception( NO_CONNECTION_EXCEPTION );
        }
        
        flow.findFlow();
    }

    public Flow() throws Exception {
        init();
        SINK = arr.length - 1;
        populateEdges(edgeMap, arr);
        populateEdges(residualEdgeMap, residualArr);
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
        this.residualEdgeMap = new HashMap<Vertex, List<Edge>>();
    }

    /**
     * Checks whether there is an augumentation path possible.
     * Marks the vertices while creating one.
     * @return
     */
    public boolean containsAugumentationPath() {
        List<Integer> visitedVertices = new ArrayList<>();

        
        Vertex startVertex = vertices.get(0);
        startVertex.setMark(Mark.NEGATIVE, new Vertex(-1) , Integer.MAX_VALUE);
        visitedVertices.add( startVertex.getId());
        List<Integer> minimumCut = new ArrayList<>();
        minimumCut.add(startVertex.getId());
        
        while (!visitedVertices.isEmpty()) {
            Integer vertexId = visitedVertices.get(0);
            //System.out.println( visitedVertices);
            visitedVertices.remove(0);
            Vertex vertex = vertices.get(vertexId);
            for (Vertex vert : vertices.values()) {
                System.out.println(vert);
            }
            for (Edge edge : edgeMap.get(vertex)) {
            	
                if (!edge.isFull() && !edge.getTo().isMarked()) {
                	int flow = Math.min(edge.getFrom().getMarkValue(), edge.getRemainingCapacity());
                	edge.getTo().setMark(Mark.POSITIVE, edge.getFrom(), flow );
                    visitedVertices.add(edge.getTo().getId());
                    minimumCut.add(edge.getTo().getId());
                    System.out.println(" ege " + edge);
                    
                }
                
            }
            System.out.println( visitedVertices );
            if( !visitedVertices.isEmpty()){
            vertices.get( visitedVertices.get(0));
            Vertex v = vertices.get( visitedVertices.get(0));
            for (Edge residualEdge : residualEdgeMap.get(v)) {

                System.out.println(residualEdge);

                System.out.println(visitedVertices);
                if (!residualEdge.isFull() && !residualEdge.getTo().isMarked()
                        //&& visitedVertices.get(0).equals(residualEdge.getFrom().getId())
                        && arr[v.getId()][residualEdge.getTo().getId()] == 0 
                        && residualArr[residualEdge.getTo().getId()][v.getId()] != 0 
                        
                        //!residualArr[edge.getTo().getId()][residualEdge.getTo().getId()].equals(
                        //arr[edge.getTo().getId()][residualEdge.getTo().getId()])
                        ) {
                    System.out.println("---");
                    System.out.println(residualEdge);
                    System.out.println( "edge to diff " + residualEdge.getTo());
                    System.out.println(" arr " );
                    System.out.println( " v.getId " + v.getId()  + " res.gettogetid " + residualEdge.getTo().getId());
                    System.out.println( arr[v.getId()][residualEdge.getTo().getId()]);
                    int flowRes = Math.min(v.getMarkValue(), residualArr[residualEdge.getTo().getId()][v.getId()] );
                    System.out.println(" FLOW RES " + flowRes);
                    System.out.println( " V mark value " + v.getMarkValue());
                    System.out.println(" resarr value " + residualArr[residualEdge.getTo().getId()][v.getId()]);
                    
                    residualEdge.getTo().setMark(Mark.NEGATIVE, residualEdge.getFrom(), flowRes);
                    System.out.println("--");
                    visitedVertices.add(residualEdge.getTo().getId());
                    minimumCut.add(residualEdge.getTo().getId());
                }
            }
        }

            if( vertexId == SINK ){
            	return true;
            }
        }
        
        List<Integer> VminX = new ArrayList<Integer>();
        for( int i=0; i<=SINK; i++ ){
            if( !minimumCut.contains(i)){
                VminX.add(i);
            }
        }
        int minCut = 0;
        int maxFlow = 0;
        for( Integer v : minimumCut ){
            for( Edge edge: edgeMap.get(vertices.get(v))){
                if( VminX.contains(edge.getTo().getId())){
                    System.out.println(edge);
                    minCut+=edge.getFlow();
                }
                if (v == 0) {
                    maxFlow += edge.getFlow();
                }
            }
        }
        
        System.out.println( minimumCut);
        System.out.println(VminX);
        System.out.println( " Minimum cut " + minCut );
        System.out.println( " Max flow " + maxFlow );
        return false;
    }
    
    
    public void setNegativesOnAllPrevious( Vertex vertex ){
    }
    
    public void findFlow(){
    	List<Integer> visitedVertices = new ArrayList<Integer>();
    	
    	while ( containsAugumentationPath() ){
    		Vertex vert = vertices.get(SINK);
                int currentFlow = vert.getMarkValue();
    		while( vert!=vertices.get(0)){
                    Mark mark = vert.getMark();
                    
                    if ( mark.equals(Mark.NEGATIVE)){
                        Vertex from = vert;
                        Vertex to = vert.getFrom();
                        for( Edge edge : edgeMap.get(from)){
                            System.out.println(edge);
                            if( edge.getTo().equals(to)){
                            int valFlow = Mark.POSITIVE.equals(mark) ? currentFlow : -currentFlow;

                            int newFlow = edge.getFlow() + valFlow;
                            edge.setFlow(newFlow);
                                System.out.println(" val flow " + valFlow);
                                System.out.println(" new flow " + newFlow);
                            System.out.println(" arr " + vert.getId() + " "+ vert.getFrom().getId() + " " + residualArr[vert.getId()][vert.getFrom().getId()]);
                            System.out.println(" arr " + residualArr[vert.getFrom().getId()][vert.getId()]);
                            residualArr[vert.getId()][vert.getFrom().getId()] = residualArr[vert.getId()][vert.getFrom().getId()] - valFlow;
                            residualArr[vert.getFrom().getId()][vert.getId()] = residualArr[vert.getFrom().getId()][vert.getId()] + valFlow;
                            System.out.println(" arr " + vert.getId() + " " + vert.getFrom().getId() + " " + residualArr[vert.getId()][vert.getFrom().getId()]);
                            System.out.println(" arr " + residualArr[vert.getFrom().getId()][vert.getId()]);

                            }
                        }
                    }
                    
                    for (Edge edge : edgeMap.get(vert.getFrom())) {
                        System.out.println("get to " + edge.getTo());
                        if (edge.getTo().equals(vert)) {
                            System.out.println(" in " + edge);
                            int valFlow = Mark.POSITIVE.equals(mark) ? currentFlow : -currentFlow;
                            int newFlow = edge.getFlow() + valFlow;
                            edge.setFlow(newFlow);
                            residualArr[vert.getId()][vert.getFrom().getId()] = arr[vert.getId()][vert.getFrom().getId()] + valFlow;
                            residualArr[vert.getFrom().getId()][vert.getId()] = arr[vert.getFrom().getId()][vert.getId()] - valFlow;

                        }
                    }

                    vert = vert.getFrom();
    		}
                
                populateEdges(residualEdgeMap, residualArr );
                System.out.println( " \n residual arr ");
    		displayMatrix(residualArr);
                System.out.println(" \nregular arr");
                displayMatrix(arr);
    		displayEdges();
    		clearMarks();
    	}

    }
 
    public void populateEdges( Map<Vertex,List<Edge>> map, Integer[][] array ) {
        for (int i = 0; i < size; i++) {
            List<Edge> edgeList = new ArrayList<Edge>();
            Vertex vertexFrom;
            if( vertices.containsKey(i)){
            	vertexFrom = vertices.get(i);
            }else{
            	vertexFrom = new Vertex(i);
                vertices.put(i, vertexFrom);
            }
            for (int j = 0; j < size; j++) {
                if (array[i][j] > 0) {
                	Vertex vertexTo;
                	if(vertices.containsKey(j) ){
                		vertexTo = vertices.get(j);
                	}else{
                		vertexTo = new Vertex(j); 
                		vertices.put(j, vertexTo);
                	}
                    edgeList.add(new Edge(vertexFrom, vertexTo, 0, array[i][j]));
                }
            }
            map.put(vertexFrom, edgeList);
        }
    }

    private void clearMarks(){
    	for(int i=1; i<=SINK; i++){
    		vertices.get(i).clearMark();
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
                System.out.print(String.format("%3s", arr[i][j]));
                //System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void displayEdges(){
    	for( List<Edge> edges : edgeMap.values()){
    		for( Edge edge : edges ){
    			System.out.println( edge );
    		}
    	}
    }
}
