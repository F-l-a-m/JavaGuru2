package lv.javaguru.java2.businesslogic.user.login;

import lv.javaguru.java2.businesslogic.Error;

import java.util.List;

public interface User_LoginServiceValidator {
    
    List<Error> validate( User_LoginRequest request );
}
