package DTO;

public class NhomTacGia {
    private int maNhomTG;
    private int maTG;
    private int maSach;

    public NhomTacGia() {
    }

    public NhomTacGia(int maNhomTG, int maTG, int maSach) {
        this.maNhomTG = maNhomTG;
        this.maTG = maTG;
        this.maSach = maSach;
    }

    public int getMaNhomTG() {
        return maNhomTG;
    }

    public int getMaTG() {
        return maTG;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaNhomTG(int maNhomTG) {
        this.maNhomTG = maNhomTG;
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
        hash = 97 * hash + this.maNhomTG;
        hash = 97 * hash + this.maTG;
        hash = 97 * hash + this.maSach;
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
        if (this.maNhomTG != other.maNhomTG) {
            return false;
        }
        if (this.maTG != other.maTG) {
            return false;
        }
        return this.maSach == other.maSach;
    }
    
    
}
