package net.blf2.util;

import net.blf2.model.entry.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by blf2 on 16-4-3.
 * 验证输入字符
 */
@Component("CheckChars")
public class CheckChars {
    public Boolean checkUserEmail(String userEmail){//判断是否为合法邮箱
        if(userEmail.length() > 100)
            return Boolean.FALSE;
        Pattern pattern = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        Matcher matcher = pattern.matcher(userEmail);

        if(matcher.matches()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public Boolean checkRegisterInfo(UserInfo userInfo){//检查注册信息是否合法
        if(this.checkUserEmail(userInfo.getUserEmail()) && !this.checkMarkNotContainQuotation(userInfo.getUserPswd()) && !this.checkMarkNotContainQuotation(userInfo.getUserName())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public Boolean checkMarkNotContainQuotation(String mark){//检查是否包含引号，防止SQL注入
        for(int i = 0;i < mark.length();i++){
            if(mark.charAt(i) == '\'' || mark.charAt(i) == '\"'){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    public String fiterScriptCode(String scriptCode){//过滤脚本代码，把“<”替换成 &lt;等,防止脚本在页面上运行
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("ScriptCodeExchange.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fileInputStream != null){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            Map<String,String> hashMap = null;
            String str = null;
            try {
                while((str = bufferedReader.readLine()) != null){
                    String[] strSp = str.split(" ");
                    hashMap = new HashMap<String, String>();
                    hashMap.put(strSp[0],strSp[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(hashMap != null){
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry entry = (Map.Entry)iterator.next();
                    scriptCode.replace((String)entry.getKey(),(String)entry.getValue());
                }
            }
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scriptCode;
    }
}
