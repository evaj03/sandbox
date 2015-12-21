package Sandbox.BmjInformatica.part5b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class ReadFileThread implements Callable< List< Name > > {
    private final String inputFile;
    private final List< Name > names = new ArrayList< Name >( );

    public ReadFileThread( String inputFile ) {
	this.inputFile = inputFile;
    }


    /**
     * Processes input file storing records in List collection.
     * Returns a 'read-only' copy of the list.
     * 
     * @return	List< Name >
     * @throws	IOException
     */
    @Override
    public final List< Name > call( ) throws IOException {
	//System.out.println( Thread.currentThread( ).getName( ) + " Start read for Input File = " + inputFile );
	
	processFile( );
	
	//System.out.println( Thread.currentThread( ).getName( ) + " Finished read for Input File = " + inputFile );
	
	return Collections.unmodifiableList( names );
    }

    
    /**
     * Reads content from file, parses each record into a Name object
     * and add to List collection.
     * 
     * @throws IOException 
     * @throws FileNotFoundException 
     * 
     */
    private void processFile( ) throws FileNotFoundException, IOException {
	// Read and store input
	try ( BufferedReader in = new BufferedReader( new FileReader( this.inputFile ) ) ) {
	    String record;

	    while ( ( record = in.readLine( ) ) != null ) {
		parseRecord( record );
	    }
	}
	catch ( FileNotFoundException e ) {
	    // Catch exception to write to log file.
	    throw new FileNotFoundException( e.getMessage( ) );
	}
	catch ( IOException e ) {
	    // Catch exception to write to log file.
	    throw new IOException( e.getMessage( ) );
	}
    }

    
    /**
     * Takes given record and parses into individual fields
     * based on space delimiter.
     * <p>
     * Resulting fields are stored in list collection.
     * 
     * @param record	Record to be parsed and stored.
     */
    private void parseRecord( final String record ) {

	if ( ! record.isEmpty( ) ) {
	    String[ ] splitRecord = record.split( "\\s+" );

	    if ( splitRecord.length == 2 ) {
		Name name = new Name( );
		
		name.setForename( splitRecord[ 0 ] );
		name.setSurname( splitRecord[ 1 ] );
		
		names.add( name );
	    }
//	    else {
//		// Undefined in spec - choosing to ignore for now.
//	    }
	}
    }
}