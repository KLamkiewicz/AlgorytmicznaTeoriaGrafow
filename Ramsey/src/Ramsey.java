import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class Ramsey {
	private final int SIZE = 4;
	private final int colorCount = 2;
	private final int edgesCount;
	private Integer[][] sapphireArr = new Integer[SIZE][SIZE];
	private Integer[][] scarletArr = new Integer[SIZE][SIZE];
	private int permutationCount;
	
	public static void main( String args[] ) throws Exception{

		Ramsey ramsey = new Ramsey();
		ramsey.findCriticals();
//		new LongestPath().containsP4( new Integer[][]{
//		new Integer[]{0, 0, 1, 1},  
//		new Integer[]{0, 0, 0, 1}, 
//		new Integer[]{1, 0, 0, 0},
//		new Integer[]{1, 1, 0, 0}
//		});
//		new LongestPath( new Integer[][]{
//				new Integer[]{
//						0, 1, 1, 0
//				},
//				new Integer[]{
//						1, 0, 1, 0
//				},
//				new Integer[]{
//						1, 1, 0, 0
//				},
//				new Integer[]{
//						0, 0, 0, 0
//				}
//		}).hamiltonianCycle();
	}
	
	
	public int[][] getAllPermutations(){
		
		int permutations[][] = new int[permutationCount][edgesCount];
		for( int i=0; i<permutationCount; i++){
			for(int j=0; j<edgesCount; j++){
		        int val = permutationCount * j + i;
		        int ret = (1 & (val >>> j));
				permutations[i][j] = ret!=0?1:0;
			}
		}
		return permutations;
	}
	
	public void findCriticals() throws Exception{
		int permutations[][] = getAllPermutations();
		for(int i=0; i<permutationCount; i++){
			System.out.print( " \nPermutation " + i);
			colorPermutations(permutations[i]);
			checkIfPermutationIsCritical(permutations[i]);
			reinitializeColorArrays();
		}
	}
	
	public void checkIfPermutationIsCritical(int[] permutations) throws Exception{
		//CHECK TO BACKTRACKING FOR FINDING THE LONGEST PATH
		LongestPath lp = new LongestPath();
		System.out.println();
		boolean sapphireIsCritical = lp.containsP4(sapphireArr);
		boolean scarletIsCritical = lp.containsP4(scarletArr);
		System.out.println(sapphireIsCritical);
		System.out.println(scarletIsCritical);
		boolean isCritical = !(sapphireIsCritical || scarletIsCritical );
		
		if( isCritical ){
			System.out.println( " CRITICAL ----------------------------------------------------------");
		}
		System.out.println( " sapphire ");
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				System.out.print(sapphireArr[x][y] + " ");
			}System.out.println();
		}
		System.out.println( " scarlet ");
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				System.out.print(scarletArr[x][y] + " ");
			}System.out.println();
		}
	}
	
	public void colorPermutations(int[] permutation){
		for(int i=0; i<permutation.length; i++){
			if( permutation[i]==0){
				fillColors(i,sapphireArr);
			}else{
				fillColors(i,scarletArr);
			}
		}		
	}
	
	public void fillColors( int i,Integer[][] color ){
		switch( i ){
			case 0:
				color[0][1] = 1;
				color[1][0] = 1;
 				break;
			case 1:
				color[0][2] = 1;
				color[2][0] = 1;
				break;
			case 2:
				color[0][3] = 1;
				color[3][0] = 1;
				break;
			case 3:
				color[1][2] = 1;
				color[2][1] = 1;
				break;
			case 4:
				color[1][3] = 1;
				color[3][1] = 1;
				break;
			case 5:
				color[2][3] = 1;
				color[3][2] = 1;
				break;
		}
	}
	
	public Ramsey() throws Exception {
		edgesCount = (SIZE*(SIZE-1))/2;
		init();
	}
	
	private void init(){
		reinitializeColorArrays();
		permutationCount = (int) Math.pow(colorCount, edgesCount);
	}
	
	private void reinitializeColorArrays(){
		for( int i=0; i<SIZE; i++){
			Arrays.fill( sapphireArr[i], 0 );
			Arrays.fill( scarletArr[i], 0 );
		}
	}
}
