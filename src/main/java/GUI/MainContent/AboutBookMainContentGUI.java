package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import GUI.MainContentDiaLog.AddAndEditAuthorGUI;
import GUI.MainContentDiaLog.AddAndEditCategoryGUI;
import GUI.MainContentDiaLog.AddAndEditPublisherGUI;
import javax.swing.JPanel;
import Utils.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class AboutBookMainContentGUI extends JPanel{
    private UIAboutPanel pnlNhaXuatBan, pnlTacGia, pnlTheLoai;
    private JTable tblNhaXuatBan, tblTacGia, tblTheLoai;
    
    private DefaultTableModel tableModelNXB, tableModelTG, tableModelTL;  
    private NhaXuatBanBUS nxbBus;
    private TacGiaBUS tgBus;
    private TheLoaiBUS tlBus;
    
    public AboutBookMainContentGUI(){
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        
        int pnlWidth = this.getPreferredSize().width - 65;
        int pnlHeight = this.getPreferredSize().height /3 - 32;
        
        //=============================( PANEL NHA XUAT BAN )===========================//
        nxbBus = new NhaXuatBanBUS();
        pnlNhaXuatBan = new UIAboutPanel("/Icon/NhaXuatBan_icon.png","NHÀ XUẤT BẢN", pnlWidth , pnlHeight);
        UIButton addNXB = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addNXB.addActionListener(e -> addNhaXuatBan());
        UIButton deleteNXB = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteNXB.addActionListener(e -> deleteNhaXuatBan());
        UIButton editNXB = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editNXB.addActionListener(e -> editNhaXuatBan());
        pnlNhaXuatBan.addButton(addNXB);
        pnlNhaXuatBan.addButton(deleteNXB);
        pnlNhaXuatBan.addButton(editNXB);
        
        String[] columnNXBNames = {"MÃ NHÀ XUẤT BẢN", "TÊN NHÀ XUẤT BẢN"};
        tableModelNXB = new DefaultTableModel(columnNXBNames,0);
        tblNhaXuatBan = new JTable(tableModelNXB);
        tblNhaXuatBan.setDefaultEditor(Object.class,null);

        tblNhaXuatBan.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblNhaXuatBan.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblNhaXuatBan.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblNhaXuatBan.setRowHeight(25);
        
        JScrollPane scrollNXB = new JScrollPane(tblNhaXuatBan);
        scrollNXB.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollNXB.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        
        pnlNhaXuatBan.getPnlContent().add(scrollNXB, BorderLayout.CENTER);
        //===========================( End Panel Nha Xuat Ban )=========================//
        
        
        
        //================================( PANEL TAC GIA )=============================//
        tgBus = new TacGiaBUS();
        pnlTacGia = new UIAboutPanel("/Icon/TacGia_icon.png","TÁC GIẢ", pnlWidth , pnlHeight);
        UIButton addTG = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addTG.addActionListener(e -> addTacGia());
        UIButton deleteTG = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteTG.addActionListener(e -> deleteTacGia());
        UIButton editTG = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editTG.addActionListener(e -> editTacGia());
        pnlTacGia.addButton(addTG);
        pnlTacGia.addButton(deleteTG);
        pnlTacGia.addButton(editTG);
        
        String[] columnTGNames = {"MÃ TÁC GIẢ", "TÊN TÁC GIẢ"};
        tableModelTG = new DefaultTableModel(columnTGNames,0);
        tblTacGia = new JTable(tableModelTG);
        tblTacGia.setDefaultEditor(Object.class,null);
        
        tblTacGia.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblTacGia.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblTacGia.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblTacGia.setRowHeight(25);
        
        JScrollPane scrollTG = new JScrollPane(tblTacGia);
        scrollTG.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollTG.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        pnlTacGia.getPnlContent().add(scrollTG, BorderLayout.CENTER);
        //==============================( End Panel Tac Gia )===========================//
        
        
        
        //================================( PANEL THE LOAI )============================//
        tlBus = new TheLoaiBUS();
        pnlTheLoai  = new UIAboutPanel("/Icon/TheLoai_icon.png","THỂ LOẠI", pnlWidth , pnlHeight);
        UIButton addTL = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addTL.addActionListener(e -> addTheLoai());
        UIButton deleteTL = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteTL.addActionListener(e -> deleteTheLoai());
        UIButton editTL = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editTL.addActionListener(e -> editTheLoai());
        pnlTheLoai.addButton(addTL);
        pnlTheLoai.addButton(deleteTL);
        pnlTheLoai.addButton(editTL);
        
        String[] columnTLNames = {"MÃ THỂ LOẠI", "THỂ LOẠI"};
        tableModelTL = new DefaultTableModel(columnTLNames,0);
        tblTheLoai = new JTable(tableModelTL);
        tblTheLoai.setDefaultEditor(Object.class,null);
        
        tblTheLoai.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblTheLoai.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblTheLoai.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblTheLoai.setRowHeight(25);
        
        JScrollPane scrollTL = new JScrollPane(tblTheLoai);
        scrollTL.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollTL.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        pnlTheLoai.getPnlContent().add(scrollTL, BorderLayout.CENTER);
        //==============================( End Panel The Loai )==========================//
        
        

        this.add(pnlNhaXuatBan);
        this.add(pnlTacGia);
        this.add(pnlTheLoai);
        loadTableDataNhaXuatBan();
        loadTableDataTacGia();
        loadTableDataTheLoai();
    }
    
    //==================================================================================
    private void loadTableDataNhaXuatBan(){
        tableModelNXB.setRowCount(0);
        ArrayList<NhaXuatBanDTO> listNXB = nxbBus.getAllNhaXuatBan();
        for (NhaXuatBanDTO nxb : listNXB) {
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
        String tenNXB = tableModelNXB.getValueAt(selectedRow, 1).toString();

        NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNXB, tenNXB);
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
        ArrayList<TacGiaDTO> listTG = tgBus.getAllTacGia();
        for (TacGiaDTO tg : listTG) {
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
        String tenTG = tableModelTG.getValueAt(selectedRow, 1).toString();

        TacGiaDTO tg = new TacGiaDTO(maTG, tenTG);
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
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa cuốn sách này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maSach = Integer.parseInt(tableModelTG.getValueAt(selectedRow, 0).toString());
            if (tgBus.deleteTacGia(maSach)) { 
                JOptionPane.showMessageDialog(this, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataTacGia();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //==================================================================================
    private void loadTableDataTheLoai() {
        tableModelTL.setRowCount(0);
        ArrayList<TheLoaiDTO> listTL = tlBus.getAllTheLoai();
        for (TheLoaiDTO tl : listTL) {
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
        String tenTL = tableModelTL.getValueAt(selectedRow, 1).toString();

        TheLoaiDTO tl = new TheLoaiDTO(maTL, tenTL);
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
