
package GUI.ThongKeComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ThongKeSach extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public ThongKeSach() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Tiêu đề cột
        String[] columnNames = {"Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Mã thể loại"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thống kê sách", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/quanlicuahangsach"; // sửa lại nếu cần
        String user = "root";
        String password = "";

        String query = """
            SELECT 
                s.maSach, 
                s.tenSach,
                GROUP_CONCAT(tg.tenTG SEPARATOR ', ') AS tenTacGia,
                nxb.tenNXB AS tenNhaXuatBan,
                ntl.maTL AS maTheLoai
            FROM sach s
            LEFT JOIN sach_tacgia stg ON s.maSach = stg.maSach
            LEFT JOIN tacgia tg ON stg.maTG = tg.maTG
            LEFT JOIN nhaxuatban nxb ON s.maNXB = nxb.maNXB
            LEFT JOIN nhomtheloai ntl ON s.maSach = ntl.maSach
            WHERE s.trangThaiXoa = 0
            GROUP BY s.maSach, s.tenSach, nxb.tenNXB, ntl.maTL
        """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            model.setRowCount(0); // clear table

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("maSach"));
                row.add(rs.getString("tenSach"));
                row.add(rs.getString("tenTacGia"));
                row.add(rs.getString("tenNhaXuatBan"));
                row.add(rs.getString("maTheLoai"));
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
