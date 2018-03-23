package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.message.AddMessageResponse;
import lv.javaguru.java2.businesslogic.message.AddMessageService;
import lv.javaguru.java2.businesslogic.room.AddRoomResponse;
import lv.javaguru.java2.businesslogic.room.AddRoomService;
import lv.javaguru.java2.businesslogic.user.AddUserResponse;
import lv.javaguru.java2.businesslogic.user.AddUserService;
import lv.javaguru.java2.businesslogic.user.AddUserToRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NewMessageView implements View {

    @Autowired private AddUserService addUserService;
    @Autowired private AddRoomService addRoomService;
    @Autowired private AddMessageService addMessageService;
    @Autowired private AddUserToRoomService addUserToRoomService;

    @Override
    public void execute() {
        System.out.println();
        System.out.print("Please enter your nickname: ");
        Scanner sc = new Scanner(System.in);
        String nickname = sc.nextLine();
        System.out.print("Please enter room name: ");
        String roomName = sc.nextLine();
        System.out.print("Please enter your message: ");
        String message = sc.nextLine();

        AddUserResponse addUserResponse = addUserService.addUser(nickname);
        if (addUserResponse.isSuccess()) {
            AddRoomResponse addRoomResponse = addRoomService.addRoom(roomName, addUserResponse.getUser().getNickname());
            if (addRoomResponse.isSuccess()) {
                addUserToRoomService.add(addUserResponse.getUser(), addRoomResponse.getRoom());
                AddMessageResponse addMessageResponse = addMessageService.addMessage(message, nickname, addRoomResponse.getRoom().getId());
                if (addMessageResponse.isSuccess()) {
                    System.out.println("Message successfully added to " + roomName);
                    System.out.println();
                } else {
                    addMessageResponse.getErrors().forEach(error -> {
                        System.out.println("Error message = " + error.getErrorMessage());
                    });
                    System.out.println();
                }
            } else {
                addRoomResponse.getErrors().forEach(error -> {
                    System.out.println("Error message = " + error.getErrorMessage());
                });
                System.out.println();
            }
        } else {
            addUserResponse.getErrors().forEach(error -> {
                System.out.println("Error message = " + error.getErrorMessage());
            });
            System.out.println();
        }
    }
}
