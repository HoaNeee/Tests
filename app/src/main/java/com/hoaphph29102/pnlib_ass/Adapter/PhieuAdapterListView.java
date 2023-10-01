package com.hoaphph29102.pnlib_ass.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaphph29102.pnlib_ass.DAO.MemberDAO;
import com.hoaphph29102.pnlib_ass.DAO.SachDAO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.DTO.PhieuDTO;
import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class PhieuAdapterListView extends BaseAdapter {
    ArrayList<PhieuDTO> list_phieu;
    Context context;

    public PhieuAdapterListView(ArrayList<PhieuDTO> list_phieu, Context context) {
        this.list_phieu = list_phieu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_phieu.size();
    }

    @Override
    public Object getItem(int position) {
        return list_phieu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list_phieu.get(position).getMa_sach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null) {
            row = View.inflate(context, R.layout.row_phieu_list_view, null);
        } else {
            row = convertView;

        }
        //dữ liệu
        PhieuDTO phieuDTO = list_phieu.get(position);

        TextView tv_ma_phieu = row.findViewById(R.id.tv_ma_phieu);
        TextView tv_ten_tv = row.findViewById(R.id.tv_ho_ten);
        TextView tv_ten_sach = row.findViewById(R.id.tv_ten_sach);
        TextView tv_gia_thue = row.findViewById(R.id.tv_tien);
        TextView tv_ngay_thue = row.findViewById(R.id.tv_ngay_thue);
        TextView tv_tra_sach = row.findViewById(R.id.tv_tra_sach);
        ImageView img_delete = row.findViewById(R.id.img_delete);

        SachDAO sachDAO = new SachDAO(context);
        SachDTO sachDTO = sachDAO.getSachById(phieuDTO.getMa_sach());

        MemberDAO memberDAO = new MemberDAO(context);
        MemberDTO memberDTO = memberDAO.getMemberById(phieuDTO.getMaTV());



        tv_ma_phieu.setText("Mã phiếu: " + phieuDTO.getMa_phieu());
        tv_ten_tv.setText("Mã thành viên: " + memberDTO.getTen_tv());
        tv_ten_sach.setText("Tên sách: " + sachDTO.getTen_sach());
        tv_gia_thue.setText("Tiền thuê: " + phieuDTO.getTien_thue());
        tv_ngay_thue.setText("Ngày thuê: " + phieuDTO.getNgay_thue()+"");
        if (phieuDTO.getTra_sach() == 1){
            tv_tra_sach.setText("Trạng thái: Đã trả");
        }
        else {
            tv_tra_sach.setText("Trạng thái: Chưa trả");
        }


        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return row;
    }
}
