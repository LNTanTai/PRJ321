use demoW1
/*create table item(
	ID nvarchar(10) primary key, 
	name nvarchar(50) not null,
	price real not null
)
insert item values(1, 'python', 1)
insert item values(2, 'java', 1)
insert item values(3, 'C/C++', 1)*/

/*create table cart(
	username varchar(20) foreign key references login(username),
	itemID nvarchar(10) foreign key references item(ID),
	quantity int not null,
	primary key (username, itemID)
)*/
/*create table checkout(
	id int not null identity(1,1) primary key,
	username varchar(20) not null foreign key references login(username),
)*/
/*create table checkoutDetail (
	checkoutID int not null foreign key references checkout(id),
	itemID nvarchar(10) foreign key references item(id),
	quantity int not null,
	primary key (checkoutID, itemID)
)*/
/*create table simpleCart(
	id int not null identity(1,1) primary key
)*/
/*create table simpleCartDetail(
	cartID int foreign key references simpleCart(id),
	itemID nvarchar(10) foreign key references item(id),
	quantity int not null,
	primary key (cartID, itemID)
)*/
--insert simpleCart default values;

SELECT userId FROM tbl_User WHERE userID = 'Tien' AND password = '123'