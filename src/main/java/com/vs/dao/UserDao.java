package com.vs.dao;

import com.vs.entity.User;
import com.vs.entity.UserClass;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    /**
     * 登录用户信息检测
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 查询班级
     * @param classId
     * @return
     */
    public UserClass findClass(Integer classId);

    /**
     * 查询学生对应班级老师
     * @param classId
     * @return
     */
    User findTeachByClassAndQ(Integer classId);

    /**
     * 查询账号是否存在
     * @param userCode
     * @return
     */
    int findUserByCode(String userCode);

    /**
     * 检测三码合一
     * @param user
     * @return
     */
    Integer checkThreeCode(User user);

    /**
     * 家长账号注册
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 通过学号查找学生信息
     * @param userCode
     * @return
     */
    User selectByUserCode(String userCode);

    /**
     * 查询学生激活码可用次数
     * @param userCode
     * @return
     */
    Integer checkThreeCodeTimes(String userCode);

    /**
     * 家长账号注册后学生激活码次数减一
     * @param user
     */
    void subUserTimesOne(User user);

    /**
     * 创建用户账号
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * 用户无效化操作
     * @param userId
     * @return
     */
    int cancelUser(Integer userId);

    /**
     * 查找一个班内所有有效学生
     * @param classId
     * @return
     */
    List<User> findStudentsByClassId(Integer classId);
}
