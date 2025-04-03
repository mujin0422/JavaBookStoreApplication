package DTO;


public class ChiTietChucNangDTO {
    private int maCN;
    private int maQuyen;

    public ChiTietChucNangDTO() {
    }

    public ChiTietChucNangDTO(int maCN, int maQuyen) {
        this.maCN = maCN;
        this.maQuyen = maQuyen;
    }

    public int getMaCN() {
        return maCN;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }
}
