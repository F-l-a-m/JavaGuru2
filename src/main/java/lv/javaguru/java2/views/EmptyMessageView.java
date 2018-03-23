package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MyTimestamp;
import lv.javaguru.java2.domain.User;

public class EmptyMessageView implements View {

    private final User user;

    public EmptyMessageView(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        MyTimestamp timestamp = new MyTimestamp();
        System.out.println(timestamp.toString() + ' ' + user.getNickname() + ':');
    }
}
