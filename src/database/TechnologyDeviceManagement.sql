CREATE TABLE devices (
    idDevice NVARCHAR(50) primary key,
    nameDevice NVARCHAR(255) NOT NULL,
    category NVARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    brand NVARCHAR(255) NOT NULL,
    manufacturingDate DATE NOT NULL,
    weight FLOAT NOT NULL,
    urlImg NVARCHAR(MAX) NOT NULL,
    quantityInStock INT NOT NULL
);

CREATE TABLE orders (
    idOrder NVARCHAR(50) primary key,
	invoiceDate DATE NOT NULL,
	amount Float NOT NULL
);
CREATE TABLE OrderDevices(
    idOrderDevice NVARCHAR(50) primary key,
    idDevice NVARCHAR(50)   FOREIGN KEY REFERENCES devices(idDevice),
    idOrder NVARCHAR(50)   FOREIGN KEY REFERENCES orders(idOrder),
)
INSERT INTO devices (idDevice, nameProduct, category, price, brand, manufacturingDate, weight, urlImg, quantityInStock) VALUES
('DEV001', 'Smartphone X', 'Smartphone', 799.99, 'Brand A', '2023-05-15', 0.2, 'http://example.com/image1.jpg', 100),
('DEV002', 'Laptop Y', 'Laptop', 1299.99, 'Brand B', '2023-04-20', 1.5, 'http://example.com/image2.jpg', 50),
('DEV003', 'Smartwatch Z', 'Wearable', 199.99, 'Brand C', '2023-07-10', 0.1, 'http://example.com/image3.jpg', 200),
('DEV004', 'Tablet W', 'Tablet', 499.99, 'Brand D', '2023-06-05', 0.8, 'http://example.com/image4.jpg', 80),
('DEV005', 'Wireless Headphones', 'Headphones', 149.99, 'Brand E', '2023-08-25', 0.3, 'http://example.com/image5.jpg', 150);
('DEV006', 'Smartphone S', 'Smartphone', 699.99, 'Brand F', '2023-09-20', 0.25, 'http://example.com/image6.jpg', 120),
('DEV007', 'Laptop T', 'Laptop', 1499.99, 'Brand G', '2023-08-15', 1.8, 'http://example.com/image7.jpg', 60),
('DEV008', 'Smartwatch R', 'Wearable', 249.99, 'Brand H', '2023-10-10', 0.12, 'http://example.com/image8.jpg', 180),
('DEV009', 'Tablet Q', 'Tablet', 599.99, 'Brand I', '2023-11-05', 0.9, 'http://example.com/image9.jpg', 90),
('DEV010', 'Wireless Earbuds', 'Headphones', 99.99, 'Brand J', '2023-12-25', 0.05, 'http://example.com/image10.jpg', 200),
('DEV011', 'Smartphone A1', 'Smartphone', 899.99, 'Brand K', '2023-01-15', 0.22, 'http://example.com/image11.jpg', 80),
('DEV012', 'Laptop B1', 'Laptop', 1199.99, 'Brand L', '2023-02-20', 1.4, 'http://example.com/image12.jpg', 70),
('DEV013', 'Smartwatch C1', 'Wearable', 179.99, 'Brand M', '2023-03-10', 0.11, 'http://example.com/image13.jpg', 150),
('DEV014', 'Tablet D1', 'Tablet', 399.99, 'Brand N', '2023-04-05', 0.7, 'http://example.com/image14.jpg', 100),
('DEV015', 'Bluetooth Speaker', 'Audio', 79.99, 'Brand O', '2023-05-25', 0.5, 'http://example.com/image15.jpg', 250),
('DEV016', 'Smartphone E1', 'Smartphone', 799.99, 'Brand P', '2023-06-15', 0.21, 'http://example.com/image16.jpg', 110),
('DEV017', 'Laptop F1', 'Laptop', 1399.99, 'Brand Q', '2023-07-20', 1.6, 'http://example.com/image17.jpg', 65),
('DEV018', 'Smartwatch G1', 'Wearable', 229.99, 'Brand R', '2023-08-10', 0.13, 'http://example.com/image18.jpg', 170),
('DEV019', 'Tablet H1', 'Tablet', 499.99, 'Brand S', '2023-09-05', 0.8, 'http://example.com/image19.jpg', 85),
('DEV020', 'Gaming Headset', 'Headphones', 199.99, 'Brand T', '2023-10-25', 0.4, 'http://example.com/image20.jpg', 120),
('DEV021', 'Smartphone U1', 'Smartphone', 899.99, 'Brand U', '2023-11-15', 0.23, 'http://example.com/image21.jpg', 75),
('DEV022', 'Laptop V1', 'Laptop', 1299.99, 'Brand V', '2023-12-20', 1.7, 'http://example.com/image22.jpg', 55),
('DEV023', 'Smartwatch W1', 'Wearable', 199.99, 'Brand W', '2023-01-10', 0.14, 'http://example.com/image23.jpg', 160),
('DEV024', 'Tablet X1', 'Tablet', 499.99, 'Brand X', '2023-02-05', 0.6, 'http://example.com/image24.jpg', 95),
('DEV025', 'Wireless Mouse', 'Peripherals', 49.99, 'Brand Y', '2023-03-25', 0.15, 'http://example.com/image25.jpg', 300);
-- Dữ liệu cho bảng "orders"
INSERT INTO orders (idOrder, invoiceDate, amount) VALUES
('ORD001', '2024-04-01', 799.99),
('ORD002', '2024-04-03', 1299.99),
('ORD003', '2024-04-05', 199.99),
('ORD004', '2024-04-07', 499.99),
('ORD005', '2024-04-09', 149.99);

-- Dữ liệu cho bảng "OrderDevices"
INSERT INTO OrderDevices (idOrderDevice, idDevice, idOrder) VALUES
('OD001', 'DEV001', 'ORD001'),
('OD002', 'DEV002', 'ORD002'),
('OD003', 'DEV003', 'ORD003'),
('OD004', 'DEV004', 'ORD004'),
('OD005', 'DEV005', 'ORD005');