/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentAboutBook;

import BUS.TheLoaiBUS;
import DTO.TheLoaiDTO;
import Utils.UIButton;
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
}
