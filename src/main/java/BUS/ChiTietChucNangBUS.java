package BUS;

import DAO.ChiTietChucNangDAO;
import DTO.ChiTietChucNangDTO;
import java.util.ArrayList;

public class ChiTietChucNangBUS {
    private ChiTietChucNangDAO chiTietChucNangDAO;

    public ChiTietChucNangBUS() {
        chiTietChucNangDAO = new ChiTietChucNangDAO();
    }

    public ArrayList<ChiTietChucNangDTO> getAllChiTietChucNang() {
        return chiTietChucNangDAO.getAll();
    }

    public boolean addChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.add(chiTietChucNang) > 0;
    }

    public boolean updateChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.update(chiTietChucNang) > 0;
    }

    public boolean deleteChiTietChucNang(int maQuyen) {
        return chiTietChucNangDAO.delete( maQuyen) > 0;
    }
}
