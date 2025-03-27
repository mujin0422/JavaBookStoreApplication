package BUS;

import DAO.ChiTietPhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import java.util.ArrayList;

public class ChiTietPhieuXuatBUS {
    private ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;

    public ChiTietPhieuXuatBUS() {
        chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuat() {
        return chiTietPhieuXuatDAO.getAll();
    }

    public boolean addChiTietPhieuXuat(ChiTietPhieuXuatDTO chiTietPhieuXuat) {
        if (chiTietPhieuXuat == null || chiTietPhieuXuat.getSoLuongSP() <= 0 || chiTietPhieuXuat.getGiaBan() <= 0) {
            return false;
        }
        return chiTietPhieuXuatDAO.add(chiTietPhieuXuat) > 0;
    }

    public boolean updateChiTietPhieuXuat(ChiTietPhieuXuatDTO chiTietPhieuXuat) {
        if (chiTietPhieuXuat == null || chiTietPhieuXuat.getMaPX() <= 0 || chiTietPhieuXuat.getMaSach() <= 0) {
            return false;
        }
        return chiTietPhieuXuatDAO.update(chiTietPhieuXuat) > 0;
    }

    public boolean deleteChiTietPhieuXuat(int maPX, int maSach) {
        return chiTietPhieuXuatDAO.delete(maPX, maSach) > 0;
    }

    public ArrayList<ChiTietPhieuXuatDTO> searchChiTietPhieuXuat(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return chiTietPhieuXuatDAO.getAll();
        }
        ArrayList<ChiTietPhieuXuatDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (ChiTietPhieuXuatDTO ctpx : chiTietPhieuXuatDAO.getAll()) {
            if (String.valueOf(ctpx.getMaPX()).contains(keyword) ||
                String.valueOf(ctpx.getMaSach()).contains(keyword)) {
                ketQua.add(ctpx);
            }
        }
        return ketQua;
    }
}
