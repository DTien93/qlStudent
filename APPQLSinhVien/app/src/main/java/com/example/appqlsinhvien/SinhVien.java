package com.example.appqlsinhvien;

public class SinhVien {

    public int id;
    public String ten;
    public String sdt;
    public byte[] anh;
    public String diachi;
    public String khoa;
    public String lop;
    public SinhVien(int id, String ten, String sdt, byte[] anh, String diachi, String khoa, String lop) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.anh = anh;
        this.diachi = diachi;
        this.khoa = khoa;
        this.lop = lop;
    }
}
