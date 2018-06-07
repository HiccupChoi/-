package com.vs.service.impl;

import com.vs.dao.UserDao;
import com.vs.entity.User;
import com.vs.entity.UserClass;
import com.vs.enums.AuthorityEnum;
import com.vs.result.Result;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 欢
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private static String parentCode;

    /**
     * 查询登录用户信息
     * @param user
     * @return
     */
    public Result login(User user){
        User userInfo = userDao.login(user);
        Result result = new Result();
        if (userInfo != null){
            result.setStatus(0);
            result.setMsg("登录成功");
            result.setSuccess(true);
            UserClass userClass = userDao.findClass(userInfo.getClassId());
            User teach = userDao.findTeachByClassAndQ(userInfo.getClassId());
            if (userClass != null){
                userInfo.setClassName(userClass.getClassName());
            }
            if (teach != null){
                userInfo.setTeachName(teach.getUserName());
            }
            result.setData(userInfo);
        } else {
            result.setStatus(1);
            if (userDao.findUserByCode(user.getUserCode()) > 0){
                result.setMsg("密码错误");
            } else {
                result.setMsg("该用户不存在或已被删除");
            }
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }

    /**
     * 检测三码合一
     * @param userCode
     * @param UserName
     * @param activationCode
     * @return
     */
    public Result checkThreeCode(String userCode, String UserName, String activationCode){
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(UserName);
        user.setActivationCode(activationCode);
        Result result = new Result();
        Integer count =userDao.checkThreeCode(user);
        Integer times = userDao.checkThreeCodeTimes(userCode);
        if (count > 0 && times > 0){
            parentCode = (6 - times) + userCode.substring(1);
            result.setStatus(0);
            result.setSuccess(true);
            result.setMsg("三码匹配成功，您即将创建的账号为："+parentCode+"!");
        } else if (times == 0){
            result.setStatus(1);
            result.setSuccess(false);
            result.setMsg("该生激活码次数已经使用两次！");
        } else {
            result.setStatus(1);
            result.setSuccess(false);
            result.setMsg("三码匹配失败，请仔细检查您的输入！");
        }
        return result;
    }

    /**
     * 家长账号注册
     * @param userCode
     * @param UserPwd
     * @return
     */
    public Result register(String userCode,String userName,String UserPwd){
        User user = new User();
        Integer times = userDao.checkThreeCodeTimes(userCode);
        String code = (6 - times) + userCode.substring(1);
        user.setUserCode(code);
        user.setUserName(userName);
        user.setUserPwd(UserPwd);
        user.setAuthority(AuthorityEnum.PARENTS.getAuthority_code());
        Integer success = userDao.insertSelective(user);
        Result result = new Result();
        if (success > 0){
            user.setTimes(--times);
            user.setUserCode(userCode);
            userDao.subUserTimesOne(user);
            result.setStatus(0);
            result.setSuccess(true);
            result.setMsg("注册成功，账号为" + code + "!请牢记，然后点击确定按钮！");
        } else {
            result.setStatus(1);
            result.setSuccess(false);
            result.setMsg("注册失败！");
        }
        return result;
    }

    /**
     * 学生/老师账号注册
     * @param user
     * @return
     */
    @Override
    public Result createUser(User user) {
        if(user.getAuthority().equals("1")){
            //使用UUID生成用户激活码
            String uuid = UUID.randomUUID().toString();
            String activationCode = uuid.substring(0,8) + uuid.substring(9,13);
            user.setActivationCode(activationCode.toUpperCase());
        }
        //生成用户密码
        String pwd = (int)((Math.random()*9+1)*100000) + "";
        user.setUserPwd(pwd);
        Result result = new Result();
        int count = userDao.createUser(user);
        if (count > 0){
            result.setStatus(0);
            result.setSuccess(true);
            result.setMsg("创建"+user.getUserName() + ( user.getAuthority().equals("1") ? "同学" : "教师" ) + "成功");
        } else {
            result.setStatus(1);
            result.setSuccess(false);
            result.setMsg("创建"+user.getUserName() + ( user.getAuthority().equals("1") ? "同学" : "教师" ) + "失败");
        }
        return null;
    }

    /**
     * 用户无效化操作
     * @param userCode
     * @return
     */
    public  Result cancelUser(String userCode){
        User user = userDao.selectByUserCode(userCode);
        int count = userDao.cancelUser(user.getUserId());
        Result result = new Result();
        //账号无效化成功并激活码被使用过，无效化其双亲账号
        if (count > 0 && user.getTimes() < 2){
            for (int i = 0; i < 2; i++) {
                String parentUserCode = 4 + i + userCode.substring(1);
                user = userDao.selectByUserCode(parentUserCode);
                if (user != null){
                    userDao.cancelUser(user.getUserId());
                }
            }
            result.setStatus(0);
            result.setSuccess(true);
            result.setMsg("已成功删除" + user.getUserName() + ( user.getAuthority().equals("1") ? "，及其父母账号！":"！" ));
        } else {
            result.setStatus(1);
            result.setSuccess(false);
            result.setMsg("删除失败！" );
        }
        return result;
    }

    @Override
    public User findUserByCode(String code) {
        return userDao.selectByUserCode(code);
    }

    @Override
    public List<User> findStudentByClass(Integer classId){
        return userDao.findStudentsByClassId(classId);
    }

    @Override
    public List<User> findUserByAuthority(String authority) {
        return userDao.findUserByAuthority(authority);
    }

}
