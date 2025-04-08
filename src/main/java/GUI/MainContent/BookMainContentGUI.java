package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.SachBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.SachDTO;
import GUI.MainContentDiaLog.*;
import Utils.UIConstants;
import Utils.UIButton;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class BookMainContentGUI extends JPanel {
    private UIButton btnAdd, btnDelete, btnEdit, btnView;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private SachBUS sachBUS;

    public BookMainContentGUI() {
        sachBUS = new SachBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null);
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addBook());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 30, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteBook());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 30, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editBook());
        btnView = new UIButton("menuButton", "XEM", 100, 30, "/Icon/chitiet_icon.png");
        btnView.addActionListener(e -> viewBookDetails());

        btnAdd.setBounds(5, 5, 90, 40);
        btnDelete.setBounds(105, 5, 90, 40);
        btnEdit.setBounds(210, 5, 90, 40);
        btnView.setBounds(315, 5, 90, 40);

        int panelWidth = this.getPreferredSize().width;
        cbFilter = new JComboBox<>();
        cbFilter.setBounds(panelWidth - 380, 10, 150, 30);
        cbFilter.addItem("Tất cả");
        loadListPublishers();
        cbFilter.addActionListener(e -> filterBook());
        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);

        pnlHeader.add(btnAdd);
        pnlHeader.add(btnDelete);
        pnlHeader.add(btnEdit);
        pnlHeader.add(btnView);
        pnlHeader.add(cbFilter);
        pnlHeader.add(txtSearch);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
        String[] columnNames = {"MÃ SÁCH", "TÊN SÁCH", "GIÁ", "NHÀ XUẤT BẢN", "TỒN KHO"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
       
        UIScrollPane scrollPane = new UIScrollPane(tblContent);  
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//

        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        addSearchFunctionality();
        loadTableData();
    }

    public void loadTableData() {
        // STEP 1: xóa dữ liệu cũ
        tableModel.setRowCount(0); 
        // STEP 2: tải từng dòng lên bảng  
        ArrayList<SachDTO> listSach = sachBUS.getAllSach();
        for (SachDTO sach : listSach) {
            tableModel.addRow(new Object[]{
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getGiaSach(),
                sachBUS.getTenNxbByMaSach(sach.getMaSach()),
                sach.getSoLuongTon()
            });
        }
    }

    private void addBook() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditBookGUI((JFrame) window, sachBUS, "Thêm sách", "add");
        loadTableData(); 
    }

    private void editBook() {
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một cuốn sách để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maSach = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenSach = tableModel.getValueAt(selectedRow, 1).toString();
        int giaSach = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
        String tenNXB = tableModel.getValueAt(selectedRow, 3).toString(); 
        int soLuongTon = Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString());

        int maNXB = 0;
        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            if (nxb.getTenNXB().equals(tenNXB)) {
                maNXB = nxb.getMaNXB();
                break;
            }
        }
        SachDTO sach = new SachDTO(maSach, tenSach, giaSach, soLuongTon, maNXB);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditBookGUI((JFrame) window, sachBUS, "Chỉnh sửa sách", "save", sach);
        loadTableData();
    }

    
    private void deleteBook() {
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một cuốn sách để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa cuốn sách này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maSach = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (sachBUS.deleteSach(maSach)) { 
                JOptionPane.showMessageDialog(this, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchBook(); }
            public void removeUpdate(DocumentEvent e) { searchBook(); }
            public void changedUpdate(DocumentEvent e) { searchBook(); }
        });
    }
    
    private void searchBook() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0); 
        ArrayList<SachDTO> listSach = sachBUS.searchSach(keyword);
        for (SachDTO sach : listSach) {
            tableModel.addRow(new Object[]{
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getGiaSach(),
                sachBUS.getTenNxbByMaSach(sach.getMaSach()),
                sach.getSoLuongTon()
            });
        }
    }
    
    private void loadListPublishers() {
        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            cbFilter.addItem(nxb.getTenNXB());
        }
    }

    private void filterBook() {
        String selectedPublisher = (String) cbFilter.getSelectedItem();
        if (selectedPublisher.equals("Tất cả")) {
            loadTableData(); 
            return;
        }
        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        int maNXB = 0;
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            if (nxb.getTenNXB().equals(selectedPublisher)) {
                maNXB = nxb.getMaNXB();
                break;
            }
        }
        if (maNXB != 0) {
            ArrayList<SachDTO> listSach = sachBUS.filterSach(maNXB);
            tableModel.setRowCount(0); 
            for (SachDTO sach : listSach) {
                tableModel.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getGiaSach(),
                    selectedPublisher,
                    sach.getSoLuongTon()
                });
            }
        }
    }
    
    private void viewBookDetails() {
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một cuốn sách để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maSach = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        SachDTO sach = sachBUS.getSachById(maSach);
        // Lấy thông tin Nhà xuất bản
        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        String tenNXB = nhaXuatBanBUS.getTenNXBById(sach.getMaNXB());
        // Lấy danh sách tác giả
        TacGiaBUS tacGiaBUS = new TacGiaBUS();
        ArrayList<String> dsTacGia = tacGiaBUS.getTacGiaByMaSach(maSach);
        // Lấy danh sách thể loại
        TheLoaiBUS theLoaiBUS = new TheLoaiBUS();
        ArrayList<String> dsTheLoai = theLoaiBUS.getTheLoaiByMaSach(maSach);
        // Hiển thị hộp thoại thông tin sách
        showBookDetailsDialog(sach, tenNXB, dsTacGia, dsTheLoai);
    }
    
    private void showBookDetailsDialog(SachDTO sach, String tenNXB, ArrayList<String> dsTacGia, ArrayList<String> dsTheLoai) {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Thông tin chi tiết sách", true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panel.add(new UILabel("MÃ SÁCH: " + sach.getMaSach(), 450, 30));
        panel.add(new UILabel("TÊN SÁCH: " + sach.getTenSach(), 450, 30));
        panel.add(new UILabel("GIÁ SÁCH: " + sach.getGiaSach() + " VND", 450, 30));
        panel.add(new UILabel("NHÀ XUẤT BẢN: " + tenNXB, 450, 30));
        panel.add(new UILabel("TÁC GIẢ: " + String.join(", ", dsTacGia), 450, 30));
        panel.add(new UILabel("THỂ LOẠI: " + String.join(", ", dsTheLoai), 450, 30));

        dialog.add(panel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
