package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.Error;

import java.util.List;

public interface View {
    
    void execute( );
    
    default void printErrors( List<Error> errors ) {
        errors.forEach( error -> System.out.println( "Error message = " + error.getErrorMessage( ) ) );
    }
}
