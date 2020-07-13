use login
/*create table checkout(
	checkoutID  int identity(1,1) primary key,
	cusName nvarchar(100) not null,
	cusAddress nvarchar(200) not null
)*/
/*create table checkoutDetail(
	checkoutID int foreign key references checkout(checkoutID),
	itemID nvarchar(10) foreign key references item(ID),
	quantity int not null,
	primary key (checkoutID, itemID)
)*/