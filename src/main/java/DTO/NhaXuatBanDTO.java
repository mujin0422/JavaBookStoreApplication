package DTO;

import java.util.Objects;

public class NhaXuatBanDTO {
    private int maNXB;
    private String tenNXB;

    public NhaXuatBanDTO() {
    }

    public NhaXuatBanDTO(int maNXB, String tenNXB) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    @Override
    public String toString() {
        return "NhaXuatBanDTO{" + "maNXB=" + maNXB + ", tenNXB=" + tenNXB + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.maNXB;
        hash = 13 * hash + Objects.hashCode(this.tenNXB);
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
        final NhaXuatBanDTO other = (NhaXuatBanDTO) obj;
        if (this.maNXB != other.maNXB) {
            return false;
        }
        return Objects.equals(this.tenNXB, other.tenNXB);
    }
    
}
