package com.hoaphph29102.pnlib_ass.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoaphph29102.pnlib_ass.Adapter.LoaiSachAdapter;
import com.hoaphph29102.pnlib_ass.Adapter.SachAdapter;
import com.hoaphph29102.pnlib_ass.Adapter.SpinnerLoaiSach;
import com.hoaphph29102.pnlib_ass.DAO.LoaiSachDAO;
import com.hoaphph29102.pnlib_ass.DAO.SachDAO;
import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class SachFragment extends Fragment {

    ArrayList<SachDTO> list_sach;
    SachAdapter sachAdapter;
    SachDTO sachDTO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lv_sach;
        SachDAO sachDAO;

        FloatingActionButton fab_add_sach = view.findViewById(R.id.fab_add_sach);
        lv_sach = view.findViewById(R.id.lv_sach);

        sachDAO = new SachDAO(getContext());

        list_sach = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(list_sach,getContext());
        lv_sach.setAdapter(sachAdapter);

        fab_add_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        lv_sach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sachDTO = list_sach.get(position);
                showDialogUpdate(sachDTO);
            }
        });
    }
    public void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_sach,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_sach = v.findViewById(R.id.ed_ten_sach);
        EditText ed_gia_thue = v.findViewById(R.id.ed_gia_thue);
        Spinner sp_ten_loai = v.findViewById(R.id.sp_loai_sach);
        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);

        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSachDTO> list_loai = loaiSachDAO.getAll();
        SpinnerLoaiSach spinnerLoaiSach = new SpinnerLoaiSach(list_loai,getContext());

        sp_ten_loai.setAdapter(spinnerLoaiSach);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_sach = ed_ten_sach.getText().toString();
                int gia_thue = Integer.parseInt(ed_gia_thue.getText().toString());
                int ma_loai = (int) sp_ten_loai.getSelectedItemId();


                SachDAO sachDAO = new SachDAO(getContext());
                SachDTO sachDTO = new SachDTO(ten_sach,gia_thue,ma_loai);
                Log.d("Before",list_sach.toString());
                long kq = sachDAO.AddRow(sachDTO);

                if (kq>0){
                    Log.d("After",list_sach.toString());
                    list_sach.clear();
                    list_sach.addAll(sachDAO.getAllSach());
                    sachAdapter.notifyDataSetChanged();
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

    public void showDialogUpdate(SachDTO sachDTO){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update_sach,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_ten_sach = v.findViewById(R.id.ed_ten_sach);
        EditText ed_gia_thue = v.findViewById(R.id.ed_gia_thue);
        Spinner sp_loai_sach = v.findViewById(R.id.sp_loai_sach);

        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);


        ed_ten_sach.setText(sachDTO.getTen_sach());
        ed_gia_thue.setText(sachDTO.getGia_thue()+"");

        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSachDTO> list_loai = loaiSachDAO.getAll();
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(list_loai,getContext());
        sp_loai_sach.setAdapter(loaiSachAdapter);

        for (int i=0 ;  i<list_loai.size() ; i++){
            if (list_loai.get(i).getMa_loai() == sachDTO.getMa_loai()){
                sp_loai_sach.setSelection(i);
            }
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten_sach = ed_ten_sach.getText().toString();
                int gia = Integer.parseInt(ed_gia_thue.getText().toString());
                int ma_loai = (int) sp_loai_sach.getSelectedItemId();

                SachDAO sachDAO = new SachDAO(getContext());
                SachDTO sachDTO_moi = new SachDTO(ten_sach,gia,ma_loai);

                sachDTO_moi.setMa_sach(sachDTO.getMa_sach());

                long kq = sachDAO.UpdateRow(sachDTO_moi);

                if (kq>0){
                    list_sach.clear();
                    list_sach.addAll(sachDAO.getAllSach());
                    sachAdapter.notifyDataSetChanged();
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
