package DTO;

public class NhomTheLoaiDTO {
    private int maTL;
    private int maSach;

    public NhomTheLoaiDTO() { }

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

}
