package DTO;

import java.util.Objects;


public class NhanVienDTO {
    private int maNV;
    private int tenNV;
    private String email;
    private String sdt;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int maNV, int tenNV, String email, String sdt) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.email = email;
        this.sdt = sdt;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getTenNV() {
        return tenNV;
    }

    public String getEmail() {
        return email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(int tenNV) {
        this.tenNV = tenNV;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.maNV;
        hash = 71 * hash + this.tenNV;
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.sdt);
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
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.tenNV != other.tenNV) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sdt, other.sdt);
    }

}
