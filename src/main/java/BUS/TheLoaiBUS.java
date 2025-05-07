package BUS;

import DTO.TheLoaiDTO;
import DAO.TheLoaiDAO;
import java.util.ArrayList;

public class TheLoaiBUS {
    private TheLoaiDAO theLoaiDAO;

    public TheLoaiBUS() {
        theLoaiDAO = new TheLoaiDAO();
    }

    public ArrayList<TheLoaiDTO> getAllTheLoai() {
        return theLoaiDAO.getAll();
    }
    public TheLoaiDTO getById(int id) {
        return theLoaiDAO.getById(id);
    }
    public boolean addTheLoai(TheLoaiDTO TL) {      
        return theLoaiDAO.add(TL) > 0;
    }
    public boolean updateTheLoai(TheLoaiDTO TL) {
        return theLoaiDAO.update(TL) > 0; 
    }
    public boolean deleteTheLoai(int MaTL) {
        return theLoaiDAO.delete(MaTL) > 0;  
    }
    public String getNextMaTl(){
        return theLoaiDAO.getNextMaTl();
    }
    
    public String getTenTheLoaiById(int maTL){
        return theLoaiDAO.getTenTheLoaiById(maTL);
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
        return theLoaiDAO.getMaTlByTenTl(tenTl);
    }
}

