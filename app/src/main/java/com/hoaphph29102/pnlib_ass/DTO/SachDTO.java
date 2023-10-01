package com.hoaphph29102.pnlib_ass.DTO;

public class SachDTO {
    int ma_sach;
    String ten_sach;
    int gia_thue;
    int ma_loai;
    String ten_loai;

    public SachDTO() {
    }
    //lấy danh sach
    public SachDTO(int ma_sach, String ten_sach, int gia_thue, int ma_loai, String ten_loai) {
        this.ma_sach = ma_sach;
        this.ten_sach = ten_sach;
        this.gia_thue = gia_thue;
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
    }
    // thêm mới
    public SachDTO(String ten_sach, int gia_thue, int ma_loai) {
        this.ten_sach = ten_sach;
        this.gia_thue = gia_thue;
        this.ma_loai = ma_loai;
    }

    public int getMa_sach() {
        return ma_sach;
    }

    public void setMa_sach(int ma_sach) {
        this.ma_sach = ma_sach;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public int getGia_thue() {
        return gia_thue;
    }

    public void setGia_thue(int gia_thue) {
        this.gia_thue = gia_thue;
    }

    public int getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(int ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }
}
