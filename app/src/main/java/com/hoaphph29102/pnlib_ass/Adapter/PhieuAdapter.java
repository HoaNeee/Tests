package com.hoaphph29102.pnlib_ass.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoaphph29102.pnlib_ass.DTO.PhieuDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class PhieuAdapter extends RecyclerView.Adapter<PhieuAdapter.viewHolder>{
    Context context;
    ArrayList<PhieuDTO> list_phieu;


    public PhieuAdapter(Context context, ArrayList<PhieuDTO> list_phieu) {
        this.context = context;
        this.list_phieu = list_phieu;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.row_phieu,parent,false);


        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PhieuDTO phieuDTO = list_phieu.get(position);

        holder.tv_ma_phieu.setText(phieuDTO.getMa_phieu()+"");
        holder.tv_ho_ten.setText(phieuDTO.getTen_TV());
        holder.tv_sach.setText(phieuDTO.getTen_sach());
        holder.tv_gia_thue.setText(phieuDTO.getTien_thue()+"");
        holder.tv_ngay_thue.setText(phieuDTO.getNgay_thue()+"");
        holder.tv_tra_sach.setText(phieuDTO.getTra_sach()+"");

    }

    @Override
    public int getItemCount() {
        return list_phieu.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_ma_phieu,tv_ho_ten,tv_sach,tv_gia_thue,tv_tra_sach,tv_ngay_thue;
        ImageView img_delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

             tv_ma_phieu = itemView.findViewById(R.id.tv_ma_phieu);
             tv_ho_ten = itemView.findViewById(R.id.tv_ho_ten);
             tv_sach = itemView.findViewById(R.id.tv_ten_sach);
             tv_gia_thue = itemView.findViewById(R.id.tv_tien);
             tv_ngay_thue = itemView.findViewById(R.id.tv_ngay_thue);
             tv_tra_sach = itemView.findViewById(R.id.tv_tra_sach);
             img_delete = itemView.findViewById(R.id.img_delete);

        }
    }
}
