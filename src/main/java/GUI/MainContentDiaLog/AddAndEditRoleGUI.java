package GUI.MainContentDiaLog;

import BUS.VaiTroBUS;
import DTO.VaiTroDTO;
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

public class AddAndEditRoleGUI extends JDialog{
    private UITextField txtMaVT, txtTenVT;
    private UIButton btnAdd, btnSave, btnCancel;
    private VaiTroBUS vaiTroBUS;
    private VaiTroDTO vt;

    public AddAndEditRoleGUI(JFrame parent, VaiTroBUS vaiTroBUS, String title, String type, VaiTroDTO vt) {
        super(parent, title, true);
        this.vaiTroBUS = vaiTroBUS;
        this.vt = vt;
        initComponent(type);
        if (vt != null) {
            txtMaVT.setText(String.valueOf(vt.getMaVT()));
            txtTenVT.setText(vt.getTenVT());
            txtMaVT.setEditable(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditRoleGUI(JFrame parent, VaiTroBUS vaiTroBUS, String title, String type) {
        super(parent, title, true);
        this.vaiTroBUS = vaiTroBUS;
        initComponent(type);
        txtMaVT.setText(vaiTroBUS.getNextMaVt());
        txtMaVT.setEditable(false);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        this.setSize(350, 180);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã vai trò:"));
        inputPanel.add(txtMaVT = new UITextField(0,0));
        inputPanel.add(new UILabel("Tên vai trò:"));
        inputPanel.add(txtTenVT = new UITextField(0,0));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);

        switch (type) {
            case "add" -> btnPanel.add(btnAdd);
            case "save" -> btnPanel.add(btnSave);
        }
        btnPanel.add(btnCancel);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addRole());
        btnSave.addActionListener(e -> saveRole());
    }

    private void saveRole() {
        if (!CheckFormInput()) return;
        try {
            int maVT = Integer.parseInt(txtMaVT.getText().trim());
            String tenVT = txtTenVT.getText().trim();
            VaiTroDTO vaiTro = new VaiTroDTO(maVT, tenVT);
            if (vaiTroBUS.updateVaiTro(vaiTro)) {
                JOptionPane.showMessageDialog(this, "Cập nhật vai trò thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRole() {
        if (!CheckFormInput()) return;
        try {
            int maVT = Integer.parseInt(txtMaVT.getText().trim());
            String tenVT = txtTenVT.getText().trim();
            VaiTroDTO vaiTro = new VaiTroDTO(maVT, tenVT);
            if (vaiTroBUS.addVaiTro(vaiTro)) {
                JOptionPane.showMessageDialog(this, "Thêm vai trò thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckFormInput() {
        try {
            String tenNXB = txtTenVT.getText().trim();
            if (tenNXB.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên vai trò không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
