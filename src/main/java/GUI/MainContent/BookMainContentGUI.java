package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.NhomTacGiaBUS;
import BUS.NhomTheLoaiBUS;
import BUS.SachBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.SachDTO;
import GUI.MainContentDiaLog.*;
import Utils.UIConstants;
import Utils.UIButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class BookMainContentGUI extends JPanel {
    private UIButton btnAdd, btnDelete, btnEdit, btnView;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;
    // $$$$ 
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
            // Tạo bảng dữ liệu
        String[] columnNames = {"MÃ SÁCH", "TÊN SÁCH", "GIÁ", "NHÀ XUẤT BẢN", "TỒN KHO"};
        tableModel = new DefaultTableModel(columnNames, 0); // ####
        tblContent = new JTable(tableModel);
        tblContent.setDefaultEditor(Object.class, null);
            // Chinh sua bang 
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.setRowHeight(25);
            // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tblContent);
        scrollPane.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);
            // Thêm JScrollPane vào pnlContent
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//

        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        addSearchFunctionality();
        loadTableData();
    }

    private void loadTableData() {
        // STEP 1: xóa dữ liệu cũ
        tableModel.setRowCount(0); 
        // STEP 2: tải từng dòng lên bảng  
         // Tạo HashMap để ánh xạ mã NXB -> tên NXB
        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        HashMap<Integer, String> nhaXuatBanMap = new HashMap<>();
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            nhaXuatBanMap.put(nxb.getMaNXB(), nxb.getTenNXB());
        }

        // Lấy danh sách sách và hiển thị tên NXB thay vì mã
        ArrayList<SachDTO> listSach = sachBUS.getAllSach();
        for (SachDTO sach : listSach) {
            String tenNXB = nhaXuatBanMap.getOrDefault(sach.getMaNXB(), "Không xác định");
            tableModel.addRow(new Object[]{
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getGiaSach(),
                tenNXB, 
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

        // Tìm mã NXB theo tên NXB
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
            NhomTheLoaiBUS nhomTheLoaiBUS = new NhomTheLoaiBUS();
            nhomTheLoaiBUS.deleteByMaSach(maSach);
            NhomTacGiaBUS nhomTacGiaBUS = new NhomTacGiaBUS();
            nhomTacGiaBUS.deleteByMaSach(maSach); 
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
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchBook();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchBook();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchBook();
            }
        });
    }
    
    private void searchBook() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0); 
        ArrayList<SachDTO> listSach = sachBUS.searchSach(keyword);

        NhaXuatBanBUS nhaXuatBanBUS = new NhaXuatBanBUS();
        HashMap<Integer, String> nhaXuatBanMap = new HashMap<>();
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            nhaXuatBanMap.put(nxb.getMaNXB(), nxb.getTenNXB());
        }

        for (SachDTO sach : listSach) {
            String tenNXB = nhaXuatBanMap.getOrDefault(sach.getMaNXB(), "Không xác định");
            tableModel.addRow(new Object[]{
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getGiaSach(),
                tenNXB, 
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
        int maNXB = -1;
        for (NhaXuatBanDTO nxb : nhaXuatBanBUS.getAllNhaXuatBan()) {
            if (nxb.getTenNXB().equals(selectedPublisher)) {
                maNXB = nxb.getMaNXB();
                break;
            }
        }
        if (maNXB != -1) {
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
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Mã sách: " + sach.getMaSach()));
        panel.add(new JLabel("Tên sách: " + sach.getTenSach()));
        panel.add(new JLabel("Giá: " + sach.getGiaSach() + " VND"));
        panel.add(new JLabel("Nhà xuất bản: " + tenNXB));
        panel.add(new JLabel("Tác giả: " + String.join(", ", dsTacGia)));
        panel.add(new JLabel("Thể loại: " + String.join(", ", dsTheLoai)));

        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(e -> dialog.dispose());
        JPanel panelButton = new JPanel();
        panelButton.add(btnClose);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(panelButton, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
