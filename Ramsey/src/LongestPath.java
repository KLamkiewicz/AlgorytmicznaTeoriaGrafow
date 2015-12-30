import java.util.ArrayList;
import java.util.List;


public class LongestPath {

    private List<Integer> visitedVertices = new ArrayList<>();
    private int verticesCount;
    private Integer[][] arr;
    private final int STARTING_VERTEX = 3;
    
   public LongestPath(  ){

   }
    
   public boolean hamiltonianCycle(Integer[][] arr){
	   this.arr = arr;
	   verticesCount = arr.length;
       for( int i=0; i<verticesCount; i++){
    	   visitedVertices.clear();
    	   if(hasConnections(i)){
	    	   visitedVertices.add(i);
	    	   if( hamiltonianCycle( STARTING_VERTEX ) ){
		           //printHamiltonianCycle();
		           return true;
	    	   }
    	   }
//	       }else{
//	           System.out.println( "Graph does not contain hamiltonian cycle " );
//	       }
       }
       
       return false;
   }
    
   public boolean hasConnections( int i ){
	   for( int a=0; a<verticesCount; a++){
		   if(arr[i][a]!=0){
			   return true;
		   }
	   }
	   return false;
   }
   public boolean hamiltonianCycle( int vertex ){
      
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
   
   public  boolean isHamiltonian( int lastVertex ){
       return visitedVertices.size()>3;
   }
   
   public void deleteLast(){
       visitedVertices.remove( visitedVertices.size() - 1 );
   }
   
   public boolean canBeVisited( int start, int end ){

	   if( arr[start][end] != 1 ){
           return false;
       }
       if( wasVisited( end )  ){
           return false;
       }
       return true;
   }
   
   public boolean wasVisited( int vertex ){
       return visitedVertices.contains( vertex );
   }

    public void printHamiltonianCycle() {
        System.out.println("Hamiltonian cycle : ");
        int size = visitedVertices.size();
        for( int i=0; i<size; i++ ){
            System.out.print( visitedVertices.get(i) + " " + (i<size-1?"-> ":""));
        }
        System.out.println("");
        
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