CREATE TABLE THELOAI (
    maTL INT PRIMARY KEY,
    tenTL NVARCHAR(30) NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO THELOAI (maTL, tenTL) VALUES
(1, N'Văn học'),
(2, N'Khoa học - Công nghệ'),
(3, N'Kinh tế - Quản lý'),
(4, N'Thiếu nhi'),
(5, N'Lịch sử - Địa lý'),
(6, N'Tâm lý - Kỹ năng sống'),
(7, N'Giáo trình - Sách giáo khoa'),
(8, N'Tiểu thuyết trinh thám'),
(9, N'Khoa học viễn tưởng'),
(10, N'Truyện tranh');


CREATE TABLE TACGIA (
    maTG INT PRIMARY KEY,
    tenTG NVARCHAR(50) NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO TACGIA (maTG, tenTG) VALUES
(1, N'Nguyễn Nhật Ánh'), 
(2, N'J.K. Rowling'), 
(3, N'Fujiko F. Fujio'), 
(4, N'Agatha Christie'), 
(5, N'Haruki Murakami'),
(6, N'Dale Carnegie'), 
(7, N'George Orwell'), 
(8, N'Yuval Noah Harari'), 
(9, N'Nguyễn Du'), 
(10, N'Ernest Hemingway'), 
(11, N'Leo Tolstoy'), 
(12, N'Fyodor Dostoevsky'), 
(13, N'Victor Hugo'),
(14, N'Gabriel García Márquez'), 
(15, N'William Shakespeare'), 
(16, N'Mark Twain'), 
(17, N'Alexandre Dumas'), 
(18, N'Paulo Coelho'), 
(19, N'Franz Kafka'), 
(20, N'Dan Brown');


CREATE TABLE NHAXUATBAN (
    maNXB INT PRIMARY KEY,
    tenNXB NVARCHAR(50) NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO NHAXUATBAN (maNXB, tenNXB) VALUES
(1, N'Nhà xuất bản Trẻ'),
(2, N'Nhà xuất bản Kim Đồng'),
(3, N'Nhà xuất bản Giáo Dục Việt Nam'),
(4, N'Nhà xuất bản Tổng Hợp TP.HCM'),
(5, N'Nhà xuất bản Văn Học');


CREATE TABLE NHACUNGCAP (
    maNCC INT PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(100),
    sdt VARCHAR(15),
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO NHACUNGCAP (maNCC, tenNCC, diaChi, sdt) VALUES
(1, N'Nhà Sách Fahasa', N'21 Lý Chính Thắng, Quận 3, TP.HCM', '02838208384'),
(2, N'Nhà Sách Phương Nam', N'940 Đường 3/2, Quận 11, TP.HCM', '02838622222'),
(3, N'Công Ty Sách Nhã Nam', N'59 Đặng Thai Mai, Tây Hồ, Hà Nội', '02437151101'),
(4, N'Tiki Trading', N'52 Út Tịch, Quận Tân Bình, TP.HCM', '19006035'),
(5, N'Alpha Books', N'20/11 Nguyễn Hữu Cảnh, Phường 19, Quận Bình Thạnh, TP.HCM', '02835126787');


CREATE TABLE NHANVIEN (
    maNV INT PRIMARY KEY,
    tenNV NVARCHAR(50) NOT NULL,
    email VARCHAR(50),
    sdt VARCHAR(15),
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO NHANVIEN (maNV, tenNV, email, sdt) VALUES
(1, N'Trần Thị Huỳnh Như', 'nhu@gmail.com', '0123456789'),
(2, N'Bào Thanh Tâm', 'tam@gmail.com', '0987654321'),
(3, N'Phạm Đình Duy Thái', 'thai@gmail.com', '0909123456'),
(4, N'Nguyễn Thị Thùy Trâm', 'tram@gmail.com', '0912345678'),
(5, N'Nguyễn Ngọc Thúy Vy', 'vy@gmail.com', '0922334455'),
(6, N'Nguyễn Văn A', 'a@gmail.com', '0934567890'),
(7, N'Trần Văn B', 'b@gmail.com', '0945678901'),
(8, N'Lê Thị C', 'c@gmail.com', '0956789012'),
(9, N'Hoàng Minh D', 'd@gmail.com', '0967890123'),
(10, N'Đỗ Thanh E', 'e@gmail.com', '0978901234');


CREATE TABLE KHACHHANG (
    maKH INT PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    sdt VARCHAR(15),
    email VARCHAR(50),
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO KHACHHANG (maKH, tenKH, sdt, email) VALUES
(1, N'Nguyễn Văn An', '0901234567', 'annguyen@gmail.com'),
(2, N'Trần Thị Bích Ngọc', '0912345678', 'ngoctran@gmail.com'),
(3, N'Lê Văn Cường', '0923456789', 'cuongle@gmail.com'),
(4, N'Phạm Minh Tâm', '0934567890', 'tampham@gmail.com'),
(5, N'Đỗ Hoàng Nam', '0945678901', 'namdo@gmail.com'),
(6, N'Võ Thanh Thảo', '0956789012', 'thaovo@gmail.com'),
(7, N'Huỳnh Nhật Hào', '0967890123', 'haohuynh@gmail.com'),
(8, N'Bùi Quỳnh Như', '0978901234', 'nhubui@gmail.com'),
(9, N'Ngô Thị Lan', '0989012345', 'lanngo@gmail.com'),
(10, N'Hoàng Tuấn Kiệt', '0990123456', 'kiethoang@gmail.com'),
(11, N'Phan Thị Hồng', '0901111222', 'hongphan@gmail.com'),
(12, N'Đặng Văn Long', '0912222333', 'longdang@gmail.com'),
(13, N'Lý Minh Khoa', '0923333444', 'khoaly@gmail.com'),
(14, N'Tống Thị Mai', '0934444555', 'maitong@gmail.com'),
(15, N'Tạ Quốc Bảo', '0945555666', 'baota@gmail.com'),
(16, N'Kiều Ngọc Trinh', '0956666777', 'trinhkieu@gmail.com'),
(17, N'Mai Thanh Hùng', '0967777888', 'hungmai@gmail.com'),
(18, N'Trịnh Thị Yến', '0978888999', 'yentrinh@gmail.com'),
(19, N'Cao Hoàng Phúc', '0989999000', 'phuccao@gmail.com'),
(20, N'Thái Thị Hương', '0990000111', 'huongthai@gmail.com'),
(21, N'Lâm Nhật Minh', '0902222333', 'minhlam@gmail.com'),
(22, N'Châu Mỹ Linh', '0913333444', 'linhchau@gmail.com'),
(23, N'Trương Văn Hào', '0924444555', 'haotruong@gmail.com'),
(24, N'Hồ Thị Hạnh', '0935555666', 'hanhho@gmail.com'),
(25, N'Vương Quốc Tuấn', '0946666777', 'tuanvuong@gmail.com');


CREATE TABLE HANHDONG (
	maHD VARCHAR(10) PRIMARY KEY,
	tenHD NVARCHAR(30) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO HANHDONG (maHD, tenHD) VALUES
('view', N'XEM'), 
('add', N'THÊM'), 
('edit', N'SỬA'), 
('delete', N'XÓA');


CREATE TABLE QUYEN (
    maQuyen INT PRIMARY KEY,
    tenQuyen NVARCHAR(30) NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO QUYEN (maQuyen, tenQuyen) VALUES
(1, N'Quản trị viên (admin)'),
(2, N'Quản lí'),
(3, N'Nhân viên bán hàng'),
(4, N'Nhân viên kế toán');


CREATE TABLE CHUCNANG (
    maCN INT PRIMARY KEY,
    tenCN NVARCHAR(30) NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO CHUCNANG (maCN, tenCN) VALUES
(1, N'Quản Lí Sách'),
(2, N'Quản Lí Thông Tin Sách'),
(3, N'Quản Lí Khách Hàng'),
(4, N'Quản Lí Nhân Viên'),
(5, N'Quản Lí Tài Khoản'),
(6, N'Quản Lí Nhà Cung Cấp'),
(7, N'Quản Lí Nhập Hàng'),
(8, N'Quản Lí Xuất Hàng'),
(9, N'Quản Lí Phân Quyền'),
(10, N'Quản Lí Thống Kê');


CREATE TABLE SACH (
    maSach INT PRIMARY KEY,
    tenSach NVARCHAR(100) NOT NULL,
    giaSach INT NOT NULL,
    soLuongTon INT NOT NULL,
    maNXB INT NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNXB) REFERENCES NHAXUATBAN(maNXB)
);
INSERT INTO SACH (maSach, tenSach, giaSach, soLuongTon, maNXB) VALUES
(1, N'Tôi thấy hoa vàng trên cỏ xanh', 120000, 50, 1),
(2, N'Harry Potter và Hòn đá phù thủy', 150000, 80, 2),
(3, N'Bí mật tư duy triệu phú', 180000, 50, 3),
(4, N'21 Bài học cho thế kỷ 21', 200000, 50, 4),
(5, N'Nhà giả kim', 130000, 70, 1),
(6, N'Nghệ thuật đàm phán', 170000, 50, 1),
(7, N'Lược sử thời gian', 220000, 60, 1),
(8, N'Một thoáng ta rực rỡ ở nhân gian', 160000, 50, 1),
(9, N'Chuyện con mèo dạy hải âu bay', 90000, 50, 2),
(10, N'Không gia đình', 140000, 50, 3),
(11, N'Bách khoa thư vũ trụ', 250000, 50, 1),
(12, N'Khoa học kỳ thú', 180000, 50, 1),
(13, N'Chinh phục toán học', 190000, 70, 3),
(14, N'Vũ trụ trong vỏ hạt dẻ', 230000, 50, 1),
(15, N'Đi tìm lẽ sống', 135000, 75, 4),
(16, N'Tư duy nhanh và chậm', 210000, 50, 2),
(17, N'Chiến tranh tiền tệ', 195000, 50, 2),
(18, N'Văn hóa & Con người', 170000, 70, 2),
(19, N'Trí tuệ xúc cảm', 165000, 50, 1),
(20, N'Sống tối giản', 145000, 70, 1);


CREATE TABLE PHIEUNHAP (
    maPN INT PRIMARY KEY,
    maNV INT NOT NULL,
    maNCC INT NOT NULL,
    tongTien INT,
    ngayNhap DATE,
    trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maNCC) REFERENCES NHACUNGCAP(maNCC)
);
INSERT INTO PHIEUNHAP (maPN, maNV, maNCC, tongTien, ngayNhap) VALUES
(1, 3, 3, 171500000, '2025-03-11'),
(2, 2, 1, 9300000, '2025-03-15'),
(3, 3, 1, 13475000, '2025-03-28');

CREATE TABLE PHIEUXUAT (
    maPX INT PRIMARY KEY,
    maNV INT NOT NULL,
    maKH INT NOT NULL,
    tongTien INT,
    ngayXuat DATE,
    trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maKH) REFERENCES KHACHHANG(maKH)
);

CREATE TABLE TAIKHOAN (
    tenDangNhap VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(50) NOT NULL,
    maNV INT NOT NULL,
    maQuyen INT NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen)
);

INSERT INTO TAIKHOAN (tenDangNhap, matKhau, maNV, maQuyen) VALUES
('admin1', '123456', 1, 1),
('admin2', '123456', 2, 1),
('admin3', '123456', 3, 1),
('admin4', '123456', 4, 1),
('admin5', '123456', 5, 1),
('banhang1', '123456', 6, 3), 
('banhang2', '123456', 7, 3), 
('banhang3', '123456', 8, 3), 
('ketoan1', '123456', 9, 4),    
('ketoan2', '123456', 10, 4);


CREATE TABLE NHOMTHELOAI (
    maTL INT NOT NULL,
    maSach INT NOT NULL,
    PRIMARY KEY (maTL, maSach),
    FOREIGN KEY (maTL) REFERENCES THELOAI(maTL),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);
INSERT INTO NHOMTHELOAI (maSach, maTL) VALUES
(1, 1),  -- Tôi thấy hoa vàng trên cỏ xanh -> Văn học
(2, 8),  -- Harry Potter và Hòn đá phù thủy -> Tiểu thuyết trinh thám
(3, 6),  -- Bí mật tư duy triệu phú -> Tâm lý - Kỹ năng sống
(4, 2),  -- 21 Bài học cho thế kỷ 21 -> Khoa học - Công nghệ
(5, 1),  -- Nhà giả kim -> Văn học
(6, 6),  -- Nghệ thuật đàm phán -> Tâm lý - Kỹ năng sống
(7, 2),  -- Lược sử thời gian -> Khoa học - Công nghệ
(8, 1),  -- Một thoáng ta rực rỡ ở nhân gian -> Văn học
(9, 4),  -- Chuyện con mèo dạy hải âu bay -> Thiếu nhi
(10, 1), -- Không gia đình -> Văn học
(11, 2), -- Bách khoa thư vũ trụ -> Khoa học - Công nghệ
(12, 2), -- Khoa học kỳ thú -> Khoa học - Công nghệ
(13, 7), -- Chinh phục toán học -> Giáo trình - Sách giáo khoa
(14, 2), -- Vũ trụ trong vỏ hạt dẻ -> Khoa học - Công nghệ
(15, 6), -- Đi tìm lẽ sống -> Tâm lý - Kỹ năng sống
(16, 3), -- Tư duy nhanh và chậm -> Kinh tế - Quản lý
(17, 3), -- Chiến tranh tiền tệ -> Kinh tế - Quản lý
(18, 5), -- Văn hóa & Con người -> Lịch sử - Địa lý
(19, 6), -- Trí tuệ xúc cảm -> Tâm lý - Kỹ năng sống
(20, 6), -- Sống tối giản -> Tâm lý - Kỹ năng sống

-- Các sách có nhiều thể loại
(2, 9),  -- Harry Potter và Hòn đá phù thủy -> Khoa học viễn tưởng
(7, 5),  -- Lược sử thời gian -> Lịch sử - Địa lý
(11, 5), -- Bách khoa thư vũ trụ -> Lịch sử - Địa lý
(14, 9), -- Vũ trụ trong vỏ hạt dẻ -> Khoa học viễn tưởng
(16, 6), -- Tư duy nhanh và chậm -> Tâm lý - Kỹ năng sống
(17, 6), -- Chiến tranh tiền tệ -> Tâm lý - Kỹ năng sống
(19, 3); -- Trí tuệ xúc cảm -> Kinh tế - Quản lý


CREATE TABLE NHOMTACGIA (
    maTG INT NOT NULL,
    maSach INT NOT NULL,
    PRIMARY KEY (maTG, maSach),
    FOREIGN KEY (maTG) REFERENCES TACGIA(maTG),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);
INSERT INTO NHOMTACGIA (maTG, maSach) VALUES
(1, 1), (2, 2), (6, 3), (8, 4), (5, 5), (16, 6), (7, 7), (18, 8), (9, 9), (10, 10),
(7, 11), (14, 11), (3, 12), (19, 12), (17, 13), (6, 13), (7, 14), (16, 14),
(12, 15), (5, 15), (4, 16), (20, 16), (11, 17), (18, 17), (13, 18), (9, 18),
(15, 19), (3, 19), (1, 20), (2, 20);

CREATE TABLE CHITIETPHIEUNHAP (
    maSach INT NOT NULL,
    maPN INT NOT NULL,
    giaNhap INT NOT NULL,
    soLuong INT NOT NULL,
    PRIMARY KEY (maSach, maPN),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach),
    FOREIGN KEY (maPN) REFERENCES PHIEUNHAP(maPN)
);
INSERT INTO CHITIETPHIEUNHAP (maSach, maPN, giaNhap, soLuong) VALUES
(1, 1, 6000000, 50),
(2, 1, 7500000, 50),
(2, 2, 4500000, 30),
(3, 1, 9000000, 50),
(4, 1, 10000000, 50),
(5, 1, 6500000, 50),
(5, 2, 2600000, 20),
(6, 1, 8500000, 50),
(7, 1, 11000000, 50),
(7, 2, 2200000, 10),
(8, 1, 8000000, 50),
(9, 1, 4500000, 50),
(10, 1, 7000000, 50),
(11, 1, 12500000, 50),
(12, 1, 9000000, 50),
(13, 1, 9500000, 50),
(13, 3, 3800000, 20),
(14, 1, 11500000, 50),
(15, 1, 6750000, 50),
(15, 3, 3375000, 25),
(16, 1, 10500000, 50),
(17, 1, 9750000, 50),
(18, 1, 8500000, 50),
(18, 3, 3400000, 20),
(19, 1, 8250000, 50),
(20, 1, 7250000, 50),
(20, 3, 2900000, 20);

CREATE TABLE CHITIETPHIEUXUAT (
    maSach INT NOT NULL,
    maPX INT NOT NULL,
    soLuong INT NOT NULL,
    giaBan INT NOT NULL,
    PRIMARY KEY (maSach, maPX),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach),
    FOREIGN KEY (maPX) REFERENCES PHIEUXUAT(maPX)
);

CREATE TABLE CHITIETCHUCNANG (
    maCN INT NOT NULL,
    maQuyen INT NOT NULL,
	maHD VARCHAR(10) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    PRIMARY KEY (maCN, maQuyen, maHD),
    FOREIGN KEY (maCN) REFERENCES CHUCNANG(maCN),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen),
	FOREIGN KEY (maHD) REFERENCES HANHDONG(maHD)
);
INSERT INTO CHITIETCHUCNANG(maCN, maQuyen, maHD) VALUES
(1, 1, 'view'), (1, 1, 'add'), (1, 1, 'edit'), (1, 1, 'delete'),
(2, 1, 'view'), (2, 1, 'add'), (2, 1, 'edit'), (2, 1, 'delete'),
(3, 1, 'view'), (3, 1, 'add'), (3, 1, 'edit'), (3, 1, 'delete'),
(4, 1, 'view'), (4, 1, 'add'), (4, 1, 'edit'), (4, 1, 'delete'),
(5, 1, 'view'), (5, 1, 'add'), (5, 1, 'edit'), (5, 1, 'delete'),
(6, 1, 'view'), (6, 1, 'add'), (6, 1, 'edit'), (6, 1, 'delete'),
(7, 1, 'view'), (7, 1, 'add'), (7, 1, 'edit'), (7, 1, 'delete'),
(8, 1, 'view'), (8, 1, 'add'), (8, 1, 'edit'), (8, 1, 'delete'),
(9, 1, 'view'), (9, 1, 'add'), (9, 1, 'edit'), (9, 1, 'delete'),
(10, 1, 'view'), (10, 1, 'add'), (10, 1, 'edit'), (10, 1, 'delete');
















