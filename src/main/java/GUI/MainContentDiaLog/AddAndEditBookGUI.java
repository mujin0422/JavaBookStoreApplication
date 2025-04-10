package GUI.MainContentDiaLog;

import BUS.NhaXuatBanBUS;
import BUS.NhomTacGiaBUS;
import BUS.NhomTheLoaiBUS;
import BUS.SachBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.NhaXuatBanDTO;
import DTO.NhomTacGiaDTO;
import DTO.NhomTheLoaiDTO;
import DTO.SachDTO;
import DTO.TacGiaDTO;
import DTO.TheLoaiDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddAndEditBookGUI extends JDialog {
    private UITextField txtMaSach, txtTenSach, txtGia, txtSoLuongTon;
    private JComboBox<String> cbMaNXB, cbMaTG, cbMaTL; 
    private JTextArea areaTacGia, areaTheLoai;
    private UIButton btnAdd, btnSave, btnCancel;
    private SachBUS sachBus;
    private NhaXuatBanBUS nhaXuatBanBus;
    private TacGiaBUS tacGiaBus;
    private TheLoaiBUS theLoaiBus;
    private SachDTO sach;
    private DefaultTableModel tableModelTG, tableModelTL;
    private UITable tableTG ,tableTL;

    public AddAndEditBookGUI(JFrame parent, SachBUS sachBus, String title, String type, SachDTO sach) {
        super(parent, title, true);
        this.sachBus = sachBus;
        this.sach = sach;
        initComponent(type);

        if (sach != null) {
            txtMaSach.setText(String.valueOf(sach.getMaSach()));
            txtTenSach.setText(sach.getTenSach());
            txtGia.setText(String.valueOf(sach.getGiaSach()));

            String tenNXB = nhaXuatBanBus.getTenNhaXuatBanById(sach.getMaNXB());
            if (tenNXB != null) {
                cbMaNXB.setSelectedItem(tenNXB);
            }

            txtSoLuongTon.setText(String.valueOf(sach.getSoLuongTon()));
            txtMaSach.setEditable(false);
            areaTheLoai.setText("");
            areaTacGia.setText("");

            NhomTheLoaiBUS nhomTheLoaiBUS = new NhomTheLoaiBUS();
            ArrayList<Integer> dsTheLoai = nhomTheLoaiBUS.getMaTheLoaiByMaSach(sach.getMaSach());
            for (Integer maTL : dsTheLoai) {
                String tenTL = theLoaiBus.getTenTheLoaiById(maTL);
                if (tenTL != null) {
                    areaTheLoai.append(tenTL + "\n");
                }
            }

            NhomTacGiaBUS nhomTacGiaBUS = new NhomTacGiaBUS();
            ArrayList<Integer> dsTacGia = nhomTacGiaBUS.getMaTacGiaByMaSach(sach.getMaSach());
            for (Integer maTG : dsTacGia) {
                String tenTG = tacGiaBus.getTenTacGiaById(maTG);
                if (tenTG != null) {
                    areaTacGia.append(tenTG + "\n");
                }
            }
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    
    public AddAndEditBookGUI(JFrame parent, SachBUS sachBus, String title, String type){
        super(parent, title, true);
        this.sachBus = sachBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type) {
        this.setSize(700, 500);
        this.setLayout(new BorderLayout(5,5));
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        
        nhaXuatBanBus = new NhaXuatBanBUS();
        theLoaiBus = new TheLoaiBUS();
        tacGiaBus = new TacGiaBUS();
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        inputPanelLeft.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanelLeft.setPreferredSize(new Dimension(400, 0));
        
        inputPanelLeft.add(new UILabel("Mã sách: ", 120, 30));
        inputPanelLeft.add(txtMaSach = new UITextField(250,30));
        inputPanelLeft.add(new UILabel("Tên sách: ", 120, 30));
        inputPanelLeft.add(txtTenSach = new UITextField(250,30));
        inputPanelLeft.add(new UILabel("Giá sách:", 120, 30));
        inputPanelLeft.add(txtGia = new UITextField(250,30));
        
        inputPanelLeft.add(new UILabel("Nhà xuất bản:", 120, 30));
        cbMaNXB = new JComboBox<>();
        cbMaNXB.setBackground(UIConstants.WHITE_FONT);
        cbMaNXB.setPreferredSize(new Dimension(250,30));
        for (NhaXuatBanDTO nxb : nhaXuatBanBus.getAllNhaXuatBan()) {
            cbMaNXB.addItem(nxb.getTenNXB());   
        }
        inputPanelLeft.add(cbMaNXB);
        
        inputPanelLeft.add(new UILabel("Các tác giả: ",120 ,30));
        areaTacGia = new JTextArea();
        areaTacGia.setPreferredSize(new Dimension (380, 60));
        UIScrollPane scrollTG = new UIScrollPane(areaTacGia);
        inputPanelLeft.add(scrollTG);
      
        inputPanelLeft.add(new UILabel("Các thể loại:",120 ,30));
        areaTheLoai = new JTextArea();
        areaTheLoai.setPreferredSize(new Dimension (380, 60));
        UIScrollPane scrollTL = new UIScrollPane(areaTheLoai);
        inputPanelLeft.add(scrollTL);
        
        inputPanelLeft.add(new UILabel("Số lượng:",120 ,30));
        inputPanelLeft.add(txtSoLuongTon = new UITextField(250,30));
        txtSoLuongTon.setText("0");
        txtSoLuongTon.setEditable(false);
        
        JPanel inputPanelRight = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanelRight.setBackground(UIConstants.MAIN_BACKGROUND);
        
        JPanel groupTG = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        groupTG.setBackground(UIConstants.MAIN_BACKGROUND);
        groupTG.setPreferredSize(new Dimension(250, 180));
        String[] columnNameTG = {"TÁC GIẢ"};
        tableModelTG = new DefaultTableModel(columnNameTG, 0); 
        tableTG = new UITable(tableModelTG);
        tableTG.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tableTG.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollTableTG = new UIScrollPane(tableTG);
        scrollTableTG.setPreferredSize(new Dimension(240,130));
        UIButton addToAreaTG = new UIButton("add", "THÊM VÀO", 120, 35);
        addToAreaTG.addActionListener(e -> addToAreaTG());
        groupTG.add(scrollTableTG);
        groupTG.add(addToAreaTG);
        
        JPanel groupTL = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        groupTL.setBackground(UIConstants.MAIN_BACKGROUND);
        groupTL.setPreferredSize(new Dimension(250 ,180));
        String[] columnNameTL = {"THỂ LOẠI"};
        tableModelTL = new DefaultTableModel(columnNameTL, 0); 
        tableTL = new UITable(tableModelTL);
        tableTL.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tableTL.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollTableTL = new UIScrollPane(tableTL);
        scrollTableTL.setPreferredSize(new Dimension(240,130));
        UIButton addToAreaTL = new UIButton("add", "THÊM VÀO", 120, 35);
        addToAreaTL.addActionListener(e -> addToAreaTL());
        groupTL.add(scrollTableTL);
        groupTL.add(addToAreaTL);
        
        inputPanelRight.add(groupTG);
        inputPanelRight.add(groupTL);
        //=============================( End Panel Input )==============================//
        
        
        
        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        switch(type) {
            case("add") -> btnPanel.add(btnAdd);
            case("save") -> btnPanel.add(btnSave);          
        }
        btnPanel.add(btnCancel);
        //============================( End Panel Button )==============================//
        
        
        this.add(inputPanelLeft, BorderLayout.WEST);
        this.add(inputPanelRight, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addBook());
        btnSave.addActionListener(e -> saveBook());
        
        loadTacGiaToTable();
        loadTheLoaiToTable();
    }
    
    private void saveBook() {
        if (!CheckFormInput()) return;
        try {
            int maSach = Integer.parseInt(txtMaSach.getText().trim());
            String tenSach = txtTenSach.getText().trim();
            int giaSach = Integer.parseInt(txtGia.getText().trim());
            int maNXB = nhaXuatBanBus.getMaNxbByTenNxb(cbMaNXB.getSelectedItem().toString());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            SachDTO sach = new SachDTO(maSach, tenSach, giaSach, soLuongTon, maNXB);

            if (sachBus.updateSach(sach)) {
                ArrayList<Integer> dsMaTL = getMaTLFromArea();
                ArrayList<Integer> dsMaTG = getMaTGFromArea();
                NhomTheLoaiBUS nhomTheLoaiBUS = new NhomTheLoaiBUS();
                NhomTacGiaBUS nhomTacGiaBUS = new NhomTacGiaBUS();

                ArrayList<Integer> currentMaTL = nhomTheLoaiBUS.getMaTheLoaiByMaSach(maSach);
                for (Integer maTL : currentMaTL) {
                    if (!dsMaTL.contains(maTL)) {
                        nhomTheLoaiBUS.deleteNhomTheLoai(maTL, maSach);
                    }
                }
                for (Integer maTL : dsMaTL) {
                    if (!nhomTheLoaiBUS.existsNhomTheLoai(maTL, maSach)) {
                        nhomTheLoaiBUS.addNhomTheLoai(new NhomTheLoaiDTO(maTL, maSach));
                    }
                }
                ArrayList<Integer> currentMaTG = nhomTacGiaBUS.getMaTacGiaByMaSach(maSach);
                for (Integer maTG : currentMaTG) {
                    if (!dsMaTG.contains(maTG)) {
                        nhomTacGiaBUS.deleteNhomTacGia(maTG, maSach); 
                    }
                }
                for (Integer maTG : dsMaTG) {
                    if (!nhomTacGiaBUS.existsNhomTacGia(maTG, maSach)) {
                        nhomTacGiaBUS.addNhomTacGia(new NhomTacGiaDTO(maTG, maSach));
                    }
                }
                JOptionPane.showMessageDialog(this, "Cập nhật sách và thông tin thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addBook() {
        if (!CheckFormInput()) return; 
        try {
            int maSach = Integer.parseInt(txtMaSach.getText().trim());
            String tenSach = txtTenSach.getText().trim();
            int giaSach = Integer.parseInt(txtGia.getText().trim());
            int maNXB = nhaXuatBanBus.getMaNxbByTenNxb(cbMaNXB.getSelectedItem().toString());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            SachDTO sach = new SachDTO(maSach, tenSach, giaSach, soLuongTon, maNXB);
            if (sachBus.addSach(sach)) {
                ArrayList<Integer> dsMaTL = getMaTLFromArea();
                ArrayList<Integer> dsMaTG = getMaTGFromArea();
                NhomTheLoaiBUS nhomTheLoaiBUS = new NhomTheLoaiBUS();
                NhomTacGiaBUS nhomTacGiaBUS = new NhomTacGiaBUS();

                if (nhomTheLoaiBUS.addNhomTheLoai(maSach, dsMaTL) && nhomTacGiaBUS.addNhomTacGia(maSach, dsMaTG)) {
                    JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm thể loại hoặc tác giả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã sách đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckFormInput() {
        try {
            String maSachStr = txtMaSach.getText().trim();
            if (maSachStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sách không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maSach = Integer.parseInt(maSachStr);
            if (maSach < 0) {
                JOptionPane.showMessageDialog(this, "Mã sách phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenSach = txtTenSach.getText().trim();
            if (tenSach.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sách không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String giaSachStr = txtGia.getText().trim();
            if (giaSachStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá sách không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int giaSach = Integer.parseInt(giaSachStr);
            if (giaSach <= 0) {
                JOptionPane.showMessageDialog(this, "Giá sách phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (cbMaNXB.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà xuất bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void addToAreaTG() {
        int selectedRow = tableTG.getSelectedRow();
        if (selectedRow != -1) {
            String selected = (String) tableModelTG.getValueAt(selectedRow, 0);
            String currentText = areaTacGia.getText();
            if (!currentText.contains(selected + "\n")) {
                areaTacGia.append(selected);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một tác giả trong bảng!");
        }
    }
    private void addToAreaTL() {
        int selectedRow = tableTL.getSelectedRow();
        if (selectedRow != -1) {
            String selected = (String) tableModelTL.getValueAt(selectedRow, 0);
            String currentText = areaTheLoai.getText();
            if (!currentText.contains(selected + "\n")) {
                areaTheLoai.append(selected);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một thể loại trong bảng!");
        }
    }
    private ArrayList<Integer> getMaTLFromArea() {
        ArrayList<Integer> dsMaTL = new ArrayList<>();
        String[] theLoaiArr = areaTheLoai.getText().split("\n");
        for (String tenTL : theLoaiArr) {
            tenTL = tenTL.trim();
            if (!tenTL.isEmpty()) {
                int maTL = theLoaiBus.getMaTlByTenTl(tenTL);
                if (maTL != 0) { 
                    dsMaTL.add(maTL);
                }
            }
        }
        return dsMaTL;
    }

    private ArrayList<Integer> getMaTGFromArea() {
        ArrayList<Integer> dsMaTG = new ArrayList<>();
        String[] tacGiaArr = areaTacGia.getText().split("\n");
        for (String tenTG : tacGiaArr) {
            tenTG = tenTG.trim();
            if (!tenTG.isEmpty()) {
                int maTG = tacGiaBus.getMaTgByTenTg(tenTG);
                if (maTG != 0) { 
                    dsMaTG.add(maTG);
                }
            }
        }
        return dsMaTG;
    }
    
    private void loadTacGiaToTable() {
        tableModelTG.setRowCount(0); 
        for (TacGiaDTO tg : tacGiaBus.getAllTacGia()) {
            tableModelTG.addRow(new Object[]{tg.getTenTG()});
        }
    }

    private void loadTheLoaiToTable() {
        tableModelTL.setRowCount(0);
        for (TheLoaiDTO tl : theLoaiBus.getAllTheLoai()) {
            tableModelTL.addRow(new Object[]{tl.getTenTL()});
        }
    }
}
