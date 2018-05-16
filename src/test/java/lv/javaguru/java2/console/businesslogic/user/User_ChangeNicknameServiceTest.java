package lv.javaguru.java2.console.businesslogic.user;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
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
        String oldNickname = "TestUser";
        String newNickname = "NewNickname";
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( newNickname ) )
                .thenReturn( errors );
        Mockito.when( userRepository.getByNickname( newNickname ) )
                .thenReturn( Optional.empty( ) ); // User not found, nickname must be unique
        User user = Mockito.mock( User.class );
        Mockito.when( userRepository.getByNickname( oldNickname ) )
                .thenReturn( Optional.of( user ) );
        
        User_ChangeNicknameResponse response = changeNicknameService.changeNickname( oldNickname, newNickname );
        
        assertTrue( response.isSuccess( ) );
        assertNull( response.getErrors( ) );
    }
}
