package DTO;

public class NhomTheLoaiDTO {
    private int maTL;
    private int maSach;

    public NhomTheLoaiDTO() {
    }

    public NhomTheLoaiDTO(int maTL, int maSach) {
        this.maTL = maTL;
        this.maSach = maSach;
    }

    public int getMaTL() {
        return maTL;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.maTL;
        hash = 79 * hash + this.maSach;
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
        if (this.maTL != other.maTL) {
            return false;
        }
        return this.maSach == other.maSach;
    }

}
