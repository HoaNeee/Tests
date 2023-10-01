package com.hoaphph29102.pnlib_ass.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.hoaphph29102.pnlib_ass.DAO.LoaiSachDAO;
import com.hoaphph29102.pnlib_ass.DAO.SachDAO;
import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.DTO.SachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    ArrayList<SachDTO> list_sach;
    Context context;

    public SachAdapter(ArrayList<SachDTO> list_sach, Context context) {
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
            row = View.inflate(context, R.layout.row_sach, null);
        } else {
            row = convertView;

        }
        //dữ liệu
        SachDTO sachDTO = list_sach.get(position);

        TextView tv_ma_sach = row.findViewById(R.id.tv_ma_sach);
        TextView tv_ten_sach = row.findViewById(R.id.tv_ten_sach);
        TextView tv_gia_thue = row.findViewById(R.id.tv_gia_thue);
        TextView tv_ten_loai = row.findViewById(R.id.tv_ten_loai);
        ImageView img_delete = row.findViewById(R.id.img_delete);

        tv_ma_sach.setText("Mã sách: "+sachDTO.getMa_sach()+"");
        tv_ten_sach.setText("Tên sách: "+sachDTO.getTen_sach());
        tv_gia_thue.setText("Giá thuê: "+sachDTO.getGia_thue());
        tv_ten_loai.setText("Loại sách: "+sachDTO.getTen_loai());

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDelete(sachDTO);
            }
        });
        return row;


    }
    //Spiner
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null){
            row = View.inflate(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, null);
        } else {
            row = convertView;
        }

        SachDTO sachDTO = list_sach.get(position);

        TextView tv_ten_sach = row.findViewById(R.id.tv_ten_sach);

        tv_ten_sach.setText( sachDTO.getTen_sach());

        return row;
    }
    public void showDialogDelete(SachDTO sachDTO){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa sách: "+sachDTO.getTen_sach());
        builder.setIcon(R.drawable.baseline_close_24);
        builder.setCancelable(true);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SachDAO sachDAO = new SachDAO(context);
                int kq = sachDAO.DeleteRow(sachDTO);

                if (kq>0){
                    list_sach.clear();
                    list_sach.addAll(sachDAO.getAllSach());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
