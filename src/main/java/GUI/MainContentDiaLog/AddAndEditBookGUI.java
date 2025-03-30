package GUI.MainContentDiaLog;

import BUS.NhaXuatBanBUS;
import BUS.SachBUS;
import DTO.NhaXuatBanDTO;
import DTO.SachDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AddAndEditBookGUI extends JDialog {
    private JTextField txtMaSach, txtTenSach, txtGia, txtSoLuongTon;
    private JComboBox<String> cbMaNXB; 
    private UIButton btnAdd, btnSave, btnCancel;
    private SachBUS sachBus;;

    private SachDTO sach;
    private HashMap<String, Integer> nxbMap;

    public AddAndEditBookGUI(JFrame parent, SachBUS sachBus, String title, String type, SachDTO sach){
        super(parent, title, true);
        this.sachBus = sachBus;
        this.sach = sach;
        initComponent(type);
        
        if (sach != null) {
            txtMaSach.setText(String.valueOf(sach.getMaSach()));
            txtTenSach.setText(sach.getTenSach());
            txtGia.setText(String.valueOf(sach.getGiaSach()));
            cbMaNXB.setSelectedItem(String.valueOf(sach.getMaNXB()));
            txtSoLuongTon.setText(String.valueOf(sach.getSoLuongTon()));
            txtMaSach.setEnabled(false);
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
        this.setSize(550, 300);
        this.setLayout(new BorderLayout());
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã sách:"));
        inputPanel.add(txtMaSach = new JTextField());
        
        inputPanel.add(new UILabel("Tên sách:"));
        inputPanel.add(txtTenSach = new JTextField());
        
        inputPanel.add(new UILabel("Giá sách:"));
        inputPanel.add(txtGia = new JTextField());
        
        inputPanel.add(new UILabel("Nhà xuất bản:"));
        cbMaNXB = new JComboBox<>();
        nxbMap = new HashMap<>();  
        NhaXuatBanBUS nhaXuatBanBus = new NhaXuatBanBUS();
        for (NhaXuatBanDTO nxb : nhaXuatBanBus.getAllNhaXuatBan()) {
            cbMaNXB.addItem(nxb.getTenNXB());  
            nxbMap.put(nxb.getTenNXB(), nxb.getMaNXB());  
        }
        inputPanel.add(cbMaNXB);

        inputPanel.add(new UILabel("Số lượng:"));
        inputPanel.add(txtSoLuongTon = new JTextField());
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
                JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException ex) {
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
                JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã sách đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException ex) {
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
            String soLuongTonStr = txtSoLuongTon.getText().trim();
            if (soLuongTonStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int soLuongTon = Integer.parseInt(soLuongTonStr);
            if (soLuongTon < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số nguyên không âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
}
