package GUI.MainContentDiaLog;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddAndEditSupplierGUI extends JDialog{
    private UITextField txtMaNCC, txtTenNCC, txtDiaChi, txtSDT;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhaCungCapBUS nccBus;
    private NhaCungCapDTO ncc;
    
    public AddAndEditSupplierGUI(JFrame parent, NhaCungCapBUS nccBus, String title, String type, NhaCungCapDTO ncc) {
        super(parent, title, true);
        this.nccBus = nccBus;
        this.ncc = ncc;
        initComponent(type);
        if (ncc != null) {
            txtMaNCC.setText(String.valueOf(ncc.getMaNCC()));
            txtTenNCC.setText(ncc.getTenNCC());
            txtDiaChi.setText(ncc.getDiaChi());
            txtSDT.setText(ncc.getSdt());
            txtMaNCC.setEditable(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditSupplierGUI(JFrame parent, NhaCungCapBUS nccBus, String title, String type) {
        super(parent, title, true);
        this.nccBus = nccBus;
        initComponent(type);
        txtMaNCC.setText(nccBus.getNextMaNcc());
        txtMaNCC.setEditable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void initComponent(String type){
        this.setSize(400, 250);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã nhà cung cấp:"));
        inputPanel.add(txtMaNCC = new UITextField(0,0));  
        inputPanel.add(new UILabel("Tên nhà cung cấp:"));
        inputPanel.add(txtTenNCC = new UITextField(0,0));    
        inputPanel.add(new UILabel("Địa chỉ:"));
        inputPanel.add(txtDiaChi = new UITextField(0,0));
        inputPanel.add(new UILabel("Số điện thọại"));
        inputPanel.add(txtSDT = new UITextField(0,0));
        
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
        btnAdd.addActionListener(e -> addSupplier());
        btnSave.addActionListener(e -> saveSupplier());
    }
    
    private void addSupplier(){
        if(!CheckFormInput()) return;
        try {
            int maNCC = Integer.parseInt(txtMaNCC.getText().trim());
            String tenNCC = txtTenNCC.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt);
            if(nccBus.addNhaCungCap(ncc)){
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Mã NCC đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveSupplier(){
    if(!CheckFormInput()) return;
        try {
            int maNCC = Integer.parseInt(txtMaNCC.getText().trim());
            String tenNCC = txtTenNCC.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt);
            if(nccBus.updateNhaCungCap(ncc)){
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!");
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }}
    
    private boolean CheckFormInput(){
        try {
            String tenNXB = txtTenNCC.getText().trim();
            if (tenNXB.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên NCC không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String diaChi = txtDiaChi.getText().trim();
            if(diaChi.isEmpty()){
                JOptionPane.showMessageDialog(this, "Địa chỉ NCC không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sdt = txtSDT.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!sdt.matches("0\\d{9}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
