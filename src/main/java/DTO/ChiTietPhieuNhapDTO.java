package DTO;


public class ChiTietPhieuNhapDTO {
    private int maCTPN;
    private int maPN;
    private int maSach;
    private int soLuong;
    private int giaNhap;

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maCTPN, int maPN, int maSach, int soLuong, int giaNhap) {
        this.maCTPN = maCTPN;
        this.maPN = maPN;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    public int getMaCTPN() {
        return maCTPN;
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

    public void setMaCTPN(int maCTPN) {
        this.maCTPN = maCTPN;
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

    @Override
    public String toString() {
        return "ChiTietPhieuNhapDTO{" + "maCTPN=" + maCTPN + ", maPN=" + maPN + ", maSach=" + maSach + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.maCTPN;
        hash = 29 * hash + this.maPN;
        hash = 29 * hash + this.maSach;
        hash = 29 * hash + this.soLuong;
        hash = 29 * hash + this.giaNhap;
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
        final ChiTietPhieuNhapDTO other = (ChiTietPhieuNhapDTO) obj;
        if (this.maCTPN != other.maCTPN) {
            return false;
        }
        if (this.maPN != other.maPN) {
            return false;
        }
        if (this.maSach != other.maSach) {
            return false;
        }
        if (this.soLuong != other.soLuong) {
            return false;
        }
        return this.giaNhap == other.giaNhap;
    }
    
}
