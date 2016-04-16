package net.blf2.controller.user;

import net.blf2.model.entity.ArticleInfo;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.UserRule;
import net.blf2.service.IAdmin;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.CheckChars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("PrimaryUser")
    private IPrimaryUser iPrimaryUser;
    @Autowired
    private CheckChars checkChars;
    @Autowired
    @Qualifier("Admin")
    private IAdmin iAdmin;
    @RequestMapping("toLogin.action")
    public String toLogin(HttpSession httpSession){//去登陆界面
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
    public String toRegister(HttpSession httpSession){//去注册界面
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "login";
        return "register";
    }
    @RequestMapping(value = "register.action",method = {RequestMethod.POST})
    public String register(String userEmail,String userPswd,String userName,HttpSession httpSession){//注册信息
        UserInfo userInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(userInfo != null && userInfo.getUserRule().isAdmian()){
            UserRule userRule = null;
            userRule = UserRule.user;
            UserInfo rUserInfo = new UserInfo(userEmail,userPswd,userName,userRule);
            if(iPrimaryUser.registerLogin(userEmail,userPswd,userName,userRule) != null) {
                List<UserInfo>userInfoAllList = iAdmin.lookUserInfoAll();
                httpSession.setAttribute("userInfoAllList",userInfoAllList);
                return "adminmain";
            }
        }
        return "error";
    }
    @RequestMapping(value = "login.action",method = {RequestMethod.POST})
    public String checkLogin(String userEmail,String userPswd,HttpSession httpSession){//登陆
        checkInitUserInfo();
        if(!checkChars.checkUserEmail(userEmail)){
            return "error";
        }
        UserInfo userInfo = iPrimaryUser.checkLogin(userEmail,userPswd);
        if(userInfo != null){
            if(userInfo.getUserRule().isInactive())
                return "error";
            httpSession.setAttribute("loginInfo",userInfo);
            if(userInfo.getUserRule().isAdmian()) {
                List<UserInfo> userInfoAllList = iAdmin.lookUserInfoAll();
                httpSession.setAttribute("userInfoAllList",userInfoAllList);
                return "adminmain";
            }
            else if(userInfo.getUserRule().isUser()) {
                List<ArticleInfo> alist = iPrimaryUser.lookWriterArticleInfo(userInfo.getUserId());
                httpSession.setAttribute("ListOfArticleByWriterId",alist);
                return "primarymain";
            }
        }
        return "login";
    }
    @RequestMapping(value = "updatePersonalInfo.action",method = {RequestMethod.POST})
    public String updatePersonalInfo(String userPswd,String userName,String Submit,HttpSession httpSession){//更新用户信息
        UserInfo currentUserInfo = (UserInfo)httpSession.getAttribute("loginInfo");
        if(currentUserInfo == null)
            return "login";
        if("no".equals(Submit))
            return "primarymain";
        if((currentUserInfo = iPrimaryUser.updateUserInfo(currentUserInfo,currentUserInfo.getUserEmail(),userPswd,userName,UserRule.user)) == null)
            return  "error";
        httpSession.setAttribute("loginInfo",currentUserInfo);
        return "primarymain";
    }
    @RequestMapping(value = "toLookPersonalInfo.action")
    public String toLookPersonalInfo(HttpSession httpSession) {//查看个人信息
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "error";
        return "LookAndEditPersonalInfo";
    }
    private void checkInitUserInfo(){
        if(iPrimaryUser.checkLogin("blf20822@126.com","mxh19940822") == null){
            iPrimaryUser.registerLogin("blf20822@126.com","mxh19940822","BLF2",UserRule.user);
        }
        if(iPrimaryUser.checkLogin("hypo2526@126.com","hypo2526") == null){
            iPrimaryUser.registerLogin("hypo2526@126.com","hypo2526","Hypo",UserRule.admin);
        }
    }
    @RequestMapping("logout.action")
    public String logout(HttpSession httpSession){//注销登陆
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "error";
        httpSession.removeAttribute("loginInfo");
        return "login";
    }
    @RequestMapping("adminEditUserInfo.action")
    public String adminEditUserInfo(HttpSession httpSession,String userId){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "error";
        UserInfo adminEditUserInfo = iPrimaryUser.lookUserInfoByUserId(Integer.parseInt(userId));
        httpSession.setAttribute("adminEditUserInfo", adminEditUserInfo);
        return "adminEditUserInfo";
    }
    @RequestMapping(value = "adminUpdateUserInfo.action",method = {RequestMethod.POST})
    public String adminUpdateUserInfo(String userEmail,String userPswd,String userName,HttpSession httpSession,String rule){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "error";
        UserInfo adminEditUserInfo = (UserInfo) httpSession.getAttribute("adminEditUserInfo");
        if(adminEditUserInfo == null)
            return "error";
        UserRule userRule = null;
        if("0".equals(rule))
            userRule = UserRule.user;
        else
            userRule = UserRule.inactive;
//        System.out.println(adminEditUserInfo.toString()+" "+userEmail+" "+userPswd+" "+userName+" "+rule);
//        if(iAdmin == null){
//            System.out.println("-------------->iAdmin == null");
//        }
        if((adminEditUserInfo = iPrimaryUser.updateUserInfo(adminEditUserInfo,userEmail,userPswd,userName,userRule)) == null)
            return "error";
        if(adminEditUserInfo.getUserId().equals(userInfo.getUserId())){
            httpSession.setAttribute("loginInfo",adminEditUserInfo);
        }
        List<UserInfo> userInfoAllList = iAdmin.lookUserInfoAll();
        httpSession.setAttribute("userInfoAllList",userInfoAllList);
        return "adminmain";
    }
    @RequestMapping("adminDeleteUserInfo.action")
    public String adminDeleteUserInfo(HttpSession httpSession,String userId){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "error";
        Integer userIdi = Integer.parseInt(userId);
        if(iAdmin.deleteUserInfoByUserId(userIdi)){
            List<UserInfo> userInfoAllList = iAdmin.lookUserInfoAll();
            httpSession.setAttribute("userInfoAllList",userInfoAllList);
            return "adminmain";
        }
        return "error";
    }
    @RequestMapping("lookUserInfoAll.action")
    public String lookUserInfoAll(HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null || !userInfo.getUserRule().isAdmian())
            return "error";
        List<UserInfo> userInfoAllList = iAdmin.lookUserInfoAll();
        httpSession.setAttribute("userInfoAllList",userInfoAllList);
        return "adminmain";
    }
    @RequestMapping("toUserMainPage.action")
    public String toUserMainPage(HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("loginInfo");
        if(userInfo == null)
            return "login";
        if(userInfo.getUserRule().isAdmian())
            return "adminmain";
        if(userInfo.getUserRule().isUser())
            return "primaymain";
        return "error";
    }
}
