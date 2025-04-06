package DAO;

import DTO.NhomTacGiaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhomTacGiaDAO {

    public int add(NhomTacGiaDTO obj) {
        String sql = "INSERT INTO nhomtacgia (maTG, maSach) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaTG());
            ps.setInt(2, obj.getMaSach());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(NhomTacGiaDTO obj) {
        String sql = "UPDATE nhomtacgia SET maTG=? WHERE maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setInt(2, obj.getMaTG());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int delete(int maTG) {
        String sql = "DELETE FROM nhomtacgia WHERE maTG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTG);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maTG, int maSach) {
        String sql = "DELETE FROM nhomtacgia WHERE maSach = ? AND maTG = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maTG);
            return ps.executeUpdate(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0; 
    }
    
    public int exists(int maTG, int maSach) {
        String sql = "SELECT COUNT(*) FROM nhomtacgia WHERE maSach = ? AND maTG = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ps.setInt(2, maTG);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;  
    }

    public ArrayList<NhomTacGiaDTO> getAll() {
        ArrayList<NhomTacGiaDTO> dsTG = new ArrayList<>();
        String sql = "SELECT * FROM nhomtacgia";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsTG.add(new NhomTacGiaDTO(
                    rs.getInt("maTG"),
                    rs.getInt("maSach")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTG;
    }

    public NhomTacGiaDTO getById(int id) {
        String sql = "SELECT * FROM nhomtacgia WHERE maTG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhomTacGiaDTO(
                        rs.getInt("maTG"),
                        rs.getInt("maSach")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Integer> getMaTacGiaByMaSach(int maSach) {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT maTG FROM nhomtacgia WHERE maSach = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("maTG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    

}
