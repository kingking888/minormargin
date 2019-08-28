package com.zhangjh.minormargin.repository;

import java.util.Optional;

import com.zhangjh.minormargin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @ClassName UserRepository
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/28
 * @Version 小缘 1.0
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * 判断id是否存在
     *
     * @param id
     * @return
     */
    Boolean existsById(Integer id);
}
