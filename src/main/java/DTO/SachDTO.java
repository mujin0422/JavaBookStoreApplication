package DTO;

import java.util.Objects;

public class SachDTO {
    private int maSach;
    private String tenSach;
    private int giaSach;
    private int soLuongTon;
    private int maNXB;

    public SachDTO() {
    }

    public SachDTO(int maSach, String tenSach, int giaSach, int soLuongTon, int maNXB) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.soLuongTon = soLuongTon;
        this.maNXB = maNXB;
    }

    public int getMaSach() {
        return maSach;
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

    @Override
    public String toString() {
        return "SachDTO{" + "maSach=" + maSach + ", tenSach=" + tenSach + ", giaSach=" + giaSach + ", soLuongTon=" + soLuongTon + ", maNXB=" + maNXB + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.maSach;
        hash = 17 * hash + Objects.hashCode(this.tenSach);
        hash = 17 * hash + this.giaSach;
        hash = 17 * hash + this.soLuongTon;
        hash = 17 * hash + this.maNXB;
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
        final SachDTO other = (SachDTO) obj;
        if (this.maSach != other.maSach) {
            return false;
        }
        if (this.giaSach != other.giaSach) {
            return false;
        }
        if (this.soLuongTon != other.soLuongTon) {
            return false;
        }
        if (this.maNXB != other.maNXB) {
            return false;
        }
        return Objects.equals(this.tenSach, other.tenSach);
    }
    
}
