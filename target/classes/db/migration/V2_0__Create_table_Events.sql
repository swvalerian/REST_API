create table Events (
    File_Id integer,
    Create_Date varchar(100),
    Update_Date varchar(100),
    Delete_Date varchar(100)
);
-- я так понял внешние связи не нужны.
-- FOREIGN KEY (File_Id) REFERENCES Files (Id)