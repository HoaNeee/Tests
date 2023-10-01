package com.hoaphph29102.pnlib_ass.DTO;

public class UserDTO {
    int maTT;
    String username;
    String password;
    String ho_ten;

    public UserDTO() {
    }

    public UserDTO(int maTT ,String username, String password, String ho_ten) {
        this.maTT = maTT;
        this.username = username;
        this.password = password;
        this.ho_ten = ho_ten;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int id) {
        this.maTT = id;
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

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }
}
