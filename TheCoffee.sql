Create Database TheCoffee
go
use TheCoffee
go

--Create Table
Create Table Administrator(
	Username varchar(50)  Primary key not null,
	[Password] varchar(20) not null
)
go
Create Table Employee(
	CodeEmp varchar(10) Primary key not null,
	[Password] varchar(20) not null,
	FullName nvarchar(50)  not null,
	Gender nvarchar(10)  not null,
	Birthday varchar(20)  not null,
	Phone varchar(20)  not null,
	Email varchar(50) ,
	CMND nchar(9) not null,
	[Address] nvarchar(Max)  not null,
	Img varbinary(MAX)
)
go
Create Table Customer(
	CodeCus varchar(10) Primary key not null,
	
	CardType	varchar(10) not null,
	FullName nvarchar(50)  not null,
	Gender nvarchar(10)  not null,
	Birthday varchar(20)  not null,
	Phone varchar(20)  not null,
	Email varchar(50),
	CMND nchar(9)  not null,
	[Address] nvarchar(Max)  not null,
	Point int
)
go
Create Table Shift(
	CodeShift varchar(10) Primary key not null,
	CodeEmp varchar(50) foreign key REFERENCES Employee(CodeEmp) not null,
	ShiftName	nvarchar(30) not null,
	TimeStart	datetime	not null,
	TimeStop	datetime not null
)
go
Create Table ProductType(
	CodeType varchar(10) Primary key not null,
	TypeName nvarchar(30) not null,
	Size nvarchar(10) not null
)
go
Create Table Drink(
	CodeDrink varchar(20) primary key not null ,
	DrinkName nvarchar(100) not null,
	CodeType varchar(10) FOREIGN KEY REFERENCES ProductType(CodeType) not null,
	Amount int not null,
	Price int not null,
	Image varbinary(MAX)
)
go
Create Table Food(
	CodeFood varchar(20) primary key not null,
	FoodName nvarchar(100) not null,
	CodeType varchar(10) FOREIGN KEY REFERENCES ProductType(CodeType) not null,
	Amount int not null,
	Price int not null,
	Image varbinary(MAX)
)
go
Create Table Bill(
	CodeBill int Identity(1,1) Primary key  not null,
	CodeEmp varchar(10) FOREIGN KEY REFERENCES Employee(CodeEmp) not null,
	CodeCus varchar(10) FOREIGN KEY REFERENCES Customer(CodeCus) not null,
	CodeTable varchar(10),
	TimePayment date not null,
	TotalMoney MONEY not null
)
go
Create Table Promotions(
	CodePromo int Identity(1,1) Primary key,
	PromoName varchar(20) not null unique,
	DiscountPromo int not null,
	StartPromo varchar(20)not null,
	EndPromo varchar(20) not null,
	Description nvarchar(Max)
)
go 
Create Table BillDetail(
	CodeBill int FOREIGN KEY REFERENCES Bill(CodeBill) not null,
	CodeDrink varchar(20) FOREIGN KEY REFERENCES Drink(CodeDrink),
	CodeFood varchar(20) FOREIGN KEY REFERENCES Food(CodeFood),
	Amount int not null,
	Price int not null,
	PromoName varchar(20) FOREIGN KEY REFERENCES Promotions(PromoName),
	DiscountPromo int FOREIGN KEY REFERENCES Promotions(DiscountPromo),
	Constraint PK_BillDetail Primary key (CodeBill,CodeDrink,CodeFood)
)
go
Create Table Revenue(
	IDRevenue int Identity(1,1) Primary key,
	Date varchar(20),
	Money varchar(20)
)
--Insert 1 Administrators
Insert into Administrator values('admin','admin')
