package GUI;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.MainContent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public final class MainLayoutGUI extends JFrame {
    private StatisticsMainContentGUI statisticsPanel;
    private BookMainContentGUI bookPanel;
    private CustomerMainContentGUI customerPanel;
    private StaffMainContentGUI staffPanel;
    private AboutBookMainContentGUI aboutBookPanel;
    private SupplierMainContentGUI supplierPanel;
    private DecentralizationMainContentGUI decentralizationPanel;
    private ImportBookMainContentGUI importBookPanel; 
    private ExportBookMainContentGUI exportBookPanel;
    private AccountMainContentGUI accountPanel;
    
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
        
        statisticsPanel = new StatisticsMainContentGUI();
        bookPanel = new BookMainContentGUI();
        customerPanel = new CustomerMainContentGUI();
        staffPanel = new StaffMainContentGUI();
        aboutBookPanel = new AboutBookMainContentGUI();
        supplierPanel = new SupplierMainContentGUI();
        decentralizationPanel = new DecentralizationMainContentGUI();
        importBookPanel = new ImportBookMainContentGUI(taiKhoan);
        exportBookPanel = new ExportBookMainContentGUI(taiKhoan);
        accountPanel = new AccountMainContentGUI();
        
        for (int i = 1; i <= 10; i++) {
            String label = buttonInfo[i - 1][0];
            String iconPath = buttonInfo[i - 1][1];
            UIButton button = new UIButton("menuButton", label, 180, 40);
            button.setButtonIcon(iconPath);
            buttons.add(button);
            JPanel targetPanel;
            switch (i) {
                case 1 -> targetPanel = bookPanel;             
                case 2 -> targetPanel = aboutBookPanel;          
                case 3 -> targetPanel = customerPanel;           
                case 4 -> targetPanel = staffPanel;              
                case 5 -> targetPanel = accountPanel;            
                case 6 -> targetPanel = supplierPanel;           
                case 7 -> targetPanel = importBookPanel;         
                case 8 -> targetPanel = exportBookPanel;        
                case 9 -> targetPanel = decentralizationPanel;  
                case 10 -> targetPanel = statisticsPanel;       
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

        JLabel lblWelcome = new JLabel();
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblWelcome.setForeground(UIConstants.BLACK_FONT);
        lblWelcome.setText("Xin chào, " + taiKhoan.getTenDangNhap() + "!");
        
        panel.add(lblWelcome);
        return panel;
    }
    
}