/**
 * Auto generated
 */
package com.jianwu.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 		yyc
 * @see	         	数据库表名：TAB_SYS_USER
 * @generate 	    2016-03-12 14:54:18
 * @note           用户表
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 215500508586860L;

    /**
     * 对应SEQ_SYS_USER
     */
    private Long id;
    /**
     * 电话号码
     */
    private String mobilePhone;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户性别 1:男；2女
     * 数据库默认值: 1 
     */
    private Long userSex;
    /**
     * 密码
     * 数据库默认值: 123456

     */
    private String userPassword;
    /**
     * 加密盐值
     */
    private String userSalt;
    /**
     * 用户状态 1:正常；0：禁用
     * 数据库默认值: 1 
     */
    private Long userStatus;
    /**
     * 头像地址
     */
    private String headerUrl;
    /**
     * 创建时间
     * 数据库默认值: sysdate 
     */
    private Date createTime;
    /**
     * 运营角色 99平台管理员,88省级管理员,77地区管理员,66企业管理员，0
     * 数据库默认值: 0 
     */
    private Long operateRole;

    /**
     * 管理员类型 1主账号 2子账号
     * 数据库默认值：0
     */
    private Integer levels;
    /**
     * 区域
     */
    private String areaName;
    /**
     * 出生年月
     */
    private Date birthDay;


    public void setId(Long id){
        this.id = id;
    }

    public Long  getId(){
        return id;
    }

    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }

    public String  getMobilePhone(){
        return mobilePhone;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String  getUserName(){
        return userName;
    }

    public void setUserSex(Long userSex){
        this.userSex = userSex;
    }

    public Long  getUserSex(){
        return userSex;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String  getUserPassword(){
        return userPassword;
    }

    public void setUserSalt(String userSalt){
        this.userSalt = userSalt;
    }

    public String  getUserSalt(){
        return userSalt;
    }

    public void setUserStatus(Long userStatus){
        this.userStatus = userStatus;
    }

    public Long  getUserStatus(){
        return userStatus;
    }

    public void setHeaderUrl(String headerUrl){
        this.headerUrl = headerUrl;
    }

    public String  getHeaderUrl(){
        return headerUrl;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date  getCreateTime(){
        return createTime;
    }

    public void setOperateRole(Long operateRole){
        this.operateRole = operateRole;
    }

    public Long  getOperateRole(){
        return operateRole;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}