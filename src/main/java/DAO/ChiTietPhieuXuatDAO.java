package DAO;

import DTO.ChiTietPhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChiTietPhieuXuatDAO {
     public int add(ChiTietPhieuXuatDTO obj) {
        String sql = "INSERT INTO chitietphieuxuat(maSach, maPX, soLuong, giaBan) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setInt(2, obj.getMaPX());
            ps.setInt(3, obj.getSoLuong());
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
            ps.setInt(1, obj.getSoLuong());
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
    
    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuatByMaPx(int maPX) {
        ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieuxuat WHERE maPX = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO(
                        rs.getInt("maPX"),
                        rs.getInt("maSach"),
                        rs.getInt("soLuong"),
                        rs.getInt("giaBan")
                    );
                    list.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
