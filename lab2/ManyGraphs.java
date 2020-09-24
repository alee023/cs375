import java.lang.Math;
import java.util.ArrayList ;
import java.util.Stack ;

public class ManyGraphs {
	static int[][] adjMatrix ;
	static ArrayList<Integer> graphNums = new ArrayList<Integer>();
	
	public static void genGraph( int n, double p ) {
		double prob ;
		
		// populate the graph
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < i; j++ ) {
				prob = Math.random() ;
				
				if( prob < p && j != i ) {
					adjMatrix[ i ][ j ] = 1 ;
					adjMatrix[ j ][ i ] = 1 ;
					if( graphNums.indexOf( i ) == -1 ) graphNums.add( i ) ;
					if( graphNums.indexOf( j ) == -1 ) graphNums.add( j ) ;
				}
			}
		}
	}
	
	public static int DPSHelper() {
		Stack<Integer> stack = new Stack<Integer>() ;
		stack.push( graphNums.remove( 0 )) ;
		int count = 1 ;
		boolean neighborFound = false ;
		
		while( !stack.isEmpty() && graphNums.size() > 0 ) {
			// parse the row indicated by current node until a neighbor is reached
			for( int i = 0; i < adjMatrix[ stack.peek()].length; i++ ) {
				// System.out.println( "current row: " + stack.peek() + " ; ") ;
				neighborFound = false ;
				
				// if a neighbor is found and unvisited
				if( adjMatrix[ stack.peek()][ i ] == 1 && graphNums.indexOf( i ) != -1 ) {
					neighborFound = true ;
					stack.push( i ) ;
					// System.out.print( " found neighbor: " + i + " ; " ) ;
					graphNums.remove( graphNums.indexOf( i )) ;
					count++ ;
					break ; // stop searching that row for now
				}
			}
			if( neighborFound == false ) {
					stack.pop() ;
			}
		}
		
		return count ; // stack is empty
	}
	
	public static int largestComp() {
		int largest = 0 ;
		int currentSize = 0; 
		while( graphNums.size() != 0 ) {
			currentSize = DPSHelper( ) ;
			if( largest < currentSize ) largest = currentSize ;
		}
		
		return( largest ) ;
	}
	
	public static void main( String[] args ) {
		int numAbove30 ; 
		double percentage ;
		int largestComp = 0 ;
		for( double c = 0.2; c <= 3.1; c += 0.2 ) {
			double p = c / 40 ; 
			System.out.print( "\nP: " + p ) ;
			numAbove30 = 0 ;
			for( int i = 0; i < 500; i++ ) {	
				adjMatrix = new int[ 40 ][ 40 ] ; // resets matrix
				graphNums.clear() ;
				
				genGraph( 40, p ) ;
				largestComp = largestComp() ;
				if( largestComp >=  30 ) { // threshold = 30
					numAbove30++ ;
				}
			}
			System.out.println( "\nNum graphs above threshold: " + numAbove30 ) ;
			percentage = ( numAbove30 / 500.0 ) * 100 ;
			System.out.println( percentage + "% for c = " + c ) ;
		}
	}
}