drop database if exists db_step_style;
create database db_step_style;
use  db_step_style;


-- -------------Tu-------------------------
create table mau_sac(
	id_mau_sac bigint auto_increment primary key,
    ma_mau_sac nvarchar(10) unique,
    ten_mau nvarchar(255),
    gia_tri nvarchar(255),
    mo_ta nvarchar(500),
    trang_thai varchar(20)
);
INSERT INTO mau_sac (ma_mau_sac, ten_mau, gia_tri, mo_ta, trang_thai)
VALUES
('MS001', 'Đỏ', '#FF0000', 'Màu đỏ tươi', 'ACTIVE'),
('MS002', 'Đen', '#0000FF', 'Màu đen', 'ACTIVE'),
('MS003', 'Xanh lá', '#00FF00', 'Màu xanh lá cây tươi', 'ACTIVE'),
('MS004', 'Vàng', '#FFFF00', 'Màu vàng sáng', 'ACTIVE'),
('MS005', 'Trắng', '#FFFFFF', 'Màu trắng tinh khôi', 'ACTIVE'),
('MS006', 'Hồng', '#FFC0CB', 'Màu hồng nhạt', 'ACTIVE'),
('MS007', 'Xanh dương', '#0000FF', 'Màu xanh dương', 'ACTIVE'),
('MS008', 'Tím', '#800080', 'Màu tím đậm', 'ACTIVE'),
('MS009', 'Cam', '#FFA500', 'Màu cam sáng', 'ACTIVE'),
('MS010', 'Nâu', '#A52A2A', 'Màu nâu đất', 'ACTIVE'),
('MS011', 'Xám', '#808080', 'Màu xám trung tính', 'ACTIVE'),
('MS012', 'Bạc', '#C0C0C0', 'Màu bạc sáng', 'ACTIVE');

create table kich_co(
	id_kich_co bigint auto_increment primary key,
    ma_kich_co nvarchar(10) unique,
    gia_tri double,
    mo_ta nvarchar(500),
    trang_thai varchar(20)
    );
INSERT INTO kich_co (ma_kich_co, gia_tri, mo_ta, trang_thai)
VALUES
('KC01', 35, 'Size 35: Thích hợp cho bàn chân nhỏ, chiều dài khoảng 22.5 cm', 'ACTIVE'),
('KC02', 36, 'Size 36: Phù hợp với bàn chân có chiều dài khoảng 23 cm', 'ACTIVE'),
('KC03', 37, 'Size 37: Dành cho bàn chân có chiều dài khoảng 23.5 cm', 'ACTIVE'),
('KC04', 38, 'Size 38: Tốt cho chiều dài bàn chân khoảng 24 cm', 'ACTIVE'),
('KC05', 39, 'Size 39: Phù hợp với bàn chân có chiều dài khoảng 24.5 cm', 'ACTIVE'),
('KC06', 40, 'Size 40: Dành cho bàn chân có chiều dài khoảng 25 cm', 'ACTIVE'),
('KC07', 41, 'Size 41: Tốt cho bàn chân có chiều dài khoảng 25.5 cm', 'ACTIVE'),
('KC08', 42, 'Size 42: Phù hợp với chiều dài bàn chân khoảng 26 cm', 'ACTIVE'),
('KC09', 43, 'Size 43: Dành cho bàn chân có chiều dài khoảng 26.5 cm', 'ACTIVE'),
('KC10', 44, 'Size 44: Tốt cho bàn chân có chiều dài khoảng 27 cm', 'ACTIVE');


create table chat_lieu(
	id_chat_lieu bigint auto_increment primary key,
    ma_chat_lieu nvarchar(10) unique,
    ten_chat_lieu nvarchar(255),
    do_ben nvarchar(255),
    mo_ta nvarchar(500),
    trang_thai varchar(20)
    );
INSERT INTO chat_lieu (ma_chat_lieu, ten_chat_lieu, do_ben, mo_ta, trang_thai)
VALUES
('CL001', 'Da thật', 'Rất bền', 'Chất liệu da thật cao cấp', 'ACTIVE'),
('CL002', 'Vải', 'Trung bình', 'Chất liệu vải mềm mại', 'ACTIVE'),
('CL003', 'Da tổng hợp', 'Khá bền', 'Chất liệu da tổng hợp chất lượng cao', 'ACTIVE'),
('CL004', 'Lưới', 'Trung bình', 'Chất liệu lưới thoáng khí', 'ACTIVE'),
('CL005', 'Nhựa', 'Bền', 'Chất liệu nhựa nhẹ, chống nước', 'ACTIVE'),
('CL006', 'Cao su', 'Rất bền', 'Chất liệu cao su chống trượt', 'ACTIVE'),
('CL007', 'Vải dệt kim', 'Trung bình', 'Chất liệu vải dệt kim co giãn', 'ACTIVE'),
('CL008', 'Bông', 'Khá bền', 'Chất liệu bông mềm mại, thoáng mát', 'ACTIVE'),
('CL009', 'Nylon', 'Bền', 'Chất liệu nylon chắc chắn và chống thấm', 'ACTIVE'),
('CL010', 'Len', 'Trung bình', 'Chất liệu len giữ ấm tốt', 'ACTIVE'),
('CL011', 'Polyester', 'Khá bền', 'Chất liệu polyester chống nhăn', 'ACTIVE');


create table trong_luong(
	id_trong_luong bigint auto_increment primary key,
    ma_trong_luong nvarchar(10) unique,
    gia_tri nvarchar(255),
    mo_ta nvarchar(500),
    trang_thai varchar(20)
    );
INSERT INTO trong_luong (ma_trong_luong, gia_tri, mo_ta, trang_thai)
VALUES
('TL001', '500', 'Khối lượng trung bình', 'ACTIVE'),
('TL002', '300', 'Nhẹ', 'ACTIVE'),
('TL003', '700', 'Khối lượng nặng', 'ACTIVE'),
('TL004', '400', 'Khối lượng trung bình nhẹ', 'ACTIVE'),
('TL005', '600', 'Khối lượng khá nặng', 'ACTIVE'),
('TL006', '250', 'Khối lượng rất nhẹ', 'ACTIVE'),
('TL007', '800', 'Khối lượng rất nặng', 'ACTIVE'),
('TL008', '350', 'Khối lượng nhẹ trung bình', 'ACTIVE'),
('TL009', '450', 'Khối lượng cân đối', 'ACTIVE'),
('TL010', '550', 'Khối lượng hơi nặng', 'ACTIVE');
-- -------------//Tu-------------------------

-- -------------Phu-------------------------
create table danh_muc (
	id_danh_muc bigint auto_increment primary key,
    ma_danh_muc varchar(10) unique,
    ten_danh_muc nvarchar(255),
    mo_ta nvarchar(500),
    ngay_tao timestamp,
    ngay_chinh_sua timestamp,
    trang_thai varchar(20)
);
INSERT INTO danh_muc (ma_danh_muc, ten_danh_muc, mo_ta, ngay_tao, ngay_chinh_sua, trang_thai)
VALUES 
('DM00000001', 'Giày Thể Thao', 'Giày dành cho ACTIVE thể thao', NOW(), NOW(), 'ACTIVE'),
('DM00000002', 'Giày Chạy Bộ', 'Giày chạy bộ chuyên nghiệp', NOW(), NOW(), 'ACTIVE'),
('DM00000003', 'Giày Lười', 'Giày không dây dễ đi', NOW(), NOW(), 'ACTIVE'),
('DM00000004', 'Giày Bóng Đá', 'Giày đá bóng chất lượng cao', NOW(), NOW(), 'ACTIVE');

create table thuong_hieu (
	id_thuong_hieu bigint auto_increment primary key,
    ma_thuong_hieu varchar(10) unique,
    ten_thuong_hieu nvarchar(255),
    xuat_xu nvarchar(255),
    mo_ta nvarchar(500),
    trang_thai varchar(20)
); 
INSERT INTO thuong_hieu (ma_thuong_hieu, ten_thuong_hieu, xuat_xu, mo_ta, trang_thai)
VALUES 
('TH00000001', 'Nike', 'Mỹ', 'Thương hiệu thể thao hàng đầu', 'ACTIVE'),
('TH00000002', 'Adidas', 'Đức', 'Thương hiệu giày nổi tiếng thế giới', 'ACTIVE'),
('TH00000003', 'Puma', 'Đức', 'Thương hiệu nổi tiếng trong thể thao', 'ACTIVE'),
('TH00000004', 'Vans', 'Mỹ', 'Thương hiệu giày phong cách đường phố', 'ACTIVE');

create table san_pham (
	id_san_pham bigint auto_increment primary key,
    ma_san_pham varchar(10) unique, 
    id_danh_muc bigint,
    id_thuong_hieu bigint,
	id_chat_lieu bigint,
    id_trong_luong bigint,
    ten_san_pham nvarchar(255),
    mo_ta nvarchar(500),
    ngay_tao timestamp,
    ngay_chinh_sua timestamp,
    nguoi_tao nvarchar(255),
    nguoi_chinh_sua nvarchar(255),
    trang_thai varchar(20),
    constraint fk_sanpham_danhmuc foreign key (id_danh_muc) references danh_muc(id_danh_muc),
    constraint fk_sanpham_thuonghieu foreign key (id_thuong_hieu) references thuong_hieu(id_thuong_hieu),
	constraint fk_spct_trongluong foreign key (id_trong_luong) references trong_luong(id_trong_luong),
    constraint fk_spct_chatlieu foreign key (id_chat_lieu) references chat_lieu(id_chat_lieu)
);
INSERT INTO san_pham (ma_san_pham, id_danh_muc, id_thuong_hieu, id_chat_lieu, id_trong_luong, ten_san_pham, mo_ta, ngay_tao, ngay_chinh_sua, nguoi_tao, nguoi_chinh_sua, trang_thai)
VALUES 
-- NIKE
('SP00000001', 1, 1, 1, 1, 'Nike Air Max 270', 'Giày thể thao phong cách năng động', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),
('SP00000002', 1, 1, 1, 1, 'Nike Jordan 1 Low Cardinal', 'Giày thể thao phong cách năng động', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),

-- ADIDAS
('SP00000003', 2, 2, 1, 1, 'Adidas Ultraboost 21', 'Giày chạy bộ chuyên nghiệp', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),
('SP00000004', 2, 2, 1, 1, 'Adidas Samba', 'Giày chạy bộ chuyên nghiệp', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),

-- PUMA
('SP00000005', 3, 3, 1, 1, 'Puma Suede Classic', 'Giày lười cổ điển', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),
('SP00000006', 3, 3, 1, 1, 'Puma Speed Cat OG', 'Giày lười cổ điển', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),

-- VANS
('SP00000007', 4, 4, 1, 1, 'Vans Old Skool', 'Giày đường phố thời trang', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE'),
('SP00000008', 4, 4, 1, 1, 'Vans Knu', 'Giày đường phố thời trang', NOW(), NOW(), 'ADMIN', 'ADMIN', 'ACTIVE');

create table san_pham_chi_tiet (
	id_spct bigint auto_increment primary key,
    ma_spct nvarchar(10) unique,
    id_san_pham bigint,
    id_mau_sac bigint,
    id_kich_co bigint,
    gia decimal,
    so_luong int,
    ngay_tao timestamp,
    ngay_chinh_sua timestamp,
    anh text,
    trang_thai nvarchar(20),
	constraint fk_spct_sanpham foreign key (id_san_pham) references san_pham(id_san_pham),
	constraint fk_spct_mausac foreign key (id_mau_sac) references mau_sac(id_mau_sac),
	constraint fk_spct_kichco foreign key (id_kich_co) references kich_co(id_kich_co)
);
INSERT INTO san_pham_chi_tiet (id_san_pham, ma_spct, id_mau_sac, id_kich_co, gia, so_luong, ngay_tao, ngay_chinh_sua, anh, trang_thai)
VALUES 

-- Sản phẩm 1
-- Màu đỏ
(1, 'SPCT000001', 1, 1, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000002', 1, 2, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000003', 1, 3, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000004', 1, 4, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000005', 1, 5, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000006', 1, 6, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
(1, 'SPCT000236', 1, 7, 1500000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+(GS).png', 'ACTIVE'),
-- Màu đen
(1, 'SPCT000007', 2, 1, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),
(1, 'SPCT000008', 2, 2, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),
(1, 'SPCT000009', 2, 3, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),
(1, 'SPCT000010', 2, 4, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),
(1, 'SPCT000011', 2, 5, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),
(1, 'SPCT000012', 2, 6, 1700000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+DEN+(GS) (1).png', 'ACTIVE'),

-- Màu xanh lá
(1, 'SPCT000013', 3, 1, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),
(1, 'SPCT000014', 3, 2, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),
(1, 'SPCT000015', 3, 3, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),
(1, 'SPCT000016', 3, 4, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),
(1, 'SPCT000017', 3, 5, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),
(1, 'SPCT000018', 3, 6, 2000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+XANH-LA.png', 'ACTIVE'),

-- Màu vàng
(1, 'SPCT000019', 4, 1, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
(1, 'SPCT000020', 4, 2, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
(1, 'SPCT000021', 4, 3, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
(1, 'SPCT000022', 4, 4, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
(1, 'SPCT000023', 4, 5, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
(1, 'SPCT000024', 4, 6, 3000000, 100, NOW(), NOW(), N'NIKE+AIR+MAX+270+VANG.png', 'ACTIVE'),
-- Sản phẩm 1//////////////////

-- Sản phẩm 2
-- Màu đỏ
(2, 'SPCT000025', 1, 1, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
(2, 'SPCT000026', 1, 2, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
(2, 'SPCT000027', 1, 3, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
(2, 'SPCT000028', 1, 4, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
(2, 'SPCT000029', 1, 5, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
(2, 'SPCT000030', 1, 6, 1500000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Red-.png', 'ACTIVE'),
-- Màu đen
(2, 'SPCT000031', 2, 1, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),
(2, 'SPCT000032', 2, 2, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),
(2, 'SPCT000033', 2, 3, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),
(2, 'SPCT000034', 2, 4, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),
(2, 'SPCT000035', 2, 5, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),
(2, 'SPCT000036', 2, 6, 1700000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Den.png', 'ACTIVE'),

-- Màu xanh lá
(2, 'SPCT000037', 3, 1, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
(2, 'SPCT000038', 3, 2, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
(2, 'SPCT000039', 3, 3, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
(2, 'SPCT000040', 3, 4, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
(2, 'SPCT000041', 3, 5, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
(2, 'SPCT000042', 3, 6, 2000000, 100, NOW(), NOW(), N'Jordan-1-Low-Cardinal-Xanh-La.png', 'ACTIVE'),
-- Sản phẩm 2//////////////////


-- Sản phẩm 3

-- Đỏ
(3, 'SPCT000050', 1, 1, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),
(3, 'SPCT000051', 1, 2, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),
(3, 'SPCT000052', 1, 3, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),
(3, 'SPCT000054', 1, 4, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),
(3, 'SPCT000055', 1, 5, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),
(3, 'SPCT000056', 1, 6, 3200000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJo_FZ1922_01_standard.png', 'ACTIVE'),

-- Đen
(3, 'SPCT000057', 2, 1, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),
(3, 'SPCT000058', 2, 2, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),
(3, 'SPCT000059', 2, 3, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),
(3, 'SPCT000060', 2, 4, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),
(3, 'SPCT000061', 2, 5, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),
(3, 'SPCT000062', 2, 6, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_DJen_FY0306_01_standard_11zon.png', 'ACTIVE'),

-- Xanh lá
(3, 'SPCT000063', 3, 1, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
(3, 'SPCT000064', 3, 2, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
(3, 'SPCT000065', 3, 3, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
(3, 'SPCT000066', 3, 4, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
(3, 'SPCT000067', 3, 5, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
(3, 'SPCT000068', 3, 6, 1700000, 100, NOW(), NOW(), N'Giay_UltraBoost_21_XANH_LA.png', 'ACTIVE'),
-- Sản phẩm 3 ///////////////

-- Sản phẩm 4
-- Đỏ
(4, 'SPCT000069', 1, 1, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),
(4, 'SPCT000070', 1, 2, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),
(4, 'SPCT000071', 1, 3, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),
(4, 'SPCT000072', 1, 4, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),
(4, 'SPCT000073', 1, 5, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),
(4, 'SPCT000074', 1, 6, 3200000, 100, NOW(), NOW(), N'adidas-samba-do.png', 'ACTIVE'),

-- Đen
(4, 'SPCT000075', 2, 1, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),
(4, 'SPCT000076', 2, 2, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),
(4, 'SPCT000077', 2, 3, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),
(4, 'SPCT000078', 2, 4, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),
(4, 'SPCT000079', 2, 5, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),
(4, 'SPCT000080', 2, 6, 1700000, 100, NOW(), NOW(), N'adidas-samba-den.png', 'ACTIVE'),

-- Xanh lá
(4, 'SPCT000081', 3, 1, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(4, 'SPCT000082', 3, 2, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(4, 'SPCT000083', 3, 3, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(4, 'SPCT000084', 3, 4, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(4, 'SPCT000085', 3, 5, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(4, 'SPCT000086', 3, 6, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
-- Sản phẩm 4 ///////////////

-- Sản phẩm 5
-- Đen
(5, 'SPCT000087', 1, 1, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),
(5, 'SPCT000088', 1, 2, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),
(5, 'SPCT000089', 1, 3, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),
(5, 'SPCT000090', 1, 4, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),
(5, 'SPCT000091', 1, 5, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),
(5, 'SPCT000092', 1, 6, 2000000, 100, NOW(), NOW(), N'Giay-Puma-Suede-Classic-21-High-Risk-Red-374915-02.jpg.png', 'ACTIVE'),

-- Đỏ
(5, 'SPCT000093', 2, 1, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),
(5, 'SPCT000094', 2, 2, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),
(5, 'SPCT000095', 2, 3, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),
(5, 'SPCT000096', 2, 4, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),
(5, 'SPCT000097', 2, 5, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),
(5, 'SPCT000098', 2, 6, 1700000, 100, NOW(), NOW(), N'2-7ca00abe-bea8-45b0-aaea-06d8120038e9.png', 'ACTIVE'),

-- Xanh lá
(5, 'SPCT000099', 3, 1, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
(5, 'SPCT000100', 3, 2, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
(5, 'SPCT000101', 3, 3, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
(5, 'SPCT000102', 3, 4, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
(5, 'SPCT000103', 3, 5, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
(5, 'SPCT000104', 3, 6, 1700000, 100, NOW(), NOW(), N'giay-nu-puma-suede-classic-21-high-risk-xanh-la-37491502-6.jpg.png', 'ACTIVE'),
-- Sản phẩm 5 ///////////////

-- Sản phẩm 6
-- Đen
(6, 'SPCT000105', 1, 1, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),
(6, 'SPCT000106', 1, 2, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),
(6, 'SPCT000107', 1, 3, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),
(6, 'SPCT000108', 1, 4, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),
(6, 'SPCT000109', 1, 5, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),
(6, 'SPCT000110', 1, 6, 2000000, 100, NOW(), NOW(), N'puma-speed-cat-do.png', 'ACTIVE'),

-- Đỏ
(6, 'SPCT000111', 2, 1, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),
(6, 'SPCT000112', 2, 2, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),
(6, 'SPCT000113', 2, 3, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),
(6, 'SPCT000114', 2, 4, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),
(6, 'SPCT000115', 2, 5, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),
(6, 'SPCT000116', 2, 6, 1700000, 100, NOW(), NOW(), N'puma-speed-cat-den.png', 'ACTIVE'),

-- Xanh lá
(6, 'SPCT000117', 3, 1, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(6, 'SPCT000118', 3, 2, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(6, 'SPCT000119', 3, 3, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(6, 'SPCT000120', 3, 4, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(6, 'SPCT000121', 3, 5, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
(6, 'SPCT000122', 3, 6, 1700000, 100, NOW(), NOW(), N'adidas-samba-xanh-la.png', 'ACTIVE'),
-- Sản phẩm 6 ///////////////


-- Sản phẩm 7
-- Đỏ
(7, 'SPCT000123', 1, 1, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000124', 1, 2, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000125', 1, 3, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000126', 1, 4, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000127', 1, 5, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000128', 1, 6, 1700000, 100, NOW(), NOW(), N'vans-classic-old-skool-brick-red-true-white-vn000vokdic-1_11zon.png', 'ACTIVE'),

-- Đen
(7, 'SPCT000129', 2, 1, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000130', 2, 2, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000131', 2, 3, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000132', 2, 4, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000133', 2, 5, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),
(7, 'SPCT000134', 2, 6, 2000000, 100, NOW(), NOW(), N'vans-old-skool-black-white-vn000d3hy28-1_11zon.png', 'ACTIVE'),

-- Xanh lá
(7, 'SPCT000135', 3, 1, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
(7, 'SPCT000136', 3, 2, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
(7, 'SPCT000137', 3, 3, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
(7, 'SPCT000138', 3, 4, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
(7, 'SPCT000139', 3, 5, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
(7, 'SPCT000140', 3, 6, 4000000, 100, NOW(), NOW(), N'vans-old-skool-xanh.png', 'ACTIVE'),
-- Sản phẩm 7 ///////////////

-- Sản phẩm 8
-- Đỏ
(8, 'SPCT000141', 1, 1, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),
(8, 'SPCT000142', 1, 2, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),
(8, 'SPCT000143', 1, 3, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),
(8, 'SPCT000144', 1, 4, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),
(8, 'SPCT000145', 1, 5, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),
(8, 'SPCT000146', 1, 6, 1700000, 100, NOW(), NOW(), N'vans-knu-do.png', 'ACTIVE'),

-- Đen
(8, 'SPCT000147', 2, 1, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),
(8, 'SPCT000148', 2, 2, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),
(8, 'SPCT000150', 2, 3, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),
(8, 'SPCT000151', 2, 4, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),
(8, 'SPCT000152', 2, 5, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),
(8, 'SPCT000154', 2, 6, 2000000, 100, NOW(), NOW(), N'vans-knu-den.png', 'ACTIVE'),

-- Xanh lá
(8, 'SPCT000155', 3, 1, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE'),
(8, 'SPCT000156', 3, 2, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE'),
(8, 'SPCT000157', 3, 3, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE'),
(8, 'SPCT000158', 3, 4, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE'),
(8, 'SPCT000159', 3, 5, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE'),
(8, 'SPCT000160', 3, 6, 4000000, 100, NOW(), NOW(), N'vans-knu-xanh-la.png', 'ACTIVE');
-- Sản phẩm 8 ///////////////

-- ----------------------------------//Phu--------------------------

-- -------------------------------Minh -----------------------------

create table chuc_vu (
    id_chuc_vu bigint auto_increment primary key,
    ma_chuc_vu varchar(10) unique,
    ten_chuc_vu nvarchar(255),
    mo_ta nvarchar(500),
    trang_thai varchar(20) 
);
INSERT INTO chuc_vu (ma_chuc_vu, ten_chuc_vu, mo_ta, trang_thai)
VALUES
('CV001', 'ADMIN', N'Quản lý cao nhất', 'ACTIVE'),
('CV002', 'EMPLOYEE', N'Nhân viên', 'ACTIVE'),
('CV003', 'CUSTOMER', N'Khách hàng', 'ACTIVE');

create table khach_hang (
	id_khach_hang bigint auto_increment primary key,
	id_chuc_vu bigint,
    ma_khach_hang varchar(10) unique,
    mat_khau varchar(255),
    ten_khach_hang nvarchar(255),
    so_dien_thoai varchar(10),
    email varchar(255),
    ngay_sinh date,
    gioi_tinh bit,
    ghi_chu nvarchar(500),
    ngay_tao timestamp,
    ngay_chinh_sua timestamp,
    trang_thai varchar(20),
    constraint fk_khachhang_chucvu foreign key (id_chuc_vu) references chuc_vu(id_chuc_vu)
);
INSERT INTO khach_hang (id_chuc_vu, ma_khach_hang, mat_khau, ten_khach_hang, so_dien_thoai, email, ngay_sinh, gioi_tinh, ghi_chu, ngay_tao, ngay_chinh_sua, trang_thai)
VALUES
(3, 'KH000', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 'Khách Hàng Lẻ', '0123456789', 'khachle@gmail.com', '1990-01-15', 1, 'Khách hàng lẻ', NOW(), NOW(), 'ACTIVE'),
(3, 'KH001', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 'Nguyễn Văn A', '0912345678', 'nguyenvana@example.com', '1990-01-15', 1, 'Khách hàng thân thiết', NOW(), NOW(), 'ACTIVE'),
(3, 'KH002', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 'Trần Thị B', '0987654321', 'tranthib@example.com', '1985-05-20', 0, 'Khách hàng mới', NOW(), NOW(), 'ACTIVE'),
(3, 'KH003', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 'Lê Hoàng C', '0933445566', 'lehoangc@example.com', '1995-07-30', 1, 'Thường xuyên mua giày thể thao', NOW(), NOW(), 'ACTIVE');

create table dia_chi_khach_hang (
    id_dia_chi_khach_hang bigint auto_increment primary key,
    ma_dia_chi_khach_hang varchar(10) unique,
    id_khach_hang bigint,
    id_tinh varchar(50),
    ten_tinh nvarchar(255),
    id_quan_huyen varchar(50),
    ten_quan_huyen nvarchar(255),
    ma_phuong_xa varchar(50),
    ten_phuong_xa nvarchar(255),
    dia_chi_chi_tiet nvarchar(255),
    trang_thai varchar(20),
    constraint fk_diachikhachhang_khachhang foreign key (id_khach_hang) references khach_hang(id_khach_hang)
);
INSERT INTO dia_chi_khach_hang (ma_dia_chi_khach_hang, id_khach_hang, id_tinh, ten_tinh, id_quan_huyen, ten_quan_huyen,  ma_phuong_xa, ten_phuong_xa, dia_chi_chi_tiet, trang_thai)
VALUES
('DCKH001', 1, 229, 'Phú Thọ', 1602, 'Thành phố Việt Trì', '150122', 'Xã Thụy Vân', 'Phú Thịnh', 'ACTIVE'),
('DCKH002', 2, 268, 'Hưng Yên', 2194, 'Huyện Phù Cừ', '220714', 'Xã Tống Trân', 'Phú Hậu', 'ACTIVE'),
('DCKH003', 3, 269, 'Lào Cai', 2264, 'Huyện Si Ma Cai', '90816',  'Thị Trấn Si Ma Cai', 'Nỗ Lực', 'ACTIVE');



create table nhan_vien (
    id_nhan_vien bigint auto_increment primary key,
    ma_nhan_vien varchar(10) unique,
    mat_khau varchar(255),
    id_chuc_vu bigint,
    ho_ten nvarchar(255),
    ngay_sinh date,
    dia_chi nvarchar(500),
    gioi_tinh bit,
    so_dien_thoai varchar(10),
    email varchar(255),
    ghi_chu nvarchar(500),
    anh varchar(100),
    ngay_tao timestamp,
    ngay_chinh_sua timestamp,
    trang_thai varchar(20),
    constraint fk_nhanvien_chucvu foreign key (id_chuc_vu) references chuc_vu(id_chuc_vu)
);
INSERT INTO nhan_vien (ma_nhan_vien, mat_khau, id_chuc_vu, ho_ten, ngay_sinh, dia_chi, gioi_tinh, so_dien_thoai, email, ghi_chu, anh, ngay_tao, ngay_chinh_sua,  trang_thai)
VALUES
('NV001', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 1, 'Nguyễn Trọng Phú', '2004-09-13', '123 Đường ABC, Quận 2, TP Hồ Chí Minh', 1, '0909876543', 'phamvand@example.com', 'Quản lý cửa hàng tại TP.HCM', 'phamvand.jpg', NOW(), NOW(), 'ACTIVE'),
('NV002', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 2, 'Bùi Anh Tuấn', '2000-01-01', '456 Đường XYZ, Quận 5, TP Hồ Chí Minh', 0, '0932456789', 'nguyenthie@example.com', 'Nhân viên bán hàng tại chi nhánh Quận 5', 'nguyenthie.jpg', NOW(), NOW(), 'ACTIVE'),
('NV003', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 2, 'Ngô Gia Tự', '2004-12-02', '789 Đường DEF, Quận 7, TP Hồ Chí Minh', 1, '0911234567', 'levanf@example.com', 'Nhân viên kho', 'levanf.jpg', NOW(), NOW(), 'ACTIVE'),
('NV004', '$2a$10$l6aZ9d2htc5zCXq8ay99ruWQTZv9IRRXbOeXXY8d6hMbWE5mumJKm', 2, 'Vũ Quang Minh', '2004-03-28', '789 Đường DEF, Quận 7, TP Hồ Chí Minh', 1, '0911234567', 'levanf@example.com', 'Nhân viên kho', 'levanf.jpg', NOW(), NOW(), 'ACTIVE');

-- -------------------------------//Minh ---------------------------


-- -------------------------------Anh Tuan ---------------------------
create table thanh_toan (
	id_thanh_toan bigint auto_increment primary key,
    ma_thanh_toan nvarchar(10) unique,
    phuong_thuc_thanh_toan nvarchar(255) ,
    trang_thai varchar(20) 
);

INSERT INTO thanh_toan (ma_thanh_toan, phuong_thuc_thanh_toan, trang_thai)
VALUES
('TT00001', 'CASH', 'ACTIVE'),
('TT00002', 'COD', 'ACTIVE'),
('TT00003', 'VNPAY', 'ACTIVE');

/**
* CASH:  tiền mặt
* COD: ship
* VPAY: chuyển khoản
*/

create table phieu_giam_gia (
	id_phieu_giam_gia bigint auto_increment primary key,
    ma_phieu_giam_gia varchar(10) unique,
    ten_phieu_giam_gia nvarchar(255),
    so_luong int,
    mo_ta nvarchar(500),
    loai_giam nvarchar(255),
    ngay_bat_dau timestamp,
    ngay_ket_thuc timestamp,
    gia_tri_giam_toi_da decimal,
    gia_tri_hoa_don_toi_thieu decimal,
    gia_tri_giam decimal,
    trang_thai varchar(20)
);
INSERT INTO phieu_giam_gia (ma_phieu_giam_gia, ten_phieu_giam_gia, so_luong, mo_ta, loai_giam, ngay_bat_dau, ngay_ket_thuc, gia_tri_giam_toi_da, gia_tri_hoa_don_toi_thieu, gia_tri_giam, trang_thai)
VALUES
('PGG0000001', 'Giảm giá mùa hè', 100, 'Giảm giá 10% cho đơn hàng trên 500k', 'PERCENT', NOW(), NOW() + INTERVAL 30 DAY, 100000, 500000, 10, 'ACTIVE'),
('PGG0000002', 'Giảm giá dịp lễ', 90, 'Giảm 50k cho đơn hàng trên 200k', 'MONEY', NOW(), NOW() + INTERVAL 15 DAY, 50000, 200000, 50000, 'ACTIVE'),
('PGG0000003', 'Giảm giá sắp diễn ra', 85, 'Giảm 50k cho đơn hàng trên 200k', 'MONEY', NOW() + INTERVAL 10 DAY, NOW() + INTERVAL 15 DAY, 50000, 200000, 50000, 'COMINGSOON');




create table hoa_don (
	id_hoa_don bigint auto_increment primary key,
    id_khach_hang bigint,
    id_nhan_vien bigint,
    id_thanh_toan bigint,
    id_phieu_giam_gia bigint,
    ma_hoa_don nvarchar(10) unique,
    ngay_tao_don timestamp,
    ma_giao_dich nvarchar(255),
    phi_van_chuyen decimal,
    tong_tien decimal,
    tong_tien_sau_giam decimal,
    ngay_chinh_sua timestamp,
    ngay_xac_nhan timestamp,
    ngay_nhan_hang timestamp,
    loai_hoa_don varchar(20),
    ten_khach_hang nvarchar(255),
    dia_chi_giao_hang nvarchar(255),
    so_dien_thoai_khach_hang varchar(10),
    ghi_chu nvarchar(500),
    trang_thai varchar(20),
    constraint fk_hoadon_khachhang foreign key (id_khach_hang) references khach_hang(id_khach_hang),
    constraint fk_hoadon_nhanvien foreign key (id_nhan_vien) references nhan_vien(id_nhan_vien),
    constraint fk_hoadon_thanhtoan foreign key (id_thanh_toan) references thanh_toan(id_thanh_toan),
    constraint fk_hoadon_phieugiamgia foreign key (id_phieu_giam_gia) references phieu_giam_gia(id_phieu_giam_gia)
);

/**
	COUNTERSALES: Bán tại quầy;
    ONLINESALES: Bán online
*/

create table hoa_don_chi_tiet (
	id_hoa_don_chi_tiet bigint auto_increment primary key ,
    id_hoa_don bigint,
    id_spct bigint,
    ma_hoa_don_chi_tiet nvarchar(10) unique,
    so_luong int,
    don_gia decimal,
    tong_tien decimal,
    trang_thai varchar(20),
    constraint fk_hoadonchitiet_hoadon foreign key (id_hoa_don) references hoa_don(id_hoa_don),
    constraint fk_hoadonchitiet_sanphamchitiet foreign key (id_spct) references san_pham_chi_tiet(id_spct)
);


create table lich_su_hoa_don (
	id_lshd bigint auto_increment primary key,
	id_hoa_don bigint,
    ma_lich_su_hoa_don nvarchar(10) unique,
    hanh_dong nvarchar(255),
    ngay_tao timestamp,
    nguoi_thuc_hien nvarchar(255),
    trang_thai varchar(10),
    constraint fk_lichsuhoadon_hoadon foreign key (id_hoa_don) references hoa_don(id_hoa_don)
);

-- -------------------------------//Anh Tuan ---------------------------
