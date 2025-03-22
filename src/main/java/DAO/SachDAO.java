package DAO;

import DAO.DatabaseConnection;
import DTO.SachDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SachDAO {

    public ArrayList<SachDTO> getALL() {
        ArrayList<SachDTO> dssach = new ArrayList<>();
        String sql = "SELECT * FROM sach";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dssach.add(new SachDTO(
                    rs.getInt("maSach"),
                    rs.getString("tenSach"),
                    rs.getInt("giaSach"),
                    rs.getInt("soLuongTon"),
                    rs.getInt("maNXB")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dssach;
    }

    
    public int add(SachDTO obj) {
        int result = 0;
        String sql = "INSERT INTO sach (maSach, tenSach, giaSach, soLuongTon, maNXB) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, obj.getMaSach());
            pstmt.setString(2, obj.getTenSach());
            pstmt.setInt(3, obj.getGiaSach());
            pstmt.setInt(4, obj.getSoLuongTon());
            pstmt.setInt(5, obj.getMaNXB());

            result = pstmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; 
    }

    public int update(SachDTO obj) {
        int result = 0;
        String sql = "UPDATE sach SET tenSach=?, giaSach=?, soLuongTon=?, maNXB=? WHERE maSach=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, obj.getTenSach());
            pstmt.setInt(2, obj.getGiaSach());
            pstmt.setInt(3, obj.getSoLuongTon());
            pstmt.setInt(4, obj.getMaNXB());
            pstmt.setInt(5, obj.getMaSach());

            result = pstmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; 
    }

    public int delete(int maSach) {  
        int result = 0;
        String sql = "DELETE FROM sach WHERE maSach=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, maSach); 

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
