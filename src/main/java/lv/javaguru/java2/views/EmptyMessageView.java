package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.Timestamp;
import lv.javaguru.java2.businesslogic.user.User;

public class EmptyMessageView implements View {

    private final User user;

    public EmptyMessageView(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        Timestamp t = new Timestamp();
        System.out.println(t.getTimestamp() + ' ' + user.getNickname() + ':');
    }
}
