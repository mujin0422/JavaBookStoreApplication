package GUI.MainContent;

import GUI.ThongKeComponent.*;
import Utils.UIButton;
import Utils.UIConstants;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StatisticsMainContentGUI extends JPanel {
    private UIButton btnDoanhThu, btnSach, btnKhachHang;
    private JPanel pnlHeader, pnlContent;
    private ThongKeDoanhThu panelTkDoanhThu;
    private ThongKeSach panelTkSach;
    private ThongKeKhachHang panelTkKhachHang;

    public StatisticsMainContentGUI() {
        this.setBackground(new Color(240, 242, 245)); // Light gray background
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(10, 10));

        initializePanels();
        setupHeader();
        setupContent();

        // Add padding around the main panel
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        // Initial panel
        switchPanel(panelTkDoanhThu);
    }

    private void initializePanels() {
        panelTkDoanhThu = new ThongKeDoanhThu();          // Panel thống kê doanh thu
        panelTkSach = new ThongKeSach();                  // Panel thống kê sách
        panelTkKhachHang = new ThongKeKhachHang();        // Panel thống kê khách hàng
    }

    private void setupHeader() {
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));
        pnlHeader.setBackground(Color.WHITE);

        pnlHeader.setBorder(new CompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new CompoundBorder(
                        new LineBorder(new Color(230, 230, 230), 1),
                        new EmptyBorder(5, 5, 5, 5)
                )
        ));

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("THỐNG KÊ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(44, 62, 80));
        titlePanel.add(titleLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Tạo nút
        btnDoanhThu = new UIButton("menuButton", "DOANH THU", 150, 50, "/Icon/doanhthu.png");
        btnDoanhThu.addActionListener(e -> switchPanel(panelTkDoanhThu));// Add listeners để chuyển đổi các panel
        btnKhachHang = new UIButton("menuButton", "KHÁCH HÀNG", 150, 50, "/Icon/khách hang.png");
        btnKhachHang.addActionListener(e -> switchPanel(panelTkKhachHang));
        btnSach = new UIButton("menuButton", "SÁCH", 150, 50, "/Icon/sach.png");
        btnSach.addActionListener(e -> switchPanel(panelTkSach));

        buttonPanel.add(btnDoanhThu);
        buttonPanel.add(btnKhachHang);
        buttonPanel.add(btnSach);

        pnlHeader.add(titlePanel);
        pnlHeader.add(buttonPanel);

        this.add(pnlHeader, BorderLayout.NORTH);
    }

    private void setupContent() {
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(Color.WHITE);

        pnlContent.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        this.add(pnlContent, BorderLayout.CENTER);
    }

    private void switchPanel(JPanel newPanel) {
        // Chuyển panel hiển thị vào pnlContent
        pnlContent.removeAll();
        newPanel.setBackground(Color.WHITE);
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();

        // Cập nhật trạng thái các nút
        updateButtonStates(newPanel);
    }

    private void updateButtonStates(JPanel activePanel) {
        Color activeColor = new Color(52, 152, 219);  // Blue for active button
        Color inactiveColor = new Color(230, 230, 230); // Gray for inactive button

        btnDoanhThu.setBackground(activePanel == panelTkDoanhThu ? activeColor : inactiveColor);
        btnKhachHang.setBackground(activePanel == panelTkKhachHang ? activeColor : inactiveColor);
        btnSach.setBackground(activePanel == panelTkSach ? activeColor : inactiveColor);
    }
}
