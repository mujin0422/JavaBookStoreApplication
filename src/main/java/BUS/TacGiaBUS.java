package BUS;

import DTO.TacGiaDTO;
import DAO.TacGiaDAO;
import java.util.ArrayList;

public class TacGiaBUS {
    private TacGiaDAO TacGiaDAO;

    public TacGiaBUS() {
        TacGiaDAO = new TacGiaDAO();
    }

    public ArrayList<TacGiaDTO> getAllTacGia() {
        return TacGiaDAO.getAll();
    }

    public boolean addTacGia(TacGiaDTO TG) {       
        return TacGiaDAO.add(TG) > 0;
    }

    public boolean updateTacGia(TacGiaDTO TG) {
        return TacGiaDAO.update(TG) > 0; 
    }

    public boolean deleteTacGia(int maTG) {
        return TacGiaDAO.delete(maTG) > 0;  
    }
    
    
}
