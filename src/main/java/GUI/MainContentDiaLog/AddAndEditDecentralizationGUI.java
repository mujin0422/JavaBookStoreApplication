//package GUI.MainContentDiaLog;
//
//import BUS.QuyenBUS;
//import BUS.ChucNangBUS;
//import DTO.QuyenDTO;
//import DTO.ChucNangDTO;
//import Utils.UIButton;
//import Utils.UIConstants;
//import Utils.UILabel;
//import java.awt.*;
//import java.util.List;
//import javax.swing.*;
//
//public class AddAndEditDecentralizationGUI extends JDialog {
//    private JTextField txtMaQuyen, txtTenQuyen;
//    private JCheckBox[] permissionChecks;
//    private UIButton btnAdd, btnSave, btnCancel;
//    private QuyenBUS quyenBUS;
//    private ChucNangBUS chucNangBUS;
//    private QuyenDTO quyen;
//    private List<ChucNangDTO> danhSachChucNang;
//
//    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, ChucNangBUS chucNangBUS, String title, String type) {
//        super(parent, title, true);
//        this.quyenBUS = quyenBUS;
//        this.chucNangBUS = chucNangBUS;
//        initComponent(type);
//        this.setLocationRelativeTo(parent);
//        this.setVisible(true);
//    }
//
//    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, ChucNangBUS chucNangBUS, String title, String type, QuyenDTO quyen) {
//        super(parent, title, true);
//        this.quyenBUS = quyenBUS;
//        this.chucNangBUS = chucNangBUS;
//        this.quyen = quyen;
//        initComponent(type);
//        
//        if (quyen != null) {
//            txtMaQuyen.setText(String.valueOf(quyen.getMaQuyen()));
//            txtTenQuyen.setText(quyen.getTenQuyen());
//            txtMaQuyen.setEnabled(false);
//            loadPermissions(quyen.getMaQuyen());
//        }
//        
//        this.setLocationRelativeTo(parent);
//        this.setVisible(true);
//    }
//
//    private void initComponent(String type) {
//        this.setSize(600, 400);
//        this.setLayout(new BorderLayout());
//        
//        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
//        inputPanel.add(new UILabel("Mã quyền:"));
//        inputPanel.add(txtMaQuyen = new JTextField());
//        inputPanel.add(new UILabel("Tên nhóm quyền:"));
//        inputPanel.add(txtTenQuyen = new JTextField());
//        
//        JPanel permissionsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
//        permissionsPanel.setBorder(BorderFactory.createTitledBorder("Danh mục chức năng"));
//        danhSachChucNang = chucNangBUS.getAllChucNang();
//        permissionChecks = new JCheckBox[danhSachChucNang.size()];
//        
//        for (int i = 0; i < danhSachChucNang.size(); i++) {
//            permissionChecks[i] = new JCheckBox(danhSachChucNang.get(i).getTenCN());
//            permissionsPanel.add(permissionChecks[i]);
//        }
//        
//        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
//        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
//        btnAdd = new UIButton("add", "THÊM", 90, 35);
//        btnSave = new UIButton("confirm", "LƯU", 90, 35);
//        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
//        
//        if (quyen == null) btnPanel.add(btnAdd);
//        else btnPanel.add(btnSave);
//        btnPanel.add(btnCancel);
//        
//        this.add(inputPanel, BorderLayout.NORTH);
//        this.add(permissionsPanel, BorderLayout.CENTER);
//        this.add(btnPanel, BorderLayout.SOUTH);
//
//        btnCancel.addActionListener(e -> dispose());
//        btnAdd.addActionListener(e -> addDecentralization());
//        btnSave.addActionListener(e -> saveDecentralization());
//    }
//
//    private void loadPermissions(int maQuyen) {
//        List<Integer> assignedPermissions = quyenBUS.getPermissionsByQuyen(maQuyen);
//        for (int i = 0; i < danhSachChucNang.size(); i++) {
//            if (assignedPermissions.contains(danhSachChucNang.get(i).getMaCN())) {
//                permissionChecks[i].setSelected(true);
//            }
//        }
//    }
//
//    private void addDecentralization() {
//        if (!validateInput()) return;
//        int maQuyen = Integer.parseInt(txtMaQuyen.getText().trim());
//        String tenQuyen = txtTenQuyen.getText().trim();
//        
//        if (quyenBUS.addQuyen(new QuyenDTO(maQuyen, tenQuyen))) {
//            for (int i = 0; i < danhSachChucNang.size(); i++) {
//                if (permissionChecks[i].isSelected()) {
//                    quyenBUS.addPermission(maQuyen, danhSachChucNang.get(i).getMaCN());
//                }
//            }
//            JOptionPane.showMessageDialog(this, "Thêm nhóm quyền thành công!");
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(this, "Lỗi thêm nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void saveDecentralization() {
//        if (!validateInput()) return;
//        int maQuyen = Integer.parseInt(txtMaQuyen.getText().trim());
//        String tenQuyen = txtTenQuyen.getText().trim();
//        
//        if (quyenBUS.updateQuyen(new QuyenDTO(maQuyen, tenQuyen))) {
//            quyenBUS.clearPermissions(maQuyen);
//            for (int i = 0; i < danhSachChucNang.size(); i++) {
//                if (permissionChecks[i].isSelected()) {
//                    quyenBUS.addPermission(maQuyen, danhSachChucNang.get(i).getMaCN());
//                }
//            }
//            JOptionPane.showMessageDialog(this, "Cập nhật nhóm quyền thành công!");
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(this, "Lỗi cập nhật nhóm quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private boolean validateInput() {
//        if (txtMaQuyen.getText().trim().isEmpty() || txtTenQuyen.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//}
