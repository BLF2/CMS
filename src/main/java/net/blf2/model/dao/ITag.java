package net.blf2.model.dao;

import net.blf2.model.entity.TagInfo;

import java.util.List;

/**
 * Created by blf2 on 16-4-2.
 * 分类信息数据库操作接口
 */
public interface ITag {
    public TagInfo insertTagInfo(TagInfo tagInfo);
    public Boolean deleteTagInfo(TagInfo tagInfo);
    public Boolean updateTagInfo(TagInfo tagInfo);
    public TagInfo queryTagInfoByTagId(Integer tagId);
    public List<TagInfo> queryTagInfoAll();
}
