package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.models.ChatLine;

public final class GlobalLine {
    public static ChatLine line;

    public static ChatLine getLine() {
        return line;
    }

    public static void SetLine(ChatLine l){
        line = l;
    }
}
