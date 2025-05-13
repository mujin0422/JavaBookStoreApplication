package BUS;

import DTO.NhomTheLoaiDTO;
import DAO.NhomTheLoaiDAO;
import java.util.ArrayList;

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

    public boolean deleteNhomTheLoai(int maSach) {
        return nhomTheLoaiDAO.delete(maSach) > 0;
    }
    
    public ArrayList<Integer> getMaTheLoaiByMaSach(int maSach) {
        return nhomTheLoaiDAO.getMaTheLoaiByMaSach(maSach);
    }
    
    public boolean addNhomTheLoai(int maSach, ArrayList<Integer> dsMaTL) {
        for (int maTL : dsMaTL) {
            if (!addNhomTheLoai(new NhomTheLoaiDTO(maTL, maSach))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean existsNhomTheLoai(int maTL, int maSach) {
        return nhomTheLoaiDAO.exists(maTL, maSach) > 0;
    }
    public boolean deleteNhomTheLoai(int maTL, int maSach) {
        return nhomTheLoaiDAO.delete(maTL, maSach) > 0;
    }


}
