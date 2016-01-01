package flow;

/**
 *
 * @author krzysiek
 */
public class Edge {
    private Vertex from;
    private Vertex to;
    private int flow;
    private int capacity;

    public Edge(Vertex from, Vertex to, int flow, int capacity) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;
    }
    
    
    public boolean isFull(){
        return flow==capacity;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public int getFlow() {
        return flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingCapacity() {
    	return capacity-flow;
    }

    
    
	public void setFrom(Vertex from) {
		this.from = from;
	}


	public void setTo(Vertex to) {
		this.to = to;
	}


	public void setFlow(int flow) {
		this.flow = flow;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	@Override
	public String toString() {
		return "Edge [from=" + from.getId() + ", to=" + to.getId() + ", flow=" + flow
				+ ", capacity=" + capacity + "]";
	}


	@Override
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (this.getTo() != other.getTo()) {
            return false;
        }
        if( this.getFrom() != other.getFrom()) {
        	return false;
        }
        return true;
	}

	
}
