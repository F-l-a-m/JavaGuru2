package lv.javaguru.java2.views;

public class ChatCommandsPrintView implements View {

    @Override
    public void execute() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║ Commands: /quit - quit app; /nick - set nickname;║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        // make auto table draw
    }
}
