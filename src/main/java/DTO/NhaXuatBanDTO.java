package DTO;

public class NhaXuatBanDTO {
    private int maNXB;
    private String tenNXB;
    private int trangThaiXoa;

    public NhaXuatBanDTO() { }

    public NhaXuatBanDTO(int maNXB, String tenNXB) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.trangThaiXoa = 0;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
}
