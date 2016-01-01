import java.util.ArrayList;
import java.util.List;


public class LongestPath {

    private List<Integer> visitedVertices = new ArrayList<>();
    private int verticesCount;
    private Integer[][] arr;
    private int STARTING_VERTEX;
    
   public LongestPath(  ){

   }
    
   public boolean containsP4(Integer[][] arr){
	   this.arr = arr;
	   verticesCount = arr.length;
	   //displayMatrix(arr);
	   for( int i=0; i<verticesCount; i++){
		   //System.out.println( " Starting vertex "  + i);
    	   visitedVertices.clear();
    	   if(hasConnections(i)){
    		   STARTING_VERTEX = i;
	    	   visitedVertices.add(i);
	    	   if( containsP4( i ) ){
	    		   //printPath();
		           return true;
	    	   }
    	   }
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
   
   public boolean containsP4( int vertex ){
      
       if( isP4(vertex) ){
           //int first = visitedVertices.get( 0 );
           //visitedVertices.add( first );
    	   printPath();
           return true;
       }
       
       for( int i=0; i<verticesCount; i++ ){
           if( canBeVisited( vertex, i ) ){
               visitedVertices.add( i );         
               if( containsP4( i ) ){
                   return true;
               }        
               deleteLast();      
           }
       }
       return false;
   } 
   
   public  boolean isP4( int lastVertex ){
	   int vSize = visitedVertices.size();
	   if( vSize > 3 ){	   
		   for( int i = 0; i<verticesCount; i++ ){
			   if( arr[lastVertex][i] == 1 && i!=visitedVertices.get(vSize-2)){
				   visitedVertices.add(i);printPath();
		           return true;
		       }
		   }
	   }
       return false;
   }
   
   public void deleteLast(){
       visitedVertices.remove( visitedVertices.size() - 1 );
   }
   
   public boolean canBeVisited( int start, int end ){
	   
	   //System.out.println( "val "  + arr[start][end] +  " Start " + start  + " end " + end);
	   if( arr[start][end] != 1 ){
           return false;
       }
       if( wasVisited( end ) ){
           return false;
       }
       return true;
   }
   
   public boolean wasVisited( int vertex ){
       return visitedVertices.contains( vertex );
   }

    public void printPath() {
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