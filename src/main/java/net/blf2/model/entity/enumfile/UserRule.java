package net.blf2.model.entity.enumfile;

/**
 * Created by blf2 on 16-3-31.
 * 用户的权限枚举
 */
public enum UserRule {
    user,admin,inactive;//普通用户  管理员 未激活或锁定状态
    public Boolean isUser(){
        if(this == user)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    public Boolean isAdmian(){
        if(this == admin)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    public Boolean isInactive(){
        if(this == inactive)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
