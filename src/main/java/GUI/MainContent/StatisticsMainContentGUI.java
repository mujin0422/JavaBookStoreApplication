package GUI.MainContent;

import GUI.ThongKeComponent.*;
import Utils.UIButton;
import Utils.UIConstants;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StatisticsMainContentGUI extends JPanel {
    private UIButton btnDoanhthu, btnSach, btnKhachHang;
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
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Initial panel
        switchPanel(panelTkDoanhThu);
    }
    
    private void initializePanels() {
        panelTkDoanhThu = new ThongKeDoanhThu();
        panelTkKhachHang = new ThongKeKhachHang();
        panelTkSach = new ThongKeSach();
    }
    
    private void setupHeader() {
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(new CompoundBorder(
            new EmptyBorder(0, 0, 10, 0),
            new CompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1),
                new EmptyBorder(10, 10, 10, 10)
            )
        ));

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("THỐNG KÊ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titlePanel.add(titleLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Create styled buttons
        btnDoanhthu = createStyledButton("DOANH THU", new Color(41, 128, 185));
        btnKhachHang = createStyledButton("KHÁCH HÀNG", new Color(46, 204, 113));
        btnSach = createStyledButton("SÁCH", new Color(155, 89, 182));

        buttonPanel.add(btnDoanhthu);
        buttonPanel.add(btnKhachHang);
        buttonPanel.add(btnSach);

        pnlHeader.add(titlePanel);
        pnlHeader.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlHeader.add(buttonPanel);
        
        this.add(pnlHeader, BorderLayout.NORTH);
    }
    
    private UIButton createStyledButton(String text, Color color) {
        UIButton button = new UIButton("menuButton", text, 160, 45);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (text.equals("DOANH THU")) {
            button.addActionListener(e -> switchPanel(panelTkDoanhThu));
        } else if (text.equals("KHÁCH HÀNG")) {
            button.addActionListener(e -> switchPanel(panelTkKhachHang));
        } else {
            button.addActionListener(e -> switchPanel(panelTkSach));
        }
        
        return button;
    }
    
    private void setupContent() {
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(Color.WHITE);
        pnlContent.setBorder(new CompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        this.add(pnlContent, BorderLayout.CENTER);
    }
    
    private void switchPanel(JPanel newPanel) {
        // Add fade effect
        pnlContent.removeAll();
        newPanel.setBackground(Color.WHITE);
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
        
        // Update button states
        updateButtonStates(newPanel);
    }
    
    private void updateButtonStates(JPanel activePanel) {
        Color inactiveColor = new Color(189, 195, 199);
        
        btnDoanhthu.setBackground(activePanel == panelTkDoanhThu ? new Color(41, 128, 185) : inactiveColor);
        btnKhachHang.setBackground(activePanel == panelTkKhachHang ? new Color(46, 204, 113) : inactiveColor);
        btnSach.setBackground(activePanel == panelTkSach ? new Color(155, 89, 182) : inactiveColor);
    }
}
