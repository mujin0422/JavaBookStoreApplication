package DTO;

public class NhomTheLoaiDTO {
    private int maNhomTL;
    private int maTL;
    private int maSach;

    public NhomTheLoaiDTO() {
    }

    public NhomTheLoaiDTO(int maNhomTL, int maTL, int maSach) {
        this.maNhomTL = maNhomTL;
        this.maTL = maTL;
        this.maSach = maSach;
    }

    public int getMaNhomTL() {
        return maNhomTL;
    }

    public int getMaTL() {
        return maTL;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaNhomTL(int maNhomTL) {
        this.maNhomTL = maNhomTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    @Override
    public String toString() {
        return "NhomTheLoaiDTO{" + "maNhomTL=" + maNhomTL + ", maTL=" + maTL + ", maSach=" + maSach + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.maNhomTL;
        hash = 83 * hash + this.maTL;
        hash = 83 * hash + this.maSach;
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
        final NhomTheLoaiDTO other = (NhomTheLoaiDTO) obj;
        if (this.maNhomTL != other.maNhomTL) {
            return false;
        }
        if (this.maTL != other.maTL) {
            return false;
        }
        return this.maSach == other.maSach;
    }
    
    
}
