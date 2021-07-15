INSERT INTO Events (event_id,created,updated,deleted,id) values(1,'2019-01-28 10:00:00','2019-03-28 10:00:00',null,1);
INSERT INTO Events (event_id,created,updated,deleted,id) values(2,'2021-05-02 10:50:35','2021-05-02 10:50:35',null,2);
INSERT INTO Events (event_id,created,updated,deleted,id) values(3,'2021-05-02 10:50:35','2021-05-02 10:50:35',null,3);
INSERT INTO Events (event_id,created,updated,deleted,id) values(4,null,null,null,4);
INSERT INTO Events (event_id,created,updated,deleted,id) values(5,null,null,null,5);
INSERT INTO Events (event_id,created,updated,deleted,id) values(6,null,null,null,6);

--Формат даты и времени может быть следующий:
--
-- 2019-03-28 10:00:00
--20190328100000
--2019/03/28 10.00.00
--2019*03*28*10*00*00

--public class TimeExample {
--
--    public static void main(String[] args) {
--
--        //  LocalDateTime to Timestamp
--        LocalDateTime now = LocalDateTime.now();
--        Timestamp timestamp = Timestamp.valueOf(now);
--
--        System.out.println(now);            // 2019-06-14T15:50:36.068076300
--        System.out.println(timestamp);      // 2019-06-14 15:50:36.0680763
--
--        //  Timestamp to LocalDateTime
--        LocalDateTime localDateTime = timestamp.toLocalDateTime();
--
--        System.out.println(localDateTime);  // 2019-06-14T15:50:36.068076300
--
--    }
--}