package net.blf2.model.dao.Impl;

import net.blf2.model.dao.IArticle;
import net.blf2.model.entry.ArticleInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by blf2 on 16-3-31.
 * 文章数据表的操作接口实现类
 */
@Repository("ArticleDaoImpl")
public class ArticleDaoImpl implements IArticle{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArticleInfo insertArticleInfo(ArticleInfo articleInfo) {
        try {
            this.sessionFactory.getCurrentSession().save(articleInfo);
        }catch (HibernateException e) {
            return articleInfo;
        }
        return null;
    }

    @Override
    public Boolean deleteArticleInfo(ArticleInfo articleInfo) {
        try {
            this.sessionFactory.getCurrentSession().delete(articleInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateArticleInfo(ArticleInfo articleInfo) {
        try {
            this.sessionFactory.getCurrentSession().update(articleInfo);
        }catch (HibernateException e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public ArticleInfo queryArticleInfoByArticleId(Integer articleId) {
        String hql = "from ArticleInfo articleInfo where articleInfo.articleId="+articleId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return (ArticleInfo)query.list().get(0);
        return null;
    }

    @Override
    public List<ArticleInfo> queryArticleInfoByArticleTitle(String articleTitle) {
        String str = articleTitle.replaceAll(" ","%");
        String hql = "from ArticleInfo articleInfo where articleInfo.articleTitle like :'%"+str+"%'";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return query.list();
        return null;
    }

    @Override
    public List<ArticleInfo> queryArticleInfoAll() {
        String hql = "from ArticleInfo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<ArticleInfo> queryArticleInfoByWriterId(Integer writerId) {
        String hql = "from ArticleInfo articleInfo where articleInfo.writerId="+writerId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        if(query.list().size() > 0)
            return query.list();
        return null;
    }
}
