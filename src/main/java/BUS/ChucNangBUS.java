package BUS;

import DAO.ChucNangDAO;
import DTO.ChucNangDTO;
import java.util.ArrayList;

public class ChucNangBUS {
    private ChucNangDAO chucNangDAO;

    public ChucNangBUS() {
        chucNangDAO = new ChucNangDAO();
    }

    public ArrayList<ChucNangDTO> getAllChucNang() {
        return chucNangDAO.getAll();
    }

    public boolean addChucNang(ChucNangDTO chucNang) {
        return chucNangDAO.add(chucNang) > 0;
    }

    public boolean updateChucNang(ChucNangDTO chucNang) {
        return chucNangDAO.update(chucNang) > 0;
    }

    public boolean deleteChucNang(int maCN) {
        return chucNangDAO.delete(maCN) > 0;
    }
}
