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
(20, N'Dan Brown'),
(21, 'Sương Nguyệt Minh'),
(22, 'Stanislas-André Steeman'),
(23, 'Dale Carnegie'),
(24, 'Gwen Adshead'),
(25, 'Eileen Horne');


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
(1, 'Nhà Sách Fahasa', '21 Lý Chính Thắng, Quận 3, TP.HCM', '0312345601'),
(2, 'Nhà Sách Phương Nam', '940 Đường 3/2, Quận 11, TP.HCM', '0312345602'),
(3, 'Công Ty Sách Nhã Nam', '59 Đặng Thai Mai, Tây Hồ, Hà Nội', '0312345603'),
(4, 'Tiki Trading', '52 Út Tịch, Quận Tân Bình, TP.HCM', '0312345604'),
(5, 'Alpha Books', '20/11 Nguyễn Hữu Cảnh, Phường 19, Quận Bình Thạnh, TP.HCM', '0312345605');


CREATE TABLE VAITRO (
	maVT INT PRIMARY KEY,
	tenVT NVARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO VAITRO (maVT, tenVT) VALUES
(1, N'Quản trị viên'),
(2, N'Quản lí'),
(3, N'Kế toán'),
(4, N'Nhân viên bán hàng'),
(5, N'Bảo vệ');


CREATE TABLE NHANVIEN (
    maNV INT PRIMARY KEY,
    tenNV NVARCHAR(50) NOT NULL,
    email VARCHAR(50),
    sdt VARCHAR(15),
	maVT INT NOT NULL,
    trangThaiXoa INT NOT NULL DEFAULT 0,
	FOREIGN KEY (maVT) REFERENCES VAITRO(maVT)
);
INSERT INTO NHANVIEN (maNV, tenNV, email, sdt, maVT) VALUES
(1, N'Trần Thị Huỳnh Như', 'nhu@gmail.com', '0123456789', 1),
(2, N'Bào Thanh Tâm', 'tam@gmail.com', '0987654321', 1),
(3, N'Phạm Đình Duy Thái', 'thai@gmail.com', '0909123456', 1),
(4, N'Nguyễn Thị Thùy Trâm', 'tram@gmail.com', '0912345678', 1),
(5, N'Nguyễn Ngọc Thúy Vy', 'vy@gmail.com', '0922334455', 1),
(6, N'Nguyễn Văn A', 'a@gmail.com', '0934567890', 4),
(7, N'Trần Văn B', 'b@gmail.com', '0945678901', 4),
(8, N'Lê Thị C', 'c@gmail.com', '0956789012', 4),
(9, N'Hoàng Minh D', 'd@gmail.com', '0967890123', 3),
(10, N'Đỗ Thanh E', 'e@gmail.com', '0978901234', 3);


CREATE TABLE KHACHHANG (
    maKH INT PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    sdt VARCHAR(15),
    email VARCHAR(50),
    trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO KHACHHANG (maKH, tenKH, sdt, email) VALUES
(1, 'Nguyễn Văn An', '0912345601', 'annguyen@gmail.com'),
(2, 'Trần Thị Bích Ngọc', '0912345602', 'ngoctran@gmail.com'),
(3, 'Lê Văn Cường', '0912345603', 'cuongle@gmail.com'),
(4, 'Phạm Minh Tâm', '0912345604', 'tampham@gmail.com'),
(5, 'Đỗ Hoàng Nam', '0912345605', 'namdo@gmail.com'),
(6, 'Võ Thanh Thảo', '0912345606', 'thaovothanh@gmail.com'),
(7, 'Huỳnh Nhật Hào', '0912345607', 'haohuynh@gmail.com'),
(8, 'Bùi Quỳnh Như', '0912345608', 'nhubui@gmail.com'),
(9, 'Ngô Thị Lan', '0912345609', 'lan001ngo@gmail.com'),
(10, 'Hoàng Tuấn Kiệt', '0912345610', 'kiethoang@gmail.com'),
(11, 'Phan Thị Hồng', '0912345611', 'hongphan@gmail.com'),
(12, 'Đặng Văn Long', '0912345612', 'longdang@gmail.com'),
(13, 'Lý Minh Khoa', '0912345613', 'khoaly010101@gmail.com'),
(14, 'Tống Thị Mai', '0912345614', 'maitong@gmail.com'),
(15, 'Tạ Hoàng Quốc Bảo', '0912345615', 'baota@gmail.com'),
(16, 'Kiều Ngọc Trinh', '0912345616', 'trinhkieu@gmail.com'),
(17, 'Mai Thanh Hùng', '0912345617', 'hungmai@gmail.com'),
(18, 'Trịnh Thị Yến', '0912345618', 'yentrinh@gmail.com'),
(19, 'Cao Hoàng Phúc', '0912345619', 'phuccao@gmail.com'),
(20, 'Thái Thị Hương', '0912345620', 'huongthai@gmail.com'),
(21, 'Lâm Nhật Minh', '0912345621', 'minhlam@gmail.com'),
(22, 'Châu Mỹ Linh', '0912345622', 'linhchau@gmail.com'),
(23, 'Trương Văn Hào', '0912345623', 'haotruong@gmail.com'),
(24, 'Hồ Thị Hồng Hạnh', '0912345624', 'hanhho@gmail.com'),
(25, 'Vương Quốc Tuấn', '0912345625', 'tuanvuong@gmail.com'),
(26, 'Phạm Văn Thuận', '0912345626', 'thuanVan@gmail.com'),
(27, 'Ngô Lê Thanh Quyền', '0912345627', 'ngoquyen@gmail.com'),
(28, 'Gia Cát Lượng', '0912345628', 'luongGia@gmail.com'),
(29, 'Hoàng Anh Huy', '0912345629', 'huyhoang@gmail.com'),
(30, 'Nguyễn Thiên Phú', '0912345630', 'phunguyen2222@gmail.com'),
(31, 'Lê Văn Nhất', '0912345631', 'toilanhat@gmail.com'),
(32, 'Nguyễn Hữu Minh Quân', '0912345632', 'ikkkkku666@gmail.com'),
(33, 'Trần Anh Khoa', '0912345633', 'khoaanhtran@gmail.com'),
(34, 'Nguyễn Thị Ngọc Ngà', '0912345634', 'ngocNGA@gmail.com'),
(35, 'Bùi Ngọc Thanh', '0912345635', 'Ngocthanh2222@gmail.com');


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
(9, N'Quản Lí Vai Trò'),
(10, N'Quản Lí Phân Quyền'),
(11, N'Quản Lí Thống Kê');


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
(1, 'Tôi thấy hoa vàng trên cỏ xanh', 120000, 73, 1),
(2, 'Harry Potter và Hòn đá phù thủy', 150000, 64, 2),
(3, 'Bí mật tư duy triệu phú', 180000, 77, 3),
(4, '21 Bài học cho thế kỷ 21', 200000, 66, 4),
(5, 'Nhà giả kim', 130000, 65, 1),
(6, 'Nghệ thuật đàm phán', 170000, 48, 1),
(7, 'Lược sử thời gian', 220000, 58, 1),
(8, 'Một thoáng ta rực rỡ ở nhân gian', 160000, 48, 1),
(9, 'Chuyện con mèo dạy hải âu bay', 90000, 48, 2),
(10, 'Không gia đình', 140000, 49, 3),
(11, 'Bách khoa thư vũ trụ', 250000, 48, 1),
(12, 'Khoa học kỳ thú', 180000, 48, 1),
(13, 'Chinh phục toán học', 190000, 67, 3),
(14, 'Vũ trụ trong vỏ hạt dẻ', 230000, 49, 1),
(15, 'Đi tìm lẽ sống', 135000, 74, 4),
(16, 'Tư duy nhanh và chậm', 210000, 48, 2),
(17, 'Chiến tranh tiền tệ', 195000, 49, 2),
(18, 'Văn hóa & Con người', 170000, 67, 2),
(19, 'Trí tuệ xúc cảm', 165000, 48, 1),
(20, 'Sống tối giản', 145000, 64, 1),
(21, 'Doraemon - Tập 1', 21000, 75, 2),
(22, 'Doraemon - Tập 2', 21000, 75, 2),
(23, 'Doraemon - Tập 3', 21000, 75, 2),
(24, 'Doraemon - Tập 4', 21000, 75, 2),
(25, 'Doraemon - Tập 5', 21000, 75, 2),
(26, 'Miền Hoang', 180000, 59, 5),
(27, 'Kẻ Sát Nhân Ở Số Nhà 21', 98000, 49, 3),
(28, 'Đắc Nhân Tâm  - Phiên Bản Tinh Gọn', 116000, 96, 1),
(29, 'Trò Chuyện Với Kẻ Sát Nhân - Trước Khi Trở Thành Ác Quỷ', 179000, 59, 1),
(30, 'Doraemon Đố Vui - Doraemon Những Cuộc Phiêu Lưu', 29000, 68, 2);


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
(1, 3, 3, 171500000, '2024-11-01'),
(2, 2, 1, 9300000, '2024-11-01'),
(3, 3, 1, 13475000, '2024-12-01'),
(4, 3, 3, 5250000, '2025-01-01'),
(5, 3, 1, 25933500, '2025-01-01'),
(6, 3, 5, 14917500, '2025-02-01'),
(7, 3, 5, 6936000, '2025-02-01');

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
INSERT INTO PHIEUXUAT (maPX, maNV, maKH, tongTien, ngayXuat) VALUES
(1, 3, 1, 465000, '2025-03-15'),
(2, 3, 11, 302000, '2025-03-15'),
(3, 3, 22, 120000, '2025-03-15'),
(4, 3, 4, 560000, '2025-03-16'),
(5, 3, 2, 730000, '2025-03-16'),
(6, 3, 3, 105000, '2025-03-17'),
(7, 3, 4, 150000, '2025-03-18'),
(8, 3, 5, 595000, '2025-03-19'),
(9, 3, 6, 345000, '2025-03-19'),
(10, 3, 7, 390000, '2025-03-20'),
(11, 3, 8, 605000, '2025-03-20'),
(12, 3, 9, 463000, '2025-03-20'),
(13, 3, 10, 280000, '2025-03-22'),
(14, 3, 12, 430000, '2025-03-22'),
(15, 3, 13, 380000, '2025-03-23'),
(16, 3, 14, 105000, '2025-03-24'),
(17, 3, 15, 180000, '2025-03-24'),
(18, 3, 16, 530000, '2025-03-24'),
(19, 3, 17, 270000, '2025-03-24'),
(20, 3, 18, 150000, '2025-03-25'),
(21, 3, 19, 300000, '2025-03-26'),
(22, 3, 20, 150000, '2025-03-27'),
(23, 3, 21, 955000, '2025-03-28'),
(24, 3, 22, 280000, '2025-03-29'),
(25, 3, 23, 300000, '2025-04-01'),
(26, 3, 24, 105000, '2025-04-01'),
(27, 3, 25, 390000, '2025-04-02'),
(28, 3, 1, 420000, '2025-04-03'),
(29, 3, 14, 310000, '2025-04-04'),
(30, 3, 17, 330000, '2025-04-04'),
(31, 3, 12, 156000, '2025-04-05'),
(32, 3, 23, 412000, '2025-04-07'),
(33, 3, 2, 266000, '2025-04-08'),
(34, 3, 15, 179000, '2025-04-11'),
(35, 3, 22, 150000, '2025-05-01'),
(36, 3, 7, 266000, '2025-05-01'),
(37, 3, 1, 150000, '2025-05-01');

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
INSERT INTO NHOMTHELOAI (maTL, maSach) VALUES
(1, 1),
(1, 5),
(1, 8),
(1, 10),
(1, 26),
(2, 4),
(2, 7),
(2, 11),
(2, 12),
(2, 14),
(3, 16),
(3, 17),
(3, 19),
(4, 9),
(5, 7),
(5, 11),
(5, 18),
(6, 3),
(6, 6),
(6, 15),
(6, 16),
(6, 17),
(6, 19),
(6, 20),
(6, 27),
(6, 28),
(6, 29),
(7, 13),
(8, 2),
(8, 27),
(9, 2),
(9, 14),
(10, 30);


CREATE TABLE NHOMTACGIA (
    maTG INT NOT NULL,
    maSach INT NOT NULL,
    PRIMARY KEY (maTG, maSach),
    FOREIGN KEY (maTG) REFERENCES TACGIA(maTG),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);
INSERT INTO NHOMTACGIA (maTG, maSach) VALUES
(1, 1),
(1, 20),
(2, 2),
(2, 20),
(3, 12),
(3, 19),
(3, 30),
(4, 16),
(5, 5),
(5, 15),
(6, 3),
(6, 13),
(6, 28),
(7, 7),
(7, 11),
(7, 14),
(8, 4),
(9, 9),
(9, 18),
(10, 10),
(11, 17),
(12, 15),
(13, 18),
(14, 11),
(15, 19),
(16, 6),
(16, 14),
(17, 13),
(18, 8),
(18, 17),
(19, 12),
(20, 16),
(21, 26),
(22, 27),
(24, 29),
(25, 29);

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
(1, 6, 3060000, 30),
(2, 1, 7500000, 50),
(2, 2, 4500000, 30),
(3, 1, 9000000, 50),
(3, 6, 4590000, 30),
(4, 1, 10000000, 50),
(4, 7, 3400000, 20),
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
(20, 3, 2900000, 20),
(21, 4, 1050000, 50),
(21, 6, 535500, 30),
(22, 4, 1050000, 50),
(22, 6, 535500, 30),
(23, 4, 1050000, 50),
(23, 6, 535500, 30),
(24, 4, 1050000, 50),
(24, 6, 535500, 30),
(25, 4, 1050000, 50),
(25, 6, 535500, 30),
(26, 5, 4590000, 30),
(26, 6, 4590000, 30),
(27, 5, 4165000, 50),
(28, 5, 9860000, 100),
(29, 5, 6086000, 40),
(29, 7, 3043000, 20),
(30, 5, 1232500, 50),
(30, 7, 493000, 20);

CREATE TABLE CHITIETPHIEUXUAT (
    maSach INT NOT NULL,
    maPX INT NOT NULL,
    soLuong INT NOT NULL,
    giaBan INT NOT NULL,
    PRIMARY KEY (maSach, maPX),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach),
    FOREIGN KEY (maPX) REFERENCES PHIEUXUAT(maPX)
);
INSERT INTO CHITIETPHIEUXUAT (maSach, maPX, soLuong, giaBan) VALUES
(1, 3, 1, 120000),
(1, 5, 2, 240000),
(1, 10, 2, 240000),
(1, 19, 1, 120000),
(1, 28, 1, 120000),
(2, 7, 1, 150000),
(2, 10, 1, 150000),
(2, 13, 1, 150000),
(2, 15, 1, 150000),
(2, 19, 1, 150000),
(2, 20, 1, 150000),
(2, 22, 1, 150000),
(2, 25, 2, 300000),
(2, 28, 2, 300000),
(2, 29, 1, 150000),
(2, 33, 1, 150000),
(2, 35, 1, 150000),
(2, 36, 1, 150000),
(2, 37, 1, 150000),
(3, 17, 1, 180000),
(3, 18, 2, 360000),
(4, 9, 1, 200000),
(4, 12, 2, 400000),
(4, 30, 1, 200000),
(5, 2, 2, 260000),
(5, 13, 1, 130000),
(5, 21, 1, 130000),
(5, 30, 1, 130000),
(6, 21, 1, 170000),
(6, 27, 1, 170000),
(7, 1, 1, 220000),
(7, 27, 1, 220000),
(8, 5, 1, 160000),
(8, 29, 1, 160000),
(9, 4, 2, 180000),
(10, 1, 1, 140000),
(11, 14, 1, 250000),
(11, 23, 1, 250000),
(12, 14, 1, 180000),
(12, 23, 1, 180000),
(13, 4, 2, 380000),
(13, 8, 1, 190000),
(14, 15, 1, 230000),
(15, 24, 1, 135000),
(16, 8, 1, 210000),
(16, 23, 1, 210000),
(17, 8, 1, 195000),
(18, 11, 1, 170000),
(18, 18, 1, 170000),
(18, 23, 1, 170000),
(19, 5, 2, 330000),
(20, 9, 1, 145000),
(20, 11, 3, 435000),
(20, 23, 1, 145000),
(20, 24, 1, 145000),
(21, 1, 1, 21000),
(21, 6, 1, 21000),
(21, 12, 1, 21000),
(21, 16, 1, 21000),
(21, 26, 1, 21000),
(22, 1, 1, 21000),
(22, 6, 1, 21000),
(22, 12, 1, 21000),
(22, 16, 1, 21000),
(22, 26, 1, 21000),
(23, 1, 1, 21000),
(23, 6, 1, 21000),
(23, 12, 1, 21000),
(23, 16, 1, 21000),
(23, 26, 1, 21000),
(24, 1, 1, 21000),
(24, 2, 1, 21000),
(24, 6, 1, 21000),
(24, 16, 1, 21000),
(24, 26, 1, 21000),
(25, 1, 1, 21000),
(25, 2, 1, 21000),
(25, 6, 1, 21000),
(25, 16, 1, 21000),
(25, 26, 1, 21000),
(26, 32, 1, 180000),
(27, 31, 1, 98000),
(28, 32, 2, 232000),
(28, 33, 1, 116000),
(28, 36, 1, 116000),
(29, 34, 1, 179000),
(30, 31, 2, 58000);

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
(1, 1, 'add'),
(1, 1, 'delete'),
(1, 1, 'edit'),
(1, 1, 'view'),
(1, 2, 'add'),
(1, 2, 'delete'),
(1, 2, 'edit'),
(1, 2, 'view'),
(1, 3, 'add'),
(1, 3, 'delete'),
(1, 3, 'edit'),
(1, 3, 'view'),
(2, 1, 'add'),
(2, 1, 'delete'),
(2, 1, 'edit'),
(2, 1, 'view'),
(2, 2, 'add'),
(2, 2, 'delete'),
(2, 2, 'edit'),
(2, 2, 'view'),
(2, 3, 'add'),
(2, 3, 'delete'),
(2, 3, 'edit'),
(2, 3, 'view'),
(3, 1, 'add'),
(3, 1, 'delete'),
(3, 1, 'edit'),
(3, 1, 'view'),
(3, 2, 'add'),
(3, 2, 'delete'),
(3, 2, 'edit'),
(3, 2, 'view'),
(3, 3, 'add'),
(3, 3, 'delete'),
(3, 3, 'edit'),
(3, 3, 'view'),
(4, 1, 'add'),
(4, 1, 'delete'),
(4, 1, 'edit'),
(4, 1, 'view'),
(4, 2, 'add'),
(4, 2, 'delete'),
(4, 2, 'edit'),
(4, 2, 'view'),
(5, 1, 'add'),
(5, 1, 'delete'),
(5, 1, 'edit'),
(5, 1, 'view'),
(5, 2, 'add'),
(5, 2, 'delete'),
(5, 2, 'edit'),
(5, 2, 'view'),
(6, 1, 'add'),
(6, 1, 'delete'),
(6, 1, 'edit'),
(6, 1, 'view'),
(7, 1, 'add'),
(7, 1, 'view'),
(7, 2, 'add'),
(7, 2, 'view'),
(8, 1, 'add'),
(8, 1, 'view'),
(8, 2, 'add'),
(8, 2, 'view'),
(8, 3, 'add'),
(8, 3, 'view'),
(9, 1, 'add'),
(9, 1, 'delete'),
(9, 1, 'edit'),
(9, 1, 'view'),
(10, 1, 'add'),
(10, 1, 'delete'),
(10, 1, 'edit'),
(10, 1, 'view'),
(11, 1, 'view');
















