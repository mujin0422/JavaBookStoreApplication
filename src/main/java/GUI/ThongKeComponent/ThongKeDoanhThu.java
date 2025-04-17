package GUI.ThongKeComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class ThongKeDoanhThu extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaSach, txtMaKH;
    private JDateChooser dateFrom, dateTo;
    private JButton btnLoc;
    private JLabel lblChiTiet;

    public ThongKeDoanhThu() {
        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 4, 10, 10)); // 3 hàng 4 cột

        txtMaSach = new JTextField();
        txtMaKH = new JTextField();
        dateFrom = new JDateChooser();
        dateTo = new JDateChooser();
        btnLoc = new JButton("Lọc");

        filterPanel.add(new JLabel("Từ ngày:"));
        filterPanel.add(dateFrom);
        filterPanel.add(new JLabel("Đến ngày:"));
        filterPanel.add(dateTo);
        filterPanel.add(new JLabel("Mã sách:"));
        filterPanel.add(txtMaSach);
        filterPanel.add(new JLabel("Mã khách hàng:"));
        filterPanel.add(txtMaKH);
        filterPanel.add(new JLabel()); // trống để đẩy nút lọc sang phải
        filterPanel.add(btnLoc);       // nút lọc nằm góc phải

        add(filterPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Sách", "Tên Sách", "Mã KH", "Tên KH", "Ngày Xuất", "Số Lượng", "Giá Bán", "Tổng Tiền"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER); // đặt bảng ở giữa

        lblChiTiet = new JLabel("Chi tiết:");
        add(lblChiTiet, BorderLayout.SOUTH); // thông tin chi tiết ở dưới

        btnLoc.addActionListener(e -> loadData());

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

    private void loadData() {
        model.setRowCount(0);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFrom.getDate() != null ? sdf.format(dateFrom.getDate()) : null;
        String toDate = dateTo.getDate() != null ? sdf.format(dateTo.getDate()) : null;
        String maSach = txtMaSach.getText().trim();
        String maKH = txtMaKH.getText().trim();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", ""); // chỉnh tên CSDL & mật khẩu đúng

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
                model.addRow(new Object[]{
                        rs.getInt("maSach"),
                        rs.getString("tenSach"),
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getDate("NgayXuat"),
                        rs.getInt("soLuong"),
                        rs.getDouble("giaBan"),
                        rs.getDouble("tongTien")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
