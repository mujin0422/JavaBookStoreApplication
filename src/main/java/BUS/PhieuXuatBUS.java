package BUS;

import DTO.PhieuXuatDTO;
import DAO.PhieuXuatDAO;
import java.util.ArrayList;

public class PhieuXuatBUS {
    private PhieuXuatDAO phieuXuatDAO;

    public PhieuXuatBUS() {
        phieuXuatDAO = new PhieuXuatDAO();
    }

    public ArrayList<PhieuXuatDTO> getAllPhieuXuat() {
        return phieuXuatDAO.getAll();
    }

    public boolean addPhieuXuat(PhieuXuatDTO phieuXuat) {       
        return phieuXuatDAO.add(phieuXuat) > 0;
    }

    public boolean updatePhieuXuat(PhieuXuatDTO phieuXuat) {
        return phieuXuatDAO.update(phieuXuat) > 0; 
    }

    public boolean deletePhieuXuat(int maPX) {
        return phieuXuatDAO.delete(maPX) > 0;  
    }
    
    public boolean existsPhieuXuat(int maPX){
        return phieuXuatDAO.exists(maPX) > 0;
    }
    
    public ArrayList<PhieuXuatDTO> searchPhieuXuat(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return phieuXuatDAO.getAll();
        }
        ArrayList<PhieuXuatDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        for (PhieuXuatDTO px : phieuXuatDAO.getAll()) {
            if (String.valueOf(px.getMaKH()).contains(keyword)) { 
                ketQua.add(px);
            }
        }
        return ketQua;
    }

    public PhieuXuatDTO getById(int maPX){
        return phieuXuatDAO.getById(maPX);
    }
    
    public int countPhieuXuatByMaKh(int maKh){
        return phieuXuatDAO.countPhieuXuatByMaKh(maKh);
    }
}
