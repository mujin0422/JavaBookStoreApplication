package BUS;

import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import java.util.ArrayList;

public class NhaXuatBanBUS {
    private NhaXuatBanDAO nhaXuatBanDAO;

    public NhaXuatBanBUS() {
        this.nhaXuatBanDAO = new NhaXuatBanDAO();
    }

    public ArrayList<NhaXuatBanDTO> getAllNhaXuatBan() {
        return nhaXuatBanDAO.getAll();
    }

    public NhaXuatBanDTO getNhaXuatBanById(int id) {
        return nhaXuatBanDAO.getById(id);
    }

    public boolean addNhaXuatBan(NhaXuatBanDTO nxb) {
        
        return nhaXuatBanDAO.add(nxb) > 0;
    }

    public boolean updateNhaXuatBan(NhaXuatBanDTO nxb) {
        return nhaXuatBanDAO.update(nxb) > 0;
    }

    public boolean deleteNhaXuatBan(int maNXB) {
        return nhaXuatBanDAO.delete(maNXB) > 0;
    }
}
