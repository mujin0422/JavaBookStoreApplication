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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import java.util.HashMap;

public class AddAndEditBookGUI extends JDialog {
    private JTextField txtMaSach, txtTenSach, txtGia, txtSoLuongTon;
    private JComboBox<String> cbMaNXB, cbMaTG, cbMaTL; 
    private JTextArea areaTacGia, areaTheLoai;
    private UIButton btnAdd, btnSave, btnCancel;
    private SachBUS sachBus;
    private SachDTO sach;
    private HashMap<String, Integer> nxbMap, tgMap, tlMap;

    public AddAndEditBookGUI(JFrame parent, SachBUS sachBus, String title, String type, SachDTO sach){
        super(parent, title, true);
        this.sachBus = sachBus;
        this.sach = sach;
        initComponent(type);
        
        if (sach != null) {
            txtMaSach.setText(String.valueOf(sach.getMaSach()));
            txtTenSach.setText(sach.getTenSach());
            txtGia.setText(String.valueOf(sach.getGiaSach()));
            for (String tenNXB : nxbMap.keySet()) {
                if (nxbMap.get(tenNXB) == sach.getMaSach()) {
                    cbMaNXB.setSelectedItem(tenNXB);
                    break;
                }
            }
            txtSoLuongTon.setText(String.valueOf(sach.getSoLuongTon()));
            txtMaSach.setEnabled(false);
            
            areaTheLoai.setText("");
            areaTacGia.setText("");

            NhomTheLoaiBUS nhomTheLoaiBUS = new NhomTheLoaiBUS();
            ArrayList<Integer> dsTheLoai = nhomTheLoaiBUS.getMaTheLoaiByMaSach(sach.getMaSach());
            for (Integer maTL : dsTheLoai) {
                for (String tenTL : tlMap.keySet()) {
                    if (tlMap.get(tenTL).equals(maTL)) {
                        areaTheLoai.append(tenTL + "\n");
                        break;
                    }
                }
            }
            NhomTacGiaBUS nhomTacGiaBUS = new NhomTacGiaBUS();
            ArrayList<Integer> dsTacGia = nhomTacGiaBUS.getMaTacGiaByMaSach(sach.getMaSach());
            for (Integer maTG : dsTacGia) {
                for (String tenTG : tgMap.keySet()) {
                    if (tgMap.get(tenTG).equals(maTG)) {
                        areaTacGia.append(tenTG + "\n");
                        break;
                    }
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
        this.setSize(550, 450);
        this.setLayout(new BorderLayout());
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 20, 10));
        inputPanel.setBackground(UIConstants.BUTTON_DEFAULT);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã sách:"));
        inputPanel.add(txtMaSach = new JTextField());
        
        inputPanel.add(new UILabel("Tên sách:"));
        inputPanel.add(txtTenSach = new JTextField());
        
        inputPanel.add(new UILabel("Giá sách:"));
        inputPanel.add(txtGia = new JTextField());
        
        inputPanel.add(new UILabel("Nhà xuất bản:"));
        cbMaNXB = new JComboBox<>();
        cbMaNXB.setBackground(UIConstants.WHITE_FONT);
        nxbMap = new HashMap<>();  
        NhaXuatBanBUS nhaXuatBanBus = new NhaXuatBanBUS();
        for (NhaXuatBanDTO nxb : nhaXuatBanBus.getAllNhaXuatBan()) {
            cbMaNXB.addItem(nxb.getTenNXB());  
            nxbMap.put(nxb.getTenNXB(), nxb.getMaNXB());  
        }
        inputPanel.add(cbMaNXB);
        
        
        inputPanel.add(new UILabel("Tác giả:"));
        JPanel inputPanelTG = new JPanel(new BorderLayout());
        cbMaTG = new JComboBox<>();
        cbMaTG.setBackground(UIConstants.WHITE_FONT);
        tgMap = new HashMap<>();
        TacGiaBUS tacGiaBus = new TacGiaBUS();
        for(TacGiaDTO tg : tacGiaBus.getAllTacGia()){
            cbMaTG.addItem(tg.getTenTG());
            tgMap.put(tg.getTenTG(), tg.getMaTG());
        }
        UIButton addToAreaTG = new UIButton("menuButton", "ADD ", 50, 25);
        addToAreaTG.addActionListener(e -> addToAreaTG());
        inputPanelTG.add(cbMaTG, BorderLayout.CENTER);
        inputPanelTG.add(addToAreaTG, BorderLayout.EAST);
        inputPanel.add(inputPanelTG);
        
        inputPanel.add(new UILabel("Các tác giả:"));
        areaTacGia = new JTextArea(2, 2);
        JScrollPane scrollTG = new JScrollPane(areaTacGia);
        scrollTG.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollTG.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        inputPanel.add(scrollTG);
        
        inputPanel.add(new UILabel("Thể loại"));
        JPanel inputPanelTL = new JPanel(new BorderLayout());
        cbMaTL = new JComboBox<>();
        cbMaTL.setBackground(UIConstants.WHITE_FONT);
        tlMap = new HashMap<>();
        TheLoaiBUS theLoaiBus = new TheLoaiBUS();
        for(TheLoaiDTO tl : theLoaiBus.getAllTheLoai()){
            cbMaTL.addItem(tl.getTenTL());
            tlMap.put(tl.getTenTL(), tl.getMaTL());
        }
        UIButton addToAreaTL = new UIButton("menuButton", "ADD ", 50, 25);
        addToAreaTL.addActionListener(e -> addToAreaTL());
        inputPanelTL.add(cbMaTL, BorderLayout.CENTER);
        inputPanelTL.add(addToAreaTL, BorderLayout.EAST);
        inputPanel.add(inputPanelTL);

        inputPanel.add(new UILabel("Các thể loại:"));
        areaTheLoai = new JTextArea(2, 2);
        JScrollPane scrollTL = new JScrollPane(areaTheLoai);
        scrollTL.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollTL.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        inputPanel.add(scrollTL);
        
        inputPanel.add(new UILabel("Số lượng:"));
        inputPanel.add(txtSoLuongTon = new JTextField("0"));
        txtSoLuongTon.setEnabled(false);
        //=============================( End Panel Input )==============================//
        
        
        
        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.BUTTON_DEFAULT);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        switch(type) {
            case("add") -> btnPanel.add(btnAdd);
            case("save") -> btnPanel.add(btnSave);          
        }
        btnPanel.add(btnCancel);
        //============================( End Panel Button )==============================//
        
        
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addBook());
        btnSave.addActionListener(e -> saveBook());
    }
    
    private void saveBook() {
        if (!CheckFormInput()) return;
        try {
            int maSach = Integer.parseInt(txtMaSach.getText().trim());
            String tenSach = txtTenSach.getText().trim();
            int giaSach = Integer.parseInt(txtGia.getText().trim());
            int maNXB = nxbMap.get(cbMaNXB.getSelectedItem().toString());
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
            int maNXB = nxbMap.get(cbMaNXB.getSelectedItem().toString());
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
        String selected = (String) cbMaTG.getSelectedItem();
        if (selected != null && !selected.isEmpty()) {
            String currentText = areaTacGia.getText();
            if (!currentText.contains(selected)) {
                areaTacGia.append(selected);
            }
        }
    }

    private void addToAreaTL() {
        String selected = (String) cbMaTL.getSelectedItem();
        if (selected != null && !selected.isEmpty()) {
            String currentText = areaTheLoai.getText();
            if (!currentText.contains(selected)) {
                areaTheLoai.append(selected);
            }
        }
    }
    
    private ArrayList<Integer> getMaTLFromArea() {
        ArrayList<Integer> dsMaTL = new ArrayList<>();
        String[] theLoaiArr = areaTheLoai.getText().split("\n");
        for (String tenTL : theLoaiArr) {
            if (tlMap.containsKey(tenTL.trim())) {
                dsMaTL.add(tlMap.get(tenTL.trim()));
            }
        }
        return dsMaTL;
    }

    private ArrayList<Integer> getMaTGFromArea() {
        ArrayList<Integer> dsMaTG = new ArrayList<>();
        String[] tacGiaArr = areaTacGia.getText().split("\n");
        for (String tenTG : tacGiaArr) {
            if (tgMap.containsKey(tenTG.trim())) {
                dsMaTG.add(tgMap.get(tenTG.trim()));
            }
        }
        return dsMaTG;
    }
}
