/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentAboutBook;

import BUS.NhaXuatBanBUS;
import DTO.NhaXuatBanDTO;
import Utils.UIButton;
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
}
