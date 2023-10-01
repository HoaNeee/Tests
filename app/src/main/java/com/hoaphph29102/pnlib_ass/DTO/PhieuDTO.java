package com.hoaphph29102.pnlib_ass.DTO;

import java.util.Date;

public class PhieuDTO {
    int ma_phieu;

    int maTV;
    String ten_TV;
    int ma_sach;
    String ten_sach;
    int tien_thue;
    String ngay_thue;
    int tra_sach;
    int maTT;

    public PhieuDTO() {
    }

    // danh sách

    public PhieuDTO(int ma_phieu, int maTV, String ten_TV, int ma_sach, String ten_sach, int tien_thue, String ngay_thue, int tra_sach, int maTT) {
        this.ma_phieu = ma_phieu;
        this.maTV = maTV;
        this.ten_TV = ten_TV;
        this.ma_sach = ma_sach;
        this.ten_sach = ten_sach;
        this.tien_thue = tien_thue;
        this.ngay_thue = ngay_thue;
        this.tra_sach = tra_sach;
        this.maTT = maTT;
    }

    //Thêm mới

    public PhieuDTO(int maTV, int ma_sach, int tien_thue, String ngay_thue, int tra_sach) {
        this.maTV = maTV;
        this.ma_sach = ma_sach;
        this.tien_thue = tien_thue;
        this.ngay_thue = ngay_thue;
        this.tra_sach = tra_sach;
    }

    //fix

    public PhieuDTO(int ma_phieu, int maTT, int maTV, int ma_sach, int tien_thue, String ngay_thue, int tra_sach) {
        this.ma_phieu = ma_phieu;
        this.maTV = maTV;
        this.ma_sach = ma_sach;
        this.tien_thue = tien_thue;
        this.ngay_thue = ngay_thue;
        this.tra_sach = tra_sach;
        this.maTT = maTT;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMa_phieu() {
        return ma_phieu;
    }

    public void setMa_phieu(int ma_phieu) {
        this.ma_phieu = ma_phieu;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getTen_TV() {
        return ten_TV;
    }

    public void setTen_TV(String ten_TV) {
        this.ten_TV = ten_TV;
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

    public int getTien_thue() {
        return tien_thue;
    }

    public void setTien_thue(int tien_thue) {
        this.tien_thue = tien_thue;
    }

    public String getNgay_thue() {
        return ngay_thue;
    }

    public void setNgay_thue(String ngay_thue) {
        this.ngay_thue = ngay_thue;
    }

    public int getTra_sach() {
        return tra_sach;
    }

    public void setTra_sach(int tra_sach) {
        this.tra_sach = tra_sach;
    }
}
