package com.example.appqlsinhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.ControlButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhap extends AppCompatActivity {
    EditText edtaikhoan, edtmatkhau;
    Button btndangnhap, btndangky, btnexit;
    String ten, mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
        ControlButton();
    }
    private void ControlButton() {
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DangNhap.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn muốn thoát ?");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DangNhap.this);
                dialog.setTitle("Hộp đang xử lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.customdialog);
                final EditText edttk = (EditText)dialog.findViewById(R.id.edttk);
                final EditText edtmk = (EditText)dialog.findViewById(R.id.edmk);
                Button btnhuy = (Button)dialog.findViewById(R.id.buttonhuy);
                Button btndongy = (Button)dialog.findViewById(R.id.butondongy);
                btndongy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ten = edttk.getText().toString().trim();
                        mk = edtmk.getText().toString().trim();

                        edtaikhoan.setText(ten);
                        edtmatkhau.setText(mk);

                        dialog.cancel();
                    }
                });
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                btndangnhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtaikhoan.getText().length() != 0 && edtmatkhau.getText().length() !=0) {
                            if (edtaikhoan.getText().toString().equals(ten) && edtmatkhau.getText().toString().equals(mk)) {
                                Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangNhap.this, FormChinh.class);
                                startActivity(intent);
                            } else if (edtaikhoan.getText().toString().equals("tien") && edtmatkhau.getText().toString().equals("123")) {
                                Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangNhap.this, FormChinh.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DangNhap.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void anhXa() {
        edtaikhoan = (EditText) findViewById(R.id.edtaikhoan);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhau);
        btndangnhap = (Button) findViewById(R.id.buttondangnhap);
        btndangky = (Button) findViewById(R.id.buttondangky);
        btnexit = (Button) findViewById(R.id.buttonexit);
    }
}