package net.blf2.model.dao.Impl;

import net.blf2.model.dao.IUser;
import net.blf2.model.entity.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by blf2 on 16-3-31.
 * IUser接口的实现类
 */
@Repository("UserDaoImpl")
public class UserDaoImpl implements IUser {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserInfo insertUserInfo(UserInfo userInfo) {
        try {
            this.sessionFactory.getCurrentSession().save(userInfo);
        }catch (HibernateException e){
            return null;
        }
        return userInfo;
    }

    @Override
    public Boolean deleteUserInfo(UserInfo userInfo) {
        try {
            this.sessionFactory.getCurrentSession().delete(userInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        try {
            this.sessionFactory.getCurrentSession().update(userInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public UserInfo queryUserInfoByUserId(Integer userId) {
        String hql = "from UserInfo userInfo where userInfo.userId="+userId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (UserInfo)query.list().get(0);
        return null;
    }

    @Override
    public UserInfo queryuserInfoByUserEmail(String userEmail) {
        String hql = "from UserInfo userInfo where userInfo.userEmail='"+userEmail+"'";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (UserInfo)query.list().get(0);
        return null;
    }

    @Override
    public List<UserInfo> queryuserInfoAll() {
        String hql = "from UserInfo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
