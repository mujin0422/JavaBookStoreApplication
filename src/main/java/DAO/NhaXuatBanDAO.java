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
        }
        return 0;
    }

    public int delete(int maNXB) {
        String sql = "UPDATE nhaxuatban SET trangThaiXoa=1 WHERE maNXB=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNXB);
            return ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }

    public ArrayList<NhaXuatBanDTO> getAll() {
        ArrayList<NhaXuatBanDTO> dsNXB = new ArrayList<>();
        String sql = "SELECT * FROM nhaxuatban WHERE trangThaiXoa=0";
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
        }
        return null;
    }
    
    public String getNextMaNxb() {
        String sql = "SELECT MAX(maNXB) AS nextID FROM nhaxuatban";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int nextId = rs.getInt("nextID");
                return String.valueOf(nextId + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }
    
    public String getTenNhaXuatBanById(int maNXB) {
        String sql = "SELECT tenNXB FROM nhaxuatban WHERE maNXB = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, maNXB);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tenNXB");
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public int getMaNxbByTenNxb(String tenNxb){
        String sql = "SELECT maNXB FROM nhaxuatban WHERE tenNXB = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, tenNxb);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maNXB");
            }
        } catch (SQLException e) {
        }
        return 0;
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
