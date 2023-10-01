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
import com.hoaphph29102.pnlib_ass.Adapter.LoaiSachAdapter;
import com.hoaphph29102.pnlib_ass.DAO.LoaiSachDAO;
import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    ArrayList<LoaiSachDTO> list_loai;
    LoaiSachAdapter loaiSachAdapter;
    LoaiSachDTO loaiSachDTO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv_loai_sach;
        LoaiSachDAO loaiSachDAO;


        FloatingActionButton fab_add_loai = view.findViewById(R.id.fab_add_loai);
        lv_loai_sach = view.findViewById(R.id.lv_loai_sach);

        loaiSachDAO = new LoaiSachDAO(getContext());

        list_loai = loaiSachDAO.getAll();
        loaiSachAdapter = new LoaiSachAdapter(list_loai,getContext());
        lv_loai_sach.setAdapter(loaiSachAdapter);

        fab_add_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        lv_loai_sach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loaiSachDTO = list_loai.get(position);
                showDialogUpdate(loaiSachDTO);
            }
        });

    }
    public void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_loai_sach,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_loai = v.findViewById(R.id.ed_ten_loai);
        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_loai = ed_ten_loai.getText().toString();
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
                LoaiSachDTO loaiSachDTO = new LoaiSachDTO(ten_loai);

                long id = loaiSachDAO.AddRow(loaiSachDTO);

                if (id>0){
                    list_loai.clear();
                    list_loai.addAll(loaiSachDAO.getAll());
                    loaiSachAdapter.notifyDataSetChanged();
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



    public void showDialogUpdate(LoaiSachDTO loaiSachDTO){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_loai_sach,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_loai = v.findViewById(R.id.ed_ten_loai);
        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);


        ed_ten_loai.setText(loaiSachDTO.getTen_loai());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten_loai = ed_ten_loai.getText().toString();

                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
                LoaiSachDTO loaiSachDTO_moi = new LoaiSachDTO(ten_loai);

                loaiSachDTO_moi.setMa_loai(loaiSachDTO.getMa_loai());

                long id = loaiSachDAO.UpdateRow(loaiSachDTO_moi);

                if (id>0){
                    list_loai.clear();
                    list_loai.addAll(loaiSachDAO.getAll());
                    loaiSachAdapter.notifyDataSetChanged();
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
