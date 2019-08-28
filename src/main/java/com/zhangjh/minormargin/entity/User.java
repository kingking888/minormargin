package com.zhangjh.minormargin.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户信息实体类
 *
 * @ClassName User
 * @Description: TODO
 * @Author zhang jiahao
 * @Date 2019/8/28
 * @Version 小缘 1.0
 **/
@Data
@Entity
@Table(name = "xy_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nick", length = 64)
    private String nick;

    /**
     * 用户等级
     */
    @Column(name = "level", length = 64)
    private String level;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    /**
     * 赞赏数
     */
    @Column(name = "good")
    private Integer good;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 性别
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 是否有效
     * 1, 1可用
     * 2，0不可用
     */
    @Column(name = "enabled", columnDefinition = "INT default 0")
    private Integer enabled = 0;

    /**
     * 手机号
     */
    @Column(name = "phone", length = 64)
    private String phone;

}