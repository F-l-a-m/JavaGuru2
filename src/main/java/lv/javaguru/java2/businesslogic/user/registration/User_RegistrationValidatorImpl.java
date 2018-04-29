package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_LoginValidator;
import lv.javaguru.java2.businesslogic.user.User_NicknameValidator;
import lv.javaguru.java2.businesslogic.user.User_PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class User_RegistrationValidatorImpl implements User_RegistrationValidator {
    
    @Autowired User_LoginValidator loginValidator;
    @Autowired User_PasswordValidator passwordValidator;
    @Autowired User_NicknameValidator nicknameValidator;
    
    @Override
    public List<Error> validate( User_RegistrationRequest request ) {
        List<Error> errors = loginValidator.validate( request.getLogin( ) );
        errors.addAll( passwordValidator.validate( request.getPassword( ) ) );
        errors.addAll( nicknameValidator.validate( request.getNickname( ) ) );
        return errors;
    }
}
