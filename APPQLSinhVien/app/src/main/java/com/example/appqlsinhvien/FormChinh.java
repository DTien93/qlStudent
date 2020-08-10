package com.example.appqlsinhvien;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appqlsinhvien.model.TTNoiDung;
import com.example.appqlsinhvien.model.TTSVAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FormChinh extends AppCompatActivity {
    Button btnChuyen;
    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    Dialog dialog;
    ListView lvSinhVien;
    ArrayList<TTNoiDung> arraySinhVien;
    TTSVAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);
        anhXa();
        ActionViewFlipper();
        Chuyen();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_main);
        //btnChuyen = (Button) findViewById(R.id.btnChuyen);
        ///btnChuyen.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        //   Intent intent = new Intent(FormChinh.this, MainActivity.class);
        //   startActivity(intent);
        adapter = new TTSVAdapter(this, R.layout.thong_tin_listview, arraySinhVien);
        lvSinhVien.setAdapter(adapter);
    }
    private  void Chuyen() {
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormChinh.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://kenh14cdn.com/zoom/280_175/2020/8/3/ndt9703-1594199723134427193720-1594615226819384260416-15964263798241118649878-crop-15964266253701125925436.jpg");
        mangquangcao.add("https://kenh14cdn.com/thumb_w/620/2020/8/2/3453627920288474605199828887721051341979648n-1530755965432173286478-15307563526921556903641-1596349495706552989918.png");
        mangquangcao.add("http://qnu.edu.vn/Temp/ArticleImage/153b1409-83ff-4ecf-b9ea-ccdc344215da-screen-44.jpg");
        mangquangcao.add("http://qnu.edu.vn/Temp/ArticleImage/045eccdc-c048-47bb-a3f0-3b2f25beb1f7-sv0-58.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        viewFlipper.startFlipping();
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void anhXa() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnChuyen = (Button) findViewById(R.id.btnChuyen);
        lvSinhVien = (ListView) findViewById(R.id.listviewsv);
        arraySinhVien = new ArrayList<>();
        arraySinhVien.add(new TTNoiDung("Tuyển sinh Ngành Sư Phạm", "Chỉ tiêu gấp 3 lần năm ngoái", R.drawable.images));
        arraySinhVien.add(new TTNoiDung("Tuyển sinh năm 2020", "Chỉ tiêu 20.000 sinh viên", R.drawable.images));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_ds, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idmyHome:
                break;
            case R.id.idsetting:
                break;
            case R.id.idDS:
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}



