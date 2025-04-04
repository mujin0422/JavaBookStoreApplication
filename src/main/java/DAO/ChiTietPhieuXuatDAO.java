/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuXuatDTO;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Dell Vostro
 */
public class ChiTietPhieuXuatDAO {
     public int add(ChiTietPhieuXuatDTO obj) {
        String sql = "INSERT INTO chitietphieuxuat(maSach, maPX, soLuong, giaBan) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setInt(2, obj.getMaPX());
            ps.setInt(3, obj.getSoLuongSP());
            ps.setInt(4, obj.getGiaBan());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(ChiTietPhieuXuatDTO obj) {
        String sql = "UPDATE chitietphieuxuat SET soLuong=?, giaBan=? WHERE maSach=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getSoLuongSP());
            ps.setInt(2, obj.getGiaBan());
            ps.setInt(3, obj.getMaSach());
            ps.setInt(4, obj.getMaPX());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maSach, int maPX) {
        String sql = "DELETE FROM chitietphieuxuat WHERE maSach=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maPX);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAll() {
        ArrayList<ChiTietPhieuXuatDTO> dsChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieuxuat";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChiTiet.add(new ChiTietPhieuXuatDTO(
                    rs.getInt("maSach"),
                    rs.getInt("maPX"),
                    rs.getInt("soLuong"),
                    rs.getInt("giaBan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTiet;
    }

    public ChiTietPhieuXuatDTO getById(int maSach, int maPX) {
        String sql = "SELECT * FROM chitietphieuxuat WHERE maSach=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietPhieuXuatDTO(
                        rs.getInt("maSach"),
                        rs.getInt("maPX"),
                        rs.getInt("soLuong"),
                        rs.getInt("giaBan")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
