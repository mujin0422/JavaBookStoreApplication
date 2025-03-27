/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentAboutBook;

import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell Vostro
 */
public class AddAndEditPublisherGUI extends JPanel{
    private JTextField txtMaNXB, txtTenNXB;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhaXuatBanBUS nxbBus;
    private NhaXuatBanDTO nxb;
    
    public AddAndEditPublisherGUI(NhaXuatBanBUS nxbBus, String type, NhaXuatBanDTO nxb){
        this.nxbBus = nxbBus;
        this.nxb = nxb;
        initComponent(type);
        if(nxb != null){
            txtMaNXB.setText(String.valueOf(nxb.getMaNXB()));
            txtTenNXB.setText(nxb.getTenNXB());
            txtMaNXB.setEnabled(true);
        }
        this.setVisible(true);
    }
    
    public AddAndEditPublisherGUI(NhaXuatBanBUS nxbBus, String type){
        this.nxbBus = nxbBus;
        initComponent(type);
        this.setVisible(true);
    }
    
    public void initComponent(String type){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new UILabel("Mã thể loại:"));
        inputPanel.add(txtMaNXB = new JTextField());
        inputPanel.add(new UILabel("Tên thể loại:"));
        inputPanel.add(txtTenNXB = new JTextField());
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
        
    
    }
}
