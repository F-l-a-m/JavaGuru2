package lv.javaguru.java2.businesslogic.user.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class User_RegistrationServiceImpl implements User_RegistrationService {
    
    @Autowired User_RegistrationValidator validator;
    
    @Override
    @Transactional
    public User_RegistrationResponse register( User_RegistrationRequest request ) {
        return null;
    }
}
