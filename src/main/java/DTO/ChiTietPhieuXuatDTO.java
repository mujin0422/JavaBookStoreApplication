
package DTO;


public class ChiTietPhieuXuatDTO {
    private int maPX;
    private int maSach;
    private int giaBan;
    private int soLuongSP;

    public ChiTietPhieuXuatDTO() {
    }

    
    public ChiTietPhieuXuatDTO(int maPX, int maSach, int giaBan, int soLuongSP) {
        this.maPX = maPX;
        this.maSach = maSach;
        this.giaBan = giaBan;
        this.soLuongSP = soLuongSP;
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

    public int getSoLuongSP() {
        return soLuongSP;
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

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }
}
