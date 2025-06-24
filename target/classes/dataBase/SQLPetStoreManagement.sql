CREATE DATABASE PetShopManagement;
GO

ALTER DATABASE PetShopManagement
SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE PetShopManagement;
GO

USE PetShopManagement;
GO

CREATE TABLE IMAGE (
	imageId INT IDENTITY(1,1) PRIMARY KEY ,
	image VARBINARY(MAX)
);

CREATE TABLE USERS (
    userId INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
	fullName NVARCHAR(100) NOT NULL,
	gender BIT,
    phoneNumber NVARCHAR(15),
    address NVARCHAR(200),
    createAt DATE NOT NULL,
    birthDate DATE NOT NULL,
	role NVARCHAR(20),
	imageId INT,
	FOREIGN KEY (imageId) REFERENCES IMAGE(imageId)
);

CREATE TABLE CUSTOMER (
    customerId INT IDENTITY(1,1) PRIMARY KEY,
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
    supplierId INT IDENTITY(1,1) PRIMARY KEY,
    supplierName NVARCHAR(50) NOT NULL,
    email NVARCHAR(100) ,
    phone NVARCHAR(15),
    address NVARCHAR(100)
);

CREATE TABLE PRODUCTS (
    productId INT IDENTITY(1,1) PRIMARY KEY,
    supplierId INT,
    name NVARCHAR(100),
    price FLOAT,
    quantity INT,
    description NVARCHAR(MAX),
	manufacturer NVARCHAR(50),
    FOREIGN KEY (supplierId) REFERENCES SUPPLIER(supplierId)
);

CREATE TABLE Accessory (
    productId INT PRIMARY KEY,
    type NVARCHAR(50),
    brand NVARCHAR(50),
    FOREIGN KEY (productId) REFERENCES PRODUCTS(productId)
);

CREATE TABLE Cage (
    productId INT PRIMARY KEY,
    dimension NVARCHAR(50),
    material NVARCHAR(50),
    FOREIGN KEY (productId) REFERENCES PRODUCTS(productId)
);

CREATE TABLE Food (
    productId INT PRIMARY KEY,
    expirationDate DATE,
    flavor NVARCHAR(50),
    FOREIGN KEY (productId) REFERENCES PRODUCTS(productId)
);

CREATE TABLE Toy (
    productId INT PRIMARY KEY,
    material NVARCHAR(50),
    size NVARCHAR(50),
    FOREIGN KEY (productId) REFERENCES PRODUCTS(productId)
);

CREATE TABLE PET (
    petId INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(50),
    age INT,
    gender BIT,
    price FLOAT,
    healthStatus NVARCHAR(100),
    origin NVARCHAR(100),
	weight FLOAT,
	furColor NVARCHAR(50),
	description NVARCHAR(MAX),
	supplierId INT,
    image VARBINARY(MAX),
	FOREIGN KEY (supplierId) REFERENCES SUPPLIER(supplierId)
);

CREATE TABLE Cat (
    petId INT PRIMARY KEY,
    isIndoor BIT,
    breed NVARCHAR(50),
    eyeColor NVARCHAR(50),
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE Dog (
    petId INT PRIMARY KEY,
    breed NVARCHAR(50),
    isTrained BIT,
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE Hamster (
    petId INT PRIMARY KEY,
    breed NVARCHAR(50),
    tailLength FLOAT,
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE Rabbit (
    petId INT PRIMARY KEY,
    breed NVARCHAR(50),
    earLength FLOAT,
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE PET_WARRANTY (
    warrantyId INT IDENTITY(1,1) PRIMARY KEY,
    insuranceType NVARCHAR(50),
    startDate DATE,
    endDate DATE,
    petId INT UNIQUE,
    FOREIGN KEY (petId) REFERENCES PET(petId)
);

CREATE TABLE PROMOTION (
    promotionId INT IDENTITY(1,1) PRIMARY KEY ,
    name NVARCHAR(50),
    discountPercentage FLOAT,
    startDate DATE,
    endDate DATE,
	isActive BIT
);

CREATE TABLE BILL (
    billId INT IDENTITY(1,1) PRIMARY KEY,
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
    billDetailId INT IDENTITY(1,1) PRIMARY KEY,
    billId INT,
    quantity INT,
    unitPrice FLOAT,
    itemId INT,
    totalPrice FLOAT,
    FOREIGN KEY (billId) REFERENCES BILL(billId)
);

CREATE TABLE FEEDBACK (
    feedbackId INT IDENTITY(1,1) PRIMARY KEY,
	userId INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(200),
    date DATE,
	FOREIGN KEY (userId) REFERENCES USERS(userId)
);

CREATE TABLE REVENUE_REPORT (
    reportId INT IDENTITY(1,1) PRIMARY KEY ,
    month INT,
    year INT,
    totalRevenue FLOAT,
    totalBill INT
);

CREATE TABLE IMAGE_PRODUCT (
	imageId INT NOT NULL,
	productId INT NOT NULL,
	PRIMARY KEY (imageId,productId),
	FOREIGN KEY (imageId) REFERENCES IMAGE(imageId),
	FOREIGN KEY (productId) REFERENCES PRODUCTS(productId)
	);

CREATE TABLE VACCINE (
    vaccineId INT PRIMARY KEY IDENTITY(1,1),       -- ID tự tăng
    vaccineName NVARCHAR(100) NOT NULL,            -- Tên vaccine
    description NVARCHAR(MAX),                     -- Mô tả
    applicableSpecies NVARCHAR(100),               -- Loài áp dụng (VD: chó, mèo,...)
    doseCount INT DEFAULT 1,                       -- Số mũi cần tiêm
    intervalDays INT DEFAULT 0,                    -- Khoảng cách giữa các mũi (ngày)
    validityMonths INT DEFAULT 12,                 -- Thời gian hiệu lực (tháng)
    isMandatory BIT DEFAULT 0                      -- Có bắt buộc hay không (0: không, 1: có)
);

CREATE TABLE VACCINE_PET (
    petId INT NOT NULL,
    vaccineId INT NOT NULL,
    --vaccinationDate DATE NOT NULL,            -- Ngày tiêm
    --doseNumber INT DEFAULT 1,                 -- Mũi tiêm thứ mấy
    
    PRIMARY KEY (petId, vaccineId),  -- Khóa chính tổ hợp
    FOREIGN KEY (petId) REFERENCES PET(petId),   -- Khóa ngoại đến bảng PET
    FOREIGN KEY (vaccineId) REFERENCES VACCINE(vaccineId) -- Khóa ngoại đến bảng VACCINE
);

CREATE TABLE Service (
    serviceId INT PRIMARY KEY IDENTITY(1,1),
    serviceName NVARCHAR(100),
    description NVARCHAR(255),
    price FLOAT,
    applicableSpecies NVARCHAR(50) -- ví dụ: Dog, Cat, All
);

CREATE TABLE PetService (
    petServiceId INT PRIMARY KEY IDENTITY(1,1),
    namePet NVARCHAR(100),
    gender NVARCHAR(10),
    age INT,
    customerId INT,
    healthStatus NVARCHAR(100),
    weight FLOAT,
    breed NVARCHAR(100),
    animal NVARCHAR(50),
    dateOfVisit DATE,
    status NVARCHAR(50),
    serviceCost FLOAT,
    note NVARCHAR(500)
);

CREATE TABLE PetService_Service (
    petServiceId INT NOT NULL,
    serviceId INT NOT NULL,
    PRIMARY KEY (petServiceId, serviceId),
    FOREIGN KEY (petServiceId) REFERENCES PetService(petServiceId),
    FOREIGN KEY (serviceId) REFERENCES Service(serviceId)
);





ALTER TABLE PET ADD role NVARCHAR(20);
ALTER TABLE PRODUCTS ADD role NVARCHAR(20);

GO
CREATE OR ALTER TRIGGER trg_RevenueReport_InsertUpdate
ON BILL
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- 1. INSERT mới completed và tháng đó chưa có
    INSERT INTO REVENUE_REPORT (month, year, totalRevenue, totalBill)
    SELECT 
        MONTH(date), YEAR(date),
        SUM(totalAmount), COUNT(*)
    FROM INSERTED
    WHERE status = 'completed'
    GROUP BY MONTH(date), YEAR(date)
    HAVING NOT EXISTS (
        SELECT 1 FROM REVENUE_REPORT r
        WHERE r.month = MONTH(date) AND r.year = YEAR(date)
    );

    -- 2. UPDATE từ chưa completed → completed → cộng dồn
    UPDATE r
    SET
        r.totalRevenue = r.totalRevenue + i.totalAmount,
        r.totalBill = r.totalBill + 1
    FROM REVENUE_REPORT r
    JOIN INSERTED i ON r.month = MONTH(i.date) AND r.year = YEAR(i.date)
    JOIN DELETED d ON d.billId = i.billId
    WHERE i.status = 'completed' AND d.status != 'completed';
END;
GO

GO
CREATE OR ALTER TRIGGER trg_RevenueReport_DeleteUpdate
ON BILL
AFTER DELETE, UPDATE
AS
BEGIN
    SET NOCOUNT ON;


    UPDATE r
    SET
        r.totalRevenue = r.totalRevenue - d.totalAmount,
        r.totalBill = r.totalBill - 1
    FROM REVENUE_REPORT r
    JOIN DELETED d ON r.month = MONTH(d.date) AND r.year = YEAR(d.date)
    WHERE d.status = 'completed';


    UPDATE r
    SET
        r.totalRevenue = r.totalRevenue - d.totalAmount,
        r.totalBill = r.totalBill - 1
    FROM REVENUE_REPORT r
    JOIN DELETED d ON r.month = MONTH(d.date) AND r.year = YEAR(d.date)
    JOIN INSERTED i ON i.billId = d.billId
    WHERE d.status = 'completed' AND i.status != 'completed';

    -- 3. Xóa các dòng nếu totalBill = 0 sau khi cập nhật
    DELETE FROM REVENUE_REPORT
    WHERE totalBill <= 0;
END;
GO


-- INSERT INTO USERS
INSERT INTO USERS VALUES
('admin1', 'admin@123', 'admin1@petshop.com', N'Nguyễn Văn A', 1, '0909123456', N'TP.HCM', '2024-01-01', '1995-05-20', 'Employee'),
('staff01', 'staff@123', 'staff01@petshop.com', N'Trần Thị B', 0, '0911123456', N'TP.HCM', '2024-01-05', '1998-03-15', 'Employee'),
('johnsmith', '123456', 'johnsmith@gmail.com', N'John Smith', 1, '0932123456', N'USA', '2024-02-02', '2000-04-12', 'Employee'),
('ngocanh', 'pass123', 'ngocanh@gmail.com', N'Ngọc Anh', 0, '0988888881', N'Đồng Nai', '2024-03-01', '2002-12-01', 'Employee'),
('huynhnhat', 'nhatpass', 'huynhnhat@gmail.com', N'Huỳnh Nhật', 1, '0977888999', N'Bình Dương', '2024-03-02', '1999-10-15','Admin'),
('trungkien', 'kien123', 'trungkien@gmail.com', N'Trung Kiên', 1, '0911112233', N'Long An', '2024-03-03', '2001-06-06','Admin');

-- INSERT INTO CUSTOMER
INSERT INTO CUSTOMER VALUES
('0987654321', N'Lê Thị C', 120),
('0977554433', N'Phạm Văn D', 90),
('0933445566', N'Nguyễn Văn E', 200),
('0909001122', N'Trần Mỹ Linh', 45),
('0966554433', N'Đỗ Mạnh Cường', 78),
('0933455667', N'Nguyễn Kim Dung', 33);


-- INSERT INTO EMPLOYEE
INSERT INTO EMPLOYEE VALUES
(1, N'Quản lý', 20000000, N'Toàn thời gian'),
(2, N'Nhân viên bán hàng', 10000000, N'Toàn thời gian'),
(3, N'Tư vấn viên', 8500000, N'Bán thời gian'),
(4, N'Nhân viên chăm sóc', 9500000, N'Toàn thời gian');

-- INSERT INTO SUPPLIER
INSERT INTO SUPPLIER VALUES
(N'Thế Giới Thú Cưng', 'supplier1@gmail.com', '0908888999', N'Q1, TP.HCM'),
(N'PetSupply Co.', 'contact@petsupply.com', '0911999888', N'Q3, TP.HCM'),
(N'Happy Pets', 'happypets@suppliers.com', '0912333444', N'Cần Thơ'),
(N'Dog & Cat World', 'dcw@shop.vn', '0909777555', N'Đà Nẵng');

-- INSERT INTO PRODUCTS
INSERT INTO PRODUCTS VALUES
(1, N'Thức ăn cho chó', 150000, 50, N'Thức ăn dinh dưỡng cho chó lớn', 'Royal Canin', 'Food'),
(1, N'Vòng cổ cho mèo', 70000, 100, N'Vòng cổ có chuông, nhiều màu sắc', 'PetRing', 'Accessory'),
(2, N'Sữa tắm cho chó mèo', 120000, 30, N'Khử mùi và dưỡng lông', 'PetCare', 'Accessory'),
(3, N'Đồ chơi gặm xương', 55000, 70, N'Dành cho chó con', 'PetFun', 'Toy'),
(3, N'Chuồng mèo mini', 650000, 10, N'Kèm khay vệ sinh', 'PetHome', 'Cage'),
(4, N'Pate cho mèo', 30000, 80, N'Hương cá hồi', 'Whiskas', 'Food');

INSERT INTO Accessory (productId, type, brand) VALUES
(2, N'Vòng cổ', 'PetRing'),
(3, N'Sữa tắm', 'PetCare');

INSERT INTO Cage (productId, dimension, material) VALUES
(5, '60x40x40 cm', N'Nhựa cứng');

INSERT INTO Food (productId, expirationDate, flavor) VALUES
(1, '2025-12-31', N'Thịt bò'),
(6, '2024-11-30', N'Cá hồi');

INSERT INTO Toy (productId, material, size) VALUES
(4, N'Nhựa dẻo', N'Nhỏ');

-- INSERT INTO PET
INSERT INTO PET VALUES
('Milo', 2, 1, 4500000, 1, N'Khỏe mạnh', N'Việt Nam', 4.5, N'Nâu vàng', N'Thân thiện, đã tiêm chủng', 1, 'Dog'),
('Luna',  1, 0, 3500000, 1, N'Tốt', N'Thái Lan', 3.2, N'Xám xanh', N'Dễ gần, sạch sẽ', 2, 'Cat'),
('Rex',  3, 1, 7000000, 1, N'Cực khỏe', N'Nga', 20.0, N'Trắng đen', N'Hiếu động, cần không gian rộng', 1, 'Dog'),
('Tom', 2, 1, 4200000, 1, N'Tốt', N'Iran', 4.1, N'Trắng kem', N'Dễ thương và ngoan', 3, 'Cat'),
('Kiki',  1, 0, 5000000, 1, N'Khỏe mạnh', N'Mexico', 2.5, N'Vàng nâu', N'Nhỏ gọn, quấn người', 4, 'Dog'),
('Bin',  4, 1, 9500000, 1, N'Cực khỏe', N'Canada', 28.0, N'Trắng xám', N'Thân thiện, dễ huấn luyện', 3, 'Dog');

INSERT INTO Cat VALUES 
(2, 1, N'Mèo Anh Lông Ngắn', N'Xanh'),
(4, 1, N'Mèo Ba Tư', N'Vàng');

INSERT INTO Dog VALUES 
(1, 'Poodle', 1),
(3, 'Husky', 0),
(5, 'Chihuahua', 1),
(6, 'Alaska', 1);

INSERT INTO Hamster VALUES 
(7, N'Hamster Gấu', 2.1);

INSERT INTO Rabbit VALUES 
(8, N'Thỏ tai cụp', 7.5);

-- INSERT INTO PET_WARRANTY
INSERT INTO PET_WARRANTY VALUES
(N'Bảo hiểm sức khỏe cơ bản', '2025-01-01', '2026-01-01', 1),
(N'Bảo hiểm toàn diện', '2025-01-10', '2026-01-10', 2),
(N'Bảo hiểm nâng cao', '2025-01-20', '2026-01-20', 3),
(N'Bảo hiểm cơ bản', '2025-02-01', '2026-02-01', 4),
(N'Bảo hiểm tiêu chuẩn', '2025-02-10', '2026-02-10', 5),
(N'Bảo hiểm toàn diện', '2025-03-01', '2026-03-01', 6);

-- INSERT INTO PROMOTION
INSERT INTO PROMOTION VALUES
(N'Tết Giảm Giá', 10.0, '2025-01-01', '2025-02-01', 1),
(N'Mùa Hè Sôi Động', 15.0, '2025-06-01', '2025-07-15', 1),
(N'Black Friday', 20.0, '2025-11-20', '2025-11-30', 1),
(N'Valentine Yêu Thương', 12.0, '2025-02-10', '2025-02-15', 1);


-- INSERT INTO BILL
INSERT INTO BILL VALUES
(2, 1, '2025-03-01', 4600000, N'Cash', 'completed'),
(2, 2, '2025-03-05', 3700000, N'Banking', 'pending'),
(4, 4, '2025-03-10', 4255000, N'Cash', 'completed'),
(5, 5, '2025-03-15', 5030000, N'Banking', 'pending'),
(6, 6, '2025-03-20', 9550000, N'Cash', 'completed');

-- INSERT INTO BILL_DETAIL
INSERT INTO BILL_DETAIL VALUES
(1, 1, 4500000, 1, 4500000),
(2, 1, 3500000, 2, 3500000),
(3, 1, 4200000, 4, 4200000),
(3, 1, 55000, 4, 55000),
(4, 1, 5000000, 5, 5000000),
(5, 1, 9500000, 6, 9500000);

-- INSERT INTO FEEDBACK
INSERT INTO FEEDBACK VALUES
(3, 5, N'Thú cưng rất dễ thương và khỏe mạnh!', '2025-03-10'),
(3, 4, N'Nhân viên tư vấn nhiệt tình.', '2025-03-12'),
(4, 5, N'Cửa hàng rất chuyên nghiệp, nhiều lựa chọn!', '2025-03-18'),
(5, 3, N'Chó rất đáng yêu nhưng giá hơi cao.', '2025-03-22'),
(6, 4, N'Tư vấn tốt, ship nhanh.', '2025-03-25');



--Rang buoc
--Image
--Them du lieu
--duoc thi them procedure va trigger


SELECT I.image, I.imageId 
                FROM IMAGE I
                JOIN IMAGE_PRODUCT IP ON I.imageId = IP.imageId

                WHERE IP.productId = 8

--Them de an chu ko xoa--
ALTER TABLE USERS ADD isDeleted BIT DEFAULT 0;
ALTER TABLE CUSTOMER ADD isDeleted BIT DEFAULT 0;
ALTER TABLE SUPPLIER ADD isDeleted BIT DEFAULT 0;
ALTER TABLE PRODUCTS ADD isDeleted BIT DEFAULT 0;
ALTER TABLE PET ADD isDeleted BIT DEFAULT 0;
ALTER TABLE BILL ADD isDeleted BIT DEFAULT 0;
ALTER TABLE PET_WARRANTY ADD isDeleted BIT DEFAULT 0;

ALTER TABLE VACCINE ADD isDeleted BIT DEFAULT 0;
ALTER TABLE Service ADD isDeleted BIT DEFAULT 0;
ALTER TABLE PetService ADD isDeleted BIT DEFAULT 0;



-- Thêm vaccine không bắt buộc cho mèo
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin giảm bạch cầu mèo', 
    N'Bảo vệ mèo khỏi virus giảm bạch cầu', 
    N'Mèo', 
    2, 
    21, 
    24, 
    0
);
-- 2. Vắc xin FVRCP (3 bệnh phổ biến ở mèo)
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin FVRCP', 
    N'Phòng bệnh viêm mũi họng do virus herpes, calicivirus và giảm bạch cầu', 
    N'Mèo', 
    3, 
    21, 
    36, 
    1
);

-- 3. Vắc xin phòng bệnh dại cho mèo
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin dại cho mèo', 
    N'Tiêm ngừa bệnh dại – bắt buộc theo luật', 
    N'Mèo', 
    1, 
    0, 
    12, 
    1
);

-- 4. Vắc xin FeLV (bệnh bạch cầu do virus ở mèo)
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin FeLV', 
    N'Ngăn ngừa virus gây bệnh bạch cầu ở mèo (Feline Leukemia Virus)', 
    N'Mèo', 
    2, 
    21, 
    12, 
    0
);

-- 5. Vắc xin phòng FIP (viêm phúc mạc do virus corona ở mèo)
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin FIP', 
    N'Giảm nguy cơ mắc viêm phúc mạc truyền nhiễm ở mèo (Feline Coronavirus)', 
    N'Mèo', 
    1, 
    0, 
    12, 
    0
);

-- Thêm vaccine cho chó
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin 5 bệnh cho chó', 
    N'Phòng bệnh Care, Parvo, Viêm gan, Cúm, Lepto', 
    N'Chó', 
    3, 
    14, 
    36, 
    1
);
-- 2. Vắc xin Leptospirosis đơn lẻ
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin Leptospirosis', 
    N'Ngừa bệnh Lepto do vi khuẩn lây từ động vật sang người', 
    N'Chó', 
    2, 
    21, 
    12, 
    0
);

-- 3. Vắc xin bệnh ho cũi chó (Bordetella)
INSERT INTO VACCINE (vaccineName, description, applicableSpecies, doseCount, intervalDays, validityMonths, isMandatory)
VALUES (
    N'Vắc xin Bordetella', 
    N'Phòng bệnh ho cũi chó – lây lan nhanh trong môi trường tập thể', 
    N'Chó', 
    1, 
    0, 
    6, 
    0
);

-- 2
INSERT INTO PetService (namePet, gender, age, customerId, healthStatus, weight, breed, animal, dateOfVisit, status, serviceCost, note)
VALUES (N'Miu', N'Cái', 1, 2, N'Viêm nhẹ', 3.2, N'Munchkin', N'Mèo', '2025-06-22', N'Đang điều trị', 180000, N'Tiêm kháng sinh');

-- 3
INSERT INTO PetService (namePet, gender, age, customerId, healthStatus, weight, breed, animal, dateOfVisit, status, serviceCost, note)
VALUES (N'Lu', N'Đực', 3, 3, N'Thừa cân', 8.1, N'Pug', N'Chó', '2025-06-20', N'Hoàn thành', 300000, N'Tư vấn dinh dưỡng');

-- 4
INSERT INTO PetService (namePet, gender, age, customerId, healthStatus, weight, breed, animal, dateOfVisit, status, serviceCost, note)
VALUES (N'Bin', N'Đực', 5, 1, N'Nhiễm trùng tai', 7.4, N'Golden', N'Chó', '2025-06-15', N'Chờ kết quả xét nghiệm', 220000, N'Khám và lấy mẫu dịch');



INSERT INTO Service (serviceName, description, price, applicableSpecies)
VALUES (
    N'Tắm thú cưng',
    N'Dịch vụ tắm rửa sạch sẽ cho chó/mèo bằng sữa tắm chuyên dụng',
    100000,
    N'All'
);

-- 2. Cắt tỉa lông
INSERT INTO Service (serviceName, description, price, applicableSpecies)
VALUES (
    N'Cắt tỉa lông',
    N'Cắt, tỉa tạo kiểu lông cho chó/mèo theo yêu cầu',
    150000,
    N'Dog'
);

-- 3. Tiêm phòng dại
INSERT INTO Service (serviceName, description, price, applicableSpecies)
VALUES (
    N'Tiêm phòng dại',
    N'Tiêm vắc xin phòng bệnh dại cho chó và mèo',
    200000,
    N'All'
);

-- 4. Khám tổng quát
INSERT INTO Service (serviceName, description, price, applicableSpecies)
VALUES (
    N'Khám sức khỏe tổng quát',
    N'Kiểm tra sức khỏe toàn diện định kỳ',
    250000,
    N'All'
);

-- 5. Cạo vôi răng
INSERT INTO Service (serviceName, description, price, applicableSpecies)
VALUES (
    N'Cạo vôi răng',
    N'Làm sạch răng, loại bỏ mảng bám và vôi răng cho thú cưng',
    180000,
    N'Dog'
);