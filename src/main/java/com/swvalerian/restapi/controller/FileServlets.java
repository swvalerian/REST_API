package com.swvalerian.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swvalerian.restapi.model.Event;
import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.model.User;
import com.swvalerian.restapi.repository.hibernate.EventRepository;
import com.swvalerian.restapi.repository.hibernate.FileRepository;
import com.swvalerian.restapi.repository.hibernate.UserRepository;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class FileServlets extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // получим доступ к БД
        FileRepository fileRepository = new FileRepository();

        //
        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String requestURI = request.getRequestURI();
        String responseFromDB = null;
        printWriter.println(requestURI);
        printWriter.println("\nrequest.getPathInfo() = " + request.getPathInfo());

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();

        if (requestURI.equals("/api/v1/files") || requestURI.equals("/api/v1/files/")) {
            List<File> fileList = fileRepository.getAll();
            responseFromDB = mapper.writeValueAsString(fileList);
        } else {
            String[] requestId = requestURI.split("/api/v1/files/");
            Integer fId = Integer.decode(requestId[1]);
            printWriter.println("Запросили ID = " + fId);
            responseFromDB = mapper.writeValueAsString(fileRepository.getId(fId));
        }

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // обработаем входные данные, для создания нового файла, и соответствующих записей в таблицы зависимостей
        LocalDateTime createTime = LocalDateTime.now();// запишем эту инфу в таблицу Events
        Integer parametrId = Integer.decode(request.getParameter("id"));
        String parametrRef = request.getParameter("ref");
        String parametrName = request.getParameter("name");

        // прежде чем создать новое событие, сделаем записи в таблицы, где содержатсья внешние ключи
        // прежде всего сам файл
        FileRepository fileRepository = new FileRepository();
        File fileSave = fileRepository.save(new File(parametrId, parametrRef));// это неработает
//        File fileSave = new File(parametrId, parametrRef);// а так должно сработать

        // далее, кто этот файл создал
        UserRepository userRepository = new UserRepository();
        User userSave = new User(11, parametrName);
        userRepository.save(userSave);

        // ну и наконец можно создать запись логирования в таблицу Events, теперь все её связи существуют, а значит - транзакция пройдет успешно
        EventRepository eventRepository = new EventRepository();
        Event event = new Event(44,createTime, null, null, fileSave, userSave);
        eventRepository.save(event);

//        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        String responseFromDB = mapper.writeValueAsString(fileSave);

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // получим доступ к БД
        FileRepository fileRepository = new FileRepository();

        // Здесь мы обрабатываем запрос типа POST и передаем в слой репозиторий. Где идет работа ХИБЕРА.
        String requestURI = request.getRequestURI();
        printWriter.println(requestURI);

        Integer parametrId = Integer.decode(request.getParameter("id"));
        String parametrRef = request.getParameter("ref");
        String responseFromDB = null;

        List<File> fileList = fileRepository.update(new File(parametrId, parametrRef));

        LocalDateTime getTime = LocalDateTime.now();// запишем эту инфу в таблицу Events

        EventRepository eventRepository = new EventRepository();
        Event event = eventRepository.getId(parametrId);
        event.setUpdated(getTime);
        eventRepository.update(event);

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        responseFromDB = mapper.writeValueAsString(fileList);

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // из запроса получим id файла который следует удалить из таблицы events
        Integer fileDeleteId = Integer.decode(request.getParameter("id"));

        LocalDateTime deleteTime = LocalDateTime.now();// запишем эту инфу в таблицу Events

        EventRepository eventRepository = new EventRepository();
        // нам необходимо из всех событий, выбрать то, что соответствует ID указанному для удаления
        Event event = eventRepository.getAll().stream().filter(f -> f.getFile().getId().equals(fileDeleteId)).findFirst().orElse(null);
        //и просто пометить, что он удален
        event.setDeleted(deleteTime);
        event.setFile(null);
        //обновим информацию в БАЗЕ ДАННЫХ.
        List<Event> eventList = eventRepository.update(event);

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        // получим обновленное событие
        String responseFromDB = mapper.writeValueAsString(eventList);

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }
}