package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class User_RegistrationValidatorImpl implements User_RegistrationValidator {
    
    @Override
    public List<Error> validate( User_RegistrationRequest request ) {
        return null;
    }
    
    Optional<Error> validateEmptyLogin( ) {
        
        return Optional.empty( );
    }
    
    Optional<Error> validateLoginLength( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validateLoginSymbols( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validateEmptyPassword( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validatePasswordLength( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validatePasswordSymbols( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validateEmptyNickname( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validateNicknameLength( ) {
        return Optional.empty( );
    }
    
    Optional<Error> validateNicknameSymbols( ) {
        return Optional.empty( );
    }
    
}
