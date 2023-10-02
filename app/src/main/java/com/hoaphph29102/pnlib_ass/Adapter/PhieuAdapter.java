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

import com.hoaphph29102.pnlib_ass.DAO.MemberDAO;
import com.hoaphph29102.pnlib_ass.DAO.SachDAO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.DTO.PhieuDTO;
import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
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

        SachDAO sachDAO = new SachDAO(context);
        SachDTO sachDTO = sachDAO.getSachById(phieuDTO.getMa_sach());

        MemberDAO memberDAO = new MemberDAO(context);
        MemberDTO memberDTO = memberDAO.getMemberById(phieuDTO.getMaTV());



        holder.tv_ma_phieu.setText("Mã phiếu: " + phieuDTO.getMa_phieu());
        holder.tv_ho_ten.setText("Mã thành viên: " + memberDTO.getTen_tv());
        holder.tv_sach.setText("Tên sách: " + sachDTO.getTen_sach());
        holder.tv_gia_thue.setText("Tiền thuê: " + phieuDTO.getTien_thue());
        holder.tv_ngay_thue.setText("Ngày thuê: " + phieuDTO.getNgay_thue()+"");
        if (phieuDTO.getTra_sach() == 1){
            holder.tv_tra_sach.setText("Trạng thái: Đã trả");
        }
        else {
            holder.tv_tra_sach.setText("Trạng thái: Chưa trả");
        }

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
