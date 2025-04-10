package DTO;

public class TacGiaDTO {
    private int maTG;
    private String tenTG;
    private int trangThaiXoa;

    public TacGiaDTO() { }

    public TacGiaDTO(int maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
        this.trangThaiXoa = 0;
    }

    public int getMaTG() {
        return maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }
}
