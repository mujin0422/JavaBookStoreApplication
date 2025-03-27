package BUS;

import DTO.TacGiaDTO;
import DAO.TacGiaDAO;
import java.util.ArrayList;

public class TacGiaBUS {
    private TacGiaDAO TacGiaDAO;

    public TacGiaBUS() {
        TacGiaDAO = new TacGiaDAO();
    }

    public ArrayList<TacGiaDTO> getAllTG() {
        return TacGiaDAO.getAll();
    }

    public boolean addTG(TacGiaDTO TG) {       
        if (TacGiaDAO.exists(TG.getMaTG())) {
            return false;
        }
        if (TG == null || TG.getTenTG().isEmpty() ) {
            return false;
        }
        return TacGiaDAO.add(TG) > 0;
    }

    public boolean updateTG(TacGiaDTO TG) {
        if (TG == null || TG.getMaTG() <= 0 || TG.getTenTG().isEmpty() ) {
            return false;
        }
        return TacGiaDAO.update(TG) > 0; 
    }

    public boolean deleteTG(int maTG) {
        return TacGiaDAO.delete(maTG) > 0;  
    }
    
    public ArrayList<TacGiaDTO> searchSach(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return TacGiaDAO.getAll();
        }
        ArrayList<TacGiaDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<TacGiaDTO> danhSach = TacGiaDAO.getAll();
        if (danhSach != null) {
            for (TacGiaDTO TG : danhSach) {
                if (TG.getTenTG().toLowerCase().contains(keyword)) {
                    ketQua.add(TG);
                }
            }
        }
        return ketQua;
    }

}
