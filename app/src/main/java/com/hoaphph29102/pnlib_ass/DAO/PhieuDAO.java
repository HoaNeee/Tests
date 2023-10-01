package com.hoaphph29102.pnlib_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.hoaphph29102.pnlib_ass.DTO.PhieuDTO;
import com.hoaphph29102.pnlib_ass.Database.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PhieuDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public PhieuDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //maTT TEXT REFERENCES thuthu(maTT), maTV INTEGER REFERENCES thanhvien(maTV), masach INTEGER REFERENCES sach(masach), tienthue INTEGER NOT NULL, ngay DATE NOT NULL, trasach INTEGER NOT NULL)
    public long AddRow(PhieuDTO phieuDTO){
        long result = -1;
        ContentValues values = new ContentValues();

        values.put("maTV",phieuDTO.getMaTV());
        values.put("masach",phieuDTO.getMa_sach());
        values.put("tienthue",phieuDTO.getTien_thue());
        values.put("ngay",phieuDTO.getNgay_thue());
        values.put("trasach",phieuDTO.getTra_sach());

        try {
           result= db.insert("phieu",null,values);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean UpdateStatus(Integer id, boolean check){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int statusValue = check ? 1 : 0;
        ContentValues values = new ContentValues();
        values.put("trasach",statusValue);
        String[] dieukien = new  String[]{String.valueOf(id)};
        long row = database.update("phieu",values,"maPM=?",dieukien);
        return row != -1;

    }


    public int UpdateRow(PhieuDTO phieuDTO){
        ContentValues values = new ContentValues();
        values.put("maTT",phieuDTO.getMaTT());
        values.put("maTV",phieuDTO.getMaTV());
        values.put("masach",phieuDTO.getMa_sach());
        values.put("tienthue",phieuDTO.getTien_thue());
//        values.put("ngay",phieuDTO.getNgay_thue());
        values.put("trasach",phieuDTO.getTra_sach());

        String[] dieukien = new String[]{String.valueOf(phieuDTO.getMa_phieu())};

        return db.update("phieu",values,"maPM=?",dieukien);
    }

    public int DeleteRow(PhieuDTO phieuDTO){
        String[] dieukien = new String[]{String.valueOf(phieuDTO.getMa_phieu())};

        return db.delete("phieu","maPM=?",dieukien);
    }


//    @SuppressLint("Range")
//    public ArrayList<PhieuDTO> getAllPhieu() {
//        ArrayList<PhieuDTO> list_phieu = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String query = "SELECT phieu.*,thanhvien.hoten,sach.tensach,thuthu.maTT FROM phieu INNER JOIN thuthu ON phieu.maTT = thuthu.maTT " +
//                "INNER JOIN thanhvien ON phieu.maTV = thanhvien.maTV " +
//                "INNER JOIN sach ON phieu.masach = sach.masach";
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                PhieuDTO phieuDTO = new PhieuDTO();
//                phieuDTO.setMa_phieu(cursor.getInt(cursor.getColumnIndex("maPM")));
//
//                phieuDTO.setTen_TV(cursor.getString(cursor.getColumnIndex("tenTV")));
//                phieuDTO.setTen_sach(cursor.getString(cursor.getColumnIndex("tensach")));
//                phieuDTO.setTien_thue(cursor.getInt(cursor.getColumnIndex("tienthue")));
//                phieuDTO.setNgay_thue(cursor.getString(cursor.getColumnIndex("ngay")));
//                phieuDTO.setTra_sach(cursor.getInt(cursor.getColumnIndex("trasach")));
//
//                list_phieu.add(phieuDTO);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        return list_phieu;
//    }

    public ArrayList<PhieuDTO> getAllPhieu(){

        ArrayList<PhieuDTO> list_phieu = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT phieu.*, thanhvien.hoten , sach.tensach FROM phieu " +
                "INNER JOIN thuthu ON phieu.maTT = thuthu.maTT " +
                "INNER JOIN thanhvien ON phieu.maTV = thanhvien.maTV " +
                "INNER JOIN sach ON phieu.masach = sach.masach",null);
//        Cursor c = db.rawQuery("SELECT * FROM phieu",null);

            if (c != null && c.getCount()>0){
                c.moveToFirst();
                while (!c.isAfterLast()){
                    int ma_phieu = c.getInt(0);
                    int ma_tv = c.getInt(1);
                    String ten_tv = c.getString(2);
                    int ma_sach = c.getInt(3);
                    String ten_sach = c.getString(4);
                    int gia_thue = c.getInt(5);
                    String ngay_thue = c.getString(6);
                    int tra_sach = c.getInt(7);
                    int ma_thu_thu = c.getInt(8);

                    PhieuDTO phieuDTO = new PhieuDTO(ma_phieu,ma_tv,ten_tv,ma_sach,ten_sach,gia_thue,ngay_thue,tra_sach,ma_thu_thu);

                    list_phieu.add(phieuDTO);

                    c.moveToNext();
                }
        }
        return list_phieu;
    }
}
