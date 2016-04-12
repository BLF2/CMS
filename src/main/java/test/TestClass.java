import net.blf2.model.dao.IUser;
import net.blf2.model.dao.Impl.UserDaoImpl;
import net.blf2.model.entity.UserInfo;
import net.blf2.model.entity.enumfile.UserRule;

/**
 * Created by blf2 on 16-4-2.
 */

public class TestClass {
    public static void main(String[] args){
        testDao();
    }
    public static void testDao(){
        String email = "blf20822@126.com";
        String pswd = "mxh19940822";
        String nickName = "Blf2";
        UserRule userRule = UserRule.admin;
        UserInfo userInfo = new UserInfo();
        userInfo.setUserEmail(email);
        userInfo.setUserName(nickName);
        userInfo.setUserPswd(pswd);
        userInfo.setUserRule(userRule);
        IUser iUser = new UserDaoImpl();
        System.out.println("-------->开始测试UserInfo的增删查改<--------");
        System.out.println("-------->开始测试UserInfo的增加<--------");
        if(iUser.insertUserInfo(userInfo) != null){
            System.out.println("增加成功");
            System.out.println("-------->开始测试UserInfo的修改<--------");
            userInfo.setUserName("Blf22");
            if(iUser.updateUserInfo(userInfo)){
                System.out.println("修改成功");
                System.out.println("-------->开始测试UserInfo的查询<--------");
                UserInfo qUserInfo = iUser.queryuserInfoByUserEmail(email);
                if(qUserInfo != null){
                    System.out.println("查询成功");
                    System.out.println("-------->开始测试上述操作正确性<--------");
                    if(qUserInfo.getUserName().equals("Blf22")){
                        System.out.println("增改查成功");
                    }else{
                        System.out.println("增改查失败");
                    }
                }else{
                    System.out.println("查询失败");
                }

            }else{
                System.out.println("修改失败");
            }
        }else{
            System.out.println("增加失败");
        }
        System.out.println("-------->开始测试UserInfo的删除<--------");
        UserInfo dUserInfo = new UserInfo("i@ihypo.net","hypo2526","hypo",UserRule.user);
        dUserInfo = iUser.insertUserInfo(dUserInfo);
        if(dUserInfo != null) {
            if(iUser.deleteUserInfo(dUserInfo) && iUser.queryUserInfoByUserId(dUserInfo.getUserId()) != null)
                System.out.println("删除成功");
            else
                System.out.println("删除失败");
        }else{
            System.out.println("增加记录失败");
        }
    }
}
