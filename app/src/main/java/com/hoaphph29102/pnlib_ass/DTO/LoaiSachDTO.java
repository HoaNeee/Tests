package com.hoaphph29102.pnlib_ass.DTO;

public class LoaiSachDTO {
    int ma_loai;
    String ten_loai;

    public LoaiSachDTO() {
    }

    public LoaiSachDTO(int ma_loai, String ten_loai) {
        this.ma_loai = ma_loai;
        this.ten_loai = ten_loai;
    }

    public LoaiSachDTO(String ten_loai) {
        this.ten_loai = ten_loai;
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
