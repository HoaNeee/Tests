package com.hoaphph29102.pnlib_ass.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class SpinnerSach extends BaseAdapter {
    ArrayList<SachDTO> list_sach;
    Context context;

    public SpinnerSach(ArrayList<SachDTO> list_sach, Context context) {
        this.list_sach = list_sach;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_sach.size();
    }

    @Override
    public Object getItem(int position) {
        return list_sach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_sach.get(position).getMa_sach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
            row = View.inflate(context, R.layout.spiner_ten_sach, null);
        } else {
            row = convertView;

        }
        //dữ liệu
        SachDTO sachDTO = list_sach.get(position);

        TextView tv_ten_sach = row.findViewById(R.id.tv_ten_sach);

        tv_ten_sach.setText("Tên sách: "+sachDTO.getTen_sach());



        return row;


    }
    //Spiner
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null){
            row = View.inflate(context, R.layout.spiner_ten_sach, null);
        } else {
            row = convertView;
        }

        SachDTO sachDTO = list_sach.get(position);

        TextView tv_ten_sach = row.findViewById(R.id.tv_ten_sach);

        tv_ten_sach.setText( sachDTO.getTen_sach());

        return row;
    }
}
