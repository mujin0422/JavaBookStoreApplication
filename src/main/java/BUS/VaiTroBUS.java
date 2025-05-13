package BUS;

import DAO.VaiTroDAO;
import DTO.VaiTroDTO;
import java.util.ArrayList;

public class VaiTroBUS {
    private VaiTroDAO vaiTroDAO;
    public VaiTroBUS (){
        vaiTroDAO = new VaiTroDAO();
    }
    
    public ArrayList<VaiTroDTO> getAllVaiTro(){
        return vaiTroDAO.getAll();
    }
    public VaiTroDTO getById(int id) {
        return vaiTroDAO.getById(id);
    }
    public boolean addVaiTro(VaiTroDTO obj) {
        return vaiTroDAO.add(obj) > 0;
    }
    public boolean updateVaiTro(VaiTroDTO obj) {
        return vaiTroDAO.update(obj) > 0;
    }
    public boolean deleteVaiTro(int maVT) {
        return vaiTroDAO.delete(maVT) > 0 ;
    }
    public String getNextMaVt() {
        return vaiTroDAO.getNextMaVt();
    }
    
    public int getSoLuongNhanVienHasVaiTro(int maVT) {
        return vaiTroDAO.getSoLuongNhanVienHasVaiTro(maVT);
    }
}
