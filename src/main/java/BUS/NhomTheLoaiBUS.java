package BUS;

import DTO.NhomTheLoaiDTO;
import DAO.NhomTheLoaiDAO;
import java.util.ArrayList;
/**
 *
 * @author Thanh tam
 */
public class NhomTheLoaiBUS {
    private final NhomTheLoaiDAO NhomTheLoaiDAO;

    public NhomTheLoaiBUS() {
        NhomTheLoaiDAO = new NhomTheLoaiDAO();
    }

    // Retrieve all author groups
    public ArrayList<NhomTheLoaiDTO> getAllTL() {
        return NhomTheLoaiDAO.getAll();
    }

    // Add a new author group
    public boolean addNTL(NhomTheLoaiDTO ntl) {
        return NhomTheLoaiDAO.add(ntl) > 0;
    }

    // Update an existing author group
    public boolean updateNTL(NhomTheLoaiDTO ntl) {
        return NhomTheLoaiDAO.update(ntl) > 0;
    }

    // Delete an author group
    public boolean deleteNTL(int MaTL) {
        return NhomTheLoaiDAO.delete(MaTL) > 0;
    }
}
