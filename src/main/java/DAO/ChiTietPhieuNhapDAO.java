/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell Vostro
 */
public class ChiTietPhieuNhapDAO {
    public int add(ChiTietPhieuNhapDTO obj) {
        String sql = "INSERT INTO chitietphieunhap(maSach, maPN, giaNhap, soLuong) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setInt(2, obj.getMaPN());
            ps.setInt(3, obj.getGiaNhap());
            ps.setInt(4, obj.getSoLuong());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(ChiTietPhieuNhapDTO obj) {
        String sql = "UPDATE chitietphieunhap SET giaNhap=?, soLuong=? WHERE maSach=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getGiaNhap());
            ps.setInt(2, obj.getSoLuong());
            ps.setInt(3, obj.getMaSach());
            ps.setInt(4, obj.getMaPN());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maSach, int maPN) {
        String sql = "DELETE FROM chitietphieunhap WHERE maSach=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maPN);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getAll() {
        ArrayList<ChiTietPhieuNhapDTO> dsChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChiTiet.add(new ChiTietPhieuNhapDTO(
                    rs.getInt("maSach"),
                    rs.getInt("maPN"),
                    rs.getInt("giaNhap"),
                    rs.getInt("soLuong")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    public ChiTietPhieuNhapDTO getById(int maSach, int maPN) {
        String sql = "SELECT * FROM chitietphieunhap WHERE maSach=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietPhieuNhapDTO(
                        rs.getInt("maSach"),
                        rs.getInt("maPN"),
                        rs.getInt("giaNhap"),
                        rs.getInt("soLuong")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
