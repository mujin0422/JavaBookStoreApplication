package BUS;

import DTO.SachDTO;

import DTO.ThongKeSachDTO;

import DTO.NhaXuatBanDTO;

import DAO.SachDAO;

import java.util.ArrayList;

import java.util.List;

import java.sql.Date;

public class SachBUS {

private SachDAO sachDAO;

private NhaXuatBanBUS nhaXuatBanBus;

public SachBUS() {

sachDAO = new SachDAO();

nhaXuatBanBus = new NhaXuatBanBUS();

}

public ArrayList<SachDTO> getAllSach() {

return sachDAO.getAll();

}

public ArrayList<NhaXuatBanDTO> getAllNhaXuatBan(){

return nhaXuatBanBus.getAllNhaXuatBan();

}

public SachDTO getSachById(int id){

return sachDAO.getById(id);

}

public boolean addSach(SachDTO sach) {

return sachDAO.add(sach) > 0;

}

public boolean updateSach(SachDTO sach) {

return sachDAO.update(sach) > 0;

}

public boolean deleteSach(int maSach) {

return sachDAO.delete(maSach) > 0;

}

public int getSoLuongTonSach(int maSach){

return sachDAO.getSoLuongTonSach(maSach);

}

public boolean updateSoLuongTonSach(int maSach, int soLuongTon){

return sachDAO.updateSoLuongTonSach(maSach, soLuongTon) > 0;

}

public String getTenNxbByMaSach(int maSach){

return sachDAO.getTenNxbByMaSach(maSach);

}

public ArrayList<SachDTO> searchSach(String keyword) {

if (keyword == null || keyword.trim().isEmpty()) {

return sachDAO.getAll();

}
