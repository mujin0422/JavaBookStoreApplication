
package DTO;


public class ChiTietPhieuXuatDTO {
    private int maPX;
    private int maSach;
    private int soLuong;
    private int giaBan;

    public ChiTietPhieuXuatDTO() {
    }

    
    public ChiTietPhieuXuatDTO(int maPX, int maSach, int soLuong, int giaBan) {
        this.maPX = maPX;
        this.maSach = maSach;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public int getMaPX() {
        return maPX;
    }

    public int getMaSach() {
        return maSach;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public void setSoLuong(int soLuongSP) {
        this.soLuong = soLuongSP;
    }
}
