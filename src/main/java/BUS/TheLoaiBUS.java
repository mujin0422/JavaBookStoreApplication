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

    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        return TheLoaiDAO.getAll();
    }

    public boolean addTheLoai(TheLoaiDTO TL) {      
        return TheLoaiDAO.add(TL) > 0;
    }

    public boolean updateTheLoai(TheLoaiDTO TL) {
        return TheLoaiDAO.update(TL) > 0; 
    }

    public boolean deleteTheLoai(int MaTL) {
        return TheLoaiDAO.delete(MaTL) > 0;  
    }
}

