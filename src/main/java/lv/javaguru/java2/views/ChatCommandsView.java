package lv.javaguru.java2.views;

public class ChatCommandsView implements View {

    @Override
    public void execute() {
        System.out.println("Commands: /quit - quit app; /nick - set nickname;\n");
    }
}
