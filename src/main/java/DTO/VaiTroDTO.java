package DTO;

public class VaiTroDTO {
    private int maVT;
    private String tenVT;
    private int trangThaiXoa;

    public VaiTroDTO() { }

    public VaiTroDTO(int maVT, String tenVT) {
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.trangThaiXoa = 0;
    }

    public int getMaVT() {
        return maVT;
    }

    public void setMaVT(int maVT) {
        this.maVT = maVT;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
    
    
}
