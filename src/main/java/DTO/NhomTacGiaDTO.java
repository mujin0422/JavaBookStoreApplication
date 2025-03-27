package DTO;

public class NhomTacGiaDTO {
    private int maTG;
    private int maSach;

    public NhomTacGiaDTO() {
    }

    public NhomTacGiaDTO(int maTG, int maSach) {
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
}
