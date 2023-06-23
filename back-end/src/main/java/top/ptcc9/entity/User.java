package top.ptcc9.entity;

import top.ptcc9.annotations.FieldsDes;

public class User {

    private String id;

    @FieldsDes(desc = "账号")
    private String account;

    @FieldsDes(desc = "密码")
    private String password;

    @FieldsDes(desc = "昵称")
    private String nickname;

    @FieldsDes(desc = "性别")
    private String gender;

    @FieldsDes(desc = "手机号")
    private String mobile;

    @FieldsDes(desc = "邮箱")
    private String email;

    @FieldsDes(desc = "身份")
    private String identity;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
