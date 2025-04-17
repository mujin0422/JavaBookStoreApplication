package GUI.ThongKeComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

public class ThongKeDoanhThu extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaSach, txtMaKH;
    private JDateChooser dateFrom, dateTo;
    private JButton btnLocNgay, btnLocThang, btnLocSachKH;
    private JLabel lblChiTiet, lblTongDoanhThu;

    public ThongKeDoanhThu() {
        setLayout(new BorderLayout(10, 10));

        // Panel lọc
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("Bộ lọc"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Components for filters
        JLabel lblFromDate = new JLabel("Từ ngày:");
        dateFrom = new JDateChooser();
        
        JLabel lblToDate = new JLabel("Đến ngày:");
        dateTo = new JDateChooser();
        
        JLabel lblMaSach = new JLabel("Mã sách:");
        txtMaSach = new JTextField();
        
        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        txtMaKH = new JTextField();
        
        btnLocNgay = new JButton("Lọc theo ngày");
        btnLocThang = new JButton("Lọc theo tháng");
        btnLocSachKH = new JButton("Lọc");

        // Add components to filter panel
        gbc.gridx = 0; gbc.gridy = 0;
        filterPanel.add(lblFromDate, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        filterPanel.add(dateFrom, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        filterPanel.add(lblToDate, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        filterPanel.add(dateTo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        filterPanel.add(lblMaSach, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        filterPanel.add(txtMaSach, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        filterPanel.add(lblMaKH, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        filterPanel.add(txtMaKH, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        filterPanel.add(btnLocNgay, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        filterPanel.add(btnLocThang, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        filterPanel.add(btnLocSachKH, gbc);

        add(filterPanel, BorderLayout.NORTH);

        // Bảng dữ liệu
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Sách", "Tên Sách", "Mã KH", "Tên KH", "Ngày Xuất", "Số Lượng", "Giá Bán", "Tổng Tiền"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chi tiết
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        lblChiTiet = new JLabel("Chi tiết:");
        lblTongDoanhThu = new JLabel("Tổng doanh thu: 0 VNĐ");
        bottomPanel.add(lblChiTiet);
        bottomPanel.add(lblTongDoanhThu);
        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện
        btnLocNgay.addActionListener(e -> loadDataTheoNgay());
        btnLocThang.addActionListener(e -> loadDataTheoThang());
        btnLocSachKH.addActionListener(e -> loadDataTheoSachKhachHang());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String tenKH = model.getValueAt(row, 3).toString();
                    String tenSach = model.getValueAt(row, 1).toString();
                    lblChiTiet.setText("Tên khách hàng: " + tenKH + " | Tên sách: " + tenSach);
                }
            }
        });
    }

    private void loadDataTheoNgay() {
        model.setRowCount(0);
        double tongDoanhThu = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFrom.getDate() != null ? sdf.format(dateFrom.getDate()) : null;
        String toDate = dateTo.getDate() != null ? sdf.format(dateTo.getDate()) : null;
        String maSach = txtMaSach.getText().trim();
        String maKH = txtMaKH.getText().trim();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");

            StringBuilder query = new StringBuilder();
            query.append("SELECT s.maSach, s.tenSach, kh.maKH, kh.tenKH, px.NgayXuat, ctpx.soLuong, ctpx.giaBan, ctpx.soLuong * ctpx.giaBan AS tongTien ");
            query.append("FROM chitietphieuxuat ctpx ");
            query.append("JOIN phieuxuat px ON ctpx.maPX = px.maPX ");
            query.append("JOIN sach s ON ctpx.maSach = s.maSach ");
            query.append("JOIN khachhang kh ON px.maKH = kh.maKH ");
            query.append("WHERE 1=1 ");

            if (fromDate != null) query.append("AND px.NgayXuat >= ? ");
            if (toDate != null) query.append("AND px.NgayXuat <= ? ");
            if (!maSach.isEmpty()) query.append("AND s.maSach = ? ");
            if (!maKH.isEmpty()) query.append("AND kh.maKH = ? ");

            stmt = conn.prepareStatement(query.toString());

            int idx = 1;
            if (fromDate != null) stmt.setString(idx++, fromDate);
            if (toDate != null) stmt.setString(idx++, toDate);
            if (!maSach.isEmpty()) stmt.setInt(idx++, Integer.parseInt(maSach));
            if (!maKH.isEmpty()) stmt.setInt(idx++, Integer.parseInt(maKH));

            rs = stmt.executeQuery();
            while (rs.next()) {
                double tongTien = rs.getDouble("tongTien");
                tongDoanhThu += tongTien;

                model.addRow(new Object[] {
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getDate("NgayXuat"),
                        rs.getInt("soLuong"),
                        rs.getDouble("giaBan"),
                        tongTien
                });
            }

            lblTongDoanhThu.setText("Tổng doanh thu: " + tongDoanhThu + " VNĐ");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    private void loadDataTheoThang() {
        model.setRowCount(0);
        double tongDoanhThu = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");

            String query = "SELECT s.maSach, s.tenSach, kh.maKH, kh.tenKH, px.NgayXuat, ctpx.soLuong, ctpx.giaBan, (ctpx.soLuong * ctpx.giaBan) AS tongTien " +
                    "FROM chitietphieuxuat ctpx " +
                    "JOIN phieuxuat px ON ctpx.maPX = px.maPX " +
                    "JOIN sach s ON ctpx.maSach = s.maSach " +
                    "JOIN khachhang kh ON px.maKH = kh.maKH " +
                    "WHERE MONTH(px.NgayXuat) = ? AND YEAR(px.NgayXuat) = ?";

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFrom.getDate());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, month);
            stmt.setInt(2, year);

            rs = stmt.executeQuery();
            while (rs.next()) {
                double tongTien = rs.getDouble("tongTien");
                tongDoanhThu += tongTien;

                model.addRow(new Object[] {
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getDate("NgayXuat"),
                        rs.getInt("soLuong"),
                        rs.getDouble("giaBan"),
                        tongTien
                });
            }

            lblTongDoanhThu.setText("Tổng doanh thu tháng " + month + "/" + year + ": " + tongDoanhThu + " VNĐ");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    private void loadDataTheoSachKhachHang() {
        model.setRowCount(0);
        double tongDoanhThu = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String maSach = txtMaSach.getText().trim();
        String maKH = txtMaKH.getText().trim();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");

            StringBuilder query = new StringBuilder();
            query.append("SELECT s.maSach, s.tenSach, kh.maKH, kh.tenKH, px.NgayXuat, ctpx.soLuong, ctpx.giaBan, ctpx.soLuong * ctpx.giaBan AS tongTien ");
            query.append("FROM chitietphieuxuat ctpx ");
            query.append("JOIN phieuxuat px ON ctpx.maPX = px.maPX ");
            query.append("JOIN sach s ON ctpx.maSach = s.maSach ");
            query.append("JOIN khachhang kh ON px.maKH = kh.maKH ");
            query.append("WHERE 1=1 ");

            if (!maSach.isEmpty()) query.append("AND s.maSach = ? ");
            if (!maKH.isEmpty()) query.append("AND kh.maKH = ? ");

            stmt = conn.prepareStatement(query.toString());

            int idx = 1;
            if (!maSach.isEmpty()) stmt.setInt(idx++, Integer.parseInt(maSach));
            if (!maKH.isEmpty()) stmt.setInt(idx++, Integer.parseInt(maKH));

            rs = stmt.executeQuery();
            while (rs.next()) {
                double tongTien = rs.getDouble("tongTien");
                tongDoanhThu += tongTien;

                model.addRow(new Object[] {
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getDate("NgayXuat"),
                        rs.getInt("soLuong"),
                        rs.getDouble("giaBan"),
                        tongTien
                });
            }

            lblTongDoanhThu.setText("Tổng doanh thu: " + tongDoanhThu + " VNĐ");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
