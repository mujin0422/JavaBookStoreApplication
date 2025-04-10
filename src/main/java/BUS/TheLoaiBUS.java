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
    public String getTenTheLoaiById(int maTL){
        return TheLoaiDAO.getTenTheLoaiById(maTL);
    }
    
    public ArrayList<String> getTheLoaiByMaSach(int maSach) {
        ArrayList<String> danhSachTenTL = new ArrayList<>();
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

        ArrayList<Integer> danhSachMaTL = theLoaiDAO.getMaTheLoaiBySach(maSach);
        for (int maTL : danhSachMaTL) {
            String tenTL = theLoaiDAO.getTenTheLoaiById(maTL);
            danhSachTenTL.add(tenTL);
        }
        return danhSachTenTL;
    }
    
    public int getMaTlByTenTl(String tenTl){
        return TheLoaiDAO.getMaTlByTenTl(tenTl);
    }
}

