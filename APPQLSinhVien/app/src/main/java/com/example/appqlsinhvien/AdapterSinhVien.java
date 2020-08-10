package com.example.appqlsinhvien;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSinhVien extends BaseAdapter {
    Activity context;
    ArrayList<SinhVien> list;

    public AdapterSinhVien(Activity context, ArrayList<SinhVien> list) {
        this.context = context;
        this.list = list;
    }
    //get count tra ra so dong
    //list.size ve ra cac dong
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView( final int position, final View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);
        ImageView imgHinhDaiDien = row.findViewById(R.id.imgHinhDaiDien);
        TextView txtId = (TextView) row.findViewById(R.id.txtId);
        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtSdt = (TextView) row.findViewById(R.id.txtSdt);
        TextView txtDiachi = (TextView) row.findViewById(R.id.txtDc);
        TextView txtKhoa = (TextView) row.findViewById(R.id.txtKhoa);
        TextView txtLop = (TextView) row.findViewById(R.id.txtLop);
        Button btnSua = (Button) row.findViewById(R.id.btnSua);
        Button btnXoa = (Button) row.findViewById(R.id.btnXoa);

        final SinhVien sinhVien = list.get(position);
        txtId.setText(sinhVien.id + "");
        txtTen.setText(sinhVien.ten);
        txtSdt.setText(sinhVien.sdt);
        txtDiachi.setText(sinhVien.diachi);
        txtKhoa.setText(sinhVien.khoa);
        txtLop.setText(sinhVien.lop);

        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(sinhVien.anh, 0, sinhVien.anh.length);
        imgHinhDaiDien.setImageBitmap(bmHinhDaiDien);

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("ID", sinhVien.id);
                context.startActivity(intent);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn muốn xóa ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        delete(sinhVien.id);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return row;
    }

    private void delete(int idSinhVien) {
        SQLiteDatabase database = Database.initDatabase(context, "EmployeeDB.sqlite.sqlite");
        database.delete("SinhVien", "ID = ?", new String[]{idSinhVien + ""});
        list.clear();

        Cursor cursor = database.rawQuery("SELECT * FROM SinhVien", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            String diachi = cursor.getString(4);
            String khoa = cursor.getString(5);
            String lop = cursor.getString(6);

            list.add(new SinhVien(id, ten, sdt, anh, diachi, khoa, lop));
        }
        notifyDataSetChanged();
    }
}
