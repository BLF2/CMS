package net.blf2.controller.article;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.ArticleStatus;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.DateFormat;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by blf2 on 16-4-12.
 */
@Controller
@RequestMapping("/Article/")
public class ArticleController {
    @Autowired
    private DateFormat dateFormat;
    @Autowired
    private IPrimaryUser iPrimaryUser;
    @RequestMapping("toAddArticleInfo")
    public String toAddArticleInfo(){
        return "addArticleInfo";
    }
    @RequestMapping(value = "addArticleInfo.action",method = {RequestMethod.POST})
    public String addArticleInfo(String userId,String Submit,String articleInfoEditor,String articleTitle,HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "login";
        ArticleStatus articleStatus = null;
        if("publish".equals(Submit))
            articleStatus = ArticleStatus.published;
        else if("drafts".equals(Submit))
            articleStatus = ArticleStatus.drafts;
        if(iPrimaryUser.addArticleInfo(articleTitle,Integer.parseInt(userId),articleInfoEditor,dateFormat.getCurrentDateTime(),articleStatus) == null)
            return "error";
        if(userInfo.getUserRule().isUser()){
            return "primarymain";
        }
        return "adminmain";
    }
}
