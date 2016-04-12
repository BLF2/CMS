package net.blf2.model.dao;

import net.blf2.model.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 16-3-31.
 * 用户信息数据库操作接口
 */
public interface IUser {
    public UserInfo insertUserInfo(UserInfo userInfo);
    public Boolean deleteUserInfo(UserInfo userInfo);
    public Boolean updateUserInfo(UserInfo userInfo);
    public UserInfo queryUserInfoByUserId(Integer userId);
    public UserInfo queryuserInfoByUserEmail(String userEmail);
    public List<UserInfo> queryuserInfoAll();
}
