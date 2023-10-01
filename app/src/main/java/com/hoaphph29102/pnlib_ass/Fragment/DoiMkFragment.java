package com.hoaphph29102.pnlib_ass.Fragment;

import android.app.Activity;
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
import com.hoaphph29102.pnlib_ass.Login_activity;
import com.hoaphph29102.pnlib_ass.R;

public class DoiMkFragment extends Fragment {

    TextInputLayout ed_new_password,ed_re_password;
    Button btn_create_new_pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doi_mk,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ed_new_password = view.findViewById(R.id.ed_new_password);
        ed_re_password = view.findViewById(R.id.ed_re_pasword);
        btn_create_new_pass = view.findViewById(R.id.btn_create_new_password);

        btn_create_new_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pass = ed_new_password.getEditText().getText().toString().trim();
                String re_pass = ed_re_password.getEditText().getText().toString().trim();

                if (!new_pass.isEmpty() && !re_pass.isEmpty()){

                    if (new_pass.equals(re_pass)){
                        String username = getActivity().getIntent().getStringExtra("username");
                        UserDAO userDAO = new UserDAO(getContext());

                        if (userDAO.updatePassword(username, new_pass)){
                            Toast.makeText(getContext(), "Đặt lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), Login_activity.class));

                        } else {
                            Toast.makeText(getContext(), "Đặt lại mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(getContext(), "Nhâp lại mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
