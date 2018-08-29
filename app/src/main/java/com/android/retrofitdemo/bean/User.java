package com.android.retrofitdemo.bean;

public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userSex;

    private String userPhone;

    private String userEmail;
    
    

    /**
     * 
     */
    public User() {
        super();
    }

    /**
     * @param userId
     * @param userName
     * @param userPwd
     * @param userSex
     * @param userPhone
     * @param userEmail
     */
    public User(Integer userId, String userName, String userPwd, String userSex, String userPhone, String userEmail) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userSex=" + userSex
                + ", userPhone=" + userPhone + ", userEmail=" + userEmail + "]";
    }
    
    
}