package DTO;

import java.util.Objects;

public class TheLoaiDTO {
    private int maTL;
    private String tenTL;
    private int trangThaiXoa;

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(int maTL, String tenTL) {
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
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
}
