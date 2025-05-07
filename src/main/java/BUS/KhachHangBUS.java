package BUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.ThongKeKhachHangDTO;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangBUS {
    private KhachHangDAO khachHangDAO;
   
    public KhachHangBUS() {
        khachHangDAO = new KhachHangDAO();
    }
    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return khachHangDAO.getAll();
    }
    public KhachHangDTO getById(int maKh){
        return khachHangDAO.getById(maKh);
    }
    public boolean addKhachHang(KhachHangDTO khachHang) {
        return khachHangDAO.add(khachHang) > 0;
    }
    public boolean updateKhachHang(KhachHangDTO khachHang) {
        return khachHangDAO.update(khachHang) > 0;
    }
    public boolean deleteKhachHang(int maKH) {
        return khachHangDAO.delete(maKH) > 0;
    }
    public String getNextMaKh(){
        return khachHangDAO.getNextMaKH();
    }
    public boolean existsSDT(String std) {
        return khachHangDAO.existsSDT(std);
    }
    
    public KhachHangDTO getKhBySDT(String sdt) {
        return khachHangDAO.getKhBySDT(sdt);
    }
    public String getTenKhByMaKh(int maKh) {
        return khachHangDAO.getTenKhByMaKh(maKh);
    }

    public ArrayList<KhachHangDTO> searchKhachHang(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return khachHangDAO.getAll();
        }
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        ArrayList<KhachHangDTO> ds = khachHangDAO.getAll();
        if(ds != null){
            for(KhachHangDTO kh : ds){
                if(kh.getTenKH().toLowerCase().contains(keyword)) 
                    ketQua.add(kh);      
            }
        }
        return ketQua;
    }
    
    public ArrayList<ThongKeKhachHangDTO> thongKeKhachHangTheoNgay(Date fromDate, Date toDate) {
        return khachHangDAO.thongKeKhachHangTheoNgay(fromDate, toDate);
    }
}



