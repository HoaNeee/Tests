package com.hoaphph29102.pnlib_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hoaphph29102.pnlib_ass.DAO.UserDAO;

public class Login_activity extends AppCompatActivity {

    TextInputLayout ed_username,ed_password;
    Button btn_login;

    CheckBox cb_remember;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_password = findViewById(R.id.ed_password);
        ed_username = findViewById(R.id.ed_username);
        btn_login = findViewById(R.id.btn_login);
        cb_remember = findViewById(R.id.cb_remember);

        preferences = getSharedPreferences("CurrentUser",MODE_PRIVATE);

        if (preferences.getBoolean("rememberMe",false)){
            String user_save = preferences.getString("username","");
            String pass_save = preferences.getString("password","");

            ed_username.getEditText().setText(user_save);
            ed_password.getEditText().setText(pass_save);

            cb_remember.setChecked(true);

        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask();
            }
        });
    }
    public void LoginTask(){

        String username = ed_username.getEditText().getText().toString().trim();
        String password = ed_password.getEditText().getText().toString().trim();


        UserDAO userDAO = new UserDAO(this);
        boolean isValidUser = userDAO.validateUser(username,password);

        if (isValidUser ){

            //nếu checkBox được chọn sẽ lưu thông tin người dùng vào SharedPreferences
                if (cb_remember.isChecked()){

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.putBoolean("rememberMe",true);
                    editor.apply();

                }
                else {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("username");
                    editor.remove("password");
                    editor.remove("rememberMe");
                    editor.apply();
                }

                Toast.makeText(this, "Thành Công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login_activity.this,MainActivity.class));
                finish();
            }
            else {
                Toast.makeText(this, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác", Toast.LENGTH_SHORT).show();
            }

    }


}