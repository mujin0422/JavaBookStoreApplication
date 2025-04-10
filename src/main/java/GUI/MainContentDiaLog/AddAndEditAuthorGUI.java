package GUI.MainContentDiaLog;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;

import javax.swing.*;
import java.awt.*;

public class AddAndEditAuthorGUI extends JDialog {
    private JTextField txtMaTG, txtTenTG;
    private UIButton btnAdd, btnSave, btnCancel;
    private TacGiaBUS tgBus;
    private TacGiaDTO tg;

    public AddAndEditAuthorGUI(JFrame parent, TacGiaBUS tgBus, String title, String type, TacGiaDTO tg) {
        super(parent, title, true);
        this.tgBus = tgBus;
        this.tg = tg;
        initComponent(type);
        
        if (tg != null) {
            txtMaTG.setText(String.valueOf(tg.getMaTG()));
            txtTenTG.setText(tg.getTenTG());
            txtMaTG.setEnabled(false);
        }

        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditAuthorGUI(JFrame parent, TacGiaBUS tgBus, String title, String type) {
        super(parent, title, true);
        this.tgBus = tgBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        this.setSize(400, 200);
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã tác giả:"));
        inputPanel.add(txtMaTG = new JTextField());
        inputPanel.add(new UILabel("Tên tác giả:"));
        inputPanel.add(txtTenTG = new JTextField());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
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
        btnAdd.addActionListener(e -> addAuthor());
        btnSave.addActionListener(e -> saveAuthor());
    }

    private void saveAuthor() {
        if (!CheckFormInput()) return;
        try {
            int maTG = Integer.parseInt(txtMaTG.getText().trim());
            String tenTG = txtTenTG.getText().trim();
            TacGiaDTO tg = new TacGiaDTO(maTG, tenTG);
            if (tgBus.updateTacGia(tg)) {
                JOptionPane.showMessageDialog(this, "Cập nhật tác giả thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addAuthor() {
        if (!CheckFormInput()) return;
        try {
            int maTG = Integer.parseInt(txtMaTG.getText().trim());
            String tenTG = txtTenTG.getText().trim();
            TacGiaDTO tg = new TacGiaDTO(maTG, tenTG);
            if (tgBus.addTacGia(tg)) {
                JOptionPane.showMessageDialog(this, "Thêm tác giả thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã tác giả đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckFormInput() {
        try {
            String maTGStr = txtMaTG.getText().trim();
            if (maTGStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã tác giả không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maTG = Integer.parseInt(maTGStr);
            if (maTG < 0) {
                JOptionPane.showMessageDialog(this, "Mã tác giả phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenTG = txtTenTG.getText().trim();
            if (tenTG.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên tác giả không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
