/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;
import DTO.NhomTheLoaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Thanh tam
 */
public class NhomTheLoaiDAO {

    // Add a new Author Group
    public int add(NhomTheLoaiDTO obj) {
        String sql = "INSERT INTO NhomTacGia (MaSach,MaTL) VALUES (?, ?)";
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

    // Update an Author Group
    public int update(NhomTheLoaiDTO obj) {
        String sql = "UPDATE NhomTheLoai SET maSach=? WHERE maTL=?";
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

    // Delete an Author Group
    public int delete(int MaTL) {
        String sql = "DELETE FROM NhomTheLoai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaTL);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get all Author Groups
    public ArrayList<NhomTheLoaiDTO> getAll() {
        ArrayList<NhomTheLoaiDTO> dsTG = new ArrayList<>();
        String sql = "SELECT * FROM NhomTheLoai";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsTG.add(new NhomTheLoaiDTO(
                    rs.getInt("MaSach"),
                    rs.getInt("MaTL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTG;
    }

    // Get Author Group by ID
    public NhomTheLoaiDTO getById(int id) {
        String sql = "SELECT * FROM NhomTheLoai WHERE maTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhomTheLoaiDTO(
                        rs.getInt("MaSach"),
                        rs.getInt("MaTL")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Function to add authors to a specific book
    public void addAuthorsToBook(int MaSach, ArrayList<Integer> authorIds) {
        String sql = "INSERT INTO Sach_NhomTheLoai (maSach, maTL) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int MaTL : authorIds) {
                ps.setInt(1, MaSach);
                ps.setInt(2, MaTL);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to get all authors for a specific book
    public ArrayList<NhomTheLoaiDTO> getAuthorsByBookId(int bookId) {
        ArrayList<NhomTheLoaiDTO> authors = new ArrayList<>();
        String sql = "SELECT N.* FROM NhomTheLoai N " +
                     "JOIN Sach_NhomTheLoai S ON N.MaTL = S.MaSach " +
                     "WHERE S.maTL = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(new NhomTheLoaiDTO(
                        rs.getInt("MaSach"),
                        rs.getInt("MaTL")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    // Check if an Author Group exists
    public boolean exists(int MaTL) {
        String sql = "SELECT COUNT(*) FROM NhomTacGia WHERE MaTL=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaTL);
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
