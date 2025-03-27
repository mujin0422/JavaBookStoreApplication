package BUS;

import DTO.TheLoaiDTO;
import DAO.TheLoaiDAO;
import java.util.ArrayList;
/**
 *
 * @author Thanh tam
 */
public class TheLoaiBUS {
    private TheLoaiDAO TheLoaiDAO;

    public TheLoaiBUS() {
        TheLoaiDAO = new TheLoaiDAO();
    }

    public ArrayList<TheLoaiDTO> getAllTG() {
        return TheLoaiDAO.getAll();
    }

    public boolean addTL(TheLoaiDTO TL) {       
        if (TheLoaiDAO.exists(TL.getMaTL())) {
            return false;
        }
        if (TL == null || TL.getTenTL().isEmpty() ) {
            return false;
        }
        return TheLoaiDAO.add(TL) > 0;
    }

    public boolean updateTL(TheLoaiDTO TL) {
        if (TL == null || TL.getMaTL() <= 0 || TL.getTenTL().isEmpty() ) {
            return false;
        }
        return TheLoaiDAO.update(TL) > 0; 
    }

    public boolean deleteTL(int MaTL) {
        return TheLoaiDAO.delete(MaTL) > 0;  
    }
    
    public ArrayList<TheLoaiDTO> searchSach(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return TheLoaiDAO.getAll();
        }
        ArrayList<TheLoaiDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<TheLoaiDTO> danhSach = TheLoaiDAO.getAll();
        if (danhSach != null) {
            for (TheLoaiDTO TL : danhSach) {
                if (TL.getTenTL().toLowerCase().contains(keyword)) {
                    ketQua.add(TL);
                }
            }
        }
        return ketQua;
    }

}

