CREATE DATABASE PetShopManagement;
GO

DROP DATABASE IF EXISTS PetShopManagement;
GO

USE PetShopManagement;
GO

CREATE TABLE USERS (
    userId INT PRIMARY KEY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
	fullName NVARCHAR(100) NOT NULL,
	gender BIT,
    phoneNumber NVARCHAR(15),
    address NVARCHAR(200),
    createAt DATE NOT NULL,
    birthDate DATE NOT NULL
);

CREATE TABLE CUSTOMER (
    customerId INT PRIMARY KEY,
    phoneNumber NVARCHAR(15),
    customerName NVARCHAR(100) NOT NULL UNIQUE,
    point INT
);

CREATE TABLE EMPLOYEE (
    userId INT,
    position NVARCHAR(50),
    salary FLOAT,
    workingHours NVARCHAR(50),
    FOREIGN KEY (userId) REFERENCES USERS (userId)
);

CREATE TABLE SUPPLIER (
    supplierId INT PRIMARY KEY,
    supplierName NVARCHAR(50) NOT NULL,
    email NVARCHAR(100) ,
    phone NVARCHAR(15),
    address NVARCHAR(100)
);

CREATE TABLE PRODUCTS (
    productId INT PRIMARY KEY,
    supplierId INT,
    name NVARCHAR(100),
    price FLOAT,
    quantity INT,
    description NVARCHAR(250),
	manufacturer NVARCHAR(50),
    FOREIGN KEY (supplierId) REFERENCES SUPPLIER(supplierId)
);

CREATE TABLE PET (
    petId INT PRIMARY KEY,
    name NVARCHAR(50),
    breed NVARCHAR(50),
    age INT,
    gender BIT,
    price FLOAT,
    vaccinated BIT,
    healthStatus NVARCHAR(100),
    origin NVARCHAR(100),
	weight FLOAT,
	furColor NVARCHAR(50),
	description NVARCHAR(250),
	supplierId INT,
	FOREIGN KEY (supplierId) REFERENCES SUPPLIER(supplierId)
);

CREATE TABLE PET_WARRANTY (
    warrantyId INT PRIMARY KEY,
    insuranceType NVARCHAR(50),
    startDate DATE,
    endDate DATE,
    petId INT UNIQUE,
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE PROMOTION (
    promotionId INT PRIMARY KEY ,
    name NVARCHAR(50),
    discountPercentage FLOAT,
    startDate DATE,
    endDate DATE,
	isActive BIT
);

CREATE TABLE BILL (
    billId INT PRIMARY KEY,
    userId INT,
	customerId INT,
    date DATE,
    totalAmount FLOAT,
    paymentMethod NVARCHAR(50),
    status NVARCHAR(20) DEFAULT 'pending',
    FOREIGN KEY (userId) REFERENCES USERS(userId),
	FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId)
);

CREATE TABLE BILL_DETAIL (
    billDetailId INT PRIMARY KEY,
    billId INT,
    quantity INT,
    unitPrice FLOAT,
    itemId INT,
    totalPrice FLOAT,
    FOREIGN KEY (billId) REFERENCES BILL(billId),
);

CREATE TABLE FEEDBACK (
    feedbackId INT PRIMARY KEY,
	userId INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(200),
    date DATE,
	FOREIGN KEY (userId) REFERENCES USERS(userId)
);

CREATE TABLE REVENUE_REPORT (
    reportId INT PRIMARY KEY ,
    startMonthYear DATE,
    endMonthYear DATE,
    totalRevenue FLOAT,
    totalBill INT
);



-- INSERT INTO USERS
INSERT INTO USERS VALUES
(1, 'admin1', 'admin@123', 'admin1@petshop.com', 'Nguyễn Văn A', 1, '0909123456', 'TP.HCM', '2024-01-01', '1995-05-20'),
(2, 'staff01', 'staff@123', 'staff01@petshop.com', 'Trần Thị B', 0, '0911123456', 'TP.HCM', '2024-01-05', '1998-03-15'),
(3, 'johnsmith', '123456', 'johnsmith@gmail.com', 'John Smith', 1, '0932123456', 'USA', '2024-02-02', '2000-04-12'),
(4, 'ngocanh', 'pass123', 'ngocanh@gmail.com', 'Ngọc Anh', 0, '0988888881', 'Đồng Nai', '2024-03-01', '2002-12-01'),
(5, 'huynhnhat', 'nhatpass', 'huynhnhat@gmail.com', 'Huỳnh Nhật', 1, '0977888999', 'Bình Dương', '2024-03-02', '1999-10-15'),
(6, 'trungkien', 'kien123', 'trungkien@gmail.com', 'Trung Kiên', 1, '0911112233', 'Long An', '2024-03-03', '2001-06-06');

-- INSERT INTO CUSTOMER
INSERT INTO CUSTOMER VALUES
(1, '0987654321', 'Lê Thị C', 120),
(2, '0977554433', 'Phạm Văn D', 90),
(3, '0933445566', 'Nguyễn Văn E', 200),
(4, '0909001122', 'Trần Mỹ Linh', 45),
(5, '0966554433', 'Đỗ Mạnh Cường', 78),
(6, '0933455667', 'Nguyễn Kim Dung', 33);


-- INSERT INTO EMPLOYEE
INSERT INTO EMPLOYEE VALUES
(1, 'Quản lý', 20000000, 'Toàn thời gian'),
(2, 'Nhân viên bán hàng', 10000000, 'Toàn thời gian'),
(3, 'Tư vấn viên', 8500000, 'Bán thời gian'),
(4, 'Nhân viên chăm sóc', 9500000, 'Toàn thời gian');


-- INSERT INTO SUPPLIER
INSERT INTO SUPPLIER VALUES
(1, 'Thế Giới Thú Cưng', 'supplier1@gmail.com', '0908888999', 'Q1, TP.HCM'),
(2, 'PetSupply Co.', 'contact@petsupply.com', '0911999888', 'Q3, TP.HCM'),
(3, 'Happy Pets', 'happypets@suppliers.com', '0912333444', 'Cần Thơ'),
(4, 'Dog & Cat World', 'dcw@shop.vn', '0909777555', 'Đà Nẵng');

-- INSERT INTO PRODUCTS
INSERT INTO PRODUCTS VALUES
(1, 1, 'Thức ăn cho chó', 150000, 50, 'Thức ăn dinh dưỡng cho chó lớn', 'Royal Canin'),
(2, 1, 'Vòng cổ cho mèo', 70000, 100, 'Vòng cổ có chuông, nhiều màu sắc', 'PetRing'),
(3, 2, 'Sữa tắm cho chó mèo', 120000, 30, 'Khử mùi và dưỡng lông', 'PetCare'),
(4, 3, 'Đồ chơi gặm xương', 55000, 70, 'Dành cho chó con', 'PetFun'),
(5, 3, 'Chuồng mèo mini', 650000, 10, 'Kèm khay vệ sinh', 'PetHome'),
(6, 4, 'Pate cho mèo', 30000, 80, 'Hương cá hồi', 'Whiskas');

-- INSERT INTO PET
INSERT INTO PET VALUES
(1, 'Milo', 'Poodle', 2, 1, 4500000, 1, 'Khỏe mạnh', 'Việt Nam', 4.5, 'Nâu vàng', 'Thân thiện, đã tiêm chủng', 1),
(2, 'Luna', 'Mèo Anh Lông Ngắn', 1, 0, 3500000, 1, 'Tốt', 'Thái Lan', 3.2, 'Xám xanh', 'Dễ gần, sạch sẽ', 2),
(3, 'Rex', 'Husky', 3, 1, 7000000, 1, 'Cực khỏe', 'Nga', 20.0, 'Trắng đen', 'Hiếu động, cần không gian rộng', 1),
(4, 'Tom', 'Mèo Ba Tư', 2, 1, 4200000, 1, 'Tốt', 'Iran', 4.1, 'Trắng kem', 'Dễ thương và ngoan', 3),
(5, 'Kiki', 'Chihuahua', 1, 0, 5000000, 1, 'Khỏe mạnh', 'Mexico', 2.5, 'Vàng nâu', 'Nhỏ gọn, quấn người', 4),
(6, 'Bin', 'Alaska', 4, 1, 9500000, 1, 'Cực khỏe', 'Canada', 28.0, 'Trắng xám', 'Thân thiện, dễ huấn luyện', 3);



-- INSERT INTO PET_WARRANTY
INSERT INTO PET_WARRANTY VALUES
(1, 'Bảo hiểm sức khỏe cơ bản', '2025-01-01', '2026-01-01', 1),
(2, 'Bảo hiểm toàn diện', '2025-01-10', '2026-01-10', 2),
(3, 'Bảo hiểm nâng cao', '2025-01-20', '2026-01-20', 3),
(4, 'Bảo hiểm cơ bản', '2025-02-01', '2026-02-01', 4),
(5, 'Bảo hiểm tiêu chuẩn', '2025-02-10', '2026-02-10', 5),
(6, 'Bảo hiểm toàn diện', '2025-03-01', '2026-03-01', 6);

-- INSERT INTO PROMOTION
INSERT INTO PROMOTION VALUES
(1, 'Tết Giảm Giá', 10.0, '2025-01-01', '2025-02-01', 1),
(2, 'Mùa Hè Sôi Động', 15.0, '2025-06-01', '2025-07-15', 1),
(3, 'Black Friday', 20.0, '2025-11-20', '2025-11-30', 1),
(4, 'Valentine Yêu Thương', 12.0, '2025-02-10', '2025-02-15', 1);


-- INSERT INTO BILL
INSERT INTO BILL VALUES
(1, 2, 1, '2025-03-01', 4600000, 'Tiền mặt', 'completed'),
(2, 2, 2, '2025-03-05', 3700000, 'Chuyển khoản', 'pending'),
(3, 4, 4, '2025-03-10', 4255000, 'Tiền mặt', 'completed'),
(4, 5, 5, '2025-03-15', 5030000, 'Chuyển khoản', 'pending'),
(5, 6, 6, '2025-03-20', 9550000, 'Tiền mặt', 'completed');

-- INSERT INTO BILL_DETAIL
INSERT INTO BILL_DETAIL VALUES
(1, 1, 1, 4500000, 1, 4500000), 
(2, 2, 1, 3500000, 2, 3500000),
(3, 3, 1, 4200000, 4, 4200000),
(4, 3, 1, 55000, 4, 55000),
(5, 4, 1, 5000000, 5, 5000000),
(6, 5, 1, 9500000, 6, 9500000);

-- INSERT INTO FEEDBACK
INSERT INTO FEEDBACK VALUES
(1, 3, 5, 'Thú cưng rất dễ thương và khỏe mạnh!', '2025-03-10'),
(2, 3, 4, 'Nhân viên tư vấn nhiệt tình.', '2025-03-12'),
(3, 4, 5, 'Cửa hàng rất chuyên nghiệp, nhiều lựa chọn!', '2025-03-18'),
(4, 5, 3, 'Chó rất đáng yêu nhưng giá hơi cao.', '2025-03-22'),
(5, 6, 4, 'Tư vấn tốt, ship nhanh.', '2025-03-25');

SELECT * FROM REVENUE_REPORT;

-- INSERT INTO REVENUE_REPORT
INSERT INTO REVENUE_REPORT VALUES
(1, '2025-03-01', '2025-03-31', 8300000, 2),
(2, '2025-04-01', '2025-04-30', 0, 0),
(3, '2025-03-01', '2025-03-31', 29035000, 5),
(4, '2025-04-01', '2025-04-30', 0, 0);






