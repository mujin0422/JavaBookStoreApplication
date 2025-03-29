package DAO;

import DTO.TheLoaiDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class TheLoaiDAO {

    public int add(TheLoaiDTO obj) {
        String sql = "INSERT INTO theloai (maTL, tenTL) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaTL());
            ps.setString(2, obj.getTenTL());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(TheLoaiDTO obj) {
        String sql = "UPDATE theloai SET tenTL=? WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenTL());
            ps.setInt(2, obj.getMaTL());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maTL) {
        String sql = "DELETE FROM theloai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTL);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<TheLoaiDTO> getAll() {
        ArrayList<TheLoaiDTO> dsTheLoai = new ArrayList<>();
        String sql = "SELECT * FROM theloai";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsTheLoai.add(new TheLoaiDTO(
                    rs.getInt("maTL"),
                    rs.getString("tenTL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTheLoai;
    }

    public TheLoaiDTO getById(int id) {
        String sql = "SELECT * FROM theloai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TheLoaiDTO(
                        rs.getInt("maTL"),
                        rs.getString("tenTL")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
