package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatRealDatabase extends JDBCDatabase implements Database {

    // user management
    @Override
    public User addNewUser(User user) {
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
                user.setId(rs.getLong(1));
            }
            return user;
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
    public void changeUserNickname(Long userId, String nickname) {
        Connection connection = null;
        try {
            connection = getConnection();
            // UPDATE `chat`.`user` SET `nickname`='newNickname' WHERE `id`='8';
            String sql = "update user set nickname = ? where id = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute database.changeUserNickname()");
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
            /*ResultSet rs = preparedStatement.getGeneratedKeys();
            ChatRoom room = new ChatRoom();
            if (rs.next()){
                room.setId(rs.getLong(1));
            }
            return room;*/
        } catch (Throwable e) {
            System.out.println("Exception while execute database.addUserToRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void removeUserFromRoom(Long userId, Long roomId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "delete from user_in_room where user_id = ? and room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roomId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute database.removeUserFromRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean findUserInRoomById(Long userId, String roomName) {
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
            return resultSet.next( );
        } catch (Throwable e) {
            System.out.println("Exception while execute database.findUserInRoomById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    /*@Override
    public boolean findUserInRoomByNickname(String nickname, String roomName) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql =
                    "select * " +
                            "from user_in_room " +
                            "join chat_room on user_in_room.room_id = chat_room.id " +
                            "join user on user_in_room.user.id = user.id" +
                            "where  user.nickname = ? and chat_room.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, roomName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return true;
            else
                return false;
        } catch (Throwable e) {
            System.out.println("Exception while execute database.findUserInRoomById()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }*/


    // room management
    @Override
    public Optional<ChatRoom> createNewChatRoom(String roomName) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into chat_room(id, name) values(default, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, roomName);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            ChatRoom room = null;
            if (rs.next()){
                room = new ChatRoom();
                room.setId(rs.getLong(1));
                room.setName(roomName);
            }
            return Optional.ofNullable(room);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.createNewChatRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<ChatRoom> findChatRoomByRoomId(Long roomId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from chat_room where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ChatRoom room = null;
            if (resultSet.next()) {
                room = new ChatRoom();
                room.setId(resultSet.getLong("id"));
                room.setName(resultSet.getString("name"));
            }
            return Optional.ofNullable(room);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.findChatRoomByRoomId()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<ChatRoom> findChatRoomByRoomName(String roomName) {
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
            System.out.println("Exception while execute database.findChatRoomByRoomId()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<ChatRoom> getListOfAllRooms() {
        Connection connection = null;
        List<ChatRoom> listOfAllChatRooms = new ArrayList<>();
        try {
            connection = getConnection();
            String sql = "select * from chat_room";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ChatRoom room;
            while (resultSet.next()) {
                room = new ChatRoom();
                room.setId(resultSet.getLong("id"));
                room.setName(resultSet.getString("name"));
                listOfAllChatRooms.add(room);
            }
            return listOfAllChatRooms;
        } catch (Throwable e) {
            System.out.println("Exception while execute database.getListOfAllRooms()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }


    // message management
    @Override
    public void addChatMessage(Message message) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into message(id, timestamp, user_nickname, message_body, room_id) " +
                    "values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, message.getTimestamp());
            preparedStatement.setString(2, message.getUser_nickname());
            preparedStatement.setString(3, message.getMessage_body());
            preparedStatement.setLong(4, message.getRoom_id());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute database.addChatMessage()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Optional<Message> getLastChatMessageInRoom(Long roomId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from message order by id desc limit 1"; // where room_id = ?
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Message msg = null;
            if (resultSet.next()) {
                msg = new Message();
                msg.setId(resultSet.getLong("id"));
                msg.setTimestamp(resultSet.getString("timestamp"));
                msg.setUser_nickname(resultSet.getString("user_nickname"));
                msg.setMessage_body(resultSet.getString("message_body"));
                msg.setRoom_id(resultSet.getLong("room_id"));
            }
            return Optional.ofNullable(msg);
        } catch (Throwable e) {
            System.out.println("Exception while execute database.getLastChatMessageInRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Message> getAllChatHistoryInRoom(Long roomId) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from message where room_id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Message> messages = new ArrayList<>();
            Message msg;
            while (resultSet.next()) {
                msg = new Message();
                msg.setId(resultSet.getLong("id"));
                msg.setTimestamp(resultSet.getString("timestamp"));
                msg.setUser_nickname(resultSet.getString("user_nickname"));
                msg.setMessage_body(resultSet.getString("message_body"));
                msg.setRoom_id(resultSet.getLong("room_id"));
                messages.add(msg);
            }
            return messages;
        } catch (Throwable e) {
            System.out.println("Exception while execute database.getAllChatHistoryInRoom()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

}
