package net.blf2.service.Impl;

import net.blf2.model.dao.IArticle;
import net.blf2.model.dao.IArticleTag;
import net.blf2.model.dao.ITag;
import net.blf2.model.dao.IUser;
import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.ArticleTag;
import net.blf2.model.entity.TagInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.service.IAdmin;
import net.blf2.util.CheckChars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by blf2 on 16-4-3.
 * 管理员类的接口实现
 */
@Service("Admin")
public class AdminImpl implements IAdmin  {

    @Autowired
    private IUser iUser;
    @Autowired
    private IArticle iArticle;
    @Autowired
    private IArticleTag iArticleTag;
    @Autowired
    private CheckChars checkChars;
    @Autowired
    private ITag iTag;

    public AdminImpl() {
    }

    public IUser getiUser() {
        return iUser;
    }

    public void setiUser(IUser iUser) {
        this.iUser = iUser;
    }

    public IArticle getiArticle() {
        return iArticle;
    }

    public void setiArticle(IArticle iArticle) {
        this.iArticle = iArticle;
    }

    public IArticleTag getiArticleTag() {
        return iArticleTag;
    }

    public void setiArticleTag(IArticleTag iArticleTag) {
        this.iArticleTag = iArticleTag;
    }

    public CheckChars getCheckChars() {
        return checkChars;
    }

    public void setCheckChars(CheckChars checkChars) {
        this.checkChars = checkChars;
    }

    public ITag getiTag() {
        return iTag;
    }

    public void setiTag(ITag iTag) {
        this.iTag = iTag;
    }

    @Override
    public List<UserInfo> lookUserInfoAll() {
        return iUser.queryuserInfoAll();
    }

    @Override
    public Boolean deleteUserInfoByUserId(Integer userId) {//删除用户
        UserInfo userInfo = iUser.queryUserInfoByUserId(userId);
        if(userInfo == null)
            return Boolean.FALSE;
        List<ArticleInfo>articles = iArticle.queryArticleInfoByWriterId(userInfo.getUserId());//查询出这个用户的所有文章
        if(articles != null) {
            Iterator<ArticleInfo> iteratora = articles.iterator();
            try {
                while (iteratora.hasNext()) {
                    ArticleInfo articleInfo = iteratora.next();
                    List<ArticleTag> aTags = iArticleTag.queryArticleTagByArticleId(articleInfo.getArticleId());//查询文章的分类
                    if (aTags != null) {
                        Iterator<ArticleTag> iteratorac = aTags.iterator();
                        while (iteratorac.hasNext()) {
                            iArticleTag.deleteArticleTag(iteratorac.next());//删除文章分类信息
                        }
                    }
                    iArticle.deleteArticleInfo(articleInfo);//删除文章
                }
            } catch (Exception e) {
                return Boolean.FALSE;
            }
        }

        return iUser.deleteUserInfo(userInfo);//删除用户信息
    }

    @Override
    public List<ArticleInfo> lookArticleInfoAll() {

        return iArticle.queryArticleInfoAll();
    }

    @Override
    public TagInfo addTagInfo(String tagName) {
        tagName = checkChars.checkTagNameLength(tagName);
        return iTag.insertTagInfo(new TagInfo(tagName));
    }

    @Override
    public Boolean updateTagInfo(TagInfo currentTagInfo, String tagName) {
        tagName = checkChars.checkTagNameLength(tagName);
        currentTagInfo.setTagName(tagName);
        return iTag.updateTagInfo(currentTagInfo);
    }


    @Override
    public Boolean deleteTagInfoByTagId(Integer tagId) {
        TagInfo tagInfo = iTag.queryTagInfoByTagId(tagId);
        if(tagInfo == null)
            return Boolean.FALSE;
        return iTag.deleteTagInfo(tagInfo);
    }
}
