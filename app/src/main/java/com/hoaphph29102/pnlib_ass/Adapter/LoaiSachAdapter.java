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
import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends BaseAdapter {
    ArrayList<LoaiSachDTO> list_loai;
    Context context;

    public LoaiSachAdapter(ArrayList<LoaiSachDTO> list_loai, Context context) {
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

        if (convertView == null){
            row = View.inflate(context, R.layout.row_loai_sach,null);
        } else {
            row = convertView;

        }
        //dữ liệu
        LoaiSachDTO loaiSachDTO = list_loai.get(position);

        TextView tv_ma_loai = row.findViewById(R.id.tv_ma_loai);
        TextView tv_ten_loai = row.findViewById(R.id.tv_ten_loai);
        ImageView img_delete = row.findViewById(R.id.img_delete);

        tv_ma_loai.setText("Mã Loại: "+loaiSachDTO.getMa_loai()+"");
        tv_ten_loai.setText("Tên Loại: "+loaiSachDTO.getTen_loai());

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDelete(loaiSachDTO);
            }
        });
        return row;
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row;

        if (convertView == null){
            row = View.inflate(context, R.layout.spiner_loai_sach, null);
        } else {
            row = convertView;
        }

        LoaiSachDTO loaiSachDTO = list_loai.get(position);

        TextView tv_ten_loai = row.findViewById(R.id.tv_ten_loai);

        tv_ten_loai.setText( loaiSachDTO.getTen_loai());

        return row;
    }

    public void showDialogDelete(LoaiSachDTO loaiSachDTO){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa loại sách: "+loaiSachDTO.getTen_loai());
        builder.setIcon(R.drawable.baseline_close_24);
        builder.setCancelable(true);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int kq = loaiSachDAO.DeleteRow(loaiSachDTO);

                if (kq>0){
                    list_loai.clear();
                    list_loai.addAll(loaiSachDAO.getAll());
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
