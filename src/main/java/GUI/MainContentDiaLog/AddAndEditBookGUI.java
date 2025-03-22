package GUI.MainContentDiaLog;

import BUS.SachBUS;
import DTO.SachDTO;
import Utils.UIButton;
import Utils.UIConstants;

import javax.swing.*;
import java.awt.*;

public class AddAndEditBookGUI extends JDialog {
    private JTextField txtMaSach, txtTenSach, txtGia, txtMaNXB, txtSoLuongTon;
    private UIButton btnSave, btnCancel;
    private SachBUS sachBUS;

    public AddAndEditBookGUI(JFrame parent, SachBUS sachBUS) {
        super(parent, "Thêm Sách", true);
        this.sachBUS = sachBUS;
        this.setSize(450, 350);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(parent);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel chứa các thành phần nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(UIConstants.SUB_BACKGROUND);

        String[] labels = {"Mã Sách:", "Tên Sách:", "Giá:", "Nhà Xuất Bản:", "Tồn Kho:"};
        JTextField[] textFields = {txtMaSach = new JTextField(), txtTenSach = new JTextField(), 
                                   txtGia = new JTextField(), txtMaNXB = new JTextField(), txtSoLuongTon = new JTextField()};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            inputPanel.add(createLabel(labels[i]), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            textFields[i].setPreferredSize(new Dimension(200, 25));
            inputPanel.add(textFields[i], gbc);
        }

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(UIConstants.SUB_BACKGROUND);
        btnSave = new UIButton("menuButton", "Lưu", 120, 35);
        btnCancel = new UIButton("menuButton", "Hủy", 120, 35);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Thêm inputPanel và buttonPanel vào dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        this.add(inputPanel, gbc);

        gbc.gridy = 1;
        this.add(buttonPanel, gbc);

        // Sự kiện nút
        btnSave.addActionListener(e -> saveBook());
        btnCancel.addActionListener(e -> dispose());

        this.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        return label;
    }

    private void saveBook() {
        try {
            int maSach = Integer.parseInt(txtMaSach.getText().trim());
            String tenSach = txtTenSach.getText().trim();
            int giaSach = Integer.parseInt(txtGia.getText().trim());
            int maNXB = Integer.parseInt(txtMaNXB.getText().trim());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());

            SachDTO sach = new SachDTO(maSach, tenSach, giaSach, soLuongTon, maNXB);
            sachBUS.addSach(sach);

            JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
