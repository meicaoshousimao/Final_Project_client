package com.Pineapple.client.dao.model;

public class Client {
    private int id;// 编号
    private String username;// 用户名
    private String password;// 密码
    private String email;// 电子邮箱
    
    //获取id方法
    public int getId() {
        return id;
    }
    //设置id方法
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}