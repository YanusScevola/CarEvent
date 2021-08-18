package com.yanus.carevent.model;

public class UserModel {
    String nickname;

    public UserModel(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
