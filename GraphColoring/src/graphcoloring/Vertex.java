package graphcoloring;

/**
 *
 * @author krzysiek
 */
public class Vertex {
   private int vertexId;
   private int vertexDegree;
   private int color = -1;

    public int getVertexId() {
        return vertexId;
    }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }

    public int getVertexDegree() {
        return vertexDegree;
    }

    public void setVertexDegree(int vertexDegree) {
        this.vertexDegree = vertexDegree;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    
}
