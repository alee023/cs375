import java.util.*;
import java.lang.Math ;

// uses comparator to help sort by duration desc, implemented in main fxn
class SortByDur implements Comparator<Job> {
	public int compare( Job a, Job b ) {
		if( a.duration < b.duration ) return 1 ;
		if( a.duration > b.duration ) return -1 ;
		return 0 ;
	}
}

public class LJF {
	// totalWait sums up all wait times to help calculate avg
	static double totalWait = 0;
	static double currTime ;
	static double maxWait = 0 ;
	
	public static void processJobs( ArrayList<Job> jobList ) {
		
		// 1st iteration through the job list will set the wait time of the first job to arrive with smallest duration to 0
		double smallestArrT = jobList.get( 0 ).arrival ;
		int indexOfFirst = 0 ;
		for( Job j : jobList ) {
			// finds smallest arrival time, which will be the first job to be run
			if( j.arrival < smallestArrT ) {
				smallestArrT = j.arrival ;
				indexOfFirst = j.ID ;
			}
		}
		currTime = jobList.get( indexOfFirst ).arrival + jobList.get( indexOfFirst ).duration ;
		jobList.get( indexOfFirst ).servT = currTime - jobList.get( indexOfFirst ).arrival ;
		jobList.get( indexOfFirst ).waitT = 0 ;
		jobList.remove( indexOfFirst ) ;
		
		while( !jobList.isEmpty()) {
			for( int i = 0; i < jobList.size(); i++ ) {
				// only jobs currently available can be done. e.g. if current time is 3 and a job arrives at time=6, that job cannot be done
				// also, since the list is sorted by duration, the first job with longest duration that available will be chosen
				if( jobList.get( i ).arrival < currTime ) {
					jobList.get( i ).waitT = currTime - jobList.get( i ).arrival ;
					if( jobList.get( i ).waitT > maxWait ) {
						maxWait = jobList.get( i ).waitT ;
					}
					totalWait += currTime - jobList.get( i ).arrival ;
					
					currTime += jobList.get( i ).duration ;
					jobList.get( i ).servT = currTime - jobList.get( i ).arrival ;
					jobList.remove( i ) ;
					
					break ;
				}
			}
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
		Collections.sort( jobs, new SortByDur()) ;
		processJobs( jobs ) ;
		
		System.out.println( "average wait time: " + totalWait / 100 ) ;
		System.out.println( "maximum wait time : " + maxWait ) ; // should probably be from the last jobs
		System.out.println( "time after finishing all jobs: " + currTime ) ; // realistically 50->100
	}
}