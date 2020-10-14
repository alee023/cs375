import java.util.*;
import java.lang.Math ;

// uses comparator to help sort by duration, implemented in main fxn ASCENDING
class SortByDurAsc implements Comparator<Job> {
	public int compare( Job a, Job b ) {
		if( a.duration < b.duration ) return -1 ;
		if( a.duration > b.duration ) return 1 ;
		return 0 ;
	}
}

// sort by duration, DESCENDING
class SortByDurDesc implements Comparator<Job> {
	public int compare( Job a, Job b ) {
		if( a.duration < b.duration ) return 1 ;
		if( a.duration > b.duration ) return -1 ;
		return 0 ;
	}
}

// uses comparator to help sort by arrival time
class SortByArrival implements Comparator<Job> {
	public int compare( Job a, Job b ) {
		if( a.arrival < b.arrival ) return -1 ;
		if( a.arrival > b.arrival ) return 1 ;
		return 0 ;
	}
}

public class Jobs {
	// totalWait sums up all wait times to help calculate avg so we don't have to iterate over the jobs list to sum up wait times
	static double totalWait ;
	static double currTime ;
	static double maxWait ;
	
	public static void SJF( ArrayList<Job> jobList ) {
		totalWait = 0 ;
		maxWait = 0 ;
		
		// 1st iteration through the job list will set the wait time of the first job to arrive with smallest duration to 0. the arrival time of the first job is not necessarily 0 bc of its random nature
		// HELPS SET currTime TO A NUMBER SO THE IF STATEMENT IN WHILE-FOR LOOP CAN RUN PROPERLY
		double smallestArrT = jobList.get( 0 ).arrival ;
		int indexOfFirst = 0 ; // index of the job with first arrival time
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
				// IF CONDITION: only jobs currently available can be done. e.g. if current time is 3 and a job arrives at time=6, that job cannot be done
				// also, since the list is sorted by duration, the first job with shortest duration that available will be chosen
				if( jobList.get( i ).arrival < currTime ) {
					jobList.get( i ).waitT = currTime - jobList.get( i ).arrival ;
					if( jobList.get( i ).waitT > maxWait ) { // used to set max waiting time
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
	
	public static void LJF( ArrayList<Job> jobList ) {
		totalWait = 0 ;
		maxWait = 0 ;
		
		// 1st iteration through the job list will set the wait time of the first job to arrive with longest duration to 0
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
	
	public static void FCFS( ArrayList<Job> jobList ) {
		totalWait = 0 ;
		maxWait = 0 ;
		
		// initializes currTime to non-zero double by starting w the first job outside a loop
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
		ArrayList<Job> jobs1 = new ArrayList<Job>() ; // to go thru SJF
		double arrT, dur ;
		
		for( int n = 0; n < 100; n++ ) {
			arrT = Math.random() ; // [ 0, 1 )
			dur = ( Math.random() * 0.5 ) + 0.5 ; // [ 0.5, 1 )
			
			jobs1.add( new Job( n, arrT, dur )) ;
		}
		
		ArrayList<Job> jobs2 = new ArrayList<Job>( jobs1 ) ; // LJF
		ArrayList<Job> jobs3 = new ArrayList<Job>( jobs1 ) ; // FCFS
		
		// sorting to hopefully minimize run time
		System.out.println( "------------------------- SJF ---------------------------" ) ;
		Collections.sort( jobs1, new SortByDurAsc()) ;
		SJF( jobs1 ) ;
		System.out.println( "average wait time: " + totalWait / 100 ) ;
		System.out.println( "maximum wait time : " + maxWait ) ; // should probably be from the last jobs
		System.out.println( "time after finishing all jobs: " + currTime ) ; // realistically 50->100
		
		System.out.println( "\n------------------------- LJF ---------------------------" ) ;
		Collections.sort( jobs2, new SortByDurDesc()) ;
		LJF( jobs2 ) ;
		System.out.println( "average wait time: " + totalWait / 100 ) ;
		System.out.println( "maximum wait time : " + maxWait ) ; // should probably be from the last jobs
		System.out.println( "time after finishing all jobs: " + currTime ) ; // realistically 50->100
		
		System.out.println( "\n------------------------- FCFS ---------------------------" ) ;
		Collections.sort( jobs3, new SortByArrival()) ;
		FCFS( jobs3 ) ;
		System.out.println( "average wait time: " + totalWait / 100 ) ;
		System.out.println( "maximum wait time : " + maxWait ) ; // should probably be from the last jobs
		System.out.println( "time after finishing all jobs: " + currTime ) ; // realistically 50->100
	}
}