package DTO;

public class HanhDongDTO {
    private String maHD;
    private int trangThaiXoa;

    public HanhDongDTO(String maHD) {
        this.maHD = maHD;
        this.trangThaiXoa = 0;
    }
 
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
    
    
}
