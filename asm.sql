use master
go 

create database asm_java4
go 

use asm_java4
go 

create table [user] 
(
	id int primary key identity,
	username varchar(10) unique not null,
	[password] varchar(10) not null,
	email varchar(50) unique not null,
	isAdmin bit not null default 0,
	isActive bit not null default 1
)
go
create table video 
(
	id int primary key identity,
	title nvarchar(225) not null,
	href varchar(50) unique not null,
	poster varchar(225) null,
	[views] int not null default 0,
	shares int not null default 0,
	[description] nvarchar(50) not null,
	isActive bit not null default 1
)
go
create table favorite 
(
	id int primary key identity,
	userId int foreign key references [user](id),
	videoId int foreign key references video(id),
	videoDate datetime not null default getDate(),
	isLiked bit not null default 0,
	likeDate datetime null
)
go

insert into [user] (username, [password],email,isAdmin) values
('dat','123','dat9a13@gmail.com',1),
('dat1','123','datt8843@gmail.com',0)
go 
insert into video(title,href,[description]) values
(N'Lindsey Stirling - Senbonzakura (Kurousa Cover)','6-wEAeNcA_A', 'Kurousa,Senbonzakura'),
(N'14. Anh Đã Ổn Hơn - RPT MCK | "99%" the album','dz6xe0xXqYE', 'MCK ,anhdaonhon'),
(N'Andree Right Hand - Chơi Như Tụi Mỹ | Official MV','A-tX5PI3V0o', 'Andree  ,choinhutuimy')
go
insert into favorite(userId,videoId,isLiked,likeDate) values
(2,1,1,getDate()),
(2,3,0,null)
go






 


