package com.hoaphph29102.pnlib_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.Database.DbHelper;

import java.util.ArrayList;

public class SachDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long AddRow(SachDTO sachDTO){
        ContentValues values = new ContentValues();
        values.put("tensach",sachDTO.getTen_sach());
        values.put("giathue",sachDTO.getGia_thue());
        values.put("maloai",sachDTO.getMa_loai());

        return db.insert("sach",null,values);
    }

    public int UpdateRow(SachDTO sachDTO){

        ContentValues values = new ContentValues();
        values.put("tensach",sachDTO.getTen_sach());
        values.put("giathue",sachDTO.getGia_thue());
        values.put("maloai",sachDTO.getMa_loai());

        String[] dieukien = new String[]{String.valueOf(sachDTO.getMa_sach())};

        return db.update("sach",values,"masach=?",dieukien);
    }

    public int DeleteRow(SachDTO sachDTO){

        String[] dieukien = new String[]{String.valueOf(sachDTO.getMa_sach())};

        return db.delete("sach","masach=?",dieukien);
    }


    public ArrayList<SachDTO> getAllSach(){
        ArrayList<SachDTO> list_sach = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT sach.*, loaisach.tenloai FROM sach INNER JOIN loaisach ON sach.maloai = loaisach.maloai",null,null);

        if (c != null && c.getCount()>0){
            c.moveToFirst();

            while (!c.isAfterLast()){
                int ma_sach = c.getInt(0);
                String ten_sach = c.getString(1);
                int gia_thue = c.getInt(2);
                int ma_loai = c.getInt(3);
                String ten_loai = c.getString(4);

                //SachDTO(int ma_sach, String ten_sach, int gia_thue, int ma_loai, String ten_loai)
                SachDTO sachDTO = new SachDTO(ma_sach,ten_sach,gia_thue,ma_loai,ten_loai);
                list_sach.add(sachDTO);
                c.moveToNext();

            }
        }
        return list_sach;
    }


    //fix

    //get sách by id
    public SachDTO getSachById(int id){
        SachDTO sachDTO = new SachDTO();
        String sql = "SELECT * FROM sach WHERE masach = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(id)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                sachDTO.setMa_sach(cursor.getInt(0));
                sachDTO.setTen_sach(cursor.getString(1));
                sachDTO.setGia_thue(cursor.getInt(2));
                sachDTO.setMa_loai(cursor.getInt(3));
                cursor.moveToNext();
            }
        }
        return sachDTO;
    }
}
