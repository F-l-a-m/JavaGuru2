package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.user.User;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

    private Long id;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
