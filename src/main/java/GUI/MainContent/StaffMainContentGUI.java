package GUI.MainContent;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.MainContentDiaLog.AddAndEditStaffGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class StaffMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private NhanVienBUS nhanVienBUS;

    public StaffMainContentGUI() {
        this.nhanVienBUS= new NhanVienBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        
        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addStaff());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 30, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteStaff());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 30, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editStaff());
        btnAdd.setBounds(5, 5, 90, 40);
        btnDelete.setBounds(105, 5, 90, 40);
        btnEdit.setBounds(210, 5, 90, 40);

        int panelWidth = this.getPreferredSize().width; 
        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);
  
        pnlHeader.add(btnAdd);
        pnlHeader.add(btnDelete);
        pnlHeader.add(btnEdit);
        pnlHeader.add(txtSearch);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] columnNames = {"MÃ NHÂN VIÊN", "TÊN NHÂN VIÊN", "EMAIL", "SỐ ĐIỆN THOẠI"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
        addSearchFunctionality();
    }
    
    private void loadTableData(){
        // STEP 1: xóa dữ liệu cũ
        tableModel.setRowCount(0); 
        // STEP 2: tải từng dòng lên bảng  
        ArrayList<NhanVienDTO> listNhanVien = nhanVienBUS.getAllNhanVien();
        for (NhanVienDTO nhanvien : listNhanVien) {
            tableModel.addRow(new Object[]{
                nhanvien.getMaNV(),
                nhanvien.getTenNV(),
                nhanvien.getEmail(),
                nhanvien.getSdt()             
            });
        }
    }
    
    private void addStaff(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditStaffGUI((JFrame) window, nhanVienBUS, "Thêm Nhân Viên", "add");
        loadTableData(); 
    }
    
    private void editStaff(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maNV = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenNV = tableModel.getValueAt(selectedRow, 1).toString();
        String email = tableModel.getValueAt(selectedRow, 2).toString(); 
        String sdt = tableModel.getValueAt(selectedRow, 3).toString(); 
        NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, email, sdt);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditStaffGUI((JFrame) window, nhanVienBUS, "Chỉnh sửa nhân viên", "save", nv);
        loadTableData();
    }
    
    private void deleteStaff(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maNV = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (nhanVienBUS.deleteNhanVien(maNV)) { 
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchStaff(); }
            public void removeUpdate(DocumentEvent e) { searchStaff(); }
            public void changedUpdate(DocumentEvent e) { searchStaff(); }
        });
    }
    
    private void searchStaff() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0); 
        ArrayList<NhanVienDTO> list = nhanVienBUS.searchNhanVien(keyword);
        for (NhanVienDTO obj : list) {
            tableModel.addRow(new Object[]{
                obj.getMaNV(),
                obj.getTenNV(),
                obj.getEmail(),
                obj.getSdt()
            });
        }
    }
}
