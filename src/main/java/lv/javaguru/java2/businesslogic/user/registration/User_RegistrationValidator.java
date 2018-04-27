package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.Error;

import java.util.List;

public interface User_RegistrationValidator {
    
    List<Error> validate( User_RegistrationRequest request );
    
}
