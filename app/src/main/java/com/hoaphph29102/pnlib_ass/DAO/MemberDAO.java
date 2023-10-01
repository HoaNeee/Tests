package com.hoaphph29102.pnlib_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.Database.DbHelper;

import java.util.ArrayList;

public class MemberDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public MemberDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long AddRow(MemberDTO memberDTO){
        ContentValues values = new ContentValues();
        values.put("hoten",memberDTO.getTen_tv());
        values.put("namsinh",memberDTO.getNam_sinh());

        return db.insert("thanhvien",null,values);
    }

    public int UpdateRow(MemberDTO memberDTO){
        ContentValues values = new ContentValues();

        values.put("hoten",memberDTO.getTen_tv());
        values.put("namsinh",memberDTO.getNam_sinh());

        String[] dieukien = new String[]{String.valueOf(memberDTO.getMa_tv())};

        return db.update("thanhvien",values,"maTV=?",dieukien);
    }
    public int DeleteRow(MemberDTO memberDTO){


        String[] dieukien = new String[]{String.valueOf(memberDTO.getMa_tv())};

        return db.delete("thanhvien","maTV=?",dieukien);
    }

    public ArrayList<MemberDTO> getAllMember(){
        ArrayList<MemberDTO> list_mem = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM thanhvien",null);

        if (c != null && c.getCount()>0){
            c.moveToFirst();

            while (!c.isAfterLast()){
                int ma_tv = c.getInt(0);
                String ho_ten = c.getString(1);
                String nam_sinh = c.getString(2);

                MemberDTO memberDTO = new MemberDTO(ma_tv,ho_ten,nam_sinh);
                list_mem.add(memberDTO);

                c.moveToNext();

            }

        }
        else {
            Log.d("zzzz","er");
        }
        return list_mem;
    }

    //get by id
    public MemberDTO getMemberById(int id){
        MemberDTO memberDTO = new MemberDTO();
        String sql = "SELECT * FROM thanhvien WHERE maTV = ?";
        Cursor c = db.rawQuery(sql,new String[]{String.valueOf(id)});

        if (c != null && c.getCount()>0){
            c.moveToFirst();

            while (!c.isAfterLast()){
                int ma_tv = c.getInt(0);
                String ho_ten = c.getString(1);
                String nam_sinh = c.getString(2);

                memberDTO = new MemberDTO(ma_tv,ho_ten,nam_sinh);

                c.moveToNext();

            }

        }
        return memberDTO;
    }
}
