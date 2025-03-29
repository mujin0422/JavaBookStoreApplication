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

    public ArrayList<NhomTheLoaiDTO> getAllNhomTheLoai() {
        return NhomTheLoaiDAO.getAll();
    }

    public boolean addNhomTheLoai(NhomTheLoaiDTO ntl) {
        return NhomTheLoaiDAO.add(ntl) > 0;
    }

    public boolean updateNhomTheLoai(NhomTheLoaiDTO ntl) {
        return NhomTheLoaiDAO.update(ntl) > 0;
    }

    public boolean deleteNhomTheLoai(int MaTL) {
        return NhomTheLoaiDAO.delete(MaTL) > 0;
    }
}
