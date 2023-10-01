package com.hoaphph29102.pnlib_ass.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoaphph29102.pnlib_ass.DTO.UserDTO;
import com.hoaphph29102.pnlib_ass.Database.DbHelper;

import java.util.ArrayList;

public class UserDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public UserDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long AddUser(UserDTO userDTO){
        ContentValues values = new ContentValues();
        values.put("username",userDTO.getUsername());
        values.put("password",userDTO.getPassword());
        values.put("hoten",userDTO.getHo_ten());

        return db.insert("thuthu",null,values);
    }

    public ArrayList<UserDTO> getAllUser(){
        ArrayList<UserDTO> list_user = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_user",null);

        if (c != null && c.getCount() > 0){
            c.moveToFirst();
            do {
                UserDTO objUser = new UserDTO();

                int id_user = c.getInt(0);
                String username = c.getString(1);
                String password = c.getString(2);
                String hoten = c.getString(3);


                objUser.setMaTT(id_user);
                objUser.setUsername(username);
                objUser.setPassword(password);
                objUser.setHo_ten(hoten);
                list_user.add(objUser);


            }while (c.moveToNext());


        }


        return list_user;
    }
    // //hàm kiểm tra xem người dùng đã tồn tại hay chứa
//    public boolean usernameExists(String username){
//        String[] columns = {"maTT"};
//        String selection = "username=?";
//        String[] selectionArgs = {username};
//
//        Cursor c =  db.query("thuthu",columns,selection,selectionArgs,null,null,null);
//
//        boolean isExistsUser = c.moveToFirst();
//        c.close();
//
//        return isExistsUser;
//
//
//    }
    public boolean usernameExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM thuthu WHERE username = ?";
            cursor = db.rawQuery(query, new String[]{username});

            // Kiểm tra xem có bản ghi nào có cùng tên đăng nhập không
            if (cursor != null && cursor.moveToFirst()) {

                return true; // Tên đăng nhập đã tồn tại
            } else {
                return false; // Tên đăng nhập không tồn tại
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //hàm kiểm tra thông tin người dùng có hợp lệ và tồn tại hay không

    public boolean validateUser(String username, String password){
        String[] columns = {"maTT"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username,password};

        Cursor c = db.query("thuthu",columns,selection,selectionArgs,null,null,null);

        boolean isValidateUser = c.moveToFirst();
        c.close();

        return isValidateUser;

    }

    public boolean updatePassword(String username, String newPassword){
        ContentValues values = new ContentValues();
        values.put("password",newPassword);
        String whereClause = "username=?";
        String[] whereArgs = {username};
        int row_thay_doi = db.update("thuthu", values,whereClause,whereArgs);

        return row_thay_doi > 0;
    }

    //Hàm lấy thông tin người dùng bằng id
    //Hàm này dùng xác minh và kiểm tra thông tin đăng nhập trong màn hình đăng nhập và cập nhật mật khẩu

    @SuppressLint("Range")
    public UserDTO getUserByUsername(String username){
        db = dbHelper.getReadableDatabase();
        UserDTO objUser = null;

        Cursor c =  db.query("thuthu",null,"username" + "=?",new String[]{username},null,null,null);

        if (c != null && c.moveToFirst() && c.getCount() > 0){
             int maTT = c.getInt(c.getColumnIndex("maTT"));
            String password = c.getString(c.getColumnIndex("password"));
            String ho_ten = c.getString(c.getColumnIndex("hoten"));

            objUser = new UserDTO(maTT,username,password,ho_ten);
        }



        return objUser;
    }
}
