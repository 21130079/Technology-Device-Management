CREATE TABLE devices (
    idDevice NVARCHAR(50) primary key,
    nameProduct NVARCHAR(255) NOT NULL,
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
	idProduct NVARCHAR(50)   FOREIGN KEY REFERENCES devices(idDevice),
	invoiceDate DATE NOT NULL,
	amount Float NOT NULL
);

select * from devices