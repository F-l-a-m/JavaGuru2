package lv.javaguru.java2.businesslogic.user.login;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class User_LoginServiceValidatorImpl implements User_LoginServiceValidator {
    
    @Autowired private lv.javaguru.java2.businesslogic.user.User_LoginValidator loginValidator;
    @Autowired private User_PasswordValidator passwordValidator;
    
    @Override
    public List<Error> validate( User_LoginRequest request ) {
        List<Error> errors = loginValidator.validate( request.getLogin( ) );
        errors.addAll( passwordValidator.validate( request.getPassword( ) ) );
        return errors;
    }
}
