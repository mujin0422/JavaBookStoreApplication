package DTO;

import java.util.Objects;

public class SachDTO {
    private int maSach;
    private String tenSach;
    private int giaSach;
    private int soLuongTon;
    private int maNXB;
    private int trangThaiXoa;

    public SachDTO() {
    }

    public SachDTO(int maSach, String tenSach, int giaSach, int soLuongTon, int maNXB) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.soLuongTon = soLuongTon;
        this.maNXB = maNXB;
        this.trangThaiXoa = 0;
    }

    public int getMaSach() {
        return maSach;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public String getTenSach() {
        return tenSach;
    }

    public int getGiaSach() {
        return giaSach;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setGiaSach(int giaSach) {
        this.giaSach = giaSach;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }
}
