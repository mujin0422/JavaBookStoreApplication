package BUS;
import DTO.SachDTO;
import DAO.SachDAO;
import DTO.ThongKeSachDTO;
import java.util.ArrayList;

public class SachBUS {
    private SachDAO sachDAO;

    public SachBUS() {
        sachDAO = new SachDAO();
    }
    public ArrayList<SachDTO> getAllSach() {
        return sachDAO.getAll();
    }
    public SachDTO getById(int id){
        return sachDAO.getById(id);
    }
    public boolean addSach(SachDTO sach) {       
        return sachDAO.add(sach) > 0;
    }
    public boolean updateSach(SachDTO sach) {
        return sachDAO.update(sach) > 0; 
    }
    public boolean deleteSach(int maSach) {
        return sachDAO.delete(maSach) > 0;  
    }
    public String getNextMaSach(){
        return sachDAO.getNextMaSach();
    }
    
    public boolean updateSoLuongTonSach(int maSach, int soLuongTon){
        return sachDAO.updateSoLuongTonSach(maSach, soLuongTon) > 0;
    }
    
    public ArrayList<SachDTO> searchSach(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return sachDAO.getAll();
        }
        ArrayList<SachDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<SachDTO> danhSach = sachDAO.getAll();
        if (danhSach != null) {
            for (SachDTO sach : danhSach) {
                if (sach.getTenSach().toLowerCase().contains(keyword))
                    ketQua.add(sach);
            }
        }
        return ketQua;
    }
    
    public ArrayList<ThongKeSachDTO> getThongKeSach(java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
        return sachDAO.getThongKeSach(ngayBatDau, ngayKetThuc);
    }
}
