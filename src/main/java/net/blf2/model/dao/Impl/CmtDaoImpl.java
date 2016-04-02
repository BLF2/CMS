package net.blf2.model.dao.Impl;

import net.blf2.model.dao.ICmt;
import net.blf2.model.entry.CmtInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 评论信息类的增删改查，实现ICmt接口
 */
public class CmtDaoImpl implements ICmt {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CmtInfo insertCmtInfo(CmtInfo cmtInfo) {
        try{
            this.sessionFactory.getCurrentSession().save(cmtInfo);
        }catch (HibernateException e){
            return null;
        }
        return cmtInfo;
    }

    @Override
    public Boolean deleteCmtInfo(CmtInfo cmtInfo) {
        try{
            this.sessionFactory.getCurrentSession().delete(cmtInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateCmtInfo(CmtInfo cmtInfo) {
        try {
            this.sessionFactory.getCurrentSession().update(cmtInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public CmtInfo queryCmtInfoByCmtId(Integer cmtId) {
        String hql = "from CmtInfo cmtInfo where cmtInfo.cmtId="+cmtId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (CmtInfo) query.list().get(0);
        return null;
    }

    @Override
    public List<CmtInfo> queryCmtInfoByArticleId(Integer articleId) {
        String hql = "from CmtInfo cmtInfo where cmtInfo.articleId="+articleId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
