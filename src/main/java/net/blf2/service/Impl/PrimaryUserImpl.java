package net.blf2.service.Impl;

import net.blf2.model.dao.IArticle;
import net.blf2.model.dao.IArticleTag;
import net.blf2.model.dao.ITag;
import net.blf2.model.dao.IUser;
import net.blf2.model.entry.ArticleInfo;
import net.blf2.model.entry.ArticleTag;
import net.blf2.model.entry.TagInfo;
import net.blf2.model.entry.UserInfo;
import net.blf2.model.entry.enumfile.ArticleStatus;
import net.blf2.model.entry.enumfile.UserRule;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.CheckChars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by blf2 on 16-4-3.
 * 普通用户操作  实现IPrimaryUser接口
 */
@Service("PrimaryUser")
public class PrimaryUserImpl implements IPrimaryUser{

    private IUser iUser;

    private CheckChars checkChars;

    private IArticle iArticle;

    private IArticleTag iArticleTag;

    private ITag iTag;

    public IUser getiUser() {
        return iUser;
    }
    @Autowired
    public void setiUser(IUser iUser) {
        this.iUser = iUser;
    }

    public CheckChars getCheckChars() {
        return checkChars;
    }
    @Autowired
    public void setCheckChars(CheckChars checkChars) {
        this.checkChars = checkChars;
    }

    public IArticle getiArticle() {
        return iArticle;
    }
    @Autowired
    public void setiArticle(IArticle iArticle) {
        this.iArticle = iArticle;
    }

    public IArticleTag getiArticleTag() {
        return iArticleTag;
    }
    @Autowired
    public void setiArticleTag(IArticleTag iArticleTag) {
        this.iArticleTag = iArticleTag;
    }

    public ITag getiTag() {
        return iTag;
    }
    @Autowired
    public void setiTag(ITag iTag) {
        this.iTag = iTag;
    }

    public UserInfo checkLogin(String userEmail, String userPswd) {//验证登录信息
        if(checkChars.checkUserEmail(userEmail)){
            UserInfo userInfo = iUser.queryuserInfoByUserEmail(userEmail);
            if(userInfo != null){
                if(userInfo.getUserPswd().equals(userPswd)){
                    return userInfo;
                }
            }
        }
        return null;
    }

    @Override
    public UserInfo registerLogin(String userEmail, String userPswd, String userName, UserRule userRule) {//注册用户
        UserInfo userInfo = new UserInfo(userEmail,userPswd,userName,userRule);
        UserInfo reUserInfo = null;
        if(checkChars.checkRegisterInfo(userInfo)){

            reUserInfo = iUser.insertUserInfo(userInfo);
//            if(reUserInfo != null)
//            System.out.println("增加通过！");
        }
        return reUserInfo;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo currentLoginUser,String userEmail, String userPswd, String userName, UserRule userRule) {//更新用户信息
        UserInfo userInfo = new UserInfo(currentLoginUser.getUserId(),userEmail,userPswd,userName,userRule);
        if(checkChars.checkRegisterInfo(userInfo) && iUser.updateUserInfo(userInfo)){
            return userInfo;
        }
        return null;
    }

    @Override
    public ArticleInfo addArticleInfo(String articleTitle, Integer writerId, String articleText, String publishDateTime, ArticleStatus articleStatus) {//添加文章
        articleTitle = checkChars.fiterScriptCode(articleTitle);
        articleText = checkChars.fiterScriptCode(articleText);
        ArticleInfo articleInfo = new ArticleInfo(articleTitle,writerId,articleText,publishDateTime,articleStatus);
        articleInfo = iArticle.insertArticleInfo(articleInfo);
        return articleInfo;
    }

    @Override
    public ArticleInfo updateArticleInfo(ArticleInfo currentAticleInfo,String articleTitle, Integer writerId, String articleText, String publishDateTime, ArticleStatus articleStatus) {//更新文章
        articleTitle = checkChars.fiterScriptCode(articleTitle);
        articleText = checkChars.fiterScriptCode(articleText);
        ArticleInfo articleInfo = new ArticleInfo(currentAticleInfo.getArticleId(),articleTitle,writerId,articleText,publishDateTime,articleStatus);
        if(iArticle.updateArticleInfo(articleInfo))
            return articleInfo;
        return null;
    }

    @Override
    public Boolean deleteArticelInfoByArticleId(Integer articleId) {//通过文章Id删除文章
        ArticleInfo articleInfo = iArticle.queryArticleInfoByArticleId(articleId);
        if(articleInfo != null){
            List<ArticleTag>list = iArticleTag.queryArticleTagByArticleId(articleId);
            Iterator<ArticleTag>iterator = list.iterator();
            while(iterator.hasNext()){
                iArticleTag.deleteArticleTag(iterator.next());
            }
            if(iArticle.deleteArticleInfo(articleInfo)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<ArticleInfo> lookWriterArticleInfo(Integer writerId) {//查看某个作者的所有文章,如参数为自己的Id，就是查看自己的文章
        List<ArticleInfo>list = iArticle.queryArticleInfoByWriterId(writerId);
        return list;
    }

    @Override
    public ArticleTag addTagToArticle(Integer articleId, Integer tagid) {//给文章添加分类
        ArticleTag articleTag = new ArticleTag(articleId,tagid);
        articleTag = iArticleTag.insertArtitcleTag(articleTag);
        return articleTag;
    }

    @Override
    public ArticleTag updateTagToArticle(Integer articleId, Integer tagid) {//更新文章分类
        ArticleTag articleTag = new ArticleTag(articleId,tagid);
        if(iArticleTag.updateArticleTag(articleTag))
            return articleTag;
        return null;
    }

    @Override
    public ArticleInfo lookArticleInfoByArticleId(Integer articleId) {

        return iArticle.queryArticleInfoByArticleId(articleId);
    }

    @Override
    public List<TagInfo> lookTagInfoByArticleId(Integer articleId) {
        List<ArticleTag>listAC = iArticleTag.queryArticleTagByArticleId(articleId);
        List<TagInfo>listTag = new LinkedList<TagInfo>();
        Iterator<ArticleTag>iterator = listAC.iterator();
        while(iterator.hasNext()){
            TagInfo tagInfo = iTag.queryTagInfoByTagId(iterator.next().getTagId());
            listTag.add(tagInfo);
        }
        return listTag;
    }

    @Override
    public List<TagInfo> lookTagInfoAll() {
        return iTag.queryTagInfoAll();
    }
}
