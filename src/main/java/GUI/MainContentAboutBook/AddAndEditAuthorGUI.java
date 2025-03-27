/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentAboutBook;

import BUS.TacGiaBUS;
import DTO.TacGiaDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dell Vostro
 */
public class AddAndEditAuthorGUI extends JPanel {
    private JTextField txtMaTG, txtTenTG;
    private UIButton btnAdd, btnSave, btnCancel;
    private TacGiaBUS tgBus;
    private TacGiaDTO tg;

    public AddAndEditAuthorGUI(TacGiaBUS tgBus, String type, TacGiaDTO tg) {
        this.tgBus = tgBus;
        this.tg = tg;
        initComponent(type);

        if (tg != null) {
            txtMaTG.setText(String.valueOf(tg.getMaTG()));
            txtTenTG.setText(tg.getTenTG());
            txtMaTG.setEnabled(false);
        }
    }

    public AddAndEditAuthorGUI(TacGiaBUS tgBus, String type) {
        this.tgBus = tgBus;
        initComponent(type);
    }

    public void initComponent(String type) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));

        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new UILabel("Mã tác giả:"));
        inputPanel.add(txtMaTG = new JTextField());
        inputPanel.add(new UILabel("Tên tác giả:"));
        inputPanel.add(txtTenTG = new JTextField());
        //=============================( End Panel Input )==============================//

        
        
        //==============================( PANEL BUTTON )================================//
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
        //============================( End Panel Button )==============================//

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);

//        btnCancel.addActionListener(e -> closePanel());
//        btnAdd.addActionListener(e -> addAuthor());
//        btnSave.addActionListener(e -> saveAuthor());
        
    }

//    private void saveAuthor() {
//        if (!CheckFormInput()) return;
//        try {
//            int maTG = Integer.parseInt(txtMaTG.getText().trim());
//            String tenTG = txtTenTG.getText().trim();
//            TacGiaDTO tg = new TacGiaDTO(maTG, tenTG);
//            if (tgBus.updateTacGia(tg)) {
//                JOptionPane.showMessageDialog(this, "Cập nhật tác giả thành công!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void addAuthor() {
//        if (!CheckFormInput()) return;
//        try {
//            int maTG = Integer.parseInt(txtMaTG.getText().trim());
//            String tenTG = txtTenTG.getText().trim();
//            TacGiaDTO tg = new TacGiaDTO(maTG, tenTG);
//            if (tgBus.addTacGia(tg)) {
//                JOptionPane.showMessageDialog(this, "Thêm tác giả thành công!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Mã tác giả đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private boolean CheckFormInput() {
//        try {
//            if (txtMaTG.getText().trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Mã tác giả không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//            int maTG = Integer.parseInt(txtMaTG.getText().trim());
//            if (maTG < 0) {
//                JOptionPane.showMessageDialog(this, "Mã tác giả phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//            if (txtTenTG.getText().trim().isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Tên tác giả không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    private void closePanel() {
//        SwingUtilities.getWindowAncestor(this).dispose();
//    }
//}
}
