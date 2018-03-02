package lv.javaguru.java2.views;

public class ProgramExitView implements View{

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
