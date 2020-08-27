#include <iostream>

int fibI( int num ) {
	int prevprev, prev, curr ;
	prev = 0 ;
	curr = 1 ;
	for( int i = 1; i < num; i++ ) {
		prevprev = prev ;
		prev = curr ;
		curr = prevprev + prev ;
	}

	return curr ;
}

int main( int argc, char *argv[]) {
	int n = atoi( argv[ 1 ]) ;
	int ret ;
	for( int i = 0; i < 1000000 - 1; i++ ) {
		ret = fibI( n ) ;
	}
	std::cout << ret << std::endl ;
}