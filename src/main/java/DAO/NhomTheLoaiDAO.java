package DAO;

import DTO.NhomTheLoaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhomTheLoaiDAO {

    public int add(NhomTheLoaiDTO obj) {
        String sql = "INSERT INTO nhomtheloai (maTL, maSach) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaTL());
            ps.setInt(2, obj.getMaSach());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(NhomTheLoaiDTO obj) {
        String sql = "UPDATE nhomtheloai SET maSach=? WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setInt(2, obj.getMaTL());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maTL) {
        String sql = "DELETE FROM nhomtheloai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTL);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhomTheLoaiDTO> getAll() {
        ArrayList<NhomTheLoaiDTO> dsNhomTheLoai = new ArrayList<>();
        String sql = "SELECT * FROM nhomtheloai";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsNhomTheLoai.add(new NhomTheLoaiDTO(
                    rs.getInt("maTL"),
                    rs.getInt("maSach")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhomTheLoai;
    }

    public NhomTheLoaiDTO getById(int id) {
        String sql = "SELECT * FROM nhomtheloai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhomTheLoaiDTO(
                        rs.getInt("maTL"),
                        rs.getInt("maSach")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Integer> getMaTheLoaiBySach(int maSach) {
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "SELECT maTL FROM nhomtheloai WHERE maSach = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("maTL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean deleteByMaSach(int maSach) {
        String sql = "DELETE FROM nhomtheloai WHERE maSach = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maSach);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
