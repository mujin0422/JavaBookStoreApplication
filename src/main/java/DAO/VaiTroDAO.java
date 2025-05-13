package DAO;

import DTO.VaiTroDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VaiTroDAO {
    public int add(VaiTroDTO obj) {
        String sql = "INSERT INTO vaitro (maVT, tenVT) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaVT());
            ps.setString(2, obj.getTenVT());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(VaiTroDTO obj) {
        String sql = "UPDATE vaitro SET tenVT = ? WHERE maVT = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenVT());
            ps.setInt(2, obj.getMaVT());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maVT) {
        String sql = "UPDATE vaitro SET trangThaiXoa = 1 WHERE maVT = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maVT);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<VaiTroDTO> getAll() {
        ArrayList<VaiTroDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM vaitro WHERE trangThaiXoa = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                list.add( new VaiTroDTO(
                    rs.getInt("maVT"),
                    rs.getString("tenVT")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public VaiTroDTO getById(int id) {
        String sql = "SELECT * FROM vaitro WHERE maVT=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new VaiTroDTO(
                        rs.getInt("maVT"),
                        rs.getString("tenVT")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getNextMaVt() {
        String sql = "SELECT MAX(maVT) AS nextID FROM vaitro";
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
    
    public int getSoLuongNhanVienHasVaiTro(int maVT) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS soLuongNhanVien "
                   + "FROM nhanvien "
                   + "WHERE maVT = ? AND trangThaiXoa = 0";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maVT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("soLuongNhanVien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
