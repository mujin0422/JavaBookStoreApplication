package DAO;

import DTO.NhomTacGiaDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class NhomTacGiaDAO {

    // Add a new Author Group
    public int add(NhomTacGiaDTO obj) {
        String sql = "INSERT INTO NhomTacGia (maTG, maSach) VALUES (?, ?)";
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

    // Update an Author Group
    public int update(NhomTacGiaDTO obj) {
        String sql = "UPDATE NhomTacGia SET maTG=? WHERE maSach=?";
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

    // Delete an Author Group
    public int deleteTG(int maTG) {
        String sql = "DELETE FROM NhomTacGia WHERE maTG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTG);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get all Author Groups
    public ArrayList<NhomTacGiaDTO> getAll() {
        ArrayList<NhomTacGiaDTO> dsTG = new ArrayList<>();
        String sql = "SELECT * FROM NhomTacGia";
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

    // Get Author Group by ID
    public NhomTacGiaDTO getById(int id) {
        String sql = "SELECT * FROM NhomTacGia WHERE maTG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhomTacGiaDTO(
                        rs.getInt("maTG"),
                        rs.getInt("tenTG")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Function to add authors to a specific book
    public void addAuthorsToBook(int maSach, ArrayList<Integer> authorIds) {
        String sql = "INSERT INTO Sach_NhomTacGia (maSach, maTG) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int maTG : authorIds) {
                ps.setInt(1, maSach);
                ps.setInt(2, maTG);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to get all authors for a specific book
    public ArrayList<NhomTacGiaDTO> getAuthorsByBookId(int bookId) {
        ArrayList<NhomTacGiaDTO> authors = new ArrayList<>();
        String sql = "SELECT N.* FROM NhomTacGia N " +
                     "JOIN Sach_NhomTacGia S ON N.maTG = S.maTG " +
                     "WHERE S.maSach = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(new NhomTacGiaDTO(
                        rs.getInt("maTG"),
                        rs.getInt("maSach")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    // Check if an Author Group exists
    public boolean exists(int maTG) {
        String sql = "SELECT COUNT(*) FROM NhomTacGia WHERE maTG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTG);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Returns true if an author exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurs or not found
    }
}
