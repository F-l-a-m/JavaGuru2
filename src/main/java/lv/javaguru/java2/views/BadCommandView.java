package lv.javaguru.java2.views;

public class BadCommandView implements View {

    @Override
    public void execute() {
        System.out.println("Command syntax error");
    }
}
