package Sandbox.BmjInformatica.part5b;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class FileMerge {
    private static List< Name > combinedNames = new ArrayList< Name >( );
    private static List< List< Name > > allLists = new ArrayList< List< Name > >( );
    
    /**
     * Accepts a list of input files and a single output file as parameters.
     * Processes all input file and sorts combined list of names based on Surname, Forename.
     * Then writes sorted list to named output file.
     * 
     * @param inputFiles
     * @param outputFile
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public void processFiles( List< String > inputFiles, String outputFile ) throws InterruptedException, ExecutionException, IOException {
		// Creates a cached thread pool
		ExecutorService executorService = Executors.newCachedThreadPool( );

		// List to keep track of all futures (results of Callable)
		List< Future< List< Name > > > futures = new ArrayList< Future< List< Name > > >( );

		// Process all input files
		for ( int i = 0; i < inputFiles.size( ); i++ ) {
			Callable< List< Name > > worker = new ReadFileThread( inputFiles.get( i ) );

			Future< List< Name > > submit = executorService.submit( worker );

			futures.add( submit );
		}

		// Process futures to create combined list
		for ( Future< List< Name > > future: futures ) {
			try {
				allLists.add( future.get( ) );
			}
			catch ( InterruptedException e ) {
				// Write error to log file then re-throw
				throw new InterruptedException( e.getMessage( ) );
			}
			catch ( ExecutionException e ) {
				// Write error to log file then re-throw
				throw new ExecutionException( e.getMessage( ), e.getCause( ) );
			}
		}

		executorService.shutdown( );

		processAllLists( );

		sortNames( );

		writeToOutputFile( outputFile );
    }
    
    
    private void processAllLists( ) {
		for( List< Name > list : allLists ) {
	    	processList( list );
		}
    }


    private void sortNames( ) {
	Collections.sort( combinedNames );
    }
    
    
    private void processList( List< Name > list ) {
		for( Name name : list ) {
			combinedNames.add( name );
		}
    }
    
    
    private void writeToOutputFile( String outputFile ) throws IOException {
	
		try ( FileWriter writer = new FileWriter( outputFile ) ) {
			for( Name name : combinedNames ) {
				// write output
				writer.append( name.toString( ) );
			}
		}
    }
}
