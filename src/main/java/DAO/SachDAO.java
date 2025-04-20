package DAO;
import DTO.ThongKeSachDTO;
import DTO.SachDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class SachDAO {

    public int add(SachDTO obj) {
        String sql = "INSERT INTO sach (maSach, tenSach, giaSach, soLuongTon, maNXB) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSach());
            ps.setString(2, obj.getTenSach());
            ps.setInt(3, obj.getGiaSach());
            ps.setInt(4, obj.getSoLuongTon());
            ps.setInt(5, obj.getMaNXB());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(SachDTO obj) {
        String sql = "UPDATE sach SET tenSach=?, giaSach=?, soLuongTon=?, maNXB=? WHERE maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenSach());
            ps.setInt(2, obj.getGiaSach());
            ps.setInt(3, obj.getSoLuongTon());
            ps.setInt(4, obj.getMaNXB());
            ps.setInt(5, obj.getMaSach());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maSach) {
        String sql = "UPDATE sach SET trangThaiXoa=1 WHERE maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<SachDTO> getAll() {
        ArrayList<SachDTO> dsSach = new ArrayList<>();
        String sql = "SELECT * FROM sach WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsSach.add(new SachDTO(
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
        return dsSach;
    }

    public SachDTO getById(int id) {
        String sql = "SELECT * FROM sach WHERE maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SachDTO(
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("giaSach"),
                        rs.getInt("soLuongTon"),
                        rs.getInt("maNXB")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getSoLuongTonSach(int maSach){
        String sql = "SELECT soLuongTon FROM sach WHERE maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("soLuongTon");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int updateSoLuongTonSach(int maSach, int soLuongTon) {
        String sql = "UPDATE sach SET soLuongTon = ? WHERE maSach = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongTon);
            ps.setInt(2, maSach);
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String getTenNxbByMaSach(int maSach){
        String sql = "SELECT tenNXB FROM sach sch "
                + "JOIN nhaxuatban nxb WHERE sch.maNXB=nxb.maNXB AND maSach=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenNXB");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
            
    public String getTenSachByMaSach(int maSach){
        String sql ="SELECT tenSach FROM sach WHERE maSach =?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenSach");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlicuahangsach";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Phương thức lấy thống kê sách
    public List<ThongKeSachDTO> getThongKeSach(Date ngayBatDau, Date ngayKetThuc) {
        List<ThongKeSachDTO> resultList = new ArrayList<>();
        String query = """
            SELECT s.maSach, s.tenSach, nxb.tenNXB,
                   IFNULL(SUM(nhap.soLuong), 0) AS soLuongNhap,
                   IFNULL(SUM(xuat.soLuong), 0) AS soLuongXuat
            FROM sach s
            JOIN nhaxuatban nxb ON s.maNXB = nxb.maNXB
            LEFT JOIN chitietphieunhap nhap ON s.maSach = nhap.maSach
            LEFT JOIN phieunhap pn ON nhap.maPN = pn.maPN AND pn.ngayNhap BETWEEN ? AND ?
            LEFT JOIN chitietphieuxuat xuat ON s.maSach = xuat.maSach
            LEFT JOIN phieuxuat px ON xuat.maPX = px.maPX AND px.ngayXuat BETWEEN ? AND ?
            GROUP BY s.maSach, s.tenSach, nxb.tenNXB
            ORDER BY s.tenSach;
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setDate(1, ngayBatDau);
            pst.setDate(2, ngayKetThuc);
            pst.setDate(3, ngayBatDau);
            pst.setDate(4, ngayKetThuc);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                resultList.add(new ThongKeSachDTO(
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getString("tenNXB"),
                        rs.getInt("soLuongNhap"),
                        rs.getInt("soLuongXuat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}


