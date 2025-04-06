package GUI.MainContent;

import BUS.ChiTietPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SachDTO;
import DTO.TaiKhoanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ImportBookMainContentGUI extends JPanel{
    private UIButton btnAdd, btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnAddToPN, btnSavePN;
    private UITextField txtSearch, txtSoLuong, txtMaPN, txtMaNV, txtTongTien;
    private JTextField txtSearchSach;
    private JComboBox<String> cbMaNCC;
    private JTable tblContent, tblForProduct , tblForForm;
    private JPanel pnlHeader, pnlContent, pnlForm, pnlProduct;
    private DefaultTableModel tableModel, tableModelForProduct;
    private PhieuNhapBUS phieuNhapBUS;
    private NhaCungCapBUS nhaCungCapBUS;
    private SachBUS sachBUS;
    private NhanVienBUS nhanVienBUS;
    private ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;

    public ImportBookMainContentGUI(TaiKhoanDTO taiKhoan) {
        phieuNhapBUS = new PhieuNhapBUS();
        nhanVienBUS = new NhanVienBUS();
        nhaCungCapBUS = new NhaCungCapBUS();
        chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        sachBUS = new SachBUS();
        
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

       //===============================( Panel Header )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));
        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.setBounds(5, 5, 90, 40);
        int panelWidth = this.getPreferredSize().width; 
        txtSearch = new UITextField(190,30);
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);
        pnlHeader.add(btnAdd);
        pnlHeader.add(txtSearch);
        //==============================( End Panel Header )============================//
        
        
        //==================================( PANEL FORM )==============================//
        pnlForm = new JPanel(new BorderLayout());
        pnlForm.setBackground(UIConstants.MAIN_BACKGROUND);
            //NORTH
        JPanel pnlFormNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlFormNorth.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlFormNorth.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlFormNorth.setPreferredSize(new Dimension(0, 100));

        pnlFormNorth.add(new UILabel("Mã phiếu nhập:", 160, 25));
        txtMaPN = new UITextField(350,25);
        pnlFormNorth.add(txtMaPN);
        pnlFormNorth.add(new UILabel("Nhân viên :", 160, 25));
        txtMaNV = new UITextField(350, 25);
        
        
        NhanVienDTO nhanVien = nhanVienBUS.getCurrentStaffByUserName(taiKhoan.getTenDangNhap());
        if (nhanVien != null) {
            txtMaNV.setText(nhanVien.getTenNV()); 
            txtMaNV.setEditable(false); 
        }
        pnlFormNorth.add(txtMaNV);
        
        pnlFormNorth.add(new UILabel("Nhà cung cấp :", 160, 25));
        cbMaNCC = new JComboBox<>();
        cbMaNCC.setPreferredSize(new Dimension(350, 25));
        cbMaNCC.setBackground(UIConstants.WHITE_FONT);
        for(NhaCungCapDTO ncc : nhaCungCapBUS.getAllNhaCungCap()){
            cbMaNCC.addItem(ncc.getTenNCC());
        }
        pnlFormNorth.add(cbMaNCC);
            //CENTER
        String[] columns = {"MÃ SÁCH", "TÊN SÁCH", "SỐ LƯỢNG", "THÀNH TIỀN"};
        DefaultTableModel tableModelForForm = new DefaultTableModel(columns, 0);
        tblForForm = new JTable(tableModelForForm);
        tblForForm.setRowHeight(30);
        UIScrollPane scrollPaneForForm = new UIScrollPane(tblForForm);
        scrollPaneForForm.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            //SOUTH
        JPanel pnlFormSouth = new JPanel(new GridLayout(2, 1, 5, 5)); 
        pnlFormSouth.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER,25,5)); 
        btnXoaKhoiPhieu = new UIButton("delete", "XÓA KHỎI PHIẾU", 130, 30);
        btnSuaSoLuong = new UIButton("edit", "SỬA SỐ LƯỢNG", 130, 30);
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        pnl1.add(btnSuaSoLuong);
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
        pnl2.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlGroupTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlGroupTongTien.add(new UILabel("Tổng tiền:",80,30));
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        txtTongTien = new UITextField(100, 30);
        txtTongTien.setEditable(false); 
        pnlGroupTongTien.add(txtTongTien);
        btnAddToPN = new UIButton("add", "THÊM", 100, 25);
        btnAddToPN.addActionListener(e -> addPhieuNhap());
        pnl2.add(pnlGroupTongTien, BorderLayout.WEST);
        pnl2.add(btnAddToPN, BorderLayout.EAST);
        pnlFormSouth.add(pnl1);
        pnlFormSouth.add(pnl2);
        
        pnlForm.add(pnlFormNorth, BorderLayout.NORTH);
        pnlForm.add(scrollPaneForForm, BorderLayout.CENTER);
        pnlForm.add(pnlFormSouth, BorderLayout.SOUTH);
        //================================( End Panel Form )============================//
        
        //=================================( PANEL PRODUCT )============================//
        pnlProduct = new JPanel(new BorderLayout(5,5));
        pnlProduct.setPreferredSize(new Dimension(550, 0));
        pnlProduct.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlProduct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtSearchSach = new JTextField("Tìm kiếm theo tên sách");
        txtSearchSach.setPreferredSize(new Dimension(400 ,30));
        txtSearchSach.setForeground(Color.GRAY);
        txtSearchSach.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txtSearchSach.getText().equals("Tìm kiếm theo tên sách")) {
                    txtSearchSach.setText("");
                    txtSearchSach.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (txtSearchSach.getText().isEmpty()) {
                    txtSearchSach.setForeground(Color.GRAY);
                    txtSearchSach.setText("Tìm kiếm theo tên sách");
                }
            }
        });
        
        String[] columnForProduct = {"MÃ SÁCH", "TÊN SÁCH", "GIÁ"};
        tableModelForProduct = new DefaultTableModel(columnForProduct, 0);
        tblForProduct = new JTable(tableModelForProduct);
        tblForProduct.setDefaultEditor(Object.class, null);
        tblForProduct.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblForProduct.getTableHeader().setPreferredSize(new Dimension(0,30));
        tblForProduct.setRowHeight(30);
        UIScrollPane scrollPaneForProduct = new UIScrollPane(tblForProduct);
        
        JPanel pnlSouthOfproduct = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlSouthOfproduct.setBackground(UIConstants.MAIN_BACKGROUND);
        UILabel lblSoLuong = new UILabel("Số lượng:", 70, 30);
        lblSoLuong.setForeground(Color.BLACK);
        pnlSouthOfproduct.add(lblSoLuong);
        txtSoLuong = new UITextField(40, 30);
        txtSoLuong.setHorizontalAlignment(JTextField.CENTER);
        pnlSouthOfproduct.add(txtSoLuong);
        btnThemVaoPhieu = new UIButton("add","THÊM VÀO PHIẾU", 140, 30);
        btnThemVaoPhieu.addActionListener(e -> addToTableForForm());
        pnlSouthOfproduct.add(btnThemVaoPhieu);
        
        pnlProduct.add(txtSearchSach, BorderLayout.NORTH);
        pnlProduct.add(scrollPaneForProduct, BorderLayout.CENTER);
        pnlProduct.add(pnlSouthOfproduct, BorderLayout.SOUTH);
        //===============================( End Panel Product )==========================//
        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel(new BorderLayout(5,5));
        pnlContent.setPreferredSize(new Dimension(0, 200));
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] columnNames = {"MÃ PHIẾU NHẬP", "NHÂN VIÊN", "NHÀ CUNG CẤP", "TỔNG TIỀN", "NGÀY GHI PHIẾU"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new JTable(tableModel);
        tblContent.setDefaultEditor(Object.class, null);
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.getTableHeader().setPreferredSize(new Dimension(0,30));
        tblContent.setRowHeight(30);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlForm, BorderLayout.CENTER);
        this.add(pnlProduct, BorderLayout.EAST);
        this.add(pnlContent, BorderLayout.SOUTH);
        loadTableData();
        loadTableProduct();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        ArrayList<PhieuNhapDTO> listPN = phieuNhapBUS.getAllPhieuNhap();
        for(PhieuNhapDTO pn : listPN){
            tableModel.addRow(new Object[]{
                pn.getMaPN(),
                nhanVienBUS.getTenNvByMaNv(pn.getMaNV()),
                nhaCungCapBUS.getTenNccByMaNcc(pn.getMaNCC()),
                pn.getTongTien(),
                pn.getNgayNhap()
            });
        }     
    }
    private void loadTableProduct(){
        tableModelForProduct.setRowCount(0);
        ArrayList<SachDTO> listSach = sachBUS.getAllSach();
        for(SachDTO sach : listSach){
            tableModelForProduct.addRow(new Object[]{
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getGiaSach()
            });
        }
    }
    
    private void addToTableForForm() {
        String soLuongText = txtSoLuong.getText().trim();
        int selectedRow = tblForProduct.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để thêm vào phiếu", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (soLuongText.isEmpty() || !soLuongText.matches("\\d+") || Integer.parseInt(soLuongText) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int soLuong = Integer.parseInt(soLuongText);
        String maSach = tblForProduct.getValueAt(selectedRow, 0).toString();
        String tenSach = tblForProduct.getValueAt(selectedRow, 1).toString();
        int giaBan = Integer.parseInt(tblForProduct.getValueAt(selectedRow, 2).toString());
        int thanhTien = giaBan * soLuong;
        DefaultTableModel model = (DefaultTableModel) tblForForm.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maSachTrongBang = model.getValueAt(i, 0).toString();
            if (maSach.equals(maSachTrongBang)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong phiếu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        model.addRow(new Object[]{maSach, tenSach, soLuong, thanhTien});
        
        calcTongTien();
        txtSoLuong.setText("");
    }
    
    private void calcTongTien() {
        int tongTien = 0;
        for (int i = 0; i < tblForForm.getRowCount(); i++) {
            int thanhTien = (int) tblForForm.getValueAt(i, 3); 
            tongTien += thanhTien;
        }
        txtTongTien.setText(String.valueOf(tongTien));
    }
    public Date getCurrentDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDateStr = sdf.format(new Date());  
            return sdf.parse(currentDateStr); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;  
        }
    }
    
    private void resetFormInput(){
        
    }
    private boolean checkFormInput(){
        try {
            String maPnStr = txtMaPN.getText().trim();
            if (maPnStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maPn= Integer.parseInt(maPnStr);
            if (maPn <= 0) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (tblForForm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void addPhieuNhap(){
        if(!checkFormInput()) return;
        int maPN = Integer.parseInt(txtMaPN.getText().trim());
        int maNV = nhanVienBUS.getMaNvByTenNv(txtMaNV.getText().trim());
        int maNCC = nhaCungCapBUS.getMaNccByTenNcc(cbMaNCC.getSelectedItem().toString());
        int tongTien = Integer.parseInt(txtTongTien.getText().trim());
        Date ngayNhap = getCurrentDate();
        if (phieuNhapBUS.existsPhieuNhap(maPN)) {
            JOptionPane.showMessageDialog(this, "Mã phiếu nhập đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PhieuNhapDTO phieuNhap = new PhieuNhapDTO(maPN, maNV, maNCC, tongTien, ngayNhap);
        if (phieuNhapBUS.addPhieuNhap(phieuNhap)) {
            DefaultTableModel tableModelForForm = (DefaultTableModel) tblForForm.getModel();
            for (int i = 0; i < tableModelForForm.getRowCount(); i++) {
                int maSach = Integer.parseInt(tableModelForForm.getValueAt(i, 0).toString());
                int soluong = Integer.parseInt(tableModelForForm.getValueAt(i, 2).toString());
                int giaNhap = Integer.parseInt(tableModelForForm.getValueAt(i, 3).toString());
                ChiTietPhieuNhapDTO chiTietPhieuNhap = new ChiTietPhieuNhapDTO(maPN, maSach, soluong, giaNhap);
                boolean isChiTietAdded = chiTietPhieuNhapBUS.addChiTietPhieuNhap(chiTietPhieuNhap);
                if (!isChiTietAdded) {
                    JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    //cap nhat so lai so luong ton
                int soLuongHienTai = sachBUS.getSoLuongTonSach(maSach); 
                int soLuongMoi = soLuongHienTai + soluong;
                sachBUS.updateSoLuongTonSach(maSach, soLuongMoi);
            }
        }
        JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
        
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof JFrame)) {
            parent = parent.getParent();
        }
        if (parent != null) {
            for (Component comp : ((JFrame) parent).getContentPane().getComponents()) {
                if (comp instanceof BookMainContentGUI) {
                    ((BookMainContentGUI) comp).loadTableData();
                    break;
                }
            }
        }
    }



    
}
