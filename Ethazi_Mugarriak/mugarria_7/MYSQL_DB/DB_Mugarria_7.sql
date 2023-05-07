create database mugarria_6;
use mugarria_6;


create table Pictures(
pictureId INT,
    title VARCHAR(50),
    date_ DATE,
    file_ varchar(50),
    visits INT,
    photographerId INT,
    PRIMARY KEY(pictureId)
);



create table Photographers(
photographerId INT,
    name_ VARCHAR(50),
    awarded BOOLEAN,
    PRIMARY KEY(photographerId)
);

alter table Pictures add foreign key(photographerId) references Photographers(photographerId);

-- select * from Photographers;

insert into Photographers(photographerId,name_,awarded) values(1, "Anse Aldams", true);
insert into Photographers(photographerId,name_,awarded) values(2, "Rothko", false);
insert into Photographers(photographerId,name_,awarded) values(3, "Vangogh", true);

insert into Pictures values(1,"Mendia Elurrarekin", "2023-02-18","images/ansealdams1.jpg",0,1);
insert into Pictures values(2,"Mendi Lehorra","2023-04-28","images/ansealdams2.jpg",0,1);
insert into Pictures values(3,"Txuria eta Naranja","2023-01-10","images/rothko1.jpg",0,2);
insert into Pictures values(4,"Zerua eta Ilargia","2023-02-02","images/vangogh1.jpg",0,3);
insert into Pictures values(5,"Zelai Berdea","2023-04-28","images/vangogh2.jpg",0,3);