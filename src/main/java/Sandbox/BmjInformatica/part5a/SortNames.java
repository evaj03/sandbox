package Sandbox.BmjInformatica.part5a;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortNames {
    private static List< Name > names = new ArrayList< Name >( );
    
    private void processFile( String inputFile, String outputFile ) throws IOException {
	validateFiles( inputFile, outputFile );
	
	parseAndStoreInput( inputFile );
	
	System.out.println( "Unsorted List" );
	displayList( );
	
	sortNames( );
	
	System.out.println( "Sorted List" );
	displayList( );
	
	writeToOutputFile( outputFile );
    }


    private void writeToOutputFile( String outputFile ) throws IOException {
	
		try ( FileWriter writer = new FileWriter( outputFile ) ) {
			for( Name name : names ) {
				// write output
				writer.append( name.toString( ) );
			}
		}
    }


    private void sortNames( ) {
		Collections.sort( names );
    }


    private void validateFiles( String inputFile, String outputFile ) throws IOException {
		String inMsg  = validateInputFile( inputFile );
		String outMsg = validateOutputFile( outputFile );

		if ( inMsg != null ) {
			throw new IOException( inMsg );
		}

		if ( outMsg != null ) {
			throw new IOException( outMsg );
		}
    }


    private void parseAndStoreInput( String inputFile ) {
		// Read and store input
		try ( BufferedReader in = new BufferedReader( new FileReader( inputFile ) ) ) {
			String record;

			while ( ( record = in.readLine( ) ) != null ) {
			parseRecord( record );
			}
		}
		catch ( Exception e ) {
			e.printStackTrace( );
		}
    }
    
    
    private void displayList( ) {
		for( Name name : names ) {
			System.out.format( "Forename [%s] Surname [%s]%n", name.getForename( ), name.getSurname( ) );
		}
    }


    private String validateInputFile( String inputFile ) {
		File file = new File( inputFile );

		if ( file.exists( ) && file.canRead( ) ) {
			return null;
		}

		return "Input file does not exist or has incorrect permissions.";
    }

    
    private String validateOutputFile( String outputFile ) {
		File file = new File( outputFile );

		if ( ( file.exists( ) && file.canWrite( ) ) || ( ! file.exists( ) ) ) {
			return null;
		}

		return "Output file exists but has incorrect permissions.";
    }

    
    /**
     * Takes given record and parses into individual fields
     * based on space delimiter.
     * <p>
     * Resulting fields are stored in list.
     * 
     * @param givenRecord
     */
    private static void parseRecord( final String givenRecord ) {

		if ( ! givenRecord.isEmpty( ) ) {
			String[ ] splitRecord = givenRecord.split( "\\s+" );

			if ( splitRecord.length == 2 ) {
			Name name = new Name( );

			name.setForename( splitRecord[ 0 ] );
			name.setSurname( splitRecord[ 1 ] );

			names.add( name );
			}
			else {
			// Undefined in spec - choosing to ignore for now.
			}
		}
    }
    

    public static void main ( String [ ] args ) {
		if ( args.length == 2 ) {
			SortNames sortNames = new SortNames( );

			try {
			sortNames.processFile( args[ 0 ], args[ 1 ] );

			displayOutputFile( args[ 1 ] );
			}
			catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		else {
			System.err.println( "Error: Insufficient parameters..." );
			System.exit( 0 );
		}
    }


    private static void displayOutputFile( String resultsFile ) throws IOException {
		File file = new File( resultsFile );

		if ( file.exists( ) && file.canRead( ) ) {
			try ( BufferedReader in = new BufferedReader( new FileReader( resultsFile ) ) ) {
			String record;

			while ( ( record = in.readLine( ) ) != null ) {
				System.out.println( record );
			}
			}
			catch ( Exception e ) {
			e.printStackTrace( );
			}
		}
		else {
			System.err.println( "Error: Cannot read or locate results file." );
		}
    }
}