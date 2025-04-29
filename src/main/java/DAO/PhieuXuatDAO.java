package DAO;

import DTO.DoanhThuDTO;
import DTO.PhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuXuatDAO {
    public int add(PhieuXuatDTO obj) {
        String sql = "INSERT INTO phieuxuat(maPX, maNV, maKH, tongTien, ngayXuat) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaPX());
            ps.setInt(2, obj.getMaNV());
            ps.setInt(3, obj.getMaKH());
            ps.setInt(4, obj.getTongTien());
            ps.setDate(5, new java.sql.Date(obj.getNgayXuat().getTime()));
            return ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }

    public int update(PhieuXuatDTO obj) {
        String sql = "UPDATE phieuxuat SET maNV=?, maKH=?, tongTien=?, ngayXuat=? WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNV());
            ps.setInt(2, obj.getMaKH());
            ps.setInt(3, obj.getTongTien());
            ps.setDate(4, new java.sql.Date(obj.getNgayXuat().getTime()));
            ps.setInt(5, obj.getMaPX());
            return ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }

    public int delete(int maPX) {
        String sql = "UPDATE phieuxuat SET trangThaiXoa=1 WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            return ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }
    
    public int exists(int maPX) {
        String sql = "SELECT COUNT(*) FROM phieuxuat WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maPX);  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  
            }
        } catch (SQLException e) {
        }
        return 0;  
    }

    public ArrayList<PhieuXuatDTO> getAll() {
        ArrayList<PhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieuxuat WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PhieuXuatDTO obj = new PhieuXuatDTO(
                    rs.getInt("maPX"),
                    rs.getInt("maNV"),
                    rs.getInt("maKH"),
                    rs.getInt("tongTien"),
                    rs.getDate("ngayXuat")
                );
                list.add(obj);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public PhieuXuatDTO getById(int maPX) {
        String sql = "SELECT * FROM phieuxuat WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuXuatDTO(
                        rs.getInt("maPX"),
                        rs.getInt("maNV"),
                        rs.getInt("maKH"),
                        rs.getInt("tongTien"),
                        rs.getDate("ngayXuat")
                    );
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public String getNextMaPx() {
        String sql = "SELECT MAX(maPX) AS nextID FROM phieuxuat";
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
    
    public int countPhieuXuatByMaKh(int maKh) {
        String sql = "SELECT COUNT(*) AS total FROM phieuxuat WHERE maKH = ? AND trangThaiXoa = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKh);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }
     
    public List<DoanhThuDTO> getDoanhThuByDateRange(Date fromDate, Date toDate) {
       List<DoanhThuDTO> doanhThuList = new ArrayList<>();
       String sql = "SELECT " +
                    "    px.maPX, " +
                    "    s.maSach, " +
                    "    s.tenSach, " +
                    "    kh.maKH, " +
                    "    kh.tenKH, " +
                    "    px.NgayXuat, " +
                    "    SUM(ctpx.soLuong) AS tongSoLuong, " +
                    "    ctpx.giaBan, " +
                    "    SUM( ctpx.giaBan) AS tongTien " +
                    "FROM chitietphieuxuat ctpx " +
                    "JOIN phieuxuat px ON ctpx.maPX = px.maPX " +
                    "JOIN sach s ON ctpx.maSach = s.maSach " +
                    "JOIN khachhang kh ON px.maKH = kh.maKH " +
                    "WHERE px.NgayXuat BETWEEN ? AND ? " +
                    "GROUP BY px.maPX, s.maSach, s.tenSach, kh.maKH, kh.tenKH, px.NgayXuat, ctpx.giaBan";

       try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

           stmt.setDate(1, new java.sql.Date(fromDate.getTime()));
           stmt.setDate(2, new java.sql.Date(toDate.getTime()));

           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               DoanhThuDTO doanhThu = new DoanhThuDTO();
               doanhThu.setMaSach(rs.getInt("maSach"));
               doanhThu.setTenSach(rs.getString("tenSach"));
               doanhThu.setMaKH(rs.getInt("maKH"));
               doanhThu.setTenKH(rs.getString("tenKH"));
               doanhThu.setNgayXuat(rs.getDate("NgayXuat"));
               doanhThu.setSoLuong(rs.getInt("tongSoLuong")); // Use the aggregated total quantity
               doanhThu.setGiaBan(rs.getDouble("giaBan"));
               doanhThu.setTongTien(rs.getDouble("tongTien")); // Use the aggregated total price
               doanhThuList.add(doanhThu);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return doanhThuList;
   }
}
