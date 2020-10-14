import java.util.* ;

public class Randomized {
	public static void swap( int[] arr, int i, int j ) {
		int temp = arr[ i ] ;
		arr[ i ] = arr[ j ] ;
		arr[ j ] = temp ;
	}
	
	public static int partition( int[] arr, int left, int right ) {
		int index = new Random().nextInt( right - left + 1 ) + left ;
		int pivot  = arr[ index ] ;
		int pI =  left ;
		
		// swap pivot with last element
		swap( arr, index, right ) ;
		for( int i = left; i < right ; i++ ) {
			if( arr[ i ] <= pivot ) {
				swap( arr, pI, i ) ;
				pI++ ;
			}
		}
		
		swap( arr, pI, right ) ;
		return pI ;
	}
	
	public static int smallestKth( int[] arr, int left, int right, int k ) {
		if( left == right ) {
			return arr[ left ] ;
		}
		
		int pI = partition( arr, left, right ) ;
		if( k == pI ) {
			return arr[ k ] ;
		}
		else if( k < pI ) {
			return smallestKth( arr, left, pI - 1, k ) ;
		}
		return smallestKth( arr, pI + 1, right, k ) ;
	}
	
	public static void main( String[] args ) {
		int[] A = { 5, 14, 9, 9, 11, 6, 13, 6, 16, 9 } ;
		int k = 5 ;
		System.out.println( smallestKth( A, 0, A.length - 1, k )) ;
	}
}