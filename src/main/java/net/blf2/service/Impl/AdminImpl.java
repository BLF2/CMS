package net.blf2.service.Impl;

import net.blf2.model.entry.ArticleInfo;
import net.blf2.model.entry.TagInfo;
import net.blf2.model.entry.UserInfo;
import net.blf2.service.IAdmin;

import java.util.List;

/**
 * Created by blf2 on 16-4-3.
 * 管理员类的接口实现
 */
public class AdminImpl extends PrimaryUserImpl implements IAdmin  {
    @Override
    public List<UserInfo> lookUserInfoAll() {
        return null;
    }

    @Override
    public Boolean deleteUserInfoByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<ArticleInfo> lookArticleInfoAll() {
        return null;
    }

    @Override
    public TagInfo addTagInfo(TagInfo tagInfo) {
        return null;
    }

    @Override
    public Boolean updateTagInfo(TagInfo tagInfo) {
        return null;
    }

    @Override
    public Boolean deleteTagInfoByTagId(Integer tagId) {
        return null;
    }
}
