CREATE TABLE NHAXUATBAN (
    maNXB INT NOT NULL PRIMARY KEY,
    tenNXB NVARCHAR(50) NOT NULL
);

CREATE TABLE TACGIA (
    maTG INT NOT NULL PRIMARY KEY,
    tenTG NVARCHAR(50) NOT NULL
);

CREATE TABLE THELOAI (
    maTL INT NOT NULL PRIMARY KEY,
    tenTL NVARCHAR(30) NOT NULL
);

CREATE TABLE NHACUNGCAP (
    maNCC INT NOT NULL PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(100),
    sdt VARCHAR(20)
);

CREATE TABLE KHACHHANG (
    maKH INT NOT NULL PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    sdt VARCHAR(20),
    email VARCHAR(50)
);

CREATE TABLE QUYEN (
    maQuyen INT NOT NULL PRIMARY KEY,
    tenQuyen NVARCHAR(30) NOT NULL
);

CREATE TABLE NHANVIEN (
    maNV INT NOT NULL PRIMARY KEY,
    tenNV NVARCHAR(50) NOT NULL,
    taiKhoan VARCHAR(30),
    matKhau VARCHAR(30),
    email VARCHAR(50),
    sdt VARCHAR(20),
    maQuyen INT NOT NULL,
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen)
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

CREATE TABLE CHITIETPHIEUNHAP (
    maCTPN INT NOT NULL PRIMARY KEY,
    maPN INT NOT NULL,
    maSach INT NOT NULL,
    soLuong INT NOT NULL,
    giaNhap INT NOT NULL,
    FOREIGN KEY (maPN) REFERENCES PHIEUNHAP(maPN),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
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

CREATE TABLE CHITIETPHIEUXUAT (
    maCTPX INT NOT NULL PRIMARY KEY,
    maSach INT NOT NULL,
    soLuong INT,
    giaBan INT,
    maPX INT NOT NULL,
    FOREIGN KEY (maPX) REFERENCES PHIEUXUAT(maPX),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE CHUCNANG (
    maCN INT NOT NULL PRIMARY KEY,
    tenCN NVARCHAR(30) NOT NULL
);

CREATE TABLE CHITIETCHUCNANG (
    maCTCN INT NOT NULL PRIMARY KEY,
    maCN INT NOT NULL,
    maQuyen INT NOT NULL,
    FOREIGN KEY (maCN) REFERENCES CHUCNANG(maCN),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen)
);

CREATE TABLE NHOMTACGIA (
    maNhomTG INT NOT NULL PRIMARY KEY,
    maTG INT NOT NULL,
    maSach INT NOT NULL,
    FOREIGN KEY (maTG) REFERENCES TACGIA(maTG),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE NHOMTHELOAI (
    maNhomTL INT NOT NULL PRIMARY KEY,
    maTL INT NOT NULL,
    maSach INT NOT NULL,
    FOREIGN KEY (maTL) REFERENCES THELOAI(maTL),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

INSERT INTO NHAXUATBAN (maNXB, tenNXB) VALUES
(1, N'NXB Kim Đồng'),
(2, N'NXB Trẻ'),
(3, N'NXB Giáo Dục'),
(4, N'NXB Văn Học'),
(5, N'NXB Lao Động');

INSERT INTO TACGIA (maTG, tenTG) VALUES
(1, N'Nguyễn Nhật Ánh'),
(2, N'Tô Hoài'),
(3, N'Mai Lan Hương'),
(4, N'Nam Cao'),
(5, N'Ngô Tất Tố');

INSERT INTO THELOAI (maTL, tenTL) VALUES
(1, N'Tiểu thuyết'),
(2, N'Khoa học viễn tưởng'),
(3, N'Văn học thiếu nhi'),
(4, N'Kinh tế'),
(5, N'Tâm lý học');

INSERT INTO NHACUNGCAP (maNCC, tenNCC, diaChi, sdt) VALUES
(1, N'Công ty sách ABC', N'123 Nguyễn Trãi, Hà Nội', '0123456789'),
(2, N'Công ty văn hóa X', N'456 Lê Lợi, TP HCM', '0987654321'),
(3, N'Công ty phát hành Y', N'789 Trần Hưng Đạo, Đà Nẵng', '0356791248'),
(4, N'Công ty in sách Z', N'321 Bạch Mai, Hà Nội', '0909090909'),
(5, N'NXB Đại học Quốc gia', N'555 Đống Đa, Hà Nội', '0876543210');

INSERT INTO KHACHHANG (maKH, tenKH, sdt, email) VALUES
(1, N'Trần Văn A', '0912345678', 'a@gmail.com'),
(2, N'Nguyễn Thị B', '0987654321', 'b@yahoo.com'),
(3, N'Hoàng Minh C', '0333444555', 'c@hotmail.com'),
(4, N'Phạm Thảo D', '0777888999', 'd@gmail.com'),
(5, N'Lê Quốc E', '0666777888', 'e@gmail.com');

INSERT INTO QUYEN (maQuyen, tenQuyen) VALUES
(1, N'Quản trị viên'),
(2, N'Nhân viên bán hàng'),
(3, N'Nhân viên nhập hàng'),
(4, N'Khách hàng'),
(5, N'Kế toán');

INSERT INTO NHANVIEN (maNV, tenNV, taiKhoan, matKhau, email, sdt, maQuyen) VALUES
(1, N'Nguyễn Văn An', 'an123', 'pass123', 'an@gmail.com', '0911111111', 1),
(2, N'Trần Thị Bình', 'binh456', 'pass456', 'binh@yahoo.com', '0922222222', 2),
(3, N'Hoàng Minh Cường', 'cuong789', 'pass789', 'cuong@hotmail.com', '0933333333', 3),
(4, N'Phạm Thảo Dương', 'duong123', 'passabc', 'duong@gmail.com', '0944444444', 4),
(5, N'Lê Quốc Đông', 'dong456', 'passdef', 'dong@gmail.com', '0955555555', 5);

INSERT INTO SACH (maSach, tenSach, giaSach, soLuongTon, maNXB) VALUES
(1, N'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 50000, 100, 1),
(2, N'Dế Mèn Phiêu Lưu Ký', 60000, 120, 2),
(3, N'Bí Mật Của Nước', 70000, 80, 3),
(4, N'Nhà Giả Kim', 90000, 50, 4),
(5, N'Tư Duy Nhanh Và Chậm', 110000, 60, 5);

INSERT INTO CHUCNANG (maCN, tenCN) VALUES
(1, N'Quản lý sách'),
(2, N'Quản lý khách hàng'),
(3, N'Quản lý nhân viên'),
(4, N'Quản lý nhà cung cấp'),
(5, N'Báo cáo doanh thu');

INSERT INTO CHITIETCHUCNANG (maCTCN, maCN, maQuyen) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5);
