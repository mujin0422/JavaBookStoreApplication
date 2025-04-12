package GUI.ThongKeComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ThongKeSach extends JPanel {
    private JPanel filterPanel; // Khai báo panel lọc
    private JButton btnLocSach; // Nút lọc sách

    public ThongKeSach() {
        setLayout(new BorderLayout());

        // Tạo panel cho nút thống kê
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 3, 10, 10)); // Bố trí đường lưới 2 hàng 3 cột
        topPanel.setBackground(Color.WHITE);

        // Tạo và thêm các nút
        JButton btnTongSach = new JButton("Tổng số sách");
        JButton btnThongKeNhap = new JButton("Danh sách nhập theo tháng");
        JButton btnThongKeXuat = new JButton("Danh sách xuất theo tháng");
        JButton btnThongKeKhoangThang = new JButton("Thống kê từ tháng đến tháng");
        JButton btnThongKeSachTonKho = new JButton("Thống kê tồn kho");
        btnLocSach = new JButton("Lọc sách theo tiêu chí");

        // Thêm các nút vào panel
        topPanel.add(btnTongSach);
        topPanel.add(btnThongKeNhap);
        topPanel.add(btnThongKeXuat);
        topPanel.add(btnThongKeKhoangThang);
        topPanel.add(btnThongKeSachTonKho);
        topPanel.add(btnLocSach);

        add(topPanel, BorderLayout.NORTH);

        // Tạo panel lọc sách
        filterPanel = createFilterPanel(); // Chỉ tạo mà không thêm vào layout
        add(filterPanel, BorderLayout.CENTER);
        filterPanel.setVisible(false); // Ẩn panel lọc ban đầu

        // Đăng ký sự kiện cho các nút
        btnThongKeNhap.addActionListener(e -> showDanhSachNhap());
        btnThongKeXuat.addActionListener(e -> showDanhSachXuat());
        btnThongKeKhoangThang.addActionListener(e -> showThongKeKhoangThang());
        btnTongSach.addActionListener(e -> thongKeTongSach());
        btnThongKeSachTonKho.addActionListener(e -> showThongKeSoLuongTungSach());

        // Đăng ký sự kiện cho nút lọc
        btnLocSach.addActionListener(e -> {
            filterPanel.setVisible(!filterPanel.isVisible()); // Bật hoặc tắt panel lọc
            revalidate(); // Cập nhật layout
            repaint(); // Vẽ lại
        });
    }

private JPanel createFilterPanel() {
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new GridBagLayout());
    filterPanel.setBackground(Color.LIGHT_GRAY); // Màu nền cho panel lọc sách
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;

    // Định nghĩa và thêm các nhãn và trường nhập liệu
    gbc.gridwidth = 1; // Đặt lại gridwidth để không bị chiếm hết cột
    gbc.gridy++;
    JLabel lblMaSach = new JLabel("Mã sách:");
    filterPanel.add(lblMaSach, gbc);
    
    JTextField txtMaSach = new JTextField(15);
    gbc.gridx = 1;
    filterPanel.add(txtMaSach, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    JLabel lblTenSach = new JLabel("Tên sách:");
    filterPanel.add(lblTenSach, gbc);
    
    JTextField txtTenSach = new JTextField(15);
    gbc.gridx = 1;
    filterPanel.add(txtTenSach, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    JLabel lblNXB = new JLabel("Nhà xuất bản:");

    JPanel nxbPanel = new JPanel(new BorderLayout());
    JComboBox<String> cmbNXB = new JComboBox<>();
    cmbNXB.addItem(""); // Tùy chọn trống
    cmbNXB.addItem("Tất cả"); // Tùy chọn "Tất cả"
    // Thêm các nhà xuất bản vào JComboBox từ CSDL
    populateNXBComboBox(cmbNXB);
    nxbPanel.add(cmbNXB, BorderLayout.WEST);

    
    
    
    filterPanel.add(lblNXB, gbc);
    gbc.gridx = 1;
    filterPanel.add(nxbPanel, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    JLabel lblTheLoai = new JLabel("Thể loại:");

    JPanel theloaiPanel = new JPanel(new BorderLayout());
    JComboBox<String> cmbTheLoai = new JComboBox<>();
    cmbTheLoai.addItem(""); // Tùy chọn trống
    cmbTheLoai.addItem("Tất cả"); // Tùy chọn "Tất cả"
    // Thêm các thể loại vào JComboBox từ CSDL
    populateTheLoaiComboBox(cmbTheLoai);
    theloaiPanel.add(cmbTheLoai, BorderLayout.WEST);

    
    
    filterPanel.add(lblTheLoai, gbc);
    gbc.gridx = 1;
    filterPanel.add(theloaiPanel, gbc);

    // Nút Lọc
    JButton btnFilter = new JButton("Lọc");
    gbc.gridx = 1;
    gbc.gridy++;
    filterPanel.add(btnFilter, gbc);

    // Thiết lập hành động cho nút Lọc
    btnFilter.addActionListener(e -> {
        String maSach = txtMaSach.getText().trim();
        String tenSach = txtTenSach.getText().trim();
        String nxb = (String) cmbNXB.getSelectedItem();
        
        String theLoai = (String) cmbTheLoai.getSelectedItem();
       
            
        locSachTheoTieuChi(maSach, tenSach, nxb, theLoai);
    });

    return filterPanel;
}

// Hàm thêm các nhà xuất bản vào JComboBox
private void populateNXBComboBox(JComboBox<String> comboBox) {
    String query = "SELECT tenNXB FROM nhaxuatban";
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            String tenNXB = rs.getString("tenNXB");
            comboBox.addItem(tenNXB); // Thêm tên NXB vào combobox
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải nhà xuất bản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

// Hàm thêm các thể loại vào JComboBox
private void populateTheLoaiComboBox(JComboBox<String> comboBox) {
    String query = "SELECT tenTL FROM theloai";
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {
        while (rs.next()) {
            String tenTL = rs.getString("tenTL");
            comboBox.addItem(tenTL); // Thêm tên thể loại vào combobox
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải thể loại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

private void showDanhSachNhap() {
        String thang = JOptionPane.showInputDialog(this, "Nhập tháng (1-12):");
        String nam = JOptionPane.showInputDialog(this, "Nhập năm:");

        if (thang == null || nam == null) return;

        String query = """
            SELECT s.maSach, s.tenSach, pn.ngayNhap, ctpn.soLuong
            FROM phieunhap pn
            JOIN chitietphieunhap ctpn ON pn.maPN = ctpn.maPN
            JOIN sach s ON ctpn.maSach = s.maSach
            WHERE MONTH(pn.ngayNhap) = ? AND YEAR(pn.ngayNhap) = ?
        """;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, Integer.parseInt(thang));
            pst.setInt(2, Integer.parseInt(nam));
            ResultSet rs = pst.executeQuery();

            StringBuilder result = new StringBuilder("Danh sách sách nhập tháng " + thang + " năm " + nam + ":\n");
            int tongSoLuong = 0;

            while (rs.next()) {
                String maSach = rs.getString("maSach");
                String tenSach = rs.getString("tenSach");
                Date ngayNhap = rs.getDate("ngayNhap");
                int soLuong = rs.getInt("soLuong");
                tongSoLuong += soLuong;
                result.append(String.format("Mã sách: %s, Tên sách: %s, Ngày nhập: %s, Số lượng: %d\n",
                        maSach, tenSach, ngayNhap, soLuong));
            }

            result.append("\nTổng số lượng sách nhập: ").append(tongSoLuong);
            JOptionPane.showMessageDialog(this, result.toString(), "Thông tin nhập", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void showThongKeSoLuongTungSach() {
    String query = """
        SELECT s.maSach, s.tenSach, nxb.tenNXB,
               GROUP_CONCAT(DISTINCT tl.tenTL SEPARATOR ', ') AS theLoai,
               IFNULL(nhap.totalNhap, 0) - IFNULL(xuat.totalXuat, 0) AS soLuongTon,
               (SELECT SUM(IFNULL(nhap.totalNhap, 0) - IFNULL(xuat.totalXuat, 0))
                FROM sach s2
                LEFT JOIN (
                    SELECT maSach, SUM(soLuong) as totalNhap
                    FROM chitietphieunhap ct
                    JOIN phieunhap pn ON ct.maPN = pn.maPN
                    GROUP BY maSach
                ) nhap ON s2.maSach = nhap.maSach
                LEFT JOIN (
                    SELECT maSach, SUM(soLuong) as totalXuat
                    FROM chitietphieuxuat ct
                    JOIN phieuxuat px ON ct.maPX = px.maPX
                    GROUP BY maSach
                ) xuat ON s2.maSach = xuat.maSach
                WHERE s2.trangThaiXoa = 0
               ) AS tongSoLuongTon
        FROM sach s
        LEFT JOIN nhaxuatban nxb ON s.maNXB = nxb.maNXB
        LEFT JOIN nhomtheloai ntl ON s.maSach = ntl.maSach
        LEFT JOIN theloai tl ON ntl.maTL = tl.maTL
        LEFT JOIN (
            SELECT maSach, SUM(soLuong) as totalNhap
            FROM chitietphieunhap ct
            JOIN phieunhap pn ON ct.maPN = pn.maPN
            GROUP BY maSach
        ) nhap ON s.maSach = nhap.maSach
        LEFT JOIN (
            SELECT maSach, SUM(soLuong) as totalXuat
            FROM chitietphieuxuat ct
            JOIN phieuxuat px ON ct.maPX = px.maPX
            GROUP BY maSach
        ) xuat ON s.maSach = xuat.maSach
        WHERE s.trangThaiXoa = 0
        GROUP BY s.maSach, s.tenSach, nxb.tenNXB, nhap.totalNhap, xuat.totalXuat
        ORDER BY s.tenSach
    """;

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        StringBuilder result = new StringBuilder("Thống kê số lượng tồn kho theo sách:\n");
        int tongSoLuongTon = 0;

        while (rs.next()) {
            String maSach = rs.getString("maSach");
            String tenSach = rs.getString("tenSach");
            String tenNXB = rs.getString("tenNXB");
            String theLoai = rs.getString("theLoai");
            int soLuongTon = rs.getInt("soLuongTon");
            tongSoLuongTon += soLuongTon;

            result.append(String.format("Mã: %s | Tên: %s | Thể loại: %s | NXB: %s | Số lượng tồn: %d\n",
                    maSach, tenSach, theLoai, tenNXB, soLuongTon));
        }

        result.append(String.format("\nTổng số lượng sách tồn kho: %d", tongSoLuongTon));
        JOptionPane.showMessageDialog(this, result.toString(), "Thống kê số lượng tồn sách", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


    private void showDanhSachXuat() {
        String thang = JOptionPane.showInputDialog(this, "Nhập tháng (1-12):");
        String nam = JOptionPane.showInputDialog(this, "Nhập năm:");

        if (thang == null || nam == null) return;

        String query = """
            SELECT s.maSach, s.tenSach, px.ngayXuat, ctpx.soLuong
            FROM phieuxuat px
            JOIN chitietphieuxuat ctpx ON px.maPX = ctpx.maPX
            JOIN sach s ON ctpx.maSach = s.maSach
            WHERE MONTH(px.ngayXuat) = ? AND YEAR(px.ngayXuat) = ?
        """;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, Integer.parseInt(thang));
            pst.setInt(2, Integer.parseInt(nam));
            ResultSet rs = pst.executeQuery();

            StringBuilder result = new StringBuilder("Danh sách sách xuất tháng " + thang + " năm " + nam + ":\n");
            int tongSoLuong = 0;

            while (rs.next()) {
                String maSach = rs.getString("maSach");
                String tenSach = rs.getString("tenSach");
                Date ngayXuat = rs.getDate("ngayXuat");
                int soLuong = rs.getInt("soLuong");
                tongSoLuong += soLuong;
                result.append(String.format("Mã sách: %s, Tên sách: %s, Ngày xuất: %s, Số lượng: %d\n",
                        maSach, tenSach, ngayXuat, soLuong));
            }

            result.append("\nTổng số lượng sách xuất: ").append(tongSoLuong);
            JOptionPane.showMessageDialog(this, result.toString(), "Thông tin xuất", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showThongKeKhoangThang() {
        try {
            int tuThang = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập tháng bắt đầu (1-12):"));
            int denThang = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập tháng kết thúc (1-12):"));
            int nam = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập năm:"));

            if (tuThang > denThang || tuThang < 1 || denThang > 12) {
                JOptionPane.showMessageDialog(this, "Khoảng tháng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int tongNhap = layTongSoLuong("phieunhap", "ngayNhap", "chitietphieunhap", "maPN", nam, tuThang, denThang);
            int tongXuat = layTongSoLuong("phieuxuat", "ngayXuat", "chitietphieuxuat", "maPX", nam, tuThang, denThang);

            String thongBao = String.format(
                    "Thống kê từ tháng %d đến %d năm %d:\n- Tổng sách nhập: %d\n- Tổng sách xuất: %d",
                    tuThang, denThang, nam, tongNhap, tongXuat
            );

            JOptionPane.showMessageDialog(this, thongBao, "Thống kê theo khoảng tháng", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int layTongSoLuong(String phieuTable, String ngayCol, String chiTietTable, String maPhieu,
                               int nam, int tuThang, int denThang) {
        String query = String.format("""
            SELECT SUM(ct.soLuong) as tong
            FROM %s p
            JOIN %s ct ON p.%s = ct.%s
            WHERE YEAR(p.%s) = ? AND MONTH(p.%s) BETWEEN ? AND ?
        """, phieuTable, chiTietTable, maPhieu, maPhieu, ngayCol, ngayCol);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, nam);
            pst.setInt(2, tuThang);
            pst.setInt(3, denThang);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("tong");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void thongKeTongSach() {
        String query = "SELECT COUNT(*) AS tong FROM sach WHERE trangThaiXoa = 0";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                int tong = rs.getInt("tong");
                JOptionPane.showMessageDialog(this, "Tổng số sách trong hệ thống: " + tong,
                        "Tổng sách", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
private void locSachTheoTieuChi(String maSach, String tenSach, String nxb, String theLoai) {
    String query = """
        SELECT s.maSach, s.tenSach, nxb.tenNXB, tl.tenTL,
               (SELECT COUNT(*) FROM sach s2
                JOIN nhaxuatban nxb2 ON s2.maNXB = nxb2.maNXB
                JOIN nhomtheloai ntl2 ON s2.maSach = ntl2.maSach
                JOIN theloai tl2 ON ntl2.maTL = tl2.maTL
                WHERE s2.trangThaiXoa = 0
                  AND (? IS NULL OR s2.maSach = ?)
                  AND (? IS NULL OR s2.tenSach = ?)
                  AND (? IS NULL OR nxb2.tenNXB = ?)
                  AND (? IS NULL OR tl2.tenTL = ?)
               ) AS tongSoLuong
        FROM sach s
        JOIN nhaxuatban nxb ON s.maNXB = nxb.maNXB
        JOIN nhomtheloai ntl ON s.maSach = ntl.maSach
        JOIN theloai tl ON ntl.maTL = tl.maTL
        WHERE s.trangThaiXoa = 0
          AND (? IS NULL OR s.maSach = ?)
          AND (? IS NULL OR s.tenSach = ?)
          AND (? IS NULL OR nxb.tenNXB = ?)
          AND (? IS NULL OR tl.tenTL = ?)
        GROUP BY s.maSach, s.tenSach, nxb.tenNXB, tl.tenTL
    """;

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlicuahangsach", "root", "");
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Gán tham số cho truy vấn
        setPreparedStatementParameters(pst, maSach, tenSach, nxb, theLoai);

        ResultSet rs = pst.executeQuery();
        StringBuilder result = new StringBuilder("Kết quả lọc sách:\n");
        int tongSoLuong = 0;
        boolean hasResults = false;

        while (rs.next()) {
            hasResults = true;
            tongSoLuong++; // Mỗi sách là 1 dòng thỏa mãn

            result.append(String.format("Mã: %s, Tên: %s, NXB: %s, Thể loại: %s\n",
                    rs.getString("maSach"), rs.getString("tenSach"),
                    rs.getString("tenNXB"), rs.getString("tenTL")));
        }

        if (!hasResults) {
            result.append("Không có kết quả nào thỏa mãn tiêu chí lọc.");
        } else {
            result.append(String.format("Tổng số lượng sách thỏa mãn tiêu chí: %d\n", tongSoLuong));
        }

        JOptionPane.showMessageDialog(this, result.toString(), "Kết quả lọc", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi lọc sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

private void setPreparedStatementParameters(PreparedStatement pst, String maSach, String tenSach, String nxb, String theLoai) throws SQLException {
    // Danh sách giá trị truyền vào
    String[] params = {maSach, tenSach, nxb, theLoai, maSach, tenSach, nxb, theLoai};

    for (int i = 0; i < params.length; i++) {
        String value = params[i];
        int index1 = i * 2 + 1; // Tham số ? IS NULL
        int index2 = i * 2 + 2; // Tham số = ?

        if (value != null && !value.equalsIgnoreCase("Tất cả") && !value.trim().isEmpty()) {
            pst.setString(index1, value);
            pst.setString(index2, value);
        } else {
            pst.setNull(index1, Types.VARCHAR);
            pst.setNull(index2, Types.VARCHAR);
        }
    }
}


    public static void main(String[] args) {
        JFrame frame = new JFrame("Thống kê sách");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.add(new ThongKeSach());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

