package com.hoaphph29102.pnlib_ass.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final String DBNAME = "PNLib";
    static final int DBVERSION = 5;

    public DbHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable(db);
        //bảng thủ thư
//        String create_tb_ThuThu = "CREATE TABLE thuthu(maTT INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT NOT NULL, password TEXT NOT NULL)";
//        db.execSQL(create_tb_ThuThu);


        //bảng loại sách
//        String create_tb_LoaiSach = "CREATE TABLE loaisach(maloai INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT NOT NULL)";
//        db.execSQL(create_tb_LoaiSach);


        //dữ liệu
//        String simple_loai_sach = "INSERT INTO loaisach(tenloai) VALUES('Marketing')";
//        db.execSQL(simple_loai_sach);

        //bảng thành viên
//        String create_tb_ThanhVien = "CREATE TABLE thanhvien(maTV INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT NOT NULL, namsinh TEXT NOT NULL)";
//        db.execSQL(create_tb_ThanhVien);

        //bảng sách
//        String create_tb_Sach = "CREATE TABLE sach(masach INTEGER PRIMARY KEY AUTOINCREMENT, tensach TEXT NOT NULL, giathue INTEGER NOT NULL, maloai INTEGER REFERENCES loaisach(maloai))";
//        db.execSQL(create_tb_Sach);

        //bảng phiếu mượn
//        String create_tb_PhieuMuon = "CREATE TABLE phieu(maPM INTEGER PRIMARY KEY AUTOINCREMENT, maTT INTEGER REFERENCES thuthu(maTT), maTV INTEGER REFERENCES thanhvien(maTV), masach INTEGER REFERENCES sach(masach), tienthue INTEGER NOT NULL, ngay DATE NOT NULL, trasach INTEGER)";
//        db.execSQL(create_tb_PhieuMuon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_tb_ThuThu = "DROP TABLE IF EXISTS thuthu";
        db.execSQL(drop_tb_ThuThu);
        String drop_tb_ThanhVien = "DROP TABLE IF EXISTS thanhvien";
        db.execSQL(drop_tb_ThanhVien);
        String drop_tb_LoaiSach= "DROP TABLE IF EXISTS loaisach";
        db.execSQL(drop_tb_LoaiSach);
        String drop_tb_Sach = "DROP TABLE IF EXISTS sach";
        db.execSQL(drop_tb_Sach);
        String drop_tb_PhieuMuon = "DROP TABLE IF EXISTS phieu";
        db.execSQL(drop_tb_PhieuMuon);

        createTable(db);

    }
    private void createTable(SQLiteDatabase db) {
        String create_tb_ThuThu = "CREATE TABLE thuthu(maTT INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, hoten TEXT NOT NULL, password TEXT NOT NULL)";
        db.execSQL(create_tb_ThuThu);
        String create_tb_PhieuMuon = "CREATE TABLE phieu(maPM INTEGER PRIMARY KEY AUTOINCREMENT, maTT INTEGER REFERENCES thuthu(maTT), maTV INTEGER REFERENCES thanhvien(maTV), masach INTEGER REFERENCES sach(masach), tienthue INTEGER NOT NULL, ngay TEXT NOT NULL, trasach INTEGER)";
        db.execSQL(create_tb_PhieuMuon);
        String create_tb_LoaiSach = "CREATE TABLE loaisach(maloai INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT NOT NULL)";
        db.execSQL(create_tb_LoaiSach);
        String create_tb_ThanhVien = "CREATE TABLE thanhvien(maTV INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT NOT NULL, namsinh TEXT NOT NULL)";
        db.execSQL(create_tb_ThanhVien);
        String create_tb_Sach = "CREATE TABLE sach(masach INTEGER PRIMARY KEY AUTOINCREMENT, tensach TEXT NOT NULL, giathue INTEGER NOT NULL, maloai INTEGER REFERENCES loaisach(maloai))";
        db.execSQL(create_tb_Sach);
    }




}
