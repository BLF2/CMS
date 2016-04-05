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
    public String register(String userEmail,String userPswd,String userName,String accreditCode,HttpSession httpSession){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/home/blf2/workspace/CMS/accreditCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = null;
        if(fileInputStream != null){
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String myaccreditCode = null;
            try {
                myaccreditCode = bufferedReader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("myaccreditCode="+myaccreditCode);
            if(myaccreditCode != null){
                if(accreditCode.equals(myaccreditCode)){
                    UserInfo userInfo = iPrimaryUser.registerLogin(userEmail,userPswd,userName, UserRule.user);

                    if(userInfo != null){
                        System.out.println("registerLogin 成功！");
                        httpSession.setAttribute("loginInfo",userInfo);
                        return "personalEdit";
                    }
                }
            }
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
            httpSession.setAttribute("loginInfo",userInfo);
            return "personalEdit";
        }
        return "login";
    }
}
