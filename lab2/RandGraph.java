import java.lang.Math;
import java.util.ArrayList ;
import java.util.Stack ;
import java.util.Scanner; 

public class RandGraph {
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
		printMatrix() ;
		System.out.print( "vertices: " ) ;
		for( int num : graphNums ) {
			System.out.print( num + " " ) ;
		}
	}
	
	public static void printMatrix() {
		for( int i = 0; i < adjMatrix.length; i++ ) {
			for( int j = 0; j < adjMatrix[ 0 ].length; j++ ) {
				System.out.print( adjMatrix[ i ][ j ] + " " ) ;
			}
			System.out.print( "\n" ) ;
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
		// assumes command line arguments are within ranges
		
		int nodes = Integer.parseInt( args[ 0 ]) ;
		double prob = Double.parseDouble( args[ 1 ]) ;
		adjMatrix = new int[ nodes ][ nodes ] ;
		
		genGraph( nodes, prob ) ;
		
		int largestSize = largestComp() ;
		System.out.println( "Size of the largest connected component: " + largestSize ) ;
		Scanner sc = new Scanner( System.in ) ; 
		int threshold = sc.nextInt();  // asks only once xd
		System.out.print( "\nThe size of the largest connected component is" ) ;
        if( largestSize < threshold ) {
			System.out.print( " not" ) ;
		}
		System.out.print( " above the threshold." ) ;
		
		// Graph plotter: https://graphonline.ru/en/create_graph_by_matrix
	}
}