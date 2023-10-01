package com.hoaphph29102.pnlib_ass.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.hoaphph29102.pnlib_ass.DAO.UserDAO;
import com.hoaphph29102.pnlib_ass.DTO.UserDTO;
import com.hoaphph29102.pnlib_ass.R;

public class AddUserFragment extends Fragment {

    TextInputLayout ed_username,ed_password,ed_re_pass,ed_ten;
    Button btn_confirm,btn_cancel;
    UserDAO userDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ed_username = view.findViewById(R.id.ed_username);
        ed_password = view.findViewById(R.id.ed_password);
        ed_re_pass = view.findViewById(R.id.ed_re_pass);
        ed_ten = view.findViewById(R.id.ed_ten);
        btn_confirm = view.findViewById(R.id.btn_confirm);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        userDAO = new UserDAO(getContext());

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getEditText().getText().toString().trim();
                String password = ed_password.getEditText().getText().toString().trim();
                String re_pass = ed_re_pass.getEditText().getText().toString().trim();
                String ho_ten = ed_ten.getEditText().getText().toString().trim();




                if (!password.equals(re_pass)) {
                    Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userDAO.usernameExists(username)) {
                    Toast.makeText(getContext(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();

                    return;
                }

                    UserDTO objUser = new UserDTO();
                    objUser.setUsername(username);
                    objUser.setPassword(password);
                    objUser.setHo_ten(ho_ten);


                    long kq = userDAO.AddUser(objUser);

                    if (kq > 0){
                        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }



            }
        });
    }

    public void registerUser(){

        String username = ed_username.getEditText().getText().toString().trim();
        String password = ed_password.getEditText().getText().toString().trim();
        String re_pass = ed_re_pass.getEditText().getText().toString().trim();
        String ho_ten = ed_ten.getEditText().getText().toString().trim();

        UserDAO userDAO = new UserDAO(getContext());

        boolean isUserExists = userDAO.usernameExists(username);

        if (isUserExists){
            Toast.makeText(getContext(), "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
            return ;

        }
        if (password.equals(re_pass)){

            UserDTO objUser = new UserDTO();
            objUser.setUsername(username);
            objUser.setPassword(password);
            objUser.setHo_ten(ho_ten);


            long kq = userDAO.AddUser(objUser);

            if (kq > 0){
                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(getContext(), "Nhập lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();

        }
    }
}
