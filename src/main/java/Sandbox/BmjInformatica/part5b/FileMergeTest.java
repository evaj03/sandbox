package Sandbox.BmjInformatica.part5b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FileMergeTest {

    public static void main( String[ ] args ) {
	Date start = new Date( );
	
	if ( args.length >= 2 ) {
	    try {
		FileMerge fileMerge = new FileMerge( );
		
		String outputFile = args[ ( args.length - 1 ) ];    // Output file is last argument
		
		fileMerge.processFiles( getInputFiles( args ), outputFile );

		//displayOutputFile( outputFile );
	    }
	    catch ( IOException | InterruptedException | ExecutionException e ) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	else {
	    System.err.println( "Error: Insufficient parameters..." );
	    System.exit( 0 );
	}
	
	Date end = new Date( );
	System.out.format( "Elapsed Time: %d milliseconds.%n", ( end.getTime( ) - start.getTime( ) ) );
    }
    
    
    private static List< String > getInputFiles( String[ ] args ) {
	ArrayList< String > files = new ArrayList< String >( );
	
	for ( int i = 0; i < ( args.length - 1 ); i++ ) {
	    files.add( args[ i ] );
	}
	
	return files;
    }

    
    public static void displayOutputFile( String resultsFile ) {
	try ( BufferedReader in = new BufferedReader( new FileReader( resultsFile ) ) ) {
	    String record;
		
	    while ( ( record = in.readLine( ) ) != null ) {
		System.out.println( record );
	    }
	}
	catch ( FileNotFoundException e ) {
	    System.err.println( "Error: " + e.getMessage( ) );
	    System.exit( 0 );
	}
	catch ( IOException e ) {
	    System.err.println( "Error: " + e.getMessage( ) );
	    System.exit( 0 );
	}
    }
}
