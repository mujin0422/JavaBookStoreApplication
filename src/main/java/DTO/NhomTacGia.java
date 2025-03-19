package DTO;

public class NhomTacGia {
    private int maTG;
    private int maSach;

    public NhomTacGia() {
    }

    public NhomTacGia(int maTG, int maSach) {
        this.maTG = maTG;
        this.maSach = maSach;
    }

    public int getMaTG() {
        return maTG;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.maTG;
        hash = 31 * hash + this.maSach;
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
        final NhomTacGia other = (NhomTacGia) obj;
        if (this.maTG != other.maTG) {
            return false;
        }
        return this.maSach == other.maSach;
    }

 
}
