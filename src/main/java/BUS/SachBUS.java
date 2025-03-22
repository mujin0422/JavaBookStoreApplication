package BUS;

import DTO.SachDTO;
import DAO.SachDAO;
import java.util.ArrayList;

public class SachBUS {
    private SachDAO sachDAO;

    public SachBUS() {
        sachDAO = new SachDAO();
    }

    public ArrayList<SachDTO> getAllSach() {
        return sachDAO.getALL();
    }

    public boolean addSach(SachDTO sach) {
        if (sach == null || sach.getTenSach().isEmpty() || sach.getGiaSach() <= 0 || sach.getSoLuongTon() < 0) {
            System.err.println("Dữ liệu sách không hợp lệ!");
            return false;
        }
        return sachDAO.add(sach) > 0; 
    }

    public boolean updateSach(SachDTO sach) {
        if (sach == null || sach.getMaSach() <= 0 || sach.getTenSach().isEmpty() || sach.getGiaSach() <= 0 || sach.getSoLuongTon() < 0) {
            System.err.println("Dữ liệu sách không hợp lệ!");
            return false;
        }
        return sachDAO.update(sach) > 0; 
    }

    public boolean deleteSach(int maSach) {
        if (maSach <= 0) {
            System.err.println("Mã sách không hợp lệ!");
            return false;
        }
        return sachDAO.delete(maSach) > 0;  
    }
}
