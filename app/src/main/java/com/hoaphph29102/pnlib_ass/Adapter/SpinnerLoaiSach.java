package com.hoaphph29102.pnlib_ass.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class SpinnerLoaiSach extends BaseAdapter {
    ArrayList<LoaiSachDTO> list_loai;
    Context context;

    public SpinnerLoaiSach(ArrayList<LoaiSachDTO> list_loai, Context context) {
        this.list_loai = list_loai;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_loai.size();
    }

    @Override
    public Object getItem(int position) {
        return list_loai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_loai.get(position).getMa_loai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
            row = View.inflate(context, R.layout.spiner_loai_sach, null);
        } else {
            row = convertView;

        }
        //dữ liệu
        LoaiSachDTO loaiSachDTO = list_loai.get(position);

        TextView tv_ten_loai = row.findViewById(R.id.tv_ten_loai);

        tv_ten_loai.setText("Loại sách: " + loaiSachDTO.getTen_loai());

        return row;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
            row = View.inflate(context, R.layout.spiner_loai_sach, null);
        } else {
            row = convertView;
        }

        LoaiSachDTO loaiSachDTO = list_loai.get(position);

        TextView tv_ten_loai = row.findViewById(R.id.tv_ten_loai);

        tv_ten_loai.setText(loaiSachDTO.getTen_loai());

        return row;
    }
}

