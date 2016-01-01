package flow;

/**
 *
 * @author krzysiek
 */
public class Edge {
    private int from;
    private int to;
    private int flow;
    private int capacity;

    public Edge(int from, int to, int flow, int capacity) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;
    }
    
    
    public boolean isFull(){
        return flow==capacity;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getFlow() {
        return flow;
    }

    public int getCapacity() {
        return capacity;
    }
    
    
}
