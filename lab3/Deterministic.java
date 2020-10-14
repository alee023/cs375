import java.util.* ;

public class Deterministic {
	public static void swap( int[] arr, int i, int j ) {
		int temp = arr[ i ] ;
		arr[ i ] = arr[ j ] ;
		arr[ j ] = temp ;
	}
	
	

	public static void main( String[] args ) {
		int[] A = { 5, 14, 9, 9, 11, 6, 13, 6, 16, 9 } ;
		int k = 5 ;
		System.out.println( smallestKth( A, 0, A.length - 1, k )) ;
	}
}