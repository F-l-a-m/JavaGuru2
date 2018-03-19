package lv.javaguru.java2.businesslogic.room;

public final class CurrentRoom {

    private static ChatRoom room;

    public static ChatRoom getRoom() {
        return room;
    }

    public static void setRoom(ChatRoom room) {
        CurrentRoom.room = room;
    }
}
