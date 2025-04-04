/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContent;

import BUS.PhieuNhapBUS;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell Vostro
 */
public class ImportBookMainContentGUI extends JPanel{
    private UIButton btnAdd;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent, pnlForm, pnlInfo;
    
    private DefaultTableModel tableModel;
    private PhieuNhapBUS phieuNhapBUS;

    public ImportBookMainContentGUI() {
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

       //===============================( Panel Header )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.setBounds(5, 5, 90, 40);

            // Tạo combobox và ô tìm kiếm
        int panelWidth = this.getPreferredSize().width; 
        cbFilter = new JComboBox<>(new String[]{"Lọc"});
        cbFilter.setBounds(panelWidth - 320, 10, 100, 30);

        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);

            // Thêm tất cả vào pnlHeader
        pnlHeader.add(btnAdd);
        pnlHeader.add(cbFilter);
        pnlHeader.add(txtSearch);
        //==============================( End Panel Header )============================//
        
        
        //==================================( PANEL FORM )==============================//
        pnlForm = new JPanel(new BorderLayout());
        pnlForm.setPreferredSize(new Dimension(500, 0));
        pnlForm.setBackground(UIConstants.MAIN_BACKGROUND);
        //================================( End Panel Form )============================//
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setPreferredSize(new Dimension(0, 200));
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);

        // Tạo bảng dữ liệu
        String[] columnNames = {"MÃ PHIẾU NHẬP", "NHÂN VIÊN", "NHÀ CUNG CẤP", "TỔNG TIỀN", "NGÀY GHI PHIẾU"};
        Object[][] data = {}; // Chưa có dữ liệu
        tblContent = new JTable(new DefaultTableModel(data, columnNames));

        // Thiết lập header của bảng
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.setRowHeight(25);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tblContent);
        scrollPane.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);

        // Thêm JScrollPane vào pnlContent
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlForm, BorderLayout.WEST);
        this.add(pnlContent, BorderLayout.SOUTH);
    }
}
