package net.blf2.model.entity;

import javax.persistence.*;

/**
 * Created by blf2 on 16-3-31.
 * 评论信息类
 */
@Entity
@Table(name="CmtInfo")
public class CmtInfo {
    Integer cmtId;//本条评论的id
    String cmtorName;//评论者昵称
    Integer articleId;//被评论的文章
    Integer replyId;//回复给谁
    String replyDateTime;//回复时间
    String cmtText;//回复内容
    String cmtorEmail;//评论者邮箱
    String cmtorMainPage;//评论者主页

    public CmtInfo() {
    }

    public CmtInfo(Integer cmtId, String cmtorName, Integer articleId, Integer replyId, String replyDateTime, String cmtText, String cmtorEmail, String cmtorMainPage) {
        this.cmtId = cmtId;
        this.cmtorName = cmtorName;
        this.articleId = articleId;
        this.replyId = replyId;
        this.replyDateTime = replyDateTime;
        this.cmtText = cmtText;
        this.cmtorEmail = cmtorEmail;
        this.cmtorMainPage = cmtorMainPage;
    }

    public CmtInfo(String cmtorName, Integer articleId, Integer replyId, String replyDateTime, String cmtText, String cmtorEmail, String cmtorMainPage) {
        this.cmtorName = cmtorName;
        this.articleId = articleId;
        this.replyId = replyId;
        this.replyDateTime = replyDateTime;
        this.cmtText = cmtText;
        this.cmtorEmail = cmtorEmail;
        this.cmtorMainPage = cmtorMainPage;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmtId")
    public Integer getCmtId() {
        return cmtId;
    }

    public void setCmtId(Integer cmtId) {
        this.cmtId = cmtId;
    }
    @Column(name = "cmtorName")
    public String getCmtorName() {
        return cmtorName;
    }

    public void setCmtorName(String cmtorName) {
        this.cmtorName = cmtorName;
    }
    @Column(name = "articleId")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    @Column(name = "replyId")
    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
    @Column(name = "replyDateTime")
    public String getReplyDateTime() {
        return replyDateTime;
    }

    public void setReplyDateTime(String replyDateTime) {
        this.replyDateTime = replyDateTime;
    }
    @Column(name = "cmtText")
    public String getCmtText() {
        return cmtText;
    }

    public void setCmtText(String cmtText) {
        this.cmtText = cmtText;
    }
    @Column(name = "cmtorEmail")
    public String getCmtorEmail() {
        return cmtorEmail;
    }

    public void setCmtorEmail(String cmtorEmail) {
        this.cmtorEmail = cmtorEmail;
    }
    @Column(name = "cmtorMainPage")
    public String getCmtorMainPage() {
        return cmtorMainPage;
    }

    public void setCmtorMainPage(String cmtorMainPage) {
        this.cmtorMainPage = cmtorMainPage;
    }
}
