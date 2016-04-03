package net.blf2.service;

import net.blf2.model.entry.ArticleInfo;
import net.blf2.model.entry.TagInfo;
import net.blf2.model.entry.UserInfo;

import java.util.List;

/**
 * Created by blf2 on 16-4-3.
 * 管理员权限设置
 */
public interface IAdmin extends IPrimaryUser{
    public List<UserInfo> lookUserInfoAll();
    public Boolean deleteUserInfoByUserId(Integer userId);
    public List<ArticleInfo> lookArticleInfoAll();
    public TagInfo addTagInfo(TagInfo tagInfo);
    public Boolean updateTagInfo(TagInfo tagInfo);
    public Boolean deleteTagInfoByTagId(Integer tagId);
}
