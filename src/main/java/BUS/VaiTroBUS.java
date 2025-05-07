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
    public int addVaiTro(VaiTroDTO obj) {
        return vaiTroDAO.add(obj);
    }
    public int updateVaiTro(VaiTroDTO obj) {
        return vaiTroDAO.update(obj);
    }
    public int deleteVaiTro(int maVT) {
        return vaiTroDAO.delete(maVT);
    }
    public String getNextMaVt() {
        return vaiTroDAO.getNextMaVt();
    }
    
    public String getTenVtByMaVt(int maVt) {
        return vaiTroDAO.getTenVtByMaVt(maVt);
    }
}
