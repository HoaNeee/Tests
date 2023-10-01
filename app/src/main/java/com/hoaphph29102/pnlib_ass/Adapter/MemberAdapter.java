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
import com.hoaphph29102.pnlib_ass.DAO.MemberDAO;
import com.hoaphph29102.pnlib_ass.DTO.LoaiSachDTO;
import com.hoaphph29102.pnlib_ass.DTO.MemberDTO;
import com.hoaphph29102.pnlib_ass.Fragment.MemberFragment;
import com.hoaphph29102.pnlib_ass.R;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    ArrayList<MemberDTO> list_mem;
    Context context;

    public MemberAdapter(ArrayList<MemberDTO> list_mem, Context context) {
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
            row = View.inflate(context,R.layout.row_member,null);
        }else {
            row = convertView;
        }

        MemberDTO memberDTO = list_mem.get(position);

        TextView tv_ma_member = row.findViewById(R.id.tv_ma_member);
        TextView tv_ten_member = row.findViewById(R.id.tv_ten_member);
        TextView tv_nam_sinh = row.findViewById(R.id.tv_nam_sinh);
        ImageView img_delete = row.findViewById(R.id.img_delete);

        tv_ma_member.setText("Mã thành viên: "+memberDTO.getMa_tv()+"");
        tv_ten_member.setText("Thành viên: "+memberDTO.getTen_tv());
        tv_nam_sinh.setText("Năm sinh: "+memberDTO.getNam_sinh());

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDelete(memberDTO);
            }
        });

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

    public void showDialogDelete(MemberDTO memberDTO){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc muốn xóa thành viên: "+memberDTO.getTen_tv());
        builder.setIcon(R.drawable.baseline_close_24);
        builder.setCancelable(true);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MemberDAO memberDAO = new MemberDAO(context);
                int kq = memberDAO.DeleteRow(memberDTO);

                if (kq>0){
                    list_mem.clear();
                    list_mem.addAll(memberDAO.getAllMember());
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
