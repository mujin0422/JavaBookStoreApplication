package GUI.MainContent;

import BUS.TaiKhoanBUS;
import BUS.VaiTroBUS;
import DTO.TaiKhoanDTO;
import DTO.VaiTroDTO;
import GUI.MainContentDiaLog.AddAndEditRoleGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class RoleMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private VaiTroBUS vaiTroBUS;
    private TaiKhoanBUS taiKhoanBUS;

    public RoleMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.vaiTroBUS = new VaiTroBUS();
        this.taiKhoanBUS = new TaiKhoanBUS();
        
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addAccount());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteAccount());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editAccount());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        applyPermissions(taiKhoan.getTenDangNhap(), 9);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Tạo bảng dữ liệu
        String[] columnNames = {"MÃ VAI TRÒ", "TÊN VAI TRÒ", "SỐ LƯỢNG NHÂN VIÊN CÓ VAI TRÒ"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void applyPermissions(String username, int maCN) {
        btnAdd.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        btnEdit.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        btnDelete.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        for(VaiTroDTO vt : vaiTroBUS.getAllVaiTro()){
            tableModel.addRow(new Object[]{
                vt.getMaVT(),
                vt.getTenVT(),
                vaiTroBUS.getSoLuongNhanVienHasVaiTro(vt.getMaVT())
            });
        }
    }
    
    private void addAccount(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRoleGUI((JFrame) window, vaiTroBUS, "Thêm Vai Trò", "add");
        loadTableData();
    }
    
    private void editAccount(){
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một vai trò để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        VaiTroDTO vt = vaiTroBUS.getById(Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()));
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRoleGUI((JFrame) window, vaiTroBUS, "Chỉnh sửa vai trò", "save", vt);
        loadTableData();
    }
    
    private void deleteAccount(){
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một vai trò để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maVT = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if( vaiTroBUS.deleteVaiTro(maVT)){
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
