package net.blf2.service;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.ArticleTag;
import net.blf2.model.entity.TagInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.ArticleStatus;
import net.blf2.model.entity.enumfile.UserRule;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 普通用户权限
 */
public interface IPrimaryUser {
    public UserInfo checkLogin(String userEmail,String userPswd);//登录验证
    public UserInfo registerLogin(String userEmail,String userPswd,String userName,UserRule userRule);//注册
    public UserInfo updateUserInfo(UserInfo currentLoginUser,String userEmail,String userPswd,String userName,UserRule userRule);//修改个人信息
    public ArticleInfo addArticleInfo(String articleTitle,Integer writerId,String articleText,String publishDateTime,ArticleStatus articleStatus);//添加文章
    public ArticleInfo updateArticleInfo(ArticleInfo currentAticleInfo,String articleTitle,Integer writerId,String articleText,String publishDateTime,ArticleStatus articleStatus);//更新文章
    public Boolean deleteArticelInfoByArticleId(Integer articleId);//通过文章Id删除文章
    public List<ArticleInfo> lookWriterArticleInfo(Integer writerId);//查看自己的文章
    public ArticleTag addTagToArticle(Integer articleId,Integer tagid);//给文章添加分类
    public ArticleTag updateTagToArticle(Integer articleId,Integer tagid);//给文章变更分类
    public ArticleInfo lookArticleInfoByArticleId(Integer articleId);//根据文章id查看文章
    public List<TagInfo> lookTagInfoByArticleId(Integer articleId);//根据文章id获取分类
    public List<TagInfo> lookTagInfoAll();//查看所有分类信息
}
