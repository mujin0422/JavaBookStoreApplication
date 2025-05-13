package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.MainContent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class MainLayoutGUI extends JFrame {
    private ArrayList<UIButton> buttons; 
    private TaiKhoanDTO taiKhoan;
    private TaiKhoanBUS taiKhoanBus;
    private JPanel pnlChucNang, pnlMenu, pnlContent, pnlTitle;
    private UIButton btnLogout;
    
    String[][] buttonInfo = {
        {"SÁCH", "/Icon/Sach_icon.png"},
        {"THÔNG TIN SÁCH", "/Icon/ThongTinSach_icon.png"},
        {"KHÁCH HÀNG", "/Icon/KhachHang_icon.png"},
        {"NHÂN VIÊN", "/Icon/NhanVien_icon.png"},
        {"TÀI KHOẢN", "/Icon/TaiKhoan_icon.png"},
        {"NHÀ CUNG CẤP", "/Icon/NhaCungCap_icon.png"},
        {"NHẬP HÀNG", "/Icon/NhapHang_icon.png"},
        {"XUẤT HÀNG", "/Icon/XuatHang_icon.png"},
        {"VAI TRÒ", "/Icon/VaiTro_icon.png"},
        {"PHÂN QUYỀN", "/Icon/PhanQuyen_icon.png"},
        {"THỐNG KÊ", "/Icon/ThongKe_icon.png"},
    };

    public MainLayoutGUI(TaiKhoanDTO taiKhoan) {
        this.taiKhoan = taiKhoan;
        initComponent();
    }

    public void initComponent() {
        this.setSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        this.getContentPane().setBackground(UIConstants.SUB_BACKGROUND);
        this.getContentPane().setLayout(new BorderLayout(5, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        taiKhoanBus = new TaiKhoanBUS();

        //==============================( PANEL TITLE )=================================//
        pnlTitle = new JPanel(null);
        pnlTitle.setBackground(UIConstants.MAIN_BUTTON);
        pnlTitle.setPreferredSize(new Dimension(UIConstants.WIDTH_TITLE, UIConstants.HEIGHT_TITLE));

        JLabel lblTitle = new JLabel("HỆ THÔNG QUẢN LÝ CỬA HÀNG SÁCH");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setForeground(UIConstants.WHITE_FONT);
        lblTitle.setBounds(10, 5, 450, 40);

        ImageIcon minimizeIcon = new ImageIcon(getClass().getResource("/Icon/minimize_icon.png"));
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/Icon/close_icon.png"));
        Image imgMinimize = minimizeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imgClose = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        JButton btnMinimize = new JButton(new ImageIcon(imgMinimize));
        btnMinimize.setBackground(UIConstants.MAIN_BACKGROUND);
        btnMinimize.setBorder(null);
        btnMinimize.setBounds(UIConstants.WIDTH - 80, 10, 30, 30);
        btnMinimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton btnClose = new JButton(new ImageIcon(imgClose));
        btnClose.setBackground(UIConstants.MAIN_BACKGROUND);
        btnClose.setBorder(null);
        btnClose.setBounds(UIConstants.WIDTH - 40, 10, 30, 30);
        btnClose.addActionListener(e -> System.exit(0));

        pnlTitle.add(lblTitle);
        pnlTitle.add(btnMinimize);
        pnlTitle.add(btnClose);
        //==============================( End Panel Title )=============================//

        
        //================================( PANEL MENU )================================//
        pnlMenu = new JPanel(new BorderLayout());
        pnlMenu.setPreferredSize(new Dimension(UIConstants.WIDTH_MENU, UIConstants.HEIGHT_MENU));
        
        pnlChucNang = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlChucNang.setBackground(UIConstants.MAIN_BACKGROUND);
        btnLogout = new UIButton("menuButton", "ĐĂNG XUẤT", 0, 50, "/Icon/logout_icon.png");
        btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
        btnLogout.addActionListener(e -> logout());
         
        pnlMenu.add(pnlChucNang, BorderLayout.CENTER);
        pnlMenu.add(btnLogout, BorderLayout.SOUTH);
        //==============================( End Panel Menu )==============================//
        
        
        //===============================( PANEL CONTENT )==============================//
        buttons = new ArrayList<>(); 
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.SUB_BACKGROUND);
     
        for (int i = 1; i <= 11; i++) {
            String label = buttonInfo[i - 1][0];
            String iconPath = buttonInfo[i - 1][1];
            UIButton button = new UIButton("menuButton", label, 180, 40, iconPath);
            buttons.add(button);
            JPanel targetPanel;
            switch (i) {
                case 1 -> targetPanel = new BookMainContentGUI(taiKhoan);            
                case 2 -> targetPanel = new AboutBookMainContentGUI(taiKhoan);        
                case 3 -> targetPanel = new CustomerMainContentGUI(taiKhoan);       
                case 4 -> targetPanel = new StaffMainContentGUI(taiKhoan);             
                case 5 -> targetPanel = new AccountMainContentGUI(taiKhoan);            
                case 6 -> targetPanel = new SupplierMainContentGUI(taiKhoan);          
                case 7 -> targetPanel = new ImportBookMainContentGUI(taiKhoan);         
                case 8 -> targetPanel = new ExportBookMainContentGUI(taiKhoan);      
                case 9 -> targetPanel = new RoleMainContentGUI(taiKhoan); 
                case 10 -> targetPanel = new DecentralizationMainContentGUI(taiKhoan); 
                case 11 -> targetPanel = new StatisticsMainContentGUI();      
                default -> targetPanel = WelcomePanel();
            }
            button.addActionListener(e -> {
                switchPanel(targetPanel);
                if (targetPanel instanceof ReloadablePanel reloadablePanel) {
                    reloadablePanel.loadTableData();
                }
            });
        }
        addChucNang();
        switchPanel(WelcomePanel());
        //==============================( End Panel Content )===========================//
        
        
        this.getContentPane().add(pnlMenu, BorderLayout.WEST);
        this.getContentPane().add(pnlContent, BorderLayout.CENTER);
        this.getContentPane().add(pnlTitle, BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void addChucNang() {
        pnlChucNang.removeAll(); 
        for (int maCn : taiKhoanBus.getDanhSachMaCnByUsername(taiKhoan.getTenDangNhap())) {
            if (maCn <= buttons.size()) {
                pnlChucNang.add(buttons.get(maCn - 1));
            }
        }
        pnlChucNang.revalidate();
        pnlChucNang.repaint();
    }

    private void switchPanel(JPanel newPanel) {
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }
    
    private void logout(){
        this.dispose(); 
        new LoginGUI(); 
    }
    
    private JPanel WelcomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(UIConstants.MAIN_BACKGROUND);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); 
        JLabel lblWelcome = new JLabel();
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 60));
        lblWelcome.setForeground(UIConstants.BLACK_FONT);
        lblWelcome.setText("Xin chào, " + taiKhoan.getTenDangNhap() + "!");
        panel.add(lblWelcome, gbc);
        gbc.gridy++; 
        JLabel lblMessage = new JLabel();
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 44));
        lblMessage.setForeground(UIConstants.BLACK_FONT);
        lblMessage.setText("Chúc bạn có một ngày làm việc vui vẻ!");
        panel.add(lblMessage, gbc);
        return panel;
    }
}