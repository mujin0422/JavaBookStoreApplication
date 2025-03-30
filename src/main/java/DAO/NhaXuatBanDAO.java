package DAO;

import DTO.NhaXuatBanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhaXuatBanDAO {

    public int add(NhaXuatBanDTO obj) {
        String sql = "INSERT INTO nhaxuatban (maNXB, tenNXB) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNXB());
            ps.setString(2, obj.getTenNXB());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(NhaXuatBanDTO obj) {
        String sql = "UPDATE nhaxuatban SET tenNXB=? WHERE maNXB=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenNXB());
            ps.setInt(2, obj.getMaNXB());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maNXB) {
        String sql = "DELETE FROM nhaxuatban WHERE maNXB=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNXB);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhaXuatBanDTO> getAll() {
        ArrayList<NhaXuatBanDTO> dsNXB = new ArrayList<>();
        String sql = "SELECT * FROM nhaxuatban";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsNXB.add(new NhaXuatBanDTO(
                    rs.getInt("maNXB"),
                    rs.getString("tenNXB")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNXB;
    }

    public NhaXuatBanDTO getById(int id) {
        String sql = "SELECT * FROM nhaxuatban WHERE maNXB=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhaXuatBanDTO(
                        rs.getInt("maNXB"),
                        rs.getString("tenNXB")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
//    public boolean exists(int maNXB) {
//        String sql = "SELECT 1 FROM nhaxuatban WHERE maNXB = ? LIMIT 1";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, maNXB);
//            try (ResultSet rs = ps.executeQuery()) {
//                return rs.next();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
