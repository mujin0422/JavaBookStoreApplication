package DAO;
import DTO.KhachHangDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

public class KhachHangDAO {
    public int add(KhachHangDTO obj) {
        String sql = "INSERT INTO khachhang(maKH, tenKH, sdt, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaKH());
            ps.setString(2, obj.getTenKH());
            ps.setString(3, obj.getSdt());
            ps.setString(4, obj.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(KhachHangDTO obj) {
        String sql = "UPDATE khachhang SET tenKH=?, sdt=?, email=? WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenKH());
            ps.setString(2, obj.getSdt());
            ps.setString(3, obj.getEmail());
            ps.setInt(4, obj.getMaKH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maKH) {
        String sql = "UPDATE khachhang SET trangThaiXoa=1 WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KhachHangDTO> getAll() {
        ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsKhachHang.add(new KhachHangDTO(
                    rs.getInt("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public KhachHangDTO getById(int maKH) {
        String sql = "SELECT * FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangDTO(
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getNextMaKH() {
        String sql = "SELECT MAX(maKH) AS nextID FROM khachhang";
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
    
    public int getMaKhByTenKh(String tenKh) {
        String sql = "SELECT maKH FROM khachhang WHERE tenKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKh); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maKH"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public String getTenKhByMaKh(int maKh) {
        String sql = "SELECT tenKH FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKh); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenKH"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    private static final String URL = "jdbc:mysql://localhost:3306/quanlicuahangsach";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Object[]> thongKeKhachHangTheoNgay(Date fromDate, Date toDate) {
        String sql = """
            SELECT kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat, SUM(ctpx.soLuong) AS tongSoLuong
            FROM khachhang kh
            JOIN phieuxuat px ON kh.maKH = px.maKH
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            WHERE px.ngayXuat BETWEEN ? AND ?
            GROUP BY kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat
            ORDER BY tongSoLuong DESC
        """;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Object[]> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, sdf.format(fromDate));
            pst.setString(2, sdf.format(toDate));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                results.add(new Object[]{
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getDate("ngayXuat"),
                        rs.getInt("tongSoLuong")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
    public List<Object[]> getKhachHangTheoThang(String monthYear) {
    String sql = """
       SELECT kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat, SUM(ctpx.soLuong) AS tongSoLuong, SUM(ctpx.giaBan) AS tongTien
            FROM khachhang kh
            JOIN phieuxuat px ON kh.maKH = px.maKH
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            GROUP BY kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat
            ORDER BY kh.maKH, px.ngayXuat
    """;
    List<Object[]> results = new ArrayList<>();
    try (Connection conn = getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, monthYear);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            results.add(new Object[]{
                    rs.getInt("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getInt("tongSoLuong"),
                    rs.getDouble("tongTien")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return results;
    }

    public List<Object[]> getTopKhachMuaNhieu() {
        String sql = """
            SELECT kh.maKH, kh.tenKH, kh.sdt, SUM(ctpx.soLuong) AS tongSoLuong, SUM(ctpx.giaBan) AS tongTien
            FROM khachhang kh
            JOIN phieuxuat px ON kh.maKH = px.maKH
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            GROUP BY kh.maKH, kh.tenKH, kh.sdt
            ORDER BY tongSoLuong DESC
            LIMIT 10
        """;
        List<Object[]> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                results.add(new Object[]{
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getInt("tongSoLuong"),
                        rs.getDouble("tongTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public List<Object[]> getDanhSachKhachDaMua() {
        String sql = """
            SELECT kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat, SUM(ctpx.soLuong) AS tongSoLuong, SUM(ctpx.giaBan) AS tongTien
            FROM khachhang kh
            JOIN phieuxuat px ON kh.maKH = px.maKH
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            GROUP BY kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat
            ORDER BY kh.maKH, px.ngayXuat
        """;
        List<Object[]> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                results.add(new Object[]{
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getDate("ngayXuat"),
                        rs.getInt("tongSoLuong"),
                        rs.getDouble("tongTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}




