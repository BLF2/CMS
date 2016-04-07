package net.blf2.controller.user;

import net.blf2.model.entry.UserInfo;
import net.blf2.model.entry.enumfile.UserRule;
import net.blf2.service.IPrimaryUser;
import net.blf2.util.CheckChars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.*;

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
    public String toLogin(){
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
            else if(userInfo.getUserRule().isUser())
                return "primarymain";
        }
        return "login";
    }
}
