package net.blf2.model.entry;

import net.blf2.model.entry.enumfile.ArticleStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by blf2 on 16-3-31.\
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
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(String publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}
