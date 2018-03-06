package lv.javaguru.java2.views;

import java.util.ArrayList;
import java.util.List;

public class PrintAvailableChatCommandsView implements View {

    @Override
    public void execute() {
        List<String> commands = new ArrayList<>();
        commands.add("Available chat commands");
        commands.add("/quit - quit application, /nick - set nickname");
        commands.add("/r - refresh view");

        // calculate the longest string
        int longestString = 0;
        for(String s : commands){
            if(s.length() > longestString)
                longestString = s.length();
        }

        // header
        System.out.print('╔');
        for(int i = 0; i < longestString; i++){
            System.out.print('═');
        }
        System.out.println('╗');

        // body
        for(String s : commands){
            System.out.print('║' + s);
            int stringSize = s.length();
            while (stringSize < longestString){
                System.out.print(' ');
                stringSize++;
            }
            System.out.println('║');
        }

        // footer
        System.out.print('╚');
        for(int i = 0; i < longestString; i++){
            System.out.print('═');
        }
        System.out.println('╝');
    }
}
