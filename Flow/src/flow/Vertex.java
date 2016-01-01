package flow;

/**
 *
 * @author krzysiek
 */
public class Vertex {

    private int id;
    private Mark mark;
    private int from;
    private int markValue;

    public Vertex(int id) {
        this.id = id;
        this.mark = Mark.NONE;
    }

    public void setMark( Mark mark, int from, int markValue ){
        this.mark = mark;
        this.from = from;
        this.markValue = markValue;
    }
    
    public boolean isMarked(){
        return !Mark.NONE.equals(this.mark);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
