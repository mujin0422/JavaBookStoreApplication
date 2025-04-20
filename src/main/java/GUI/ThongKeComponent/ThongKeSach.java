package GUI.ThongKeComponent;

import BUS.SachBUS;
import DTO.ThongKeSachDTO;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class ThongKeSach extends JPanel {
    private JTable table;
    private JLabel lblTongNhap, lblTongXuat;
    private JDateChooser dateChooserBatDau, dateChooserKetThuc;
    private SachBUS sachBUS;
    private DefaultTableModel tableModel;

    public ThongKeSach() {
        sachBUS = new SachBUS();

        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15)); // Add some padding

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        headerPanel.setBackground(new Color(245, 245, 245)); // Light gray background

        JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
        lblNgayBatDau.setFont(new Font("Arial", Font.BOLD, 14));

        dateChooserBatDau = new JDateChooser();
        dateChooserBatDau.setPreferredSize(new Dimension(120, 30));
        dateChooserBatDau.setLocale(new Locale("vi", "VN")); // Set locale for Vietnamese calendar

        JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
        lblNgayKetThuc.setFont(new Font("Arial", Font.BOLD, 14));

        dateChooserKetThuc = new JDateChooser();
        dateChooserKetThuc.setPreferredSize(new Dimension(120, 30));
        dateChooserKetThuc.setLocale(new Locale("vi", "VN"));

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Arial", Font.BOLD, 14));
        btnThongKe.setForeground(Color.WHITE);
        btnThongKe.setBackground(new Color(0, 123, 255)); // Blue button
        btnThongKe.setFocusPainted(false);
        btnThongKe.addActionListener(e -> loadData());

        headerPanel.add(lblNgayBatDau);
        headerPanel.add(dateChooserBatDau);
        headerPanel.add(lblNgayKetThuc);
        headerPanel.add(dateChooserKetThuc);
        headerPanel.add(btnThongKe);

        add(headerPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Mã sách", "Tên sách", "Tên NXB", "Số lượng nhập", "Số lượng xuất"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE); // White background for the table content
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        footerPanel.setBackground(new Color(245, 245, 245));

        lblTongNhap = new JLabel("Tổng số lượng nhập: 0");
        lblTongNhap.setFont(new Font("Arial", Font.BOLD, 14));

        lblTongXuat = new JLabel("Tổng số lượng xuất: 0");
        lblTongXuat.setFont(new Font("Arial", Font.BOLD, 14));
        lblTongXuat.setBorder(new EmptyBorder(0, 15, 0, 0)); // Add some left padding

        footerPanel.add(lblTongNhap);
        footerPanel.add(lblTongXuat);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        Date ngayBatDauUtil = dateChooserBatDau.getDate();
        Date ngayKetThucUtil = dateChooserKetThuc.getDate();

        if (ngayBatDauUtil == null || ngayKetThucUtil == null) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn ngày bắt đầu và ngày kết thúc!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ngayBatDauUtil.after(ngayKetThucUtil)) {
            JOptionPane.showMessageDialog(this,
                    "Ngày bắt đầu không được sau ngày kết thúc!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(ngayBatDauUtil);
        String endDateStr = sdf.format(ngayKetThucUtil);

        try {
            java.sql.Date ngayBatDauSql = java.sql.Date.valueOf(startDateStr);
            java.sql.Date ngayKetThucSql = java.sql.Date.valueOf(endDateStr);

            List<ThongKeSachDTO> data = sachBUS.getThongKeSach(ngayBatDauSql, ngayKetThucSql);

            tableModel.setRowCount(0); // Clear existing data
            int tongNhap = 0, tongXuat = 0;

            for (ThongKeSachDTO sach : data) {
                tableModel.addRow(new Object[]{
                        sach.getMaSach(), sach.getTenSach(), sach.getTenNXB(), // Thêm tên NXB
                        sach.getSoLuongNhap(), sach.getSoLuongXuat()
                });
                tongNhap += sach.getSoLuongNhap();
                tongXuat += sach.getSoLuongXuat();
            }

            // Update total quantities
            lblTongNhap.setText("Tổng số lượng nhập: " + tongNhap);
            lblTongXuat.setText("Tổng số lượng xuất: " + tongXuat);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Đã xảy ra lỗi khi tải dữ liệu: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Thống kê sách");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 550); // Increased size for better layout
        frame.add(new ThongKeSach());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
