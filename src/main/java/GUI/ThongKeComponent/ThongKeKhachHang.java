package GUI.ThongKeComponent;
import BUS.KhachHangBUS;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ThongKeKhachHang extends JPanel {

    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton btnThongKeTheoThang, btnKhachMuaNhieu, btnDanhSachKhachDaMua;

    private final KhachHangBUS khachhangbus;

    public ThongKeKhachHang() {
        khachhangbus = new KhachHangBUS(); 

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(240, 242, 245));

        // Tạo phần trên cùng với các nút chức năng
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(Color.WHITE);

        btnThongKeTheoThang = new JButton("Thống kê theo tháng");
        btnKhachMuaNhieu = new JButton("Top khách hàng mua nhiều");
        btnDanhSachKhachDaMua = new JButton("Danh sách khách đã mua");

        topPanel.add(btnThongKeTheoThang);
        topPanel.add(btnKhachMuaNhieu);
        topPanel.add(btnDanhSachKhachDaMua);
        add(topPanel, BorderLayout.NORTH);

        // Tạo bảng hiển thị
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);

        // Gắn sự kiện cho các nút
        btnThongKeTheoThang.addActionListener(e -> showThongKeTheoThangDialog());
        btnKhachMuaNhieu.addActionListener(e -> thongKeKhachMuaNhieu());
        btnDanhSachKhachDaMua.addActionListener(e -> danhSachKhachDaMua());

        // Hiển thị mặc định
        thongKeKhachMuaNhieu();
    }

    // Hiển thị dialog để chọn tháng và lấy thống kê theo tháng
    private void showThongKeTheoThangDialog() {
        JDateChooser dateFrom = new JDateChooser();
        JDateChooser dateTo = new JDateChooser();
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Ngày bắt đầu:"));
        panel.add(dateFrom);
        panel.add(new JLabel("Ngày kết thúc:"));
        panel.add(dateTo);

        int result = JOptionPane.showConfirmDialog(this, panel, "Chọn khoảng ngày",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Date fromDate = dateFrom.getDate();
            Date toDate = dateTo.getDate();
            if (fromDate == null || toDate == null || fromDate.after(toDate)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập khoảng ngày hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            thongKeKhachHangTheoNgay(fromDate, toDate);
        }
    }

    // Thống kê tổng số tiền mua của khách hàng theo tháng
    private void thongKeKhachHangTheoNgay(Date fromDate, Date toDate) {
        List<Object[]> data = khachhangbus.getKhachHangTheoNgay(fromDate, toDate);
        tableModel.setColumnIdentifiers(new Object[]{"Mã KH", "Tên KH", "SĐT", "Ngày mua", "Tổng SL mua"});
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    // Thống kê top khách hàng mua nhiều
    private void thongKeKhachMuaNhieu() {
        List<Object[]> data = khachhangbus.getTopKhachMuaNhieu();
        tableModel.setColumnIdentifiers(new Object[]{"Mã KH", "Tên KH", "SĐT", "Tổng SL mua", "Tổng tiền"});
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    // Lấy danh sách toàn bộ khách hàng đã mua
    private void danhSachKhachDaMua() {
        List<Object[]> data = khachhangbus.getDanhSachKhachDaMua();
        tableModel.setColumnIdentifiers(new Object[]{"Mã KH", "Tên KH", "SĐT", "Ngày mua", "Tổng SL mua", "Tổng tiền"});
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Thống kê khách hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(new ThongKeKhachHang());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
