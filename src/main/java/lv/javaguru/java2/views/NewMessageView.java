package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.message.AddMessageResponse;
import lv.javaguru.java2.businesslogic.message.AddMessageService;
import lv.javaguru.java2.businesslogic.room.AddRoomResponse;
import lv.javaguru.java2.businesslogic.room.AddRoomService;
import lv.javaguru.java2.businesslogic.room.FindRoomResponse;
import lv.javaguru.java2.businesslogic.room.FindRoomService;
import lv.javaguru.java2.businesslogic.user.*;
import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class NewMessageView implements View {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private AddRoomService addRoomService;
    @Autowired
    private AddMessageService addMessageService;
    @Autowired
    private AddUserToRoomService addUserToRoomService;
    @Autowired
    private FindUserService findUserService;
    @Autowired
    private FindRoomService findRoomService;
    @Autowired
    private FindUserInRoomService findUserInRoomService;

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


        boolean userInitialized = false;
        User user = null;

        FindUserResponse findUserResponse = findUserService.findUserByNickname(nickname);
        if (findUserResponse.isSuccess()) {
            // use found user
            user = findUserResponse.getUser();
            userInitialized = true;
        } else {
            // add new user
            AddUserResponse addUserResponse = addUserService.addUser(nickname);
            if (addUserResponse.isSuccess()) {
                user = addUserResponse.getUser();
                userInitialized = true;
            } else {
                printErrors(addUserResponse.getErrors());
            }
        }


        if (userInitialized) {

            boolean roomInitialized = false;
            Room room = null;

            FindRoomResponse findRoomResponse = findRoomService.findRoomByName(roomName);
            if (findRoomResponse.isSuccess()) {
                // use found room
                room = findRoomResponse.getRoom();
                roomInitialized = true;
            } else {
                // add new room
                AddRoomResponse addRoomResponse = addRoomService.addRoom(roomName, nickname);
                if (addRoomResponse.isSuccess()) {
                    room = addRoomResponse.getRoom();
                } else {
                    printErrors(addRoomResponse.getErrors());
                }
            }

            if (roomInitialized) {
                boolean isUserAlreadyInThatRoom = findUserInRoomService.findUserInRoom(user.getId(), roomName);
                if(!isUserAlreadyInThatRoom) {
                    addUserToRoomService.add(user, room);
                }
                AddMessageResponse addMessageResponse = addMessageService.addMessage(message, nickname, room.getId());
                if (addMessageResponse.isSuccess()) {
                    Message messageObj = addMessageResponse.getMessage();
                    System.out.println("\n" + messageObj);
                } else {
                    printErrors(addMessageResponse.getErrors());
                }
            }
        }

        System.out.println();
    }

    private void printErrors(List<Error> errors) {
        errors.forEach(error -> {
            System.out.println("Error message = " + error.getErrorMessage());
        });
    }
}
