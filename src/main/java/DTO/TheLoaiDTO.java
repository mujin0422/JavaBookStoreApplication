package DTO;

import java.util.Objects;

public class TheLoaiDTO {
    private int maTL;
    private String tenTL;

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(int maTL, String tenTL) {
        this.maTL = maTL;
        this.tenTL = tenTL;
    }

    public int getMaTL() {
        return maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }

    @Override
    public String toString() {
        return "TheLoaiDTO{" + "maTL=" + maTL + ", tenTL=" + tenTL + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.maTL;
        hash = 43 * hash + Objects.hashCode(this.tenTL);
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
        final TheLoaiDTO other = (TheLoaiDTO) obj;
        if (this.maTL != other.maTL) {
            return false;
        }
        return Objects.equals(this.tenTL, other.tenTL);
    }
    
}
