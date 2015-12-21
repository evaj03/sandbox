package Sandbox.BmjInformatica.part5b;

public class Name implements Comparable< Name >{
    private String surname;
    private String forename;

    
    public String getSurname( ) {
	return surname;
    }

    
    public void setSurname( String surname ) {
	this.surname = surname;
    }

    
    public String getForename( ) {
	return forename;
    }

    
    public void setForename( String forename ) {
	this.forename = forename;
    }
    
    
    /*
     * Converts Name object to a string.
     * 
     * returns String	Forename appended to Surname.
     */
    public String toString( ) {
	return ( new StringBuilder( ).append( this.forename ).append( " " ).append(  this.surname ).append( "\n" ).toString( ) );
    }
    
    
    /* 
     * Compares two name objects 
     */
    @Override
    public int compareTo( Name thatName ) {
	int result;
	 
	if ( ( result = this.surname.compareTo( thatName.surname ) ) != 0 ) return result;
	if ( ( result = this.forename.compareTo( thatName.forename ) ) != 0 ) return result;
	
	return result;
    }
    
    
}
