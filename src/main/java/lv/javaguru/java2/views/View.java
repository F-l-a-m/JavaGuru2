package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.Error;

import java.util.List;

public interface View {
    
    void execute( );
    
    default void printErrors( List<Error> errors ) {
        errors.forEach( error -> System.out.println( "Error message = " + error.getErrorMessage( ) ) );
    }
}
