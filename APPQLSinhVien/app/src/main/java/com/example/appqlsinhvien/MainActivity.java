package com.example.appqlsinhvien;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String DATABASE_NAME = "EmployeeDB.sqlite.sqlite";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<SinhVien> list;
    AdapterSinhVien adapter;
    Button btnAdd;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readData();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.trang_chu);
    }

    private void addControls() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterSinhVien(this, list);
        listView.setAdapter(adapter);
    }
    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM SinhVien", null);
        list.clear();
        //Bien co so
        //De tro den so so du lieu vao
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            String diachi = cursor.getString(4);
            String khoa = cursor.getString(5);
            String lop = cursor.getString(6);
            list.add(new SinhVien(id, ten, sdt, anh, diachi, khoa, lop));
        }
        //Ve lai giao dien
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idHome:
                dialog.show();
                break;
            case R.id.idquanly:
                break;
            case R.id.idtimkiem:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}