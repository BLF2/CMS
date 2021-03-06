package net.blf2.model.entity;

import javax.persistence.*;

/**
 * Created by blf2 on 16-3-31.
 * 文章分类信息类
 */
@Entity
@Table(name="TagInfo")
public class TagInfo {
    private Integer tagId;//分类ID
    private String tagName;//分类名

    public TagInfo() {
    }

    public TagInfo(Integer tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public TagInfo(String tagName) {
        this.tagName = tagName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tagId")
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    @Column(name="tagName")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
