package net.blf2.controller.article;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.ArticleStatus;
import net.blf2.service.IAdmin;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.DateFormat;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 16-4-12.
 */
@Controller
@RequestMapping("/Article/")
public class ArticleController {
    @Autowired
    private DateFormat dateFormat;
    @Autowired
    @Qualifier("PrimaryUser")
    private IPrimaryUser iPrimaryUser;
    @Autowired
    @Qualifier("Admin")
    private IAdmin iAdmin;
    @RequestMapping("toAddArticleInfo.action")
    public String toAddArticleInfo(HttpSession httpSession){//去添加文章信息界面
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "login";
        return "addArticleInfo";
    }
    @RequestMapping(value = "addArticleInfo.action",method = {RequestMethod.POST})//添加文章信息
    public String addArticleInfo(String userId,String Submit,String articleInfoEditor,String articleTitle,HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || (!userInfo.getUserRule().isAdmian() && !userInfo.getUserId().toString().equals(userId)))
            return "login";
        ArticleStatus articleStatus = null;
        if("publish".equals(Submit))
            articleStatus = ArticleStatus.published;
        else if("drafts".equals(Submit))
            articleStatus = ArticleStatus.drafts;
        System.out.println("----------->"+articleTitle);
        if(iPrimaryUser.addArticleInfo(articleTitle,Integer.parseInt(userId),articleInfoEditor,dateFormat.getCurrentDateTime(),articleStatus) == null)
            return "error";
        List<ArticleInfo>articleInfoList = iPrimaryUser.lookWriterArticleInfo(userInfo.getUserId());
        httpSession.setAttribute("ListOfArticleByWriterId",articleInfoList);
        if(userInfo.getUserRule().isUser()){
            return "primarymain";
        }
        return "adminmain";
    }
    @RequestMapping(value = "edit.action")
    public String toEditArticleInfo(HttpSession httpSession,String articleId){//去编辑文章界面
        ArticleInfo articleInfo = iPrimaryUser.lookArticleInfoByArticleId(Integer.parseInt(articleId));
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || (!userInfo.getUserRule().isAdmian() && !(userInfo.getUserId() == articleInfo.getWriterId())))
            return "login";
        httpSession.setAttribute("editCurrentArticle",articleInfo);
        return "editArticleInfo";
    }
    @RequestMapping(value = "updateArticleInfo.action",method = {RequestMethod.POST})//更新文章信息
    public String updateArticleInfo(String userId,String Submit,String articleInfoEditor,String articleTitle,HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || (!userInfo.getUserRule().isAdmian() && !userInfo.getUserId().toString().equals(userId)))
            return "login";
        ArticleStatus articleStatus = null;
        if("publish".equals(Submit))
            articleStatus = ArticleStatus.published;
        else if("drafts".equals(Submit))
            articleStatus = ArticleStatus.drafts;
        ArticleInfo currentArticleInfo = (ArticleInfo) httpSession.getAttribute("editCurrentArticle");
        if(iPrimaryUser.updateArticleInfo(currentArticleInfo,articleTitle, Integer.parseInt(userId), articleInfoEditor, dateFormat.getCurrentDateTime(), articleStatus) == null)
            return "error";
        httpSession.removeAttribute("editCurrentArticle");
        List<ArticleInfo>articleInfoList = iPrimaryUser.lookWriterArticleInfo(userInfo.getUserId());
        httpSession.setAttribute("ListOfArticleByWriterId", articleInfoList);
        if(userInfo.getUserRule().isUser()){
            return "primarymain";
        }
        return "adminmain";
    }
    @RequestMapping("delete.action")
    public String deleteArticleInfo(String articleId,HttpSession httpSession,String userId){//删除文章信息
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || (!userInfo.getUserRule().isAdmian() && !userInfo.getUserId().toString().equals(userId)))
            return "login";
        if(iPrimaryUser.deleteArticelInfoByArticleId(Integer.parseInt(articleId))){
            List<ArticleInfo>articleInfoList = iPrimaryUser.lookWriterArticleInfo(userInfo.getUserId());
            httpSession.setAttribute("ListOfArticleByWriterId", articleInfoList);
            if(userInfo.getUserRule().isAdmian()){
                List<ArticleInfo>articleInfoAllList = iAdmin.lookArticleInfoAll();
                Map<Integer,String> map = new HashMap<Integer,String>();
                List<UserInfo>ulist = iAdmin.lookUserInfoAll();
                Iterator<UserInfo>iterator = ulist.iterator();
                while(iterator.hasNext()) {
                    UserInfo userInfol = iterator.next();
                    map.put(userInfol.getUserId(),userInfo.getUserName());
                }
                httpSession.setAttribute("articleInfoAllList",articleInfoAllList);
                httpSession.setAttribute("UserIdToName",map);
                return "adminArticleAll";
            }
            return "primarymain";
        }
        return "error";
    }
    @RequestMapping("lookArticleInfoAll.action")
    public String lookArticleInfoAll(HttpSession httpSession){//查看所有文章
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "login";
        List<ArticleInfo>articleInfoAllList = iAdmin.lookArticleInfoAll();
        Map<Integer,String> map = new HashMap<Integer,String>();
        List<UserInfo>ulist = iAdmin.lookUserInfoAll();
        Iterator<UserInfo>iterator = ulist.iterator();
        while(iterator.hasNext()) {
            UserInfo userInfol = iterator.next();
            map.put(userInfol.getUserId(),userInfol.getUserName());
        }
        httpSession.setAttribute("articleInfoAllList",articleInfoAllList);
        httpSession.setAttribute("UserIdToName",map);
        return "adminArticleAll";
    }
    @RequestMapping("toIndex.action")
    public String indexLookArticleInfoAll(HttpSession httpSession){
        List<ArticleInfo>articleInfoAllList = iAdmin.lookArticleInfoAll();
        Map<Integer,String> map = new HashMap<Integer,String>();
        List<UserInfo>ulist = iAdmin.lookUserInfoAll();
        Iterator<UserInfo>iterator = ulist.iterator();
        while(iterator.hasNext()) {
            UserInfo userInfol = iterator.next();
            map.put(userInfol.getUserId(),userInfol.getUserName());
        }
        httpSession.setAttribute("articleInfoAllList",articleInfoAllList);
        httpSession.setAttribute("UserIdToName",map);
        return "IndexPage";
    }
}
