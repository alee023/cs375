import java.util.* ;

public class Deterministic {
	public static void swap( int[] arr, int i, int j ) {
		int temp = arr[ i ] ;
		arr[ i ] = arr[ j ] ;
		arr[ j ] = temp ;
	}
	
	public static int partition( int[] arr, int pivot ) {
		int pI = 0 ;
		
		int index = 0 ;
		// finds index of pivot
		for( int i = 0; i < arr.length; i++ ) {
			if( arr[ i ] == pivot ) {
				index = i ;
				break ;
			}
		}
		// swap pivot with last element
		swap( arr, index, arr.length - 1 ) ;
		
		for( int i = 0; i < arr.length - 1; i++ ) {
			if( arr[ i ] <= pivot ) {
				swap( arr, pI, i ) ;
				pI++ ;
			}
		}
		
		// swap( arr, pI, right ) ;
		return pI ;
	}
	
	public static int select( int[] arr, int k ) {
		if( arr.length < 10 ) {
			Arrays.sort( arr ) ;
			return arr[ k ] ;
		}
		
		int[] medians = new int[( arr.length + 4 ) / 5 ] ;
		for( int i = 0; i < arr.length / 5; i++ ) {
			int l = i * 5 ;
			int r = ( i + 1 ) * 5 ;
			// System.out.println( "l: " + l + " r: " + r + "\n" ) ;
			
			int[] newArr = Arrays.copyOfRange( arr, l, r ) ;
			medians[ i ] = select( newArr, 3 ) ;
			// System.out.println( medians[ i ] ) ;
		}
		if( arr.length % 5 != 0 ) {
			int[] newArr = Arrays.copyOfRange( arr, ( arr.length / 5 ) * 5, arr.length ) ;
			medians[ medians.length - 1 ] = select( newArr, newArr.length / 2 ) ;
			// System.out.println( medians[ medians.length - 1 ] ) ;
		}
		
		int Median = select( medians, medians.length / 2 ) ;
		// System.out.print( "med of meds: " + Median + "\n" ) ;
		
		int pivotIndex = partition( arr, Median ) ;
		int[] arr1 = Arrays.copyOfRange( arr, 0, pivotIndex ) ;
		int[] arr3 = Arrays.copyOfRange( arr, pivotIndex + 1, arr.length ) ;
		
		if( k <= arr1.length ) {
			return select( arr1, k ) ;
		}
		else if( k > arr1.length + 1 ) {
			return select( arr3, k - arr1.length - 1 ) ;
		}
		else {
			return Median ;
		}
	}
	
	public static void main( String[] args ) {
		int[] A = { 4, 16, 19, 9, 17, 2, 11, 16, 8, 16, 9, 14, 9, 11, 8, 13, 10, 9, 14, 17 } ;
		int k = 10 ;
		System.out.println( select( A, k )) ;
	}
}