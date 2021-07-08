create table Users (
    User_Id int not null,
    Event_Name varchar(100) not null,
    File_Id int not null
);
-- FOREIGN KEY (File_Id) REFERENCES Events (File_Id)