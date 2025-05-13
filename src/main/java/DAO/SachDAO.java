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

    public ArrayList<ThongKeSachDTO> getThongKeSach(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<ThongKeSachDTO> resultList = new ArrayList<>();
        String query = "SELECT s.maSach, s.tenSach, " +
                       "       COALESCE(nhap.soLuongNhap, 0) AS soLuongNhap, " +
                       "       COALESCE(xuat.soLuongXuat, 0) AS soLuongXuat, " +
                       "       (COALESCE(tongNhap.soLuongNhap, 0) - COALESCE(tongXuat.soLuongXuat, 0)) AS soLuongTon " +
                       "FROM sach s " +
                       "LEFT JOIN ( " +
                       "    SELECT ctpn.maSach, SUM(ctpn.soLuong) AS soLuongNhap " +
                       "    FROM chitietphieunhap ctpn " +
                       "    JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                       "    WHERE pn.ngayNhap BETWEEN ? AND ? " +
                       "    GROUP BY ctpn.maSach " +
                       ") nhap ON s.maSach = nhap.maSach " +
                       "LEFT JOIN ( " +
                       "    SELECT ctpx.maSach, SUM(ctpx.soLuong) AS soLuongXuat " +
                       "    FROM chitietphieuxuat ctpx " +
                       "    JOIN phieuxuat px ON ctpx.maPX = px.maPX " +
                       "    WHERE px.ngayXuat BETWEEN ? AND ? " +
                       "    GROUP BY ctpx.maSach " +
                       ") xuat ON s.maSach = xuat.maSach " +
                       "LEFT JOIN ( " +
                       "    SELECT ctpn.maSach, SUM(ctpn.soLuong) AS soLuongNhap " +
                       "    FROM chitietphieunhap ctpn " +
                       "    JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                       "    WHERE pn.ngayNhap <= ? " +
                       "    GROUP BY ctpn.maSach " +
                       ") tongNhap ON s.maSach = tongNhap.maSach " +
                       "LEFT JOIN ( " +
                       "    SELECT ctpx.maSach, SUM(ctpx.soLuong) AS soLuongXuat " +
                       "    FROM chitietphieuxuat ctpx " +
                       "    JOIN phieuxuat px ON ctpx.maPX = px.maPX " +
                       "    WHERE px.ngayXuat <= ? " +
                       "    GROUP BY ctpx.maSach " +
                       ") tongXuat ON s.maSach = tongXuat.maSach " +
                       "ORDER BY soLuongXuat DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setDate(1, ngayBatDau);   // BETWEEN ? AND ?
            pst.setDate(2, ngayKetThuc);
            pst.setDate(3, ngayBatDau);   // BETWEEN ? AND ?
            pst.setDate(4, ngayKetThuc);
            pst.setDate(5, ngayKetThuc);  // <= ?
            pst.setDate(6, ngayKetThuc);  // <= ?

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                resultList.add(new ThongKeSachDTO(
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("soLuongNhap"),
                        rs.getInt("soLuongXuat"),
                        rs.getInt("soLuongTon")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}


