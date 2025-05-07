package BUS;

import DTO.TacGiaDTO;
import DAO.TacGiaDAO;
import java.util.ArrayList;

public class TacGiaBUS {
    private TacGiaDAO tacGiaDAO;

    public TacGiaBUS() {
        tacGiaDAO = new TacGiaDAO();
    }

    public ArrayList<TacGiaDTO> getAllTacGia() {
        return tacGiaDAO.getAll();
    }
    public TacGiaDTO getById(int id){
        return tacGiaDAO.getById(id);
    }
    public boolean addTacGia(TacGiaDTO TG) {       
        return tacGiaDAO.add(TG) > 0;
    }
    public boolean updateTacGia(TacGiaDTO TG) {
        return tacGiaDAO.update(TG) > 0; 
    }
    public boolean deleteTacGia(int maTG) {
        return tacGiaDAO.delete(maTG) > 0;  
    }
    public String getNextMaTg(){
        return tacGiaDAO.getNextMaTg();
    }
    
    public ArrayList<String> getTacGiaByMaSach(int maSach) {
        ArrayList<String> danhSachTenTG = new ArrayList<>();
        TacGiaDAO tacGiaDAO = new TacGiaDAO();

        ArrayList<Integer> danhSachMaTG = tacGiaDAO.getMaTacGiaBySach(maSach);
        for (int maTG : danhSachMaTG) {
            String tenTG = tacGiaDAO.getTenTacGiaById(maTG);
            danhSachTenTG.add(tenTG);
        }
        return danhSachTenTG;
    }
    
    public int getMaTgByTenTg(String tenTg){
        return tacGiaDAO.getMaTgByTenTg(tenTg);
    }
    
    public String getTenTacGiaById(int maTG){
        return tacGiaDAO.getTenTacGiaById(maTG);
    }
}
