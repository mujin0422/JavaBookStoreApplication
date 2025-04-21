package GUI.ThongKeComponent;

import BUS.KhachHangBUS;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;

public class ThongKeKhachHang extends JPanel {

    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton btnThongKeTheoThang, btnKhachMuaNhieu, btnDanhSachKhachDaMua;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JDateChooser dateFromPicker;
    private JDateChooser dateToPicker;
    private JButton btnThongKeTheoNgaySubmit;

    private final KhachHangBUS khachhangbus;
    private final Color primaryColor = new Color(58, 153, 216);
    private final Color backgroundColor = new Color(245, 247, 250);
    private final Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

    public ThongKeKhachHang() {
        khachhangbus = new KhachHangBUS();

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(backgroundColor);

        // Tạo phần trên cùng với các nút chức năng
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        topPanel.setBackground(backgroundColor);

        btnThongKeTheoThang = createStyledButton("Thống kê theo khoảng thời gian");
        btnKhachMuaNhieu = createStyledButton("Top khách hàng mua nhiều");
        btnDanhSachKhachDaMua = createStyledButton("Danh sách khách hàng");

        topPanel.add(btnThongKeTheoThang);
        topPanel.add(btnKhachMuaNhieu);
        topPanel.add(btnDanhSachKhachDaMua);
        add(topPanel, BorderLayout.NORTH);

        // Tạo panel chứa các component cho thống kê theo khoảng thời gian và bảng dữ liệu
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(backgroundColor);

        // Panel cho chọn khoảng thời gian
        JPanel dateRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        dateRangePanel.setBackground(backgroundColor);
        dateRangePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Chọn khoảng thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, mainFont),
                new EmptyBorder(5, 5, 5, 5)
        ));

        dateFromPicker = new JDateChooser();
        dateFromPicker.setFont(mainFont);
        dateToPicker = new JDateChooser();
        dateToPicker.setFont(mainFont);
        btnThongKeTheoNgaySubmit = createStyledButton("Thống kê");

        dateRangePanel.add(new JLabel("Từ ngày:", SwingConstants.LEFT));
        dateRangePanel.add(dateFromPicker);
        dateRangePanel.add(new JLabel("Đến ngày:", SwingConstants.LEFT));
        dateRangePanel.add(dateToPicker);
        dateRangePanel.add(btnThongKeTheoNgaySubmit);

        // Tạo bảng hiển thị
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setFont(mainFont);
        dataTable.setRowHeight(25);
        dataTable.setSelectionBackground(primaryColor.brighter());
        dataTable.setSelectionForeground(Color.WHITE);
        JTableHeader header = dataTable.getTableHeader();
        header.setFont(mainFont.deriveFont(Font.BOLD));
        header.setBackground(new Color(230, 230, 230));
        header.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(204, 204, 204))
        ));

        contentPanel.add(dateRangePanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Ẩn panel thống kê theo khoảng thời gian ban đầu
        dateRangePanel.setVisible(false);

        // Gắn sự kiện cho các nút
        btnThongKeTheoThang.addActionListener(e -> showThongKeTheoThangPanel());
        btnKhachMuaNhieu.addActionListener(e -> thongKeKhachMuaNhieu());
        btnDanhSachKhachDaMua.addActionListener(e -> danhSachKhachDaMua());
        btnThongKeTheoNgaySubmit.addActionListener(e -> {
            Date fromDate = dateFromPicker.getDate();
            Date toDate = dateToPicker.getDate();
            if (fromDate == null || toDate == null || fromDate.after(toDate)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn khoảng thời gian hợp lệ!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            thongKeKhachHangTheoNgay(fromDate, toDate);
        });

        // Hiển thị mặc định
        hienThiDanhSachKhachHang();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(mainFont.deriveFont(Font.BOLD));
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
                button.setBackground(primaryColor.darker());
            } else {
                button.setBackground(primaryColor);
            }
        });
        return button;
    }

    private void updateTable(Object[] columnNames, List<Object[]> data) {
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private void showThongKeTheoThangPanel() {
        // Ẩn tất cả các panel chức năng khác nếu có
        // Hiện panel chọn khoảng thời gian và nút thống kê
        Component[] components = ((JPanel) contentPanel.getComponent(0)).getComponents();
        for (Component comp : components) {
            comp.setVisible(true);
        }
        ((JPanel) contentPanel.getComponent(0)).setVisible(true);
        hienThiDanhSachKhachHang(); // Hiển thị lại danh sách khách hàng mặc định khi chọn chức năng này
    }

    private void thongKeKhachHangTheoNgay(Date fromDate, Date toDate) {
        List<Object[]> data = khachhangbus.getKhachHangTheoNgay(fromDate, toDate);
        updateTable(new Object[]{"Mã KH", "Tên KH", "SĐT", "Ngày mua", "Tổng SL mua"}, data);
    }

    private void thongKeKhachMuaNhieu() {
        // Ẩn panel chọn khoảng thời gian
        if (contentPanel.getComponentCount() > 0) {
            contentPanel.getComponent(0).setVisible(false);
        }
        List<Object[]> data = khachhangbus.getTopKhachMuaNhieu();
        updateTable(new Object[]{"Mã KH", "Tên KH", "SĐT", "Tổng SL mua", "Tổng tiền"}, data);
    }

    private void danhSachKhachDaMua() {
        // Ẩn panel chọn khoảng thời gian
        if (contentPanel.getComponentCount() > 0) {
            contentPanel.getComponent(0).setVisible(false);
        }
        hienThiDanhSachKhachHang();
    }

    private void hienThiDanhSachKhachHang() {
        List<Object[]> data = khachhangbus.getDanhSachKhachDaMua();
        updateTable(new Object[]{"Mã KH", "Tên KH", "SĐT", "Ngày mua gần nhất", "Tổng SL mua", "Tổng tiền đã mua"}, data);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống kê khách hàng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.add(new ThongKeKhachHang());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
