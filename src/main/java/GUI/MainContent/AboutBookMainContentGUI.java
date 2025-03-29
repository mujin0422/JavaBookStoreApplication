package GUI.MainContent;

import BUS.NhaXuatBanBUS;
import BUS.TacGiaBUS;
import BUS.TheLoaiBUS;
import DTO.TacGiaDTO;
import javax.swing.JPanel;
import GUI.MainContentAboutBook.*;
import Utils.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AboutBookMainContentGUI extends JPanel{
    private UIAboutPanel pnlNhaXuatBan, pnlTacGia, pnlTheLoai;
    private JTable tblNhaXuatBan, tblTacGia, tblTheLoai;
    
    
    private DefaultTableModel tableModelNXB, tableModelTG, tableModelTL;  
    private NhaXuatBanBUS nxbBus;
    
    private AddAndEditAuthorGUI pnlAddAndEditTG;
    private TacGiaBUS tgBus;
    private TheLoaiBUS tlBus;
    
    
    
    public AboutBookMainContentGUI(){
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        
        int pnlWidth = this.getPreferredSize().width - 65;
        int pnlHeight = this.getPreferredSize().height /3 - 32;
        
        //=============================( PANEL NHA XUAT BAN )===========================//
        nxbBus = new NhaXuatBanBUS();
        pnlNhaXuatBan = new UIAboutPanel("/Icon/NhaXuatBan_icon.png","NHÀ XUẤT BẢN", pnlWidth , pnlHeight);
        UIButton addNXB = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        UIButton deleteNXB = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        UIButton editNXB = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        pnlNhaXuatBan.addButton(addNXB);
        pnlNhaXuatBan.addButton(deleteNXB);
        pnlNhaXuatBan.addButton(editNXB);
        
       
        
        String[] columnNXBNames = {"MÃ NHÀ XUẤT BẢN", "TÊN NHÀ XUẤT BẢN"};
        Object[][] dataNXB = {};
        
        tblNhaXuatBan = new JTable(new DefaultTableModel(dataNXB, columnNXBNames));
        tblNhaXuatBan.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblNhaXuatBan.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblNhaXuatBan.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblNhaXuatBan.setRowHeight(25);
        
        JScrollPane scrollNXB = new JScrollPane(tblNhaXuatBan);
        scrollNXB.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);
        
        pnlNhaXuatBan.getPnlContent().add(scrollNXB, BorderLayout.EAST);
        //===========================( End Panel Nha Xuat Ban )=========================//
        
        
        
        //================================( PANEL TAC GIA )=============================//
        tgBus = new TacGiaBUS();
        pnlTacGia = new UIAboutPanel("/Icon/TacGia_icon.png","TÁC GIẢ", pnlWidth , pnlHeight);
        UIButton addTG = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addTG.addActionListener(e -> addTacGia());
        UIButton deleteTG = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        UIButton editTG = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        pnlTacGia.addButton(addTG);
        pnlTacGia.addButton(deleteTG);
        pnlTacGia.addButton(editTG);
        //$$$$$
        pnlTacGia.getPnlInutAndButton().add(new AddAndEditAuthorGUI(tgBus,"add"));
        
        String[] columnTGNames = {"MÃ TÁC GIẢ", "TÊN TÁC GIẢ"};
        tableModelTG = new DefaultTableModel(columnTGNames,0);
        tblTacGia = new JTable(tableModelTG);
        tblTacGia.setDefaultEditor(Object.class,null);
        
        tblTacGia.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblTacGia.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblTacGia.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblTacGia.setRowHeight(25);
        
        JScrollPane scrollTG = new JScrollPane(tblTacGia);
        scrollTG.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);
        
        pnlTacGia.getPnlContent().add(scrollTG, BorderLayout.EAST);
        //==============================( End Panel Tac Gia )===========================//
        
        
        
        //================================( PANEL THE LOAI )============================//
        tlBus = new TheLoaiBUS();
        pnlTheLoai  = new UIAboutPanel("/Icon/TheLoai_icon.png","THỂ LOẠI", pnlWidth , pnlHeight);
        UIButton addTL = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        UIButton deleteTL = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        UIButton editTL = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        pnlTheLoai.addButton(addTL);
        pnlTheLoai.addButton(deleteTL);
        pnlTheLoai.addButton(editTL);
        
        String[] columnTLNames = {"MÃ THỂ LOẠI", "THỂ LOẠI"};
        Object[][] dataTL = {};
        
        tblTheLoai = new JTable(new DefaultTableModel(dataTL, columnTLNames));
        tblTheLoai.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblTheLoai.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblTheLoai.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblTheLoai.setRowHeight(25);
        
        JScrollPane scrollTL = new JScrollPane(tblTheLoai);
        scrollTL.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);
        
        pnlTheLoai.getPnlContent().add(scrollTL, BorderLayout.EAST);
        //==============================( End Panel The Loai )==========================//
        
        
        
        
        
        this.add(pnlNhaXuatBan);
        this.add(pnlTacGia);
        this.add(pnlTheLoai);
        loadTableDataNhaXuatBan();
        loadTableDataTacGia();
        loadTableDataTheLoai();
    }
    
    
    
    
    //==================================================================================
    private void loadTableDataNhaXuatBan(){
    }
    
    //==================================================================================
    private void loadTableDataTacGia() {
        // STEP 1: xóa dữ liệu cũ
        tableModelTG.setRowCount(0); 
        // STEP 2: tải từng dòng lên bảng  
        ArrayList<TacGiaDTO> listTG = tgBus.getAllTacGia();
        for (TacGiaDTO tg : listTG) {
            tableModelTG.addRow(new Object[]{
                tg.getMaTG(),
                tg.getTenTG()
            });
        }
    }
   private void addTacGia(){
       pnlTacGia.getPnlInutAndButton().add(new AddAndEditAuthorGUI(tgBus,"add"));
       loadTableDataTacGia();
   
   }
   
   private void editTacGia(){}
   
   private void deleteTacGIa(){}
    
    
    //==================================================================================
    private void loadTableDataTheLoai(){
    
    }
    
}
