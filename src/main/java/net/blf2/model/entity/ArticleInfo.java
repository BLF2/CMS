package net.blf2.model.entity;

import net.blf2.model.entity.enumfile.ArticleStatus;

import javax.persistence.*;

/**
 * Created by blf2 on 16-3-31.
 * 文章信息类
 */
@Entity
@Table(name="ArticleInfo")
public class ArticleInfo {
    private Integer articleId;//文章id
    private String articleTitle;//文章标题
    private Integer writerId;//作者id
    private String articleText;//文章主体
    private String publishDateTime;//发表时间
    private ArticleStatus articleStatus;//文章状态  已发表 草稿箱 垃圾箱

    public ArticleInfo(Integer articleId, String articleTitle, Integer writerId, String articleText, String publishDateTime, ArticleStatus articleStatus) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.writerId = writerId;
        this.articleText = articleText;
        this.publishDateTime = publishDateTime;
        this.articleStatus = articleStatus;
    }

    public ArticleInfo() {

    }

    public ArticleInfo(String articleTitle, Integer writerId, String articleText, String publishDateTime, ArticleStatus articleStatus) {
        this.articleTitle = articleTitle;
        this.writerId = writerId;
        this.articleText = articleText;
        this.publishDateTime = publishDateTime;
        this.articleStatus = articleStatus;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleId")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    @Column(name = "articleTitle")
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    @Column(name = "writerId")
    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }
    @Column(name = "articleText",length = 9999999)
    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }
    @Column(name = "publishDateTime")
    public String getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(String publishDateTime) {
        this.publishDateTime = publishDateTime;
    }
    @Column(name = "articleStatus")
    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}
