import java.util.*;
import java.lang.Math ;

// uses comparator to help sort by arrival time, implemented in main fxn
class SortByArrival implements Comparator<Job> {
	public int compare( Job a, Job b ) {
		if( a.arrival < b.arrival ) return -1 ;
		if( a.arrival > b.arrival ) return 1 ;
		return 0 ;
	}
}

public class FCFS {
	// totalWait sums up all wait times to help calculate avg
	static double totalWait = 0;
	static double currTime ;
	static double maxWait = 0 ;
	
	public static void processJobs( ArrayList<Job> jobList ) {
		// initializes currTime to non-zero double
		currTime = jobList.get( 0 ).arrival + jobList.get( 0 ).duration ;
		jobList.get( 0 ).servT = currTime - jobList.get( 0 ).arrival ;
		jobList.get( 0 ).waitT = 0 ;
		
		for( int i = 1; i < jobList.size(); i++ ) {
			jobList.get( i ).waitT = currTime - jobList.get( i ).arrival ;
			if( jobList.get( i ).waitT > maxWait ) {
				maxWait = jobList.get( i ).waitT ;
			}
			totalWait += currTime - jobList.get( i ).arrival ;
			
			currTime += jobList.get( i ).duration ;
			jobList.get( i ).servT = currTime - jobList.get( i ).arrival ;
		}
	}
	
	public static void main( String[] args ) {
		ArrayList<Job> jobs = new ArrayList<Job>() ;
		double arrT, dur ;
		
		for( int n = 0; n < 100; n++ ) {
			arrT = Math.random() ; // [ 0, 1 )
			dur = ( Math.random() * 0.5 ) + 0.5 ; // [ 0.5, 1 )
			
			jobs.add( new Job( n, arrT, dur )) ;
		}
		
		// sorting to hopefully minimize run time
		Collections.sort( jobs, new SortByArrival()) ;
		processJobs( jobs ) ;
		
		System.out.println( "average wait time: " + totalWait / 100 ) ;
		System.out.println( "maximum wait time : " + maxWait ) ; // should probably be from the last jobs
		System.out.println( "time after finishing all jobs: " + currTime ) ; // realistically 50->100
	}
}