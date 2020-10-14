import java.util.ArrayList ;

public class coins {
	static int coins[] = { 1, 5, 10, 25, 100 } ; // assumes sorted least->greatest
	
	public static ArrayList<String> minCoins( int value ) {
		ArrayList<String> output = new ArrayList<String>() ;
		int counter = 0 ;
		
		int denom_size = coins.length ;
		for( int i = denom_size - 1; i >= 0; i-- ) {
			counter = 0 ;
			while( coins[ i ] <= value ) {
				value -= coins[ i ] ;
				counter++ ;
			}
			output.add( "" + counter + " " + coins[ i ] + "-cent coins\n" ) ;
		}
		
		return output ;
	}
	
	public static void main( String[] args ) {
		int cents = 56 ; // two 25s, one 5, one 1
		System.out.println( "The minimum coins forming " + cents + " cents is: \n" ) ;
		ArrayList<String> ret = minCoins( cents ) ;
		for( String str : ret ) {
			System.out.print( str ) ;
		}
		
		System.out.println( "\n" + ret.size() + " coins are needed." ) ; 
	}
}