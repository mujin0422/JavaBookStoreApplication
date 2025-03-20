package Utils;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class UIAboutPanel extends JPanel {
    private JPanel pnlIcon_Text, pnlButton ,pnlContent;
    
    public UIAboutPanel(String iconUrl, String text, int width, int height) {
        initComponent(iconUrl, text, width, height);
    }
    
    public void initComponent(String iconUrl, String text, int width, int height){
        this.setBackground(UIConstants.MAIN_BUTTON);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        //======================= Panel Icon and Text ==========================
        pnlIcon_Text = new JPanel(new BorderLayout());
        pnlIcon_Text.setBackground(UIConstants.MAIN_BUTTON);
        pnlIcon_Text.setPreferredSize(new Dimension(width *15/100, height));
        // Thêm Icon
        int iconWidth = pnlIcon_Text.getPreferredSize().width;
        int iconHeight = iconWidth;
        JLabel lblIcon = new JLabel();
        lblIcon.setBackground(UIConstants.MAIN_BUTTON);
        ImageIcon icon = new ImageIcon(getClass().getResource(iconUrl));
        Image scaleIcon = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        lblIcon.setIcon(new ImageIcon(scaleIcon));
        // Thêm Text
        JLabel lblText = new JLabel(text, JLabel.CENTER);
        lblText.setBackground(UIConstants.MAIN_BUTTON);
        lblText.setFont(UIConstants.TITLE_FONT);
        lblText.setForeground(UIConstants.WHITE_FONT);
        
        pnlIcon_Text.add(lblIcon,BorderLayout.CENTER);
        pnlIcon_Text.add(lblText,BorderLayout.SOUTH);
        
        //=========================== Panel Button =============================
        pnlButton = new JPanel();
        pnlButton.setPreferredSize(new Dimension(width *10/100, height));
        
        //=========================== Panel Content ============================
        pnlContent = new JPanel();
        pnlContent.setPreferredSize(new Dimension(width *75/100, height));
        
        this.add(pnlIcon_Text);
        this.add(pnlButton);
        this.add(pnlContent);
    }
    
   
    
}
