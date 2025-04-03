package BUS;

import DTO.NhomTacGiaDTO;
import DAO.NhomTacGiaDAO;
import java.util.ArrayList;

public class NhomTacGiaBUS {
    private final NhomTacGiaDAO nhomTacGiaDAO;

    public NhomTacGiaBUS() {
        nhomTacGiaDAO = new NhomTacGiaDAO();
    }

    public ArrayList<NhomTacGiaDTO> getAllNhomTacGia() {
        return nhomTacGiaDAO.getAll();
    }

    public boolean addNhomTacGia(NhomTacGiaDTO ntg) {
        return nhomTacGiaDAO.add(ntg) > 0;
    }

    public boolean updateNhomTacGia(NhomTacGiaDTO ntg) {
        return nhomTacGiaDAO.update(ntg) > 0;
    }

    public boolean deleteNhomTacGia(int maTG) {
        return nhomTacGiaDAO.delete(maTG) > 0;
    }
    
    public ArrayList<Integer> getMaTacGiaBySach(int maSach) {
        return nhomTacGiaDAO.getMaTacGiaBySach(maSach);
    }

    public boolean deleteByMaSach(int maSach) {
        return nhomTacGiaDAO.deleteByMaSach(maSach);
    }

    
    public boolean addNhomTacGia(int maSach, ArrayList<Integer> dsMaTG) {
        for (int maTG : dsMaTG) {
            if (!addNhomTacGia(new NhomTacGiaDTO(maTG, maSach))) {
                return false;
            }
        }
        return true;
    }
}
