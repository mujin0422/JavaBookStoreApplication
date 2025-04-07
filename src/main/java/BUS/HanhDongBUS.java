/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HanhDongDAO;
import DTO.HanhDongDTO;
import java.util.ArrayList;

/**
 *
 * @author Dell Vostro
 */
public class HanhDongBUS {
    private HanhDongDAO hanhDongDAO;
    
    public HanhDongBUS(){
        hanhDongDAO = new HanhDongDAO();
    }
    
    public ArrayList<HanhDongDTO> getAllHanhDong(){
        return hanhDongDAO.getAll();
    }
    
    public boolean addHanhDong(HanhDongDTO hanhDong){
        return hanhDongDAO.add(hanhDong) > 0;
    }
    
    public boolean deleteHanhDong(String maHD){
        return hanhDongDAO.delete(maHD) > 0;
    }
}
