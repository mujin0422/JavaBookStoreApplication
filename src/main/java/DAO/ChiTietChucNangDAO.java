package DAO;

import DTO.ChiTietChucNangDTO;
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

public class ChiTietChucNangDAO {
    
    public int add(ChiTietChucNangDTO obj) {
        String sql = "INSERT INTO chitietchucnang (maCN, maQuyen, maHD) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaCN());
            ps.setInt(2, obj.getMaQuyen());
            ps.setString(3, obj.getMaHD());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(ChiTietChucNangDTO obj) {
        String sql = "UPDATE chitietchucnang SET maCN=? maHD=? WHERE maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaCN());
            ps.setString(2, obj.getMaHD());
            ps.setInt(3, obj.getMaQuyen());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maQuyen) {
        String sql = "UPDATE chitietchucnang trangThaiXoa=1 WHERE maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maQuyen);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietChucNangDTO> getAll() {
        ArrayList<ChiTietChucNangDTO> dsChiTietCN = new ArrayList<>();
        String sql = "SELECT * FROM chitietchucnang WHERE trangThaiXoa=0 ";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChiTietCN.add(new ChiTietChucNangDTO(
                    rs.getInt("maCN"),
                    rs.getInt("maQuyen"),
                    rs.getString("maHD")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietCN;
    }

}

