package net.blf2.model.dao;

import net.blf2.model.entry.CmtInfo;

import java.util.List;

/**
 * Created by blf2 on 16-3-31.
 *评论类的操作接口
 */
public interface ICmt {
    public CmtInfo insertCmtInfo(CmtInfo cmtInfo);
    public Boolean deleteCmtInfo(CmtInfo cmtInfo);
    public Boolean updateCmtInfo(CmtInfo cmtInfo);
    public CmtInfo queryCmtInfoByCmtId(Integer cmtId);
    public List<CmtInfo> queryCmtInfoByArticleId(Integer articleId);
}
