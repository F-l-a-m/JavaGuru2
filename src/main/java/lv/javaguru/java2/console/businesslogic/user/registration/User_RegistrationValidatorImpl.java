package lv.javaguru.java2.console.businesslogic.user.registration;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.businesslogic.user.User_LoginValidator;
import lv.javaguru.java2.console.businesslogic.user.User_NicknameValidator;
import lv.javaguru.java2.console.businesslogic.user.User_PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class User_RegistrationValidatorImpl implements User_RegistrationValidator {
    
    @Autowired private User_LoginValidator loginValidator;
    @Autowired private User_PasswordValidator passwordValidator;
    @Autowired private User_NicknameValidator nicknameValidator;
    
    @Override
    public List<Error> validate( User_RegistrationRequest request ) {
        List<Error> errors = loginValidator.validate( request.getLogin( ) );
        errors.addAll( passwordValidator.validate( request.getPassword( ) ) );
        errors.addAll( nicknameValidator.validate( request.getNickname( ) ) );
        return errors;
    }
}
