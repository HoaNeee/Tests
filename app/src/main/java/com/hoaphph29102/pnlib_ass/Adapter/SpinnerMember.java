package com.hoaphph29102.pnlib_ass.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class SpinnerMember extends BaseAdapter {
    ArrayList<MemberDTO> list_mem;
    Context context;

    public SpinnerMember(ArrayList<MemberDTO> list_mem, Context context) {
        this.list_mem = list_mem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_mem.size();
    }

    @Override
    public Object getItem(int position) {
        return list_mem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_mem.get(position).getMa_tv();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null){
            row = View.inflate(context, R.layout.spiner_ten_tv,null);
        }else {
            row = convertView;
        }

        MemberDTO memberDTO = list_mem.get(position);

        TextView tv_ten_member = row.findViewById(R.id.tv_ten_tv);

        tv_ten_member.setText("Thành viên: "+memberDTO.getTen_tv());

        return row;
    }
    //Spiner
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null){
            row = View.inflate(context, R.layout.spiner_ten_tv, null);
        } else {
            row = convertView;
        }

        MemberDTO memberDTO = list_mem.get(position);

        TextView tv_ten_tv = row.findViewById(R.id.tv_ten_tv);

        tv_ten_tv.setText( memberDTO.getTen_tv());

        return row;
    }
}
