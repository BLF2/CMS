package net.blf2.service;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.TagInfo;
import net.blf2.model.entity.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 16-4-3.
 * 管理员权限设置
 */
public interface IAdmin {
    public List<UserInfo> lookUserInfoAll();
    public Boolean deleteUserInfoByUserId(Integer userId);
    public List<ArticleInfo> lookArticleInfoAll();
    public TagInfo addTagInfo(String tagName);
    public Boolean updateTagInfo(TagInfo currentTagInfo,String tagName);
    public Boolean deleteTagInfoByTagId(Integer tagId);
}
