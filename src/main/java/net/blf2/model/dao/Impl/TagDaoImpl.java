package net.blf2.model.dao.Impl;

import net.blf2.model.dao.ITag;
import net.blf2.model.entity.TagInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 分类信息数据库操作，实现ITag接口
 */
@Repository("TagDaoImpl")
public class TagDaoImpl implements ITag {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TagInfo insertTagInfo(TagInfo tagInfo) {
        try{
            this.sessionFactory.getCurrentSession().save(tagInfo);
        }catch (HibernateException e){
            return null;
        }
        return tagInfo;
    }

    @Override
    public Boolean deleteTagInfo(TagInfo tagInfo) {
        try {
            this.sessionFactory.getCurrentSession().delete(tagInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateTagInfo(TagInfo tagInfo) {
        try {
            this.sessionFactory.getCurrentSession().update(tagInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return  Boolean.TRUE;
    }

    @Override
    public TagInfo queryTagInfoByTagId(Integer tagId) {
        String hql = "from TagInfo tagInfo where tagInfo.tagId="+tagId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (TagInfo) query.list().get(0);
        return null;
    }

    @Override
    public List<TagInfo> queryTagInfoAll() {
        String hql = "from TagInfo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
