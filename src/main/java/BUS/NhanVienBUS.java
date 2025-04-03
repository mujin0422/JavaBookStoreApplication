package BUS;

import DTO.NhanVienDTO;
import DAO.NhanVienDAO;
import java.util.ArrayList;

public class NhanVienBUS {
    private NhanVienDAO NhanVienDAO;

    public NhanVienBUS() {
        NhanVienDAO = new NhanVienDAO();
    }

    public ArrayList<NhanVienDTO> getAllNhanVien() {
        return NhanVienDAO.getAll();
    }

    public boolean addNhanVien(NhanVienDTO nhanVien) {       
        return NhanVienDAO.add(nhanVien) > 0;
    }

    public boolean updateNhanVien(NhanVienDTO nhanVien) {
        return NhanVienDAO.update(nhanVien) > 0; 
    }

    public boolean deleteNhanVien(int maNV) {
        return NhanVienDAO.delete(maNV) > 0;  
    }
    
}
