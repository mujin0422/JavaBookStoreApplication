//package GUI.MainContentDiaLog;
//
//import BUS.ChiTietChucNangBUS;
//import BUS.QuyenBUS;
//import BUS.ChucNangBUS;
//import DTO.QuyenDTO;
//import DTO.ChucNangDTO;
//import Utils.UIButton;
//import Utils.UIConstants;
//import Utils.UILabel;
//import Utils.UITextField;
//import java.awt.*;
//import java.util.List;
//import javax.swing.*;
//
//public class AddAndEditDecentralizationGUI extends JDialog {
//    private UITextField txtMaQuyen, txtTenQuyen;
//    private JCheckBox[][] ckbChucNang;
//    private UIButton btnAdd, btnSave, btnCancel;
//    private QuyenBUS quyenBUS;
//    private ChucNangBUS chucNangBUS;
//    private ChiTietChucNangBUS chiTietChucNangBUS;
//    private QuyenDTO quyen;
//    private List<ChucNangDTO> danhSachChucNang;
//
//    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, ChucNangBUS chucNangBUS, String title, String type) {
//        super(parent, title, true);
//        this.quyenBUS = quyenBUS;
//        this.chucNangBUS = chucNangBUS;
//        initComponent(type);
//        this.setLocationRelativeTo(parent);
//        this.setVisible(true);
//    }
//
//    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, ChucNangBUS chucNangBUS, String title, String type, QuyenDTO quyen) {
//        super(parent, title, true);
//        this.quyenBUS = quyenBUS;
//        this.chucNangBUS = chucNangBUS;
//        this.quyen = quyen;
//        initComponent(type);
//        
//        if (quyen != null) {
//            txtMaQuyen.setText(String.valueOf(quyen.getMaQuyen()));
//            txtTenQuyen.setText(quyen.getTenQuyen());
//            txtMaQuyen.setEnabled(false);
//            loadPermissions(quyen.getMaQuyen());
//        }
//        this.setLocationRelativeTo(parent);
//        this.setVisible(true);
//    }
//
//    private void initComponent(String type) {
//        quyenBUS = new QuyenBUS();
//        chucNangBUS = new ChucNangBUS();
//        chiTietChucNangBUS = new ChiTietChucNangBUS();
//        
//        String[] danhMucChucNang = {"QUẢN LÍ SÁCH", "QUẢN LÍ NHÂN VIÊN", "QUẢN LÍ KHÁCH HÀNG","QUẢN LÍ THỂ LOẠI", "QUẢN LÍ TÁC GIẢ", "QUẢN LÍ NHÀ XUẤT BẢN", "QUẢN LÍ NHÀ CUNG CẤP", "QUẢN LÍ PHÂN QUYỀN", "QUẢN LÍ TÀI KHOẢN", "QUẢN LÍ NHẬP HÀNG", "QUẢN LÍ XUẤT HÀNG", "QUẢN LÍ THỐNG KÊ"};
//        this.setSize(700, 500);
//        this.setLayout(new BorderLayout());
//        
//        JPanel pnlTextField = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
//        pnlTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        pnlTextField.setBackground(UIConstants.MAIN_BACKGROUND);
//        JPanel pnlGroupMaQuyen = new JPanel(new FlowLayout());
//        pnlGroupMaQuyen.add(new UILabel("Mã quyền:", 100, 30));
//        pnlGroupMaQuyen.add(txtMaQuyen = new UITextField(100 ,30));
//        JPanel pnlGroupTenQuyen = new JPanel(new FlowLayout());
//        pnlGroupTenQuyen.add(new UILabel("Tên nhóm quyền:", 150, 30));
//        pnlGroupTenQuyen.add(txtTenQuyen = new UITextField(150, 30));
//        pnlTextField.add(pnlGroupMaQuyen);
//        pnlTextField.add(pnlGroupTenQuyen);
//        
//               
//        JPanel pnlLabel = new JPanel(new GridLayout(danhMucChucNang.length + 1, 1));
//        pnlLabel.setPreferredSize(new Dimension(200,0));
//        UILabel labelTenChucNang = new UILabel("CÁC CHỨC NĂNG");
//        pnlLabel.add(labelTenChucNang);
//        for(String dmcn : danhMucChucNang){
//            pnlLabel.add(new UILabel(dmcn));
//        }
//        
//        
//        JPanel pnlCheckBox = new JPanel(new GridLayout(0, 2, 10, 10));
//        danhSachChucNang = chucNangBUS.getAllChucNang();
//        
//        
//        JPanel pnlButton = new JPanel(new FlowLayout());
//        
//        
//        
//        this.add(pnlTextField, BorderLayout.NORTH);
//        this.add(pnlLabel, BorderLayout.WEST);
//        this.add(pnlCheckBox, BorderLayout.CENTER);
//        this.add(pnlButton, BorderLayout.SOUTH);
//
//        btnCancel.addActionListener(e -> dispose());
//        //btnAdd.addActionListener(e -> addDecentralization());
//        //btnSave.addActionListener(e -> saveDecentralization());
//    }
//
//}
