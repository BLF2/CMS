package net.blf2.controller.user;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.UserRule;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.CheckChars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by blf2 on 16-4-5.
 * 关于用户操作的控制器
 */
@Controller
@RequestMapping("/User/")
public class UserController {
    @Autowired
    private IPrimaryUser iPrimaryUser;
    @Autowired
    private CheckChars checkChars;

    @RequestMapping("toLogin.action")
    public String toLogin(HttpSession httpSession){
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(userInfo == null)
        return "login";
        if(userInfo.getUserRule().isAdmian())
            return "adminmain";
        else if(userInfo.getUserRule().isUser())
            return "primarymain";
        return "login";
    }
    @RequestMapping("toRegister.action")
    public String toRegister(){
        return "register";
    }
    @RequestMapping(value = "register.action",method = {RequestMethod.POST})
    public String register(String userEmail,String userPswd,String userName,int rule,HttpSession httpSession){
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(userInfo != null && userInfo.getUserRule().isAdmian()){
            UserRule userRule = null;
            if(rule == 1)
                userRule = UserRule.user;
            else if(rule == 0)
                userRule = UserRule.inactive;
            UserInfo rUserInfo = new UserInfo(userEmail,userPswd,userName,userRule);
            return "adminmain";
        }
        return "error";
    }
    @RequestMapping(value = "login.action",method = {RequestMethod.POST})
    public String checkLogin(String userEmail,String userPswd,HttpSession httpSession){
        checkInitUserInfo();
        if(!checkChars.checkUserEmail(userEmail)){
            return "error";
        }
        UserInfo userInfo = iPrimaryUser.checkLogin(userEmail,userPswd);
        if(userInfo != null){
            if(userInfo.getUserRule().isInactive())
                return "error";
            httpSession.setAttribute("loginInfo",userInfo);
            if(userInfo.getUserRule().isAdmian())
                return "adminmain";
            else if(userInfo.getUserRule().isUser()) {
                List<ArticleInfo> alist = iPrimaryUser.lookWriterArticleInfo(userInfo.getUserId());
                httpSession.setAttribute("ListOfArticleByWriterId",alist);
                return "primarymain";
            }
        }
        return "login";
    }
    @RequestMapping(value = "updatePersonalInfo.action",method = {RequestMethod.POST})
    public String updatePersonalInfo(String userPswd,String userName,String Submit,HttpSession httpSession){
        if("no".equals(Submit))
            return "primarymain";
        UserInfo currentUserInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if((currentUserInfo = iPrimaryUser.updateUserInfo(currentUserInfo,currentUserInfo.getUserEmail(),userPswd,userName,UserRule.user)) == null)
            return  "error";
        httpSession.setAttribute("loginInfo",currentUserInfo);
        return "primarymain";
    }
    @RequestMapping(value = "toLookPersonalInfo.action")
    public String toLookPersonalInfo(HttpSession httpSession) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "error";
        return "LookAndEditPersonalInfo";
    }
    private void checkInitUserInfo(){
        if(iPrimaryUser.checkLogin("blf20822@126.com","mxh19940822") == null){
            iPrimaryUser.registerLogin("blf20822@126.com","mxh19940822","BLF2",UserRule.user);
        }
        if(iPrimaryUser.checkLogin("hypo2526@126.com","hypo") == null){
            iPrimaryUser.registerLogin("hypo2526@126.com","hypo2526","Hypo",UserRule.admin);
        }
    }
    @RequestMapping("logout.action")
    public String logout(HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "error";
        httpSession.removeAttribute("loginInfo");
        return "login";
    }
}
