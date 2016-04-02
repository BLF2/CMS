package net.blf2.model.dao.Impl;

import net.blf2.model.dao.IArticleTag;
import net.blf2.model.entry.ArticleTag;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 文章-分类 关系数据表的操作 实现IArticleTag接口
 */
public class ArticleTagDaoImpl implements IArticleTag{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArticleTag insertArtitcleTag(ArticleTag articleTag) {
        try{
            this.sessionFactory.getCurrentSession().save(articleTag);
        }catch (HibernateException e){
            return articleTag;
        }
        return null;
    }

    @Override
    public Boolean deleteArticleTag(ArticleTag articleTag) {
        try {
            this.sessionFactory.getCurrentSession().delete(articleTag);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateArticleTag(ArticleTag articleTag) {
        try {
            this.sessionFactory.getCurrentSession().update(articleTag);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public ArticleTag queryArticelTagByArticleTagId(Integer articleTagId) {
        String hql = "from ArticleTag ac where ac.acId="+articleTagId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (ArticleTag) query.list().get(0);
        return null;
    }

    @Override
    public List<ArticleTag> queryArticleTagAll() {
        String hql = "from ArticleTag";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
