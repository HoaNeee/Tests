package com.hoaphph29102.pnlib_ass.DTO;

public class MemberDTO {
    int ma_tv;
    String ten_tv;
    String nam_sinh;

    public MemberDTO() {
    }
    // lấy danh sách
    public MemberDTO(int ma_tv, String ten_tv, String nam_sinh) {
        this.ma_tv = ma_tv;
        this.ten_tv = ten_tv;
        this.nam_sinh = nam_sinh;
    }
    // dành cho thêm mới
    public MemberDTO(String ten_tv, String nam_sinh) {
        this.ten_tv = ten_tv;
        this.nam_sinh = nam_sinh;
    }

    public int getMa_tv() {
        return ma_tv;
    }

    public void setMa_tv(int ma_tv) {
        this.ma_tv = ma_tv;
    }

    public String getTen_tv() {
        return ten_tv;
    }

    public void setTen_tv(String ten_tv) {
        this.ten_tv = ten_tv;
    }

    public String getNam_sinh() {
        return nam_sinh;
    }

    public void setNam_sinh(String nam_sinh) {
        this.nam_sinh = nam_sinh;
    }
}
