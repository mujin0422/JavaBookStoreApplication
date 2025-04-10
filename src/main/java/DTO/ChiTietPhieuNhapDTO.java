package DTO;

public class ChiTietPhieuNhapDTO {
    private int maPN;
    private int maSach;
    private int soLuong;
    private int giaNhap;

    public ChiTietPhieuNhapDTO() {}

    public ChiTietPhieuNhapDTO(int maPN, int maSach, int soLuong, int giaNhap) {
        this.maPN = maPN;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaSach() {
        return maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }
}
