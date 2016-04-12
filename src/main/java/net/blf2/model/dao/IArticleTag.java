package net.blf2.model.dao;

import net.blf2.model.entity.ArticleTag;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 文章-分类 关系表操作接口
 */
public interface IArticleTag {
    public ArticleTag insertArtitcleTag(ArticleTag articleTag);
    public Boolean deleteArticleTag(ArticleTag articleTag);
    public Boolean updateArticleTag(ArticleTag articleTag);
    public ArticleTag queryArticelTagByArticleTagId(Integer articleTagId);
    public List<ArticleTag> queryArticleTagAll();
    public List<ArticleTag> queryArticelTagByTagId(Integer tagId);
    public List<ArticleTag> queryArticleTagByArticleId(Integer articleId);
}
