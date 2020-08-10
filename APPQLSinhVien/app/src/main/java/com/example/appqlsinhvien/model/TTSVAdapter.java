package com.example.appqlsinhvien.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appqlsinhvien.R;

import java.util.List;

public class TTSVAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TTNoiDung> svList;

    public TTSVAdapter(Context context, int layout, List<TTNoiDung> svList) {
        this.context = context;
        this.layout = layout;
        this.svList = svList;
    }

    @Override
    public int getCount() {
        return svList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        //anh xa view
        TextView txtTen = (TextView) view.findViewById(R.id.textviewTen);
        TextView txtMoTa = (TextView) view.findViewById(R.id.textviewmota);
        ImageView imgSV = (ImageView) view.findViewById(R.id.imagehinhsv);

        TTNoiDung ttnoidung = svList.get(i);

        txtTen.setText(ttnoidung.getTen());
        txtMoTa.setText(ttnoidung.getMoTa());
        imgSV.setImageResource(ttnoidung.getHinh());
        return view;
    }
}
