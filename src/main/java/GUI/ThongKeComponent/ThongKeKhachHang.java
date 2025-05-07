package GUI.ThongKeComponent;

import BUS.KhachHangBUS;
import DTO.ThongKeKhachHangDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.Locale;


public class ThongKeKhachHang extends JPanel {
    private UITable dataTable;
    private DefaultTableModel tableModel;
    private JPanel topPanel, contentPanel;
    private JDateChooser dateFromPicker, dateToPicker;
    private UIButton btnThongKeTheoNgaySubmit;
    private final KhachHangBUS khachhangbus;

    public ThongKeKhachHang() {
        khachhangbus = new KhachHangBUS();
        setLayout(new BorderLayout(5,5));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(UIConstants.MAIN_BACKGROUND);

        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        topPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        dateFromPicker = new JDateChooser();
        dateFromPicker.setPreferredSize(new Dimension(150, 30));
        dateFromPicker.setLocale(new Locale("vi", "VN"));
        dateToPicker = new JDateChooser();
        dateToPicker.setPreferredSize(new Dimension(150, 30));
        dateToPicker.setLocale(new Locale("vi", "VN"));
        topPanel.add(new UILabel("Từ:",30,30));
        topPanel.add(dateFromPicker);
        topPanel.add(new UILabel("Đến:",35,30));
        topPanel.add(dateToPicker);
        btnThongKeTheoNgaySubmit = new UIButton("add", "THỐNG KÊ", 100,30);
        topPanel.add(btnThongKeTheoNgaySubmit);
        add(topPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        // Tạo bảng hiển thị
        String[] columnNames = {"MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "SỐ LẦN MUA HÀNG", "TỔNG TIỀN ĐÃ MUA"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(dataTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);


        btnThongKeTheoNgaySubmit.addActionListener(e -> ThongKeTheoNgay());
    }
    
    private void ThongKeTheoNgay() {
        Date fromDate = dateFromPicker.getDate();
        Date toDate = dateToPicker.getDate();

        if (fromDate == null || toDate == null || fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoảng thời gian hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            tableModel.setRowCount(0);
            for (ThongKeKhachHangDTO thongKe : khachhangbus.thongKeKhachHangTheoNgay(fromDate, toDate)) {
                Object[] data = {
                    thongKe.getMaKH(),
                    thongKe.getTenKH(),
                    thongKe.getSoLanMua(),
                    String.format("%,.0f VND", thongKe.getTongTienDaMua()) 
                };
                tableModel.addRow(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Đã xảy ra lỗi khi thực hiện thống kê: " + ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
