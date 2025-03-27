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
}
