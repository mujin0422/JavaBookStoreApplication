/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentAboutBook;

import BUS.TheLoaiBUS;
import DTO.TheLoaiDTO;
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
public class AddAndEditCategoryGUI extends JPanel{
    private JTextField txtMaTL, txtTenTL;
    private UIButton btnAdd, btnSave, btnCancel;
    private TheLoaiBUS tlBus;
    private TheLoaiDTO tl;
    
    public AddAndEditCategoryGUI(TheLoaiBUS tlBus, String type, TheLoaiDTO tl){
        this.tlBus = tlBus;
        this.tl = tl;
        initComponent(type);
        if(tl != null){
            txtMaTL.setText(String.valueOf(tl.getMaTL()));
            txtTenTL.setText(tl.getTenTL());
            txtMaTL.setEnabled(true);
        }
        this.setVisible(true);
    }
    
    public AddAndEditCategoryGUI(TheLoaiBUS tlBus, String type){
        this.tlBus = tlBus;
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
        inputPanel.add(txtMaTL = new JTextField());
        inputPanel.add(new UILabel("Tên thể loại:"));
        inputPanel.add(txtTenTL = new JTextField());
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
