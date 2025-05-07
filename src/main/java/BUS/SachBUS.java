package BUS;
import DTO.SachDTO;
import DAO.SachDAO;
import DTO.ThongKeSachDTO;
import java.util.ArrayList;
import java.util.Date;

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
    
    public int getSoLuongTonSach(int maSach){
        return sachDAO.getSoLuongTonSach(maSach);
    }
    public boolean updateSoLuongTonSach(int maSach, int soLuongTon){
        return sachDAO.updateSoLuongTonSach(maSach, soLuongTon) > 0;
    }
    public String getTenNxbByMaSach(int maSach){
        return sachDAO.getTenNxbByMaSach(maSach);
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
    public ArrayList<SachDTO> filterSach(int maNXB) {
        ArrayList<SachDTO> ketQua = new ArrayList<>();
        for (SachDTO sach : sachDAO.getAll()) {
            if (sach.getMaNXB() == maNXB) {
                ketQua.add(sach);
            }
        }
        return ketQua;
    }
    
     public int getGiaSachByMaSach(int maSach) {
        return sachDAO.getGiaSachByMaSach(maSach);
    }

    public String getTenSachByMaSach(int maSach){
        return sachDAO.getTenSachByMaSach(maSach);
    }
    public ArrayList<ThongKeSachDTO> getThongKeSach(java.sql.Date ngayBatDau, java.sql.Date ngayKetThuc) {
        return sachDAO.getThongKeSach(ngayBatDau, ngayKetThuc);
    }
}
