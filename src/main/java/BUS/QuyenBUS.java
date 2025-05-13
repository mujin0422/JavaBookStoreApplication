package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import java.util.ArrayList;

public class QuyenBUS {
    private QuyenDAO quyenDAO;
    
    public QuyenBUS(){
        quyenDAO = new QuyenDAO();
    }
    public ArrayList<QuyenDTO> getAllQuyen(){
        return quyenDAO.getAll();
    }
    public QuyenDTO getById(int id){
        return quyenDAO.getById(id);
    }
    public boolean addQuyen(QuyenDTO quyen){
        return quyenDAO.add(quyen) > 0;
    }
    public boolean updateQuyen(QuyenDTO quyen){
        return quyenDAO.update(quyen) > 0;
    }
    public boolean deleteQuyen(int maQuyen){
        return quyenDAO.delete(maQuyen) > 0;
    }
    public String getNextMaQuyen(){
        return quyenDAO.getNextMaQuyen();
    }
    
    public int getMaQuyenByTenQuyen(String tenQuyen) {
        return quyenDAO.getMaQuyenByTenQuyen(tenQuyen);
    }
    public int getSoLuongNhanVienHasQuyen(int maQuyen) {
        return quyenDAO.getSoLuongNhanVienHasQuyen(maQuyen);
    }
}
