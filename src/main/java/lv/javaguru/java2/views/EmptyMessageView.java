package lv.javaguru.java2.views;

import lv.javaguru.java2.GlobalLine;

public class EmptyMessageView implements View {

    @Override
    public void execute() {
        System.out.println(GlobalLine.getLine());
    }
}
