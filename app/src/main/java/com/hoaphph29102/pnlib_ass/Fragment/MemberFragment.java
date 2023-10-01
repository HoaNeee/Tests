package com.hoaphph29102.pnlib_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoaphph29102.pnlib_ass.Adapter.MemberAdapter;
import com.hoaphph29102.pnlib_ass.DAO.MemberDAO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class MemberFragment extends Fragment {
    ArrayList<MemberDTO> list_mem;
    MemberAdapter memberAdapter;
    MemberDTO memberDTO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv_member;
        FloatingActionButton fab_add_member;
        MemberDAO memberDAO;

        lv_member = view.findViewById(R.id.lv_member);
        fab_add_member = view.findViewById(R.id.fab_add_member);
        memberDAO = new MemberDAO(getContext());

        list_mem = memberDAO.getAllMember();
        memberAdapter = new MemberAdapter(list_mem,getContext());
        lv_member.setAdapter(memberAdapter);

        fab_add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        lv_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                memberDTO = list_mem.get(position);
                showDialogUpdate(memberDTO);
            }
        });
    }
    public void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_member,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_tv = v.findViewById(R.id.ed_ten_tv);
        EditText ed_nam_sinh = v.findViewById(R.id.ed_nam_sinh);

        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_tv = ed_ten_tv.getText().toString();
                String nam_sinh = ed_nam_sinh.getText().toString();



                MemberDAO memberDAO = new MemberDAO(getContext());
                MemberDTO memberDTO = new MemberDTO(ten_tv,nam_sinh);

                long kq = memberDAO.AddRow(memberDTO);

                if (kq>0){
                    list_mem.clear();
                    list_mem.addAll(memberDAO.getAllMember());
                    memberAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getContext(), "không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialogUpdate(MemberDTO memberDTO){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_member,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_tv = v.findViewById(R.id.ed_ten_tv);
        EditText ed_nam_sinh = v.findViewById(R.id.ed_nam_sinh);


        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);


        ed_ten_tv.setText(memberDTO.getTen_tv());
        ed_nam_sinh.setText(memberDTO.getNam_sinh()+"");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ho_ten = ed_ten_tv.getText().toString();
                String nam_sinh = ed_nam_sinh.getText().toString();


                MemberDAO memberDAO = new MemberDAO(getContext());
                MemberDTO memberDTO_moi = new MemberDTO(ho_ten,nam_sinh);

                memberDTO_moi.setMa_tv(memberDTO.getMa_tv());

                long kq = memberDAO.UpdateRow(memberDTO_moi);

                if (kq>0){
                    list_mem.clear();
                    list_mem.addAll(memberDAO.getAllMember());
                    memberAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getContext(), "không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
