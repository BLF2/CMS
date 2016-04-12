package net.blf2.model.dao;

import net.blf2.model.entity.ArticleInfo;

import java.util.List;

/**
 * Created by blf2 on 16-3-31.
 * 文章信息数据表的操作
 */
public interface IArticle {
    public ArticleInfo insertArticleInfo(ArticleInfo articleInfo);
    public Boolean deleteArticleInfo(ArticleInfo articleInfo);
    public Boolean updateArticleInfo(ArticleInfo articleInfo);
    public ArticleInfo queryArticleInfoByArticleId(Integer articleId);
    public List<ArticleInfo> queryArticleInfoByArticleTitle(String articleTitle);
    public List<ArticleInfo> queryArticleInfoAll();
    public List<ArticleInfo> queryArticleInfoByWriterId(Integer writerId);
}
