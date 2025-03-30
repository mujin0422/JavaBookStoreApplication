package BUS;

import DTO.SachDTO;
import DTO.NhaXuatBanDTO;
import DAO.SachDAO;
import java.util.ArrayList;

public class SachBUS {
    private SachDAO sachDAO;
    private NhaXuatBanBUS nhaXuatBanBus;

    public SachBUS() {
        sachDAO = new SachDAO();
        nhaXuatBanBus = new NhaXuatBanBUS();
    }

    public ArrayList<SachDTO> getAllSach() {
        return sachDAO.getAll();
    }
    
    public ArrayList<NhaXuatBanDTO> getAllNhaXuatBan(){
        return nhaXuatBanBus.getAllNhaXuatBan();
    }
    
    public boolean addSach(SachDTO sach) {       
        if (sachDAO.exists(sach.getMaSach())) {
            return false;
        }
        if (sach == null || sach.getTenSach().isEmpty() || sach.getGiaSach() <= 10000 || sach.getSoLuongTon() < 0) {
            return false;
        }
        return sachDAO.add(sach) > 0;
    }

    public boolean updateSach(SachDTO sach) {
        if (sach == null || sach.getMaSach() <= 0 || sach.getTenSach().isEmpty() || sach.getGiaSach() <= 0 || sach.getSoLuongTon() < 0) {
            return false;
        }
        return sachDAO.update(sach) > 0; 
    }

    public boolean deleteSach(int maSach) {
        return sachDAO.delete(maSach) > 0;  
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
                if (sach.getTenSach().toLowerCase().contains(keyword)) {
                    ketQua.add(sach);
                }
            }
        }
        return ketQua;
    }

}
