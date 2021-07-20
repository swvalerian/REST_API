package com.swvalerian.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

public class EventsServlets extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // получим доступ к БД
        EventRepository eventRepository = new EventRepository();

        //
        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String requestURI = request.getRequestURI();
        String responseFromDB = null;
        printWriter.println(requestURI);
        printWriter.println("\nrequest.getPathInfo() = " + request.getPathInfo());
        // request.getPathInfo() - возвращает путь, который идет после URI указанного в webxml через mapped

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();

        if (requestURI.equals("/api/v1/events") || requestURI.equals("/api/v1/events/")) {
            List<Event> eventList = eventRepository.getAll();
            responseFromDB = mapper.writeValueAsString(eventList);
        } else {
            String[] requestId = requestURI.split("/api/v1/events/");
            Integer fId = Integer.decode(requestId[1]);
            printWriter.println("Запросили ID = " + fId);
            responseFromDB = mapper.writeValueAsString(eventRepository.getId(fId));
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

        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String requestURI = request.getRequestURI();
        String responseFromDB = null;
        printWriter.println(requestURI);
        printWriter.println("\nrequest.getPathInfo() = " + request.getPathInfo());
        // request.getPathInfo() - возвращает путь, который идет после URI указанного в webxml через mapped

        // самое интересное тут
        Integer pEventId = Integer.decode(request.getParameter("event_id"));// необязателно - автоподставление ключа
        LocalDateTime pCreated = LocalDateTime.now();

        Integer parametrId = Integer.decode(request.getParameter("id")); // необязательно - какой файл создать - автоподставляется
        String parametrRef = request.getParameter("ref"); // имя файла обязательно
        String parametrName = request.getParameter("name"); // имя пользователя, которому принадлежит файл

        // получаем доступ к таблице Files
        FileRepository fileRepository = new FileRepository();
        File fileSave = fileRepository.save(new File(parametrId, parametrRef));

        UserRepository userRepository = new UserRepository();
        User userSave = userRepository.save(new User(44, parametrName));

        // получим доступ к БД
        EventRepository eventRepository = new EventRepository();
        Event eventSave = eventRepository.save(new Event(pEventId, pCreated, null, null,fileSave, userSave));

//        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        responseFromDB = mapper.writeValueAsString(eventSave);

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // получим доступ к БД
        EventRepository eventRepository = new EventRepository();

        //
        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String requestURI = request.getRequestURI();
        String responseFromDB = null;
        printWriter.println(requestURI);
        printWriter.println("\nrequest.getPathInfo() = " + request.getPathInfo());

        Integer pEventId = Integer.decode(request.getParameter("event_id"));
        LocalDateTime pUpdated = LocalDateTime.now(); // запомним дату и время обновления файла
        Integer parametrId = Integer.decode(request.getParameter("id"));
        String parametrRef = request.getParameter("ref");

        FileRepository fileRepository = new FileRepository();
        fileRepository.update(new File(parametrId, parametrRef)); // таким образом мы можем даже изменить сам файл.

        Event event = eventRepository.getId(pEventId); // получим изменяемую сущность
        event.setUpdated(pUpdated); // установим время изменения
        List<Event> eventList = eventRepository.update(event); // запишем сущность в БД

//        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        responseFromDB = mapper.writeValueAsString(eventList);

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        //
        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String requestURI = request.getRequestURI();
        String responseFromDB = null;
        printWriter.println(requestURI);
        printWriter.println("\nrequest.getPathInfo() = " + request.getPathInfo());

        Integer pEventId = Integer.decode(request.getParameter("event_id"));

        // получим доступ к БД
        EventRepository eventRepository = new EventRepository();
        eventRepository.deleteById(pEventId);

//        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        responseFromDB = mapper.writeValueAsString(eventRepository.getAll());

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }
}
