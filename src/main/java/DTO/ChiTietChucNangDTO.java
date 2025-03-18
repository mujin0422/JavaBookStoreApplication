package DTO;


public class ChiTietChucNangDTO {
    private int maCTCN;
    private int maCN;
    private int maQuyen;

    public ChiTietChucNangDTO() {
    }

    public ChiTietChucNangDTO(int maCTCN, int maCN, int maQuyen) {
        this.maCTCN = maCTCN;
        this.maCN = maCN;
        this.maQuyen = maQuyen;
    }

    public int getMaCTCN() {
        return maCTCN;
    }

    public int getMaCN() {
        return maCN;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaCTCN(int maCTCN) {
        this.maCTCN = maCTCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.maCTCN;
        hash = 17 * hash + this.maCN;
        hash = 17 * hash + this.maQuyen;
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
        final ChiTietChucNangDTO other = (ChiTietChucNangDTO) obj;
        if (this.maCTCN != other.maCTCN) {
            return false;
        }
        if (this.maCN != other.maCN) {
            return false;
        }
        return this.maQuyen == other.maQuyen;
    }
    
    
}
