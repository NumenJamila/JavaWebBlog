package source.service;

import source.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by sophia on 31/07/2017.
 */
public class UserService extends MysqlLink {
    private static final String TABLE = "user";

    //判断登录
    public static boolean judgeLogin(String id, String password) {
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("id", "password"), toArray(id, password)));
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    String current_id = resultSet.getString("id");
                    String current_password = resultSet.getString("password");
                    return (current_id.equals(id)) && (current_password.equals(password));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //添加账号成功
    public static boolean register(String id, String password, String email) {
        return !isExisted(id) && insert(TABLE, toArray("id", "password", "email"), toArray(id, password, email));
    }

    //判断账号是否存在
    private static boolean isExisted(String id) {
        return exist(TABLE, "id", equalsOr(toArray("id"), toArray(id)));
    }

    //调用个人信息
    public static User getInfo(String id) {
        User user = new User();
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("id"), toArray(id)));
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    user.setUser_id(resultSet.getInt("user_id"));
                    user.setId(id);
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setNickname(resultSet.getString("nickname"));
                    user.setSex(resultSet.getString("sex"));
                    user.setAge(resultSet.getInt("age"));
                    user.setPhotos_id(resultSet.getInt("photos_id"));
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getVisitId(int article_id) {
        String id = null;
        ResultSet resultSet = select(TABLE, ALL, equalsAnd(toArray("article_id"), toArray(article_id)));
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return resultSet.getString("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}