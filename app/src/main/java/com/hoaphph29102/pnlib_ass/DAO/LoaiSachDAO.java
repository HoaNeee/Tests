package com.hoaphph29102.pnlib_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {

    SQLiteDatabase db;
    DbHelper dbHelper;

    public LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long AddRow(LoaiSachDTO loaiSachDTO){
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSachDTO.getTen_loai());

        return db.insert("loaisach",null,values);

    }
    public int UpdateRow(LoaiSachDTO loaiSachDTO){
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSachDTO.getTen_loai());

        String[] dieu_kien = new String[]{String.valueOf(loaiSachDTO.getMa_loai())};

        return db.update("loaisach",values,"maloai=?",dieu_kien);
    }
    public int DeleteRow(LoaiSachDTO loaiSachDTO){

        String[] dieu_kien = new String[]{String.valueOf(loaiSachDTO.getMa_loai())};

        return db.delete("loaisach","maloai=?",dieu_kien);
    }

    public ArrayList<LoaiSachDTO> getAll(){
        ArrayList<LoaiSachDTO> list_loai = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM loaisach",null);

        if (c != null && c.getCount()>0){
            c.moveToFirst();

            while (!c.isAfterLast()){
                int ma_loai = c.getInt(0);
                String ten_loai = c.getString(1);

                LoaiSachDTO loaiSachDTO = new LoaiSachDTO(ma_loai,ten_loai);
                list_loai.add(loaiSachDTO);

                c.moveToNext();

            }

        }
        else {
            Log.d("zzzz","er");
        }
        return list_loai;
    }
}
