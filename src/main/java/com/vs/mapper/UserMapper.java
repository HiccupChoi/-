package com.vs.mapper;

import com.vs.entity.User;
import org.apache.ibatis.annotations.Param;

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

    Integer checkThreeCode(@Param("user") User user);
}