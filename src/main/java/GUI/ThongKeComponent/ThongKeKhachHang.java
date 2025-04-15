package GUI.ThongKeComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ThongKeKhachHang extends JPanel {

    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JPanel topPanel;
    private JButton btnThongKeTheoThang;
    private JButton btnKhachMuaNhieu;

    // Màu sắc tương đồng với các nút ở MainContent
    private final Color ACTIVE_BUTTON_COLOR = new Color(52, 152, 219); // Blue
    private final Color INACTIVE_BUTTON_COLOR = new Color(230, 230, 230); // Light Gray
    private final Color TEXT_COLOR = new Color(44, 62, 80); // Dark Gray

    public ThongKeKhachHang() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 242, 245)); // Light gray background

        // Tạo panel trên cùng với các nút chức năng
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)), // Light gray border
                new EmptyBorder(10, 10, 10, 10)
        ));

        btnThongKeTheoThang = createStyledButton("Thống kê theo tháng");
        btnKhachMuaNhieu = createStyledButton("Top khách hàng mua nhiều");

        topPanel.add(btnThongKeTheoThang);
        topPanel.add(btnKhachMuaNhieu);
        add(topPanel, BorderLayout.NORTH);

        // Tạo bảng để hiển thị dữ liệu
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
        dataTable.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Hiển thị mặc định thống kê top khách hàng mua nhiều
        thongKeKhachMuaNhieu();

        // Lắng nghe sự kiện cho các nút
        btnThongKeTheoThang.addActionListener(e -> showThongKeTheoThangDialog());
        btnKhachMuaNhieu.addActionListener(e -> thongKeKhachMuaNhieu());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Dimension buttonSize = new Dimension(200, 40);

        button.setFont(buttonFont);
        button.setPreferredSize(buttonSize);
        button.setBackground(INACTIVE_BUTTON_COLOR); // Default inactive color
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Add mouse listener for hover effect (optional, but makes it more interactive)
button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACTIVE_BUTTON_COLOR.brighter());
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(INACTIVE_BUTTON_COLOR);
                button.setForeground(TEXT_COLOR);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(ACTIVE_BUTTON_COLOR);
                button.setForeground(Color.WHITE);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(INACTIVE_BUTTON_COLOR);
                button.setForeground(TEXT_COLOR);
            }
        });

        return button;
    }

    private void showThongKeTheoThangDialog() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Tháng (1-12):"));
        JTextField thangField = new JTextField();
        inputPanel.add(thangField);
        inputPanel.add(new JLabel("Năm:"));
        JTextField namField = new JTextField();
        inputPanel.add(namField);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Nhập tháng và năm",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String thang = thangField.getText();
            String nam = namField.getText();
            if (!thang.isEmpty() && !nam.isEmpty()) {
                try {
                    int thangInt = Integer.parseInt(thang);
                    int namInt = Integer.parseInt(nam);
                    if (thangInt >= 1 && thangInt <= 12) {
                        thongKeKhachHangTheoThang(thangInt, namInt);
                    } else {
                        JOptionPane.showMessageDialog(this, "Tháng không hợp lệ (1-12).", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số cho tháng và năm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tháng và năm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void thongKeKhachHangTheoThang(int thang, int nam) {
        String query = """
            SELECT kh.maKH, kh.tenKH, kh.sdt, px.ngayXuat, SUM(ctpx.soLuong) AS tongSoLuong
            FROM khachhang kh
            JOIN phieuxuat px ON kh.maKH = px.maKH
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            WHERE MONTH(px.ngayXuat) = ? AND YEAR(px.ngayXuat) = ?
