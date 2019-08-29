package com.zhangjh.minormargin.repository;

import com.zhangjh.minormargin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
     * 判断用户名是否存在
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * 判断手机号是否存在
     *
     * @param phone
     * @return
     */
    Boolean existsByPhone(String phone);

    /**
     * 判断id是否存在
     *
     * @param id
     * @return
     */
    Boolean existsById(Integer id);

    /**
     * 修改密码
     *
     * @param phone
     * @param password
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Modifying
    @Query(value = "update xy_user u set u.password =:password where u.phone =:phone", nativeQuery = true)
    int updateByPasword(@Param("phone") String phone, @Param("password") String password);
}
