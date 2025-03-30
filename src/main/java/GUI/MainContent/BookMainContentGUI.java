package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.SachBUS;
import DTO.NhaXuatBanDTO;
import DTO.SachDTO;
import GUI.MainContentDiaLog.*;
import Utils.UIConstants;
import Utils.UIButton;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookMainContentGUI extends JPanel {
    private UIButton btnAdd, btnDelete, btnEdit;
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
        btnAdd.setBounds(5, 5, 90, 40);
        btnDelete.setBounds(105, 5, 90, 40);
        btnEdit.setBounds(210, 5, 90, 40);

        int panelWidth = this.getPreferredSize().width;
        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Theo NXB"});
        cbFilter.setBounds(panelWidth - 320, 10, 100, 30);
        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);

        pnlHeader.add(btnAdd);
        pnlHeader.add(btnDelete);
        pnlHeader.add(btnEdit);
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
        String tenNXB = tableModel.getValueAt(selectedRow, 3).toString(); // Tên NXB thay vì mã
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
            if (sachBUS.deleteSach(maSach)) { 
                JOptionPane.showMessageDialog(this, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
