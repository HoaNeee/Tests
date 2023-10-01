package com.hoaphph29102.pnlib_ass.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoaphph29102.pnlib_ass.Adapter.PhieuAdapter;
import com.hoaphph29102.pnlib_ass.Adapter.PhieuAdapterListView;
import com.hoaphph29102.pnlib_ass.Adapter.SpinnerMember;
import com.hoaphph29102.pnlib_ass.Adapter.SpinnerSach;
import com.hoaphph29102.pnlib_ass.DAO.MemberDAO;
import com.hoaphph29102.pnlib_ass.DAO.PhieuDAO;
import com.hoaphph29102.pnlib_ass.DAO.SachDAO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.DTO.PhieuDTO;
import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class PhieuFragment extends Fragment {
    ArrayList<PhieuDTO> list_phieu;
    PhieuAdapter phieuAdapter;
    PhieuAdapterListView phieuAdapterListView;
    PhieuDTO phieuDTO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phieu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RecyclerView rc_phieu;
        ListView lv_phieu;
        FloatingActionButton fab_add_phieu;
        PhieuDAO phieuDAO;

        fab_add_phieu = view.findViewById(R.id.fab_add_phieu);
        lv_phieu = view.findViewById(R.id.lv_phieu);

        phieuDAO = new PhieuDAO(getContext());
        list_phieu = phieuDAO.getAllPhieu();
        if(list_phieu.isEmpty()){
            Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show();
        }
        phieuAdapterListView = new PhieuAdapterListView(list_phieu,getContext());

//        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        rc_phieu.setLayoutManager(manager);
//
//        rc_phieu.setAdapter(phieuAdapter);

        lv_phieu.setAdapter(phieuAdapterListView);

        fab_add_phieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

    }
    public void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_phieu,null);
        builder.setView(v);
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();

        EditText ed_gia_thue = v.findViewById(R.id.ed_gia_thue);
        EditText ed_ngay_thue = v.findViewById(R.id.ed_ngay_thue);
        Spinner sp_ten_tv = v.findViewById(R.id.sp_ten_tv);
        Spinner sp_ten_sach = v.findViewById(R.id.sp_ten_sach);
        CheckBox cb_tra_sach = v.findViewById(R.id.cb_tra_sach);
        Button btn_save = v.findViewById(R.id.btn_save);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);


        MemberDAO memberDAO = new MemberDAO(getContext());
        ArrayList<MemberDTO> list_mem = memberDAO.getAllMember();
        SpinnerMember spinnerMember = new SpinnerMember(list_mem,getContext());
        sp_ten_tv.setAdapter(spinnerMember);

        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<SachDTO> list_sach = sachDAO.getAllSach();
        SpinnerSach spinnerSach = new SpinnerSach(list_sach,getContext());
        sp_ten_sach.setAdapter(spinnerSach);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gia_thue = Integer.parseInt(ed_gia_thue.getText().toString());
                String ngay_thue = ed_ngay_thue.getText().toString();
                int ma_tv = (int) sp_ten_tv.getSelectedItemId();
                int ma_sach = (int) sp_ten_sach.getSelectedItemId();
                int tra_sach = cb_tra_sach.isChecked() ? 1:0;
//PhieuDTO(int maTV, int ma_sach, int tien_thue, String ngay_thue, int tra_sach) {
                PhieuDAO phieuDAO = new PhieuDAO(getContext());

                PhieuDTO phieuDTO = new PhieuDTO(ma_tv,ma_sach,gia_thue,ngay_thue,tra_sach);


                Log.d("Before",list_phieu.toString());
                long kq = phieuDAO.AddRow(phieuDTO);

                if (kq != -1){
                    Log.d("After",list_phieu.toString());
                    list_phieu.clear();
                    list_phieu.addAll(phieuDAO.getAllPhieu());
                    phieuAdapterListView.notifyDataSetChanged();
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
