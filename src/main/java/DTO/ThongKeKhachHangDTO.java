package DTO;

public class ThongKeKhachHangDTO {
    private int maKH;
    private String tenKH;
    private int soLanMua;
    private double tongTienDaMua;

    public ThongKeKhachHangDTO() {
    }

    public ThongKeKhachHangDTO(int maKH, String tenKH, int soLanMua, double tongTienDaMua) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soLanMua = soLanMua;
        this.tongTienDaMua = tongTienDaMua;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSoLanMua() {
        return soLanMua;
    }

    public void setSoLanMua(int soLanMua) {
        this.soLanMua = soLanMua;
    }

    public double getTongTienDaMua() {
        return tongTienDaMua;
    }

    public void setTongTienDaMua(double tongTienDaMua) {
        this.tongTienDaMua = tongTienDaMua;
    }
    
}
