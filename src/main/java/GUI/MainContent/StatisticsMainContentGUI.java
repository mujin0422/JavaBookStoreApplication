package GUI.MainContent;

import GUI.ThongKeComponent.*;
import Utils.UIButton;
import Utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class StatisticsMainContentGUI extends JPanel {
    private UIButton btnDoanhThu, btnSach, btnKhachHang;
    private JPanel pnlHeader, pnlContent;
    private ThongKeDoanhThu panelTkDoanhThu;
    private ThongKeSach panelTkSach;
    private ThongKeKhachHang panelTkKhachHang;

    public StatisticsMainContentGUI() {
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));
        
        panelTkDoanhThu = new ThongKeDoanhThu();
        panelTkKhachHang = new ThongKeKhachHang();
        panelTkSach = new ThongKeSach();
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnDoanhThu = new UIButton("menuButton", "DOANH THU", 150, 40, "/Icon/doanhthu.png");
        btnKhachHang = new UIButton("menuButton", "KHÁCH HÀNG", 150, 40, "/Icon/khách hang.png");
        btnSach = new UIButton("menuButton", "SẢN PHẨM", 150, 40, "/Icon/sach.png");
        btnDoanhThu.addActionListener(e -> switchPanel(panelTkDoanhThu));
        btnKhachHang.addActionListener(e -> switchPanel(panelTkKhachHang));
        btnSach.addActionListener(e -> switchPanel(panelTkSach));
        
        pnlButton.add(btnDoanhThu);
        pnlButton.add(btnKhachHang);
        pnlButton.add(btnSach);
        pnlHeader.add(pnlButton, BorderLayout.WEST);
        
        
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.SUB_BACKGROUND);
 
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        switchPanel(panelTkDoanhThu);
    }
    private void switchPanel(JPanel newPanel) {
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }
}
    