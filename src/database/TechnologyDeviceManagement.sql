create
database tdm;
    use
tdm;
CREATE TABLE devices
(
    idDevice          NVARCHAR(50) primary key,
    nameDevice        NVARCHAR(255) NOT NULL,
    category          NVARCHAR(255) NOT NULL,
    price             FLOAT NOT NULL,
    brand             NVARCHAR(255) NOT NULL,
    manufacturingDate DATE  NOT NULL,
    weight            FLOAT NOT NULL,
    urlImg            NVARCHAR(255) NOT NULL,
    quantityInStock   INT   NOT NULL
);

CREATE TABLE orders
(
    idOrder     NVARCHAR(50) primary key,
    invoiceDate DATE NOT NULL

);
CREATE TABLE OrderDevices
(
    idOrderDevice int AUTO_INCREMENT PRIMARY KEY,
    idDevice      NVARCHAR(50),
    idOrder       NVARCHAR(50),
    FOREIGN KEY (idDevice) REFERENCES devices (idDevice),
    FOREIGN KEY (idOrder) REFERENCES orders (idOrder)
);

CREATE TRIGGER generate_idDevice_trigger
    BEFORE INSERT
    ON devices
    FOR EACH ROW
BEGIN
    DECLARE new_id INT;
    SET new_id = (SELECT IFNULL(MAX(CAST(SUBSTRING(idDevice, 4) AS UNSIGNED)), 0) + 1 FROM devices);
    SET NEW.idDevice = CONCAT(LOWER(SUBSTRING(NEW.nameDevice, 1, 3)), LPAD(new_id, 3, '0'));
END;


INSERT INTO devices (nameDevice, category, price, brand, manufacturingDate, weight, urlImg, quantityInStock)
VALUES ('Samsung Galaxy S22 Ultra', 'Smartphone', 1199.99, 'Samsung', '2024-03-15', 0.230,
        'https://example.com/samsunggalaxys22ultra.jpg', 200),
       ('MacBook Pro 14-inch M1 Pro', 'Laptop', 1999.00, 'Apple', '2024-02-20', 1.4,
        'https://example.com/macbookpro14m1pro.jpg', 150),
       ('Garmin Fenix 7', 'Smartwatch', 799.99, 'Garmin', '2024-01-10', 0.082, 'https://example.com/garminfenix7.jpg',
        100),
       ('Microsoft Surface Pro 8', 'Tablet', 1099.99, 'Microsoft', '2024-04-05', 0.77,
        'https://example.com/surfacepro8.jpg', 120),
       ('Bose Noise Cancelling Headphones 700', 'Headphones', 379.99, 'Bose', '2024-03-20', 0.25,
        'https://example.com/bosenoise700.jpg', 180),
       ('Google Pixel 7 Pro', 'Smartphone', 899.00, 'Google', '2024-02-10', 0.210,
        'https://example.com/googlepixel7pro.jpg', 220),
       ('Dell XPS 15', 'Laptop', 1499.99, 'Dell', '2024-01-05', 1.8, 'https://example.com/dellxps15.jpg', 170),
       ('Apple Watch Series 8', 'Smartwatch', 399.00, 'Apple', '2024-04-10', 0.070,
        'https://example.com/applewatchseries8.jpg', 250),
       ('Amazon Fire HD 10', 'Tablet', 199.99, 'Amazon', '2024-03-15', 0.504, 'https://example.com/amazonfirehd10.jpg',
        130),
       ('JBL Flip 6 Portable Bluetooth Speaker', 'Audio', 129.95, 'JBL', '2024-02-20', 0.54,
        'https://example.com/jblflip6.jpg', 200),
       ('OnePlus 10 Pro', 'Smartphone', 899.00, 'OnePlus', '2024-01-10', 0.210, 'https://example.com/oneplus10pro.jpg',
        180),
       ('HP Spectre x360', 'Laptop', 1299.99, 'HP', '2024-04-05', 1.3, 'https://example.com/hpspectrex360.jpg', 150),
       ('Huawei Watch GT 3', 'Smartwatch', 299.99, 'Huawei', '2024-03-20', 0.056,
        'https://example.com/huaweiwatchgt3.jpg', 220),
       ('Lenovo Tab P11 Plus', 'Tablet', 299.99, 'Lenovo', '2024-02-10', 0.500,
        'https://example.com/lenovotabp11plus.jpg', 190),
       ('Sony WH-XB910N Extra Bass Wireless Headphones', 'Headphones', 249.99, 'Sony', '2024-01-05', 0.25,
        'https://example.com/sonywhxb910n.jpg', 210),
       ('Xiaomi Mi 12', 'Smartphone', 799.00, 'Xiaomi', '2024-04-10', 0.210, 'https://example.com/xiaomimi12.jpg', 200),
       ('Acer Predator Helios 300', 'Laptop', 1399.99, 'Acer', '2024-03-15', 2.5,
        'https://example.com/acerpredatorhelios300.jpg', 160),
       ('Fossil Gen 6 Smartwatch', 'Smartwatch', 299.95, 'Fossil', '2024-02-20', 0.080,
        'https://example.com/fossilgen6.jpg', 230),
       ('Samsung Galaxy Tab S8', 'Tablet', 699.99, 'Samsung', '2024-01-10', 0.580,
        'https://example.com/samsunggalaxytabs8.jpg', 140),
       ('Sennheiser Momentum 4 Wireless Headphones', 'Headphones', 399.95, 'Sennheiser', '2024-04-05', 0.30,
        'https://example.com/sennheisermomentum4.jpg', 190),
       ('Motorola Edge 40', 'Smartphone', 699.00, 'Motorola', '2024-03-20', 0.210,
        'https://example.com/motorolaedge40.jpg', 210),
       ('ASUS ROG Zephyrus G14', 'Laptop', 1799.99, 'ASUS', '2024-02-10', 1.6,
        'https://example.com/asusrogzephyrusg14.jpg', 140),
       ('Garmin Venu 2 Plus', 'Smartwatch', 399.99, 'Garmin', '2024-01-05', 0.082,
        'https://example.com/garminvenu2plus.jpg', 240),
       ('Microsoft Surface Go 3', 'Tablet', 399.99, 'Microsoft', '2024-04-10', 0.547,
        'https://example.com/surfacego3.jpg', 180),
       ('Beats Studio Buds', 'Headphones', 149.99, 'Beats', '2024-03-15', 0.005,
        'https://example.com/beatsstudiobuds.jpg', 220);

-- Dữ liệu cho bảng "orders"
INSERT INTO orders (idOrder, invoiceDate)
VALUES ('1', '2024-04-01'),
       ('2', '2024-04-03'),
       ('3', '2024-04-05'),
       ('4', '2024-04-07'),
       ('5', '2024-04-09');

SELECT *
from devices
-- Dữ liệu cho bảng "OrderDevices"
    INSERT
INTO OrderDevices (idOrder, idDevice)
VALUES
    ('1', 'ace017' ), ('1', 'ace017'), ('2', 'bea025'), ('2', 'bea025'), ('2', 'bea025');

