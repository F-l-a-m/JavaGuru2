package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;

import java.util.Date;

public class MessageBuilder {
    
    private Long id;
    private Date creationTime;
    private String user_nickname;
    private String message_body;
    private Room room;
    
    private MessageBuilder( ) {
    }
    
    public static MessageBuilder createMessage( ) {
        return new MessageBuilder( );
    }
    
    public Message build( ) {
        Message message = new Message( );
        message.setId( id );
        message.setCreationTime( creationTime );
        message.setUser_nickname( user_nickname );
        message.setMessage_body( message_body );
        message.setRoom( room );
        return message;
    }
    
    public MessageBuilder withId( Long id ) {
        this.id = id;
        return this;
    }
    
    public MessageBuilder withCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
        return this;
    }
    
    public MessageBuilder withUserNickname( String user_nickname ) {
        this.user_nickname = user_nickname;
        return this;
    }
    
    public MessageBuilder withMessageBody( String message_body ) {
        this.message_body = message_body;
        return this;
    }
    
    public MessageBuilder withRoom( Room room ) {
        this.room = room;
        return this;
    }
}
