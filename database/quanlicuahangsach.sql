CREATE TABLE THELOAI (
    maTL INT NOT NULL PRIMARY KEY,
    tenTL NVARCHAR(30) NOT NULL
);

CREATE TABLE TACGIA (
    maTG INT NOT NULL PRIMARY KEY,
    tenTG NVARCHAR(50) NOT NULL
);

CREATE TABLE NHAXUATBAN (
    maNXB INT NOT NULL PRIMARY KEY,
    tenNXB NVARCHAR(50) NOT NULL
);

CREATE TABLE NHACUNGCAP (
    maNCC INT NOT NULL PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(100),
    sdt VARCHAR(15)
);

CREATE TABLE NHANVIEN (
    maNV INT NOT NULL PRIMARY KEY,
    tenNV NVARCHAR(50) NOT NULL,
    email VARCHAR(50),
    sdt VARCHAR(15)
);

CREATE TABLE KHACHHANG (
    maKH INT NOT NULL PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    sdt VARCHAR(15),
    email VARCHAR(50)
);

CREATE TABLE QUYEN (
    maQuyen INT NOT NULL PRIMARY KEY,
    tenQuyen NVARCHAR(30) NOT NULL
);

CREATE TABLE CHUCNANG (
    maCN INT NOT NULL PRIMARY KEY,
    tenCN NVARCHAR(30) NOT NULL
);

CREATE TABLE SACH (
    maSach INT NOT NULL PRIMARY KEY,
    tenSach NVARCHAR(100) NOT NULL,
    giaSach INT NOT NULL,
    soLuongTon INT NOT NULL,
    maNXB INT NOT NULL,
    FOREIGN KEY (maNXB) REFERENCES NHAXUATBAN(maNXB)
);

CREATE TABLE PHIEUNHAP (
    maPN INT NOT NULL PRIMARY KEY,
    maNV INT NOT NULL,
    maNCC INT NOT NULL,
    tongTien INT,
    ngayNhap DATE,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maNCC) REFERENCES NHACUNGCAP(maNCC)
);

CREATE TABLE PHIEUXUAT (
    maPX INT NOT NULL PRIMARY KEY,
    maNV INT NOT NULL,
    maKH INT NOT NULL,
    tongTien INT,
    ngayXuat DATE,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maKH) REFERENCES KHACHHANG(maKH)
);

CREATE TABLE TAIKHOAN (
    tenDangNhap VARCHAR(50) NOT NULL PRIMARY KEY,
    matKhau VARCHAR(50) NOT NULL,
    maNV INT NOT NULL,
    maQuyen INT NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen)
);

CREATE TABLE NHOMTHELOAI (
    maTL INT NOT NULL,
    maSach INT NOT NULL,
    PRIMARY KEY (maTL, maSach),
    FOREIGN KEY (maTL) REFERENCES THELOAI(maTL),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE NHOMTACGIA (
    maTG INT NOT NULL,
    maSach INT NOT NULL,
    PRIMARY KEY (maTG, maSach),
    FOREIGN KEY (maTG) REFERENCES TACGIA(maTG),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE CHITIETPHIEUNHAP (
    maSach INT NOT NULL,
    maPN INT NOT NULL,
    giaNhap INT NOT NULL,
    soLuong INT NOT NULL,
    PRIMARY KEY (maSach, maPN),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach),
    FOREIGN KEY (maPN) REFERENCES PHIEUNHAP(maPN)
);

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
    PRIMARY KEY (maCN, maQuyen),
    FOREIGN KEY (maCN) REFERENCES CHUCNANG(maCN),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen)
);

INSERT INTO QUYEN (maQuyen, tenQuyen) VALUES
(1, N'Quản trị viên (admin)'),
(2, N'Nhân viên bán hàng'),
(3, N'Kế toán');

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

INSERT INTO NHAXUATBAN (maNXB, tenNXB) VALUES
(1, N'Nhà xuất bản Trẻ'),
(2, N'Nhà xuất bản Kim Đồng'),
(3, N'Nhà xuất bản Giáo Dục Việt Nam'),
(4, N'Nhà xuất bản Tổng Hợp TP.HCM'),
(5, N'Nhà xuất bản Văn Học');

INSERT INTO NHACUNGCAP (maNCC, tenNCC, diaChi, sdt) VALUES
(1, N'Nhà Sách Fahasa', N'21 Lý Chính Thắng, Quận 3, TP.HCM', '02838208384'),
(2, N'Nhà Sách Phương Nam', N'940 Đường 3/2, Quận 11, TP.HCM', '02838622222'),
(3, N'Công Ty Sách Nhã Nam', N'59 Đặng Thai Mai, Tây Hồ, Hà Nội', '02437151101'),
(4, N'Tiki Trading', N'52 Út Tịch, Quận Tân Bình, TP.HCM', '19006035');

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

INSERT INTO TAIKHOAN (tenDangNhap, matKhau, maNV, maQuyen) VALUES
('admin1', '123456', 1, 1),
('admin2', '123456', 2, 1),
('admin3', '123456', 3, 1),
('admin4', '123456', 4, 1),
('admin5', '123456', 5, 1),
('nhanvien6', '123456', 6, 2), 
('nhanvien7', '123456', 7, 2), 
('nhanvien8', '123456', 8, 2), 
('ketoan9', '123456', 9, 3),    
('ketoan10', '123456', 10, 3);

INSERT INTO SACH (maSach, tenSach, giaSach, soLuongTon, maNXB) VALUES
(1, N'Tôi thấy hoa vàng trên cỏ xanh', 120000, 50, 1),
(2, N'Harry Potter và Hòn đá phù thủy', 150000, 30, 2),
(3, N'Bí mật tư duy triệu phú', 180000, 30, 3),
(4, N'21 Bài học cho thế kỷ 21', 200000, 30, 4),
(5, N'Nhà giả kim', 130000, 25, 1),
(6, N'Nghệ thuật đàm phán', 170000, 47, 1),
(7, N'Lược sử thời gian', 220000, 53, 1),
(8, N'Một thoáng ta rực rỡ ở nhân gian', 160000, 56, 1),
(9, N'Chuyện con mèo dạy hải âu bay', 90000, 27, 2),
(10, N'Không gia đình', 140000, 40, 3),
(11, N'Bách khoa thư vũ trụ', 250000, 32, 1),
(12, N'Khoa học kỳ thú', 180000, 55, 1),
(13, N'Chinh phục toán học', 190000, 65, 3),
(14, N'Vũ trụ trong vỏ hạt dẻ', 230000, 30, 1),
(15, N'Đi tìm lẽ sống', 135000, 40, 4),
(16, N'Tư duy nhanh và chậm', 210000, 63, 2),
(17, N'Chiến tranh tiền tệ', 195000, 48, 2),
(18, N'Văn hóa & Con người', 170000, 59, 2),
(19, N'Trí tuệ xúc cảm', 165000, 40, 1),
(20, N'Sống tối giản', 145000, 60, 1);

INSERT INTO NHOMTACGIA (maTG, maSach) VALUES
(1, 1), (2, 2), (6, 3), (8, 4), (5, 5), (16, 6), (7, 7), (18, 8), (9, 9), (10, 10),
(7, 11), (14, 11), (3, 12), (19, 12), (17, 13), (6, 13), (7, 14), (16, 14),
(12, 15), (5, 15), (4, 16), (20, 16), (11, 17), (18, 17), (13, 18), (9, 18),
(15, 19), (3, 19), (1, 20), (2, 20);

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