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

    // user management
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
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                CurrentUser.id = (rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute database.addNewUser()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("nickname"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.getUserById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<User> getUserByNickname(String nickname) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from user where nickname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("nickname"));
            }
            return Optional.ofNullable(user);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.getUserByNickname()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addUserToRoom(Long userId, Long roomId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into user_in_room(user_id, room_id) values(?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roomId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute database.addUserToRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void removeUserFromRoom(Long userId, String roomName) {

    }

    @Override
    public boolean findUserInARoom(Long userId, String roomName) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql =
                    "select * " +
                    "from user_in_room " +
                    "join chat_room on user_in_room.room_id = chat_room.id " +
                    "where  user_in_room.user_id = ? and chat_room.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, roomName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return true;
            else
                return false;
        } catch (Throwable e) {
            System.out.println("Exception while execute database.findUserInARoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }


    // room management
    @Override
    public void createNewChatRoom(String roomName) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into chat_room(id, name) values(default, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, roomName);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute database.addNewUser()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<ChatRoom> findChatRoom(String roomName) {
        Connection connection = null;

        try {
            connection = getConnection();
            String sql = "select * from chat_room where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomName);
            ResultSet resultSet = preparedStatement.executeQuery();
            ChatRoom room = null;
            if (resultSet.next()) {
                room = new ChatRoom();
                room.setId(resultSet.getLong("id"));
                room.setName(resultSet.getString("name"));
            }
            return Optional.ofNullable(room);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.findChatRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<ChatRoom> getListOfAllRooms() {
        return null;
    }


    // message management
    @Override
    public void addChatMessage(Message message) {
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
    public Optional<Message> getLastChatMessageInRoom(String roomName) {
        return Optional.empty();
    }

    @Override
    public List<Message> getAllChatHistoryInRoom(String roomName) {
        return null;
    }

}
