package net.blf2.model.entity;

import javax.persistence.*;

/**
 * Created by blf2 on 16-3-31.
 * 文章分类的关系表
 */
@Entity
@Table(name="ArticleTag")
public class ArticleTag {
    Integer acId;//为数据库主键 实际意义不大
    Integer articleId;//文章ID
    Integer tagId;//标签ID

    public ArticleTag() {
    }

    public ArticleTag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public ArticleTag(Integer acId, Integer articleId, Integer tagId) {
        this.acId = acId;
        this.articleId = articleId;
        this.tagId = tagId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acId")
    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }
    @Column(name = "articleId")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    @Column(name = "tagId")
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
