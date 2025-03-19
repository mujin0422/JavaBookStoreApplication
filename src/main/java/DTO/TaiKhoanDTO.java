package DTO;

import java.util.Objects;


public class TaiKhoanDTO {
    private String tenDangNhap;
    private String matKhau;
    private int maNV;
    private int maQuyen;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String tenDangNhap, String matKhau, int maNV, int maQuyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.maQuyen = maQuyen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.tenDangNhap);
        hash = 23 * hash + Objects.hashCode(this.matKhau);
        hash = 23 * hash + this.maNV;
        hash = 23 * hash + this.maQuyen;
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
        final TaiKhoanDTO other = (TaiKhoanDTO) obj;
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.maQuyen != other.maQuyen) {
            return false;
        }
        if (!Objects.equals(this.tenDangNhap, other.tenDangNhap)) {
            return false;
        }
        return Objects.equals(this.matKhau, other.matKhau);
    }

    
}
