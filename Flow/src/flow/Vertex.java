package flow;

/**
 *
 * @author krzysiek
 */
public class Vertex {

    private int id;
    private Mark mark;
    private Vertex from;
    private Integer markValue;

    public Vertex(int id) {
        this.id = id;
        this.mark = Mark.NONE;
    }

    public void clearMark(){
    	this.mark = Mark.NONE;
    	this.from = null;
    	this.markValue = null;
    }
    
    public void setMark( Mark mark, Vertex from, Integer markValue ){
        this.mark = mark;
        this.from = from;
        this.markValue = markValue;
    }
    
    public boolean isMarked(){
        return !Mark.NONE.equals(this.mark);
    }
    
    
    
    public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public Integer getMarkValue() {
		return markValue;
	}

	public void setMarkValue(Integer markValue) {
		this.markValue = markValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
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

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", mark=" + mark + ", from=" + from
				+ ", markValue=" + markValue + "]";
	}
    
    

}
