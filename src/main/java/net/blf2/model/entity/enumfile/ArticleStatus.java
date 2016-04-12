package net.blf2.model.entity.enumfile;


/**
 * Created by blf2 on 16-3-31.
 * 文章的状态枚举
 */
public enum ArticleStatus {
    published,drafts,dustbin;//已发表，草稿箱，垃圾箱
    public Boolean isPublished(){
        if(this == published)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    public Boolean isDrafts(){
        if(this == drafts)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    public Boolean isDustbin(){
        if(this == dustbin)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
