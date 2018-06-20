package source.entities;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import source.service.MysqlLink;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sophia on 28/07/2017.
 */
public class User extends MysqlLink {
    private int user_id;
    private String id;
    private String password;
    private String email;
    private String nickname;
    private String sex;
    private int age;
    private int photos_id;

    public User(){
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhotos_id() {
        return photos_id;
    }

    public void setPhotos_id(int photos_id) {
        this.photos_id = photos_id;
    }
}
