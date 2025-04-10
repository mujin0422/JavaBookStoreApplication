package DAO;

import DTO.ChiTietPhieuNhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhapByMaPn(int maPN) {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap WHERE maPN = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(
                        rs.getInt("maPN"),
                        rs.getInt("maSach"),
                        rs.getInt("soLuong"),
                        rs.getInt("giaNhap")
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
