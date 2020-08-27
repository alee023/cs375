#include <iostream>

int fibR( int num ) {
	if( num == 0 ) {
		return 0 ;
	}
	else if( num == 1 ) {
		return 1 ;
	}
	else {
		return fibR( num - 1 ) + fibR( num - 2 ) ;
	}
}

int main( int argc, char *argv[]) {
	int n = atoi( argv[ 1 ]) ;
	std::cout << fibR( n ) << std::endl ;
}