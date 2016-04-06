package net.blf2.model.entry;

import net.blf2.model.entry.enumfile.UserRule;

import javax.persistence.*;

/**
 * Created by blf2 on 16-3-31.
 * 用户信息类
 */
@Entity
@Table(name="UserInfo")
public class UserInfo {
    private Integer userId;//登陆id
    private String userEmail;//登陆邮箱
    private String userPswd;//登陆密码
    private String userName;//用户昵称
    private UserRule userRule;//用户身份

    public UserInfo() {
    }

    public UserInfo(Integer userId, String userEmail, String userPswd, String userName, UserRule userRule) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPswd = userPswd;
        this.userName = userName;
        this.userRule = userRule;
    }

    public UserInfo(String userEmail, String userPswd, String userName, UserRule userRule) {
        this.userEmail = userEmail;
        this.userPswd = userPswd;
        this.userName = userName;
        this.userRule = userRule;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRule getUserRule() {
        return userRule;
    }

    public void setUserRule(UserRule userRule) {
        this.userRule = userRule;
    }
}
