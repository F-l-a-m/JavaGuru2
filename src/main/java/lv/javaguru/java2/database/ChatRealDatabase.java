package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.CurrentUser;
import lv.javaguru.java2.businesslogic.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class ChatRealDatabase extends JDBCDatabase implements Database {

    @Override
    public void addNewUser(User user) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into user(id, login, password, nickname) values(default, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickname());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<User> getLastUser() {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, CurrentUser.id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("login"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.getByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addChatLine(Message message) {
        /*Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into message(id, timestamp, message_body, user_id, room_id) values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, message.getTimestamp());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.setString(3, message.);

            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.save()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }*/
    }

    @Override
    public List<Message> getAllChat() {
        return null;
    }

    @Override
    public Message getLastChatMessage() {
        return null;
    }

    @Override
    public void addToRoomList(ChatRoom newRoom) {

    }

    @Override
    public void removeFormRoomList(ChatRoom newRoom) {

    }

    @Override
    public List<ChatRoom> getRoomList() {
        return null;
    }

    @Override
    public Optional<ChatRoom> findChatRoom(String name) {
        return Optional.empty();
    }
}
