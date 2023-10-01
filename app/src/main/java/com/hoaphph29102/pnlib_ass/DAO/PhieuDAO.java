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

    //fix

    //get all
    public ArrayList<PhieuDTO> getAllPhieu(){
        ArrayList<PhieuDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieu";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                PhieuDTO phieuDTO = new PhieuDTO();
                phieuDTO.setMa_phieu(cursor.getInt(0));
                phieuDTO.setMaTT(cursor.getInt(1));
                phieuDTO.setMaTV(cursor.getInt(2));
                phieuDTO.setMa_sach(cursor.getInt(3));
                phieuDTO.setTien_thue(cursor.getInt(4));
                phieuDTO.setNgay_thue(cursor.getString(5));
                phieuDTO.setTra_sach(cursor.getInt(6));
                list.add(phieuDTO);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
