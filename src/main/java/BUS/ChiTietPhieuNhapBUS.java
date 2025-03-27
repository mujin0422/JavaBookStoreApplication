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
        if (chiTietPhieuNhap == null || chiTietPhieuNhap.getSoLuong() <= 0 || chiTietPhieuNhap.getGiaNhap() <= 0) {
            return false;
        }
        return chiTietPhieuNhapDAO.add(chiTietPhieuNhap) > 0;
    }

    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        if (chiTietPhieuNhap == null || chiTietPhieuNhap.getMaPN() <= 0 || chiTietPhieuNhap.getMaSach() <= 0) {
            return false;
        }
        return chiTietPhieuNhapDAO.update(chiTietPhieuNhap) > 0;
    }

    public boolean deleteChiTietPhieuNhap(int maPN, int maSach) {
        if (maPN <= 0 || maSach <= 0) {
            return false;
        }
        return chiTietPhieuNhapDAO.delete(maPN, maSach) > 0;
    }

    public ArrayList<ChiTietPhieuNhapDTO> searchChiTietPhieuNhap(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return chiTietPhieuNhapDAO.getAll();
        }
        ArrayList<ChiTietPhieuNhapDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (ChiTietPhieuNhapDTO ctpn : chiTietPhieuNhapDAO.getAll()) {
            if (String.valueOf(ctpn.getMaPN()).contains(keyword) ||
                String.valueOf(ctpn.getMaSach()).contains(keyword)) {
                ketQua.add(ctpn);
            }
        }
        return ketQua;
    }
}
