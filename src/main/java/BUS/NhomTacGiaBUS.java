package BUS;

import DTO.NhomTacGiaDTO;
import DAO.NhomTacGiaDAO;
import java.util.ArrayList;

public class NhomTacGiaBUS {
    private final NhomTacGiaDAO nhomTacGiaDAO;

    public NhomTacGiaBUS() {
        nhomTacGiaDAO = new NhomTacGiaDAO();
    }

    // Retrieve all author groups
    public ArrayList<NhomTacGiaDTO> getAllTG() {
        return nhomTacGiaDAO.getAll();
    }

    // Add a new author group
    public boolean addTG(NhomTacGiaDTO ntg) {
        return nhomTacGiaDAO.add(ntg) > 0;
    }

    // Update an existing author group
    public boolean updateNTG(NhomTacGiaDTO ntg) {
        return nhomTacGiaDAO.update(ntg) > 0;
    }

    // Delete an author group
    public boolean deleteNTG(int maTG) {
        return nhomTacGiaDAO.deleteTG(maTG) > 0;
    }
}
