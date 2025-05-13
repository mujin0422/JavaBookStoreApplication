package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    }
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        return chiTietPhieuNhapDAO.getAll();
    }
    public boolean addChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.add(chiTietPhieuNhap) > 0;
    }
    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.update(chiTietPhieuNhap) > 0;
    }
    public boolean deleteChiTietPhieuNhap(int maPN, int maSach) {
        return chiTietPhieuNhapDAO.delete(maPN, maSach) > 0;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhapByMaPn(int maPn){
        return chiTietPhieuNhapDAO.getAllChiTietPhieuNhapByMaPn(maPn);
    }
}
