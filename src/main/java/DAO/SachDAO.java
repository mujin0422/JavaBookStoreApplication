package DAO;
import DTO.ThongKeSachDTO;
import DTO.SachDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    public String getNextMaSach() {
        String sql = "SELECT MAX(maSach) AS nextID FROM sach";
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
     public int getGiaSachByMaSach(int maSach) {
        int giaSach = 0;
        String sql = "SELECT giaSach FROM sach WHERE maSach = ?"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("giaSach");
            }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return giaSach;
    }

    public ArrayList<ThongKeSachDTO> getThongKeSach(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<ThongKeSachDTO> resultList = new ArrayList<>();
        String query = "SELECT s.maSach, s.tenSach, SUM(ctpn.soLuong) AS soLuongNhap, SUM(ctpx.soLuong) AS soLuongXuat "
                + "FROM sach s "
                + "LEFT JOIN chitietphieunhap ctpn ON s.maSach = ctpn.maSach "
                + "LEFT JOIN chitietphieuxuat ctpx ON s.maSach = ctpx.maSach "
                + "LEFT JOIN phieunhap pn ON pn.maPN = ctpn.maPN AND pn.ngayNhap BETWEEN ? AND ? "
                + "LEFT JOIN phieuxuat px ON px.maPX = ctpx.maPX AND px.ngayXuat BETWEEN ? AND ? "
                + "GROUP BY s.maSach, s.tenSach "
                + "ORDER BY soLuongXuat DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setDate(1, ngayBatDau);
            pst.setDate(2, ngayKetThuc);
            pst.setDate(3, ngayBatDau);
            pst.setDate(4, ngayKetThuc);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int soLuongNhap = rs.getInt("soLuongNhap");
                int soLuongXuat = rs.getInt("soLuongXuat");
                resultList.add(new ThongKeSachDTO(
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        soLuongNhap,
                        soLuongXuat,
                        soLuongNhap - soLuongXuat
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}


