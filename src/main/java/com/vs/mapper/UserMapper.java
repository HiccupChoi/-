package com.vs.mapper;

import com.vs.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 检查登录
     * @param user
     * @return
     */
    User chickStudent(@Param("user") User user);

    /**
     * 通过账号查询该用户是否存在
     * @return
     */
    int findUserByCode(@Param("userCode") String userCode);

    /**
     * 查找学生对应老师
     * @param classId
     * @return
     */
    User findTeachByClassAndQ(@Param("classId") Integer classId);

    /**
     * 查找一个班内所有有效学生
     * @param classId
     * @return
     */
    List<User> findStudentsByClassId(@Param("classId")Integer classId);

    Integer checkThreeCode(@Param("user") User user);

    Integer checkThreeCodeTimes(@Param("userCode") String userCode);

    User selectByUserCode(@Param("userCode") String userCode);

    void subUserTimesOne(@Param("userCode") String userCode);

    void subUserTimesOne(@Param("user") User user);

    int cancelUser(@Param("userId") int userId);

    List<User> findUserByAuthority(String authority);

    String findMaxStudentCode(String authority);

    List<User> findInvalidUser(String authority);
}