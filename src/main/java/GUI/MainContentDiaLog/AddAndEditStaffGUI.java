package GUI.MainContentDiaLog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell Vostro
 */
public class AddAndEditStaffGUI extends JDialog{
    private JTextField txtMaNV, txtTenNV, txtEmail, txtSDT;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhanVienBUS nvBus;
    private NhanVienDTO nv;
    
    public AddAndEditStaffGUI(JFrame parent, NhanVienBUS nvBus, String title, String type, NhanVienDTO nv) {
        super(parent, title, true);
        this.nvBus = nvBus;
        this.nv = nv;
        initComponent(type);
        if (nv != null) {
            txtMaNV.setText(String.valueOf(nv.getMaNV()));
            txtTenNV.setText(nv.getTenNV());
            txtEmail.setText(nv.getEmail());
            txtSDT.setText(nv.getSdt());
            txtMaNV.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditStaffGUI(JFrame parent, NhanVienBUS nvBus, String title, String type) {
        super(parent, title, true);
        this.nvBus = nvBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void initComponent(String type){
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã nhân viên:"));
        inputPanel.add(txtMaNV = new JTextField());
        
        inputPanel.add(new UILabel("Tên nhân viên:"));
        inputPanel.add(txtTenNV = new JTextField());
        
        inputPanel.add(new UILabel("Email:"));
        inputPanel.add(txtEmail = new JTextField());
        
        inputPanel.add(new UILabel("Số điện thọại"));
        inputPanel.add(txtSDT = new JTextField());
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        if ("add".equals(type)) btnPanel.add(btnAdd);
        if ("save".equals(type)) btnPanel.add(btnSave);
        btnPanel.add(btnCancel);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addStaff());
        btnSave.addActionListener(e -> saveStaff());
    }
    
    private void addStaff(){
        if(!CheckFormInput()) return;
        try {
            int maNV = Integer.parseInt(txtMaNV.getText().trim());
            String tenNV = txtTenNV.getText().trim();
            String email = txtEmail.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, email, sdt);
            if(nvBus.addNhanVien(nv)){
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveStaff(){
        if(!CheckFormInput()) return;
        try {
            int maNV = Integer.parseInt(txtMaNV.getText().trim());
            String tenNV = txtTenNV.getText().trim();
            String email = txtEmail.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, email, sdt);
            if(nvBus.updateNhanVien(nv)){
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean CheckFormInput(){
        try {
            String maNVStr = txtMaNV.getText().trim();
            if (maNVStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maNV = Integer.parseInt(maNVStr);
            if (maNV < 0) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenNV = txtTenNV.getText().trim();
            if (tenNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String email = txtEmail.getText().trim();
            if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sdt = txtSDT.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!sdt.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 10 đến 11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
