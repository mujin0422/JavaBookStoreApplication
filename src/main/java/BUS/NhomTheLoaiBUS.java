package BUS;

import DTO.NhomTheLoaiDTO;
import DAO.NhomTheLoaiDAO;
import java.util.ArrayList;
/**
 *
 * @author Thanh tam
 */
public class NhomTheLoaiBUS {
    private final NhomTheLoaiDAO nhomTheLoaiDAO;

    public NhomTheLoaiBUS() {
        nhomTheLoaiDAO = new NhomTheLoaiDAO();
    }

    public ArrayList<NhomTheLoaiDTO> getAllNhomTheLoai() {
        return nhomTheLoaiDAO.getAll();
    }

    public boolean addNhomTheLoai(NhomTheLoaiDTO ntl) {
        return nhomTheLoaiDAO.add(ntl) > 0;
    }

    public boolean updateNhomTheLoai(NhomTheLoaiDTO ntl) {
        return nhomTheLoaiDAO.update(ntl) > 0;
    }

    public boolean deleteNhomTheLoai(int MaTL) {
        return nhomTheLoaiDAO.delete(MaTL) > 0;
    }
    
    public ArrayList<Integer> getMaTheLoaiBySach(int maSach) {
        return nhomTheLoaiDAO.getMaTheLoaiBySach(maSach);
    }

    public boolean deleteByMaSach(int maSach) {
        return nhomTheLoaiDAO.deleteByMaSach(maSach);
    }

    
    public boolean addNhomTheLoai(int maSach, ArrayList<Integer> dsMaTL) {
        for (int maTL : dsMaTL) {
            if (!addNhomTheLoai(new NhomTheLoaiDTO(maTL, maSach))) {
                return false;
            }
        }
        return true;
    }
}
