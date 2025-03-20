package GUI.MainContent;

import javax.swing.JPanel;
import Utils.*;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class AboutBookMainContentGUI extends JPanel{
    UIAboutPanel pnlNhaXuatBan, pnlTacGia, pnlTheLoai;
    
    public AboutBookMainContentGUI(){
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        
        int pnlWidth = this.getPreferredSize().width;
        int pnlHeight = this.getPreferredSize().height;
        
        pnlNhaXuatBan = new UIAboutPanel("/Icon/NhaXuatBan_icon.png","NHÀ XUẤT BẢN", pnlWidth - 65, pnlHeight/3 -32);
        pnlTacGia = new UIAboutPanel("/Icon/TacGia_icon.png","TÁC GIẢ", pnlWidth - 65 , pnlHeight/3 - 32);
        pnlTheLoai  = new UIAboutPanel("/Icon/TheLoai_icon.png","THỂ LOẠI", pnlWidth - 65, pnlHeight/3 - 32);
        
        this.add(pnlNhaXuatBan);
        this.add(pnlTacGia);
        this.add(pnlTheLoai);
    }
}
