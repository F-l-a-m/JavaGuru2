package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class User_ChangeNicknameServiceTest {
    
    @Mock private UserRepository userRepository;
    @Mock private User_NicknameValidator validator;
    
    @InjectMocks private User_ChangeNicknameService changeNicknameService;
    
    @Test
    public void shouldChangeUserNickname( ) {
        String userNickname = "TestUser";
        String newNickname = "NewNickname";
        List<Error> errors = new ArrayList<>( );
        User user = Mockito.mock( User.class );
        Mockito.when( validator.validate( userNickname ) )
                .thenReturn( errors );
        Mockito.when( userRepository.get( newNickname ) )
                .thenReturn( Optional.empty( ) ); // User not found, nickname must be unique
        
        User_ChangeNicknameResponse response = changeNicknameService.changeNickname( user, newNickname );
        
        assertTrue( response.isSuccess( ) );
        assertNull( response.getErrors( ) );
    }
}
