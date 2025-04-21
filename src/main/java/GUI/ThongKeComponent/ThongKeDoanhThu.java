package GUI.ThongKeComponent;

import BUS.PhieuXuatBUS;
import DTO.DoanhThuDTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThongKeDoanhThu extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTongDoanhThuValue;
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private PhieuXuatBUS phieuXuatBUS;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ThongKeDoanhThu() {
        phieuXuatBUS = new PhieuXuatBUS();
        setLayout(new BorderLayout(10, 10)); // Main BorderLayout with gaps
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Filter Panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        filterPanel.setBackground(Color.WHITE);

        JLabel lblFromDate = new JLabel("Từ ngày:");
        lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));
        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(150, 30));

        JLabel lblToDate = new JLabel("Đến ngày:");
        lblToDate.setFont(new Font("Arial", Font.BOLD, 14));
        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(150, 30));

        JButton btnLocThang = new JButton("Thống Kê");
        btnLocThang.setFont(new Font("Arial", Font.BOLD, 14));
        btnLocThang.setBackground(new Color(0, 123, 255));
        btnLocThang.setForeground(Color.WHITE);
        btnLocThang.setFocusPainted(false);
        btnLocThang.addActionListener(e -> loadDataTheoKhoangThoiGian());

        filterPanel.add(lblFromDate);
        filterPanel.add(dateFrom);
        filterPanel.add(lblToDate);
        filterPanel.add(dateTo);
        filterPanel.add(btnLocThang);

        add(filterPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{
                "Mã Sách", "Tên Sách", "Tên KH", "Ngày Xuất", "Số Lượng", "Tổng Tiền"
        });
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setRowHeight(25);

        // Custom cell renderer for right alignment of numeric columns
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer); // Số Lượng
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer); // Tổng Tiền

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Total revenue
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(Color.WHITE);
        JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu:");
        lblTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongDoanhThuValue = new JLabel("0 VNĐ");
        lblTongDoanhThuValue.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongDoanhThuValue.setForeground(new Color(255, 0, 51)); // Red color for emphasis
        totalPanel.add(lblTongDoanhThu);
        totalPanel.add(lblTongDoanhThuValue);
        add(totalPanel, BorderLayout.SOUTH);
    }

    private void loadDataTheoKhoangThoiGian() {
        model.setRowCount(0);
        double tongDoanhThu = 0;

        // Get the selected dates
        Date fromDate = dateFrom.getDate();
        Date toDate = dateTo.getDate();

        // Validate dates
        if (fromDate == null || toDate == null || fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoảng thời gian hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get data from the business layer
        List<DoanhThuDTO> doanhThuList = phieuXuatBUS.getDoanhThuByDateRange(fromDate, toDate);
        for (DoanhThuDTO dt : doanhThuList) {
            double tongTien = dt.getTongTien();
            tongDoanhThu += tongTien;

            model.addRow(new Object[]{
                    dt.getMaSach(),
                    dt.getTenSach(),
                    dt.getTenKH(),
                    dateFormat.format(dt.getNgayXuat()),
                    dt.getSoLuong(),
                    currencyFormat.format(tongTien)
            });
        }

        lblTongDoanhThuValue.setText(currencyFormat.format(tongDoanhThu));
    }

   
}
