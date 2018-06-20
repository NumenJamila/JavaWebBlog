package source.service;

import source.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sophia on 01/08/2017.
 */
public class MessageService extends MysqlLink {
    private static final String TABLE = "message";

    public static boolean insertMessage(String messageContent, int user_id, int guest_id) {
        return insert(TABLE, toArray("messageContent", "user_id", "guest_id"), toArray(messageContent, user_id, guest_id));
    }

    public static boolean deleteMessage(String messageContent, int user_id, int guest_id) {
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("messageContent", "user_id", "guest_id"), toArray(messageContent, user_id, guest_id)));
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return delete(TABLE, equalsAnd(toArray("messageContent", "user_id", "guest_id"), toArray(messageContent, user_id, guest_id)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

//    //调用留言板信息
//    public static User getInfo(String id, String password) {
//        User user = new User();
//        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("id", "password"), toArray(id, password)));
//        if (resultSet != null) {
//            try {
//                if (resultSet.next()) {
//                    user.setId(id);
//                    user.setPassword(password);
//                    user.setEmail(resultSet.getString("email"));
//                    user.setNickname(resultSet.getString("nickname"));
//                    user.setSex(resultSet.getString("sex"));
//                    user.setAge(resultSet.getInt("age"));
//                    user.setPhotos_id(resultSet.getInt("photos_id"));
//                    return user;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}

