
package DTO;


public class ChiTietPhieuXuatDTO {
    private int maCTPX;
    private int maPX;
    private int maSach;
    private int giaBan;
    private int soLuongSP;

    public ChiTietPhieuXuatDTO() {
    }

    
    public ChiTietPhieuXuatDTO(int maCTPX, int maPX, int maSach, int giaBan, int soLuongSP) {
        this.maCTPX = maCTPX;
        this.maPX = maPX;
        this.maSach = maSach;
        this.giaBan = giaBan;
        this.soLuongSP = soLuongSP;
    }

    public int getMaCTPX() {
        return maCTPX;
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

    public void setMaCTPX(int maCTPX) {
        this.maCTPX = maCTPX;
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

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO{" + "maCTPX=" + maCTPX + ", maPX=" + maPX + ", maSach=" + maSach + ", giaBan=" + giaBan + ", soLuongSP=" + soLuongSP + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.maCTPX;
        hash = 89 * hash + this.maPX;
        hash = 89 * hash + this.maSach;
        hash = 89 * hash + this.giaBan;
        hash = 89 * hash + this.soLuongSP;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieuXuatDTO other = (ChiTietPhieuXuatDTO) obj;
        if (this.maCTPX != other.maCTPX) {
            return false;
        }
        if (this.maPX != other.maPX) {
            return false;
        }
        if (this.maSach != other.maSach) {
            return false;
        }
        if (this.giaBan != other.giaBan) {
            return false;
        }
        return this.soLuongSP == other.soLuongSP;
    }
    
}
