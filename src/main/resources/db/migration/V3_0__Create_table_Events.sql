create table Events (
    event_id int PRIMARY KEY Auto_increment,
    created DATETIME,
    updated DATETIME,
    deleted DATETIME
    ,file_id integer not null
    ,user_id integer not null
    ,foreign key (user_id) REFERENCES users (id)
    ,foreign key (file_id) REFERENCES files (id)
);
-- ,id integer


-- при удаление из таблицы Files происходит такая ошибка:
-- Cannot delete or update a parent row: a foreign key constraint fails (`swvalerian`.`events`, CONSTRAINT `events_ibfk_1` FOREIGN KEY (`id`) REFERENCES `files` (`Id`))
--FOREIGN KEY (id) REFERENCES Files (Id)



--Формат даты и времени может быть следующий:
--
-- 2019-03-28 10:00:00
--20190328100000
--2019/03/28 10.00.00
--2019*03*28*10*00*00

-- я так понял внешние связи не нужны.
-- FOREIGN KEY (File_Id) REFERENCES Files (Id)