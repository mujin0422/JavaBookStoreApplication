package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TaiKhoanBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import DTO.TaiKhoanDTO;
import DTO.TheLoaiDTO;
import GUI.MainContentDiaLog.AddAndEditAuthorGUI;
import GUI.MainContentDiaLog.AddAndEditCategoryGUI;
import GUI.MainContentDiaLog.AddAndEditPublisherGUI;
import javax.swing.JPanel;
import Utils.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class AboutBookMainContentGUI extends JPanel{
    private UIButton addNXB, addTG, addTL;
    private UIButton editNXB, editTG, editTL;
    private UIButton deleteNXB, deleteTG, deleteTL;
    private UIAboutPanel pnlNhaXuatBan, pnlTacGia, pnlTheLoai;
    private UITable tblNhaXuatBan, tblTacGia, tblTheLoai;
    private DefaultTableModel tableModelNXB, tableModelTG, tableModelTL;  
    private NhaXuatBanBUS nxbBus;
    private TacGiaBUS tgBus;
    private TheLoaiBUS tlBus;
    private TaiKhoanBUS taiKhoanBUS;
    
    public AboutBookMainContentGUI(TaiKhoanDTO taiKhoan){
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        
        int pnlWidth = this.getPreferredSize().width - 65;
        int pnlHeight = this.getPreferredSize().height /3 - 32;
        
        //=============================( PANEL NHA XUAT BAN )===========================//
        nxbBus = new NhaXuatBanBUS();
        pnlNhaXuatBan = new UIAboutPanel("/Icon/NhaXuatBan_icon.png","NHÀ XUẤT BẢN", pnlWidth , pnlHeight);
        addNXB = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        addNXB.addActionListener(e -> addNhaXuatBan());
        deleteNXB = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteNXB.addActionListener(e -> deleteNhaXuatBan());
        editNXB = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editNXB.addActionListener(e -> editNhaXuatBan());
        pnlNhaXuatBan.addButton(addNXB);
        pnlNhaXuatBan.addButton(deleteNXB);
        pnlNhaXuatBan.addButton(editNXB);
        
        String[] columnNXBNames = {"MÃ NHÀ XUẤT BẢN", "TÊN NHÀ XUẤT BẢN"};
        tableModelNXB = new DefaultTableModel(columnNXBNames,0);
        tblNhaXuatBan = new UITable(tableModelNXB);
        tblNhaXuatBan.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tblNhaXuatBan.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollNXB = new UIScrollPane(tblNhaXuatBan);
        pnlNhaXuatBan.getPnlContent().add(scrollNXB, BorderLayout.CENTER);
        //===========================( End Panel Nha Xuat Ban )=========================//
        
        
        
        //================================( PANEL TAC GIA )=============================//
        tgBus = new TacGiaBUS();
        pnlTacGia = new UIAboutPanel("/Icon/TacGia_icon.png","TÁC GIẢ", pnlWidth , pnlHeight);
        addTG = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        addTG.addActionListener(e -> addTacGia());
        deleteTG = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteTG.addActionListener(e -> deleteTacGia());
        editTG = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editTG.addActionListener(e -> editTacGia());
        pnlTacGia.addButton(addTG);
        pnlTacGia.addButton(deleteTG);
        pnlTacGia.addButton(editTG);
        
        String[] columnTGNames = {"MÃ TÁC GIẢ", "TÊN TÁC GIẢ"};
        tableModelTG = new DefaultTableModel(columnTGNames,0);
        tblTacGia = new UITable(tableModelTG);
        tblTacGia.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tblTacGia.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollTG = new UIScrollPane(tblTacGia);
        pnlTacGia.getPnlContent().add(scrollTG, BorderLayout.CENTER);
        //==============================( End Panel Tac Gia )===========================//
        
        
        
        //================================( PANEL THE LOAI )============================//
        tlBus = new TheLoaiBUS();
        pnlTheLoai  = new UIAboutPanel("/Icon/TheLoai_icon.png","THỂ LOẠI", pnlWidth , pnlHeight);
        addTL = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        addTL.addActionListener(e -> addTheLoai());
        deleteTL = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteTL.addActionListener(e -> deleteTheLoai());
        editTL = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editTL.addActionListener(e -> editTheLoai());
        pnlTheLoai.addButton(addTL);
        pnlTheLoai.addButton(deleteTL);
        pnlTheLoai.addButton(editTL);
        
        String[] columnTLNames = {"MÃ THỂ LOẠI", "THỂ LOẠI"};
        tableModelTL = new DefaultTableModel(columnTLNames,0);
        tblTheLoai = new UITable(tableModelTL);
        tblTheLoai.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tblTheLoai.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollTL = new UIScrollPane(tblTheLoai);
        pnlTheLoai.getPnlContent().add(scrollTL, BorderLayout.CENTER);
        //==============================( End Panel The Loai )==========================//
        applyPermissions(taiKhoan.getTenDangNhap(), 2);
        

        this.add(pnlNhaXuatBan);
        this.add(pnlTacGia);
        this.add(pnlTheLoai);
        loadTableDataNhaXuatBan();
        loadTableDataTacGia();
        loadTableDataTheLoai();
    }
    
    //==================================================================================
    private void applyPermissions(String username, int maCN) {
        addNXB.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addTG.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addTL.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        editNXB.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editTG.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editTL.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        deleteNXB.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteTG.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteTL.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
    }
    
    
    private void loadTableDataNhaXuatBan(){
        tableModelNXB.setRowCount(0);
        for (NhaXuatBanDTO nxb : nxbBus.getAllNhaXuatBan()) {
            tableModelNXB.addRow(new Object[]{ nxb.getMaNXB(), nxb.getTenNXB()});
        }
    }
    private void addNhaXuatBan() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditPublisherGUI((JFrame) window, nxbBus, "Thêm Nhà Xuất Bản", "add");
        loadTableDataNhaXuatBan();
    }
    private void editNhaXuatBan() {
        int selectedRow = tblNhaXuatBan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một Nhà Xuất Bản để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maNXB = Integer.parseInt(tableModelNXB.getValueAt(selectedRow, 0).toString());
        NhaXuatBanDTO nxb = nxbBus.getById(maNXB);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditPublisherGUI((JFrame) window, nxbBus, "Sửa Nhà Xuất Bản", "save", nxb);
        loadTableDataNhaXuatBan();
    }
    private void deleteNhaXuatBan() {
        int selectedRow = tblNhaXuatBan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một Nhà Xuất Bản để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa Nhà Xuất Bản này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maNXB = Integer.parseInt(tableModelNXB.getValueAt(selectedRow, 0).toString());
            if (nxbBus.deleteNhaXuatBan(maNXB)) {
                JOptionPane.showMessageDialog(this, "Xóa Nhà Xuất Bản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataNhaXuatBan();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa Nhà Xuất Bản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //==================================================================================
    private void loadTableDataTacGia() {
        tableModelTG.setRowCount(0);  
        for (TacGiaDTO tg : tgBus.getAllTacGia()) {
            tableModelTG.addRow(new Object[]{ tg.getMaTG(), tg.getTenTG()});
        }
    }
    private void addTacGia(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditAuthorGUI((JFrame) window, tgBus, "Thêm tác giả", "add");
        loadTableDataTacGia(); 
    }
    private void editTacGia(){
        int selectedRow = tblTacGia.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tác giả để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maTG = Integer.parseInt(tableModelTG.getValueAt(selectedRow, 0).toString());
        TacGiaDTO tg = tgBus.getById(maTG);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditAuthorGUI((JFrame) window, tgBus, "Sửa thông tin tác giả", "save", tg);
        loadTableDataTacGia(); 
    }
    private void deleteTacGia() {
        int selectedRow = tblTacGia.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tác giả để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tác giả này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maSach = Integer.parseInt(tableModelTG.getValueAt(selectedRow, 0).toString());
            if (tgBus.deleteTacGia(maSach)) { 
                JOptionPane.showMessageDialog(this, "Xóa tác giả thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataTacGia();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa tác giả thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //==================================================================================
    private void loadTableDataTheLoai() {
        tableModelTL.setRowCount(0);
        for (TheLoaiDTO tl : tlBus.getAllTheLoai()) {
            tableModelTL.addRow(new Object[]{ tl.getMaTL(), tl.getTenTL() });
        }
    }
    private void addTheLoai() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCategoryGUI((JFrame) window, tlBus, "Thêm thể loại", "add");
        loadTableDataTheLoai();
    }
    private void editTheLoai() {
        int selectedRow = tblTheLoai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thể loại để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maTL = Integer.parseInt(tableModelTL.getValueAt(selectedRow, 0).toString());
        TheLoaiDTO tl = tlBus.getById(maTL);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCategoryGUI((JFrame) window, tlBus, "Sửa thông tin thể loại", "save", tl);
        loadTableDataTheLoai();
    }
    private void deleteTheLoai() {
        int selectedRow = tblTheLoai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thể loại để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thể loại này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maTL = Integer.parseInt(tableModelTL.getValueAt(selectedRow, 0).toString());
            if (tlBus.deleteTheLoai(maTL)) { 
                JOptionPane.showMessageDialog(this, "Xóa thể loại thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataTheLoai();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thể loại thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
