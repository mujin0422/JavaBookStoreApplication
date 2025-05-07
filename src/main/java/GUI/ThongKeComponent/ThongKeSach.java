package GUI.ThongKeComponent;

import BUS.SachBUS;
import DTO.ThongKeSachDTO;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThongKeSach extends JPanel {
    private UITable table;
    private JDateChooser dateChooserBatDau, dateChooserKetThuc;
    private SachBUS sachBUS;
    private DefaultTableModel tableModel;

    public ThongKeSach() {
        sachBUS = new SachBUS();
        setLayout(new BorderLayout(5, 5));
        setBorder(new EmptyBorder(5, 5, 5, 5)); 

        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        headerPanel.setBackground(UIConstants.MAIN_BACKGROUND); 
        headerPanel.add(new UILabel("Từ:",30,30));
        dateChooserBatDau = new JDateChooser();
        dateChooserBatDau.setPreferredSize(new Dimension(150, 30));
        dateChooserBatDau.setLocale(new Locale("vi", "VN")); 
        headerPanel.add(dateChooserBatDau);

        headerPanel.add(new UILabel("Đến:",35,30));
        dateChooserKetThuc = new JDateChooser();
        dateChooserKetThuc.setPreferredSize(new Dimension(150, 30));
        dateChooserKetThuc.setLocale(new Locale("vi", "VN"));
        headerPanel.add(dateChooserKetThuc);

        UIButton btnThongKe = new UIButton("add","THỐNG KÊ", 100, 30);
        btnThongKe.addActionListener(e -> loadData());
        headerPanel.add(btnThongKe);

        add(headerPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"MÃ SÁCH", "TÊN SÁCH", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN", "SỐ LƯỢNG TỒN"}, 0);
        table = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        Date ngayBatDauUtil = dateChooserBatDau.getDate();
        Date ngayKetThucUtil = dateChooserKetThuc.getDate();
        if (ngayBatDauUtil == null || ngayKetThucUtil == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (ngayBatDauUtil.after(ngayKetThucUtil)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(ngayBatDauUtil);
        String endDateStr = sdf.format(ngayKetThucUtil);

        try {
            java.sql.Date ngayBatDauSql = java.sql.Date.valueOf(startDateStr);
            java.sql.Date ngayKetThucSql = java.sql.Date.valueOf(endDateStr);
            ArrayList<ThongKeSachDTO> data = sachBUS.getThongKeSach(ngayBatDauSql, ngayKetThucSql);
            tableModel.setRowCount(0); 
            for (ThongKeSachDTO sach : data) {
                tableModel.addRow(new Object[]{
                    sach.getMaSach(), 
                    sach.getTenSach(),
                    sach.getSoLuongNhap(), 
                    sach.getSoLuongXuat(),
                    sach.getSoLuongTon()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tải dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }  
}
