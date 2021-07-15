package com.swvalerian.restapi.controller;

import com.swvalerian.restapi.model.Event;
import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.repository.hibernate.EventRepository;
import com.swvalerian.restapi.repository.hibernate.FileRepository;

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
        // request.getPathInfo() - возвращает путь, который идет после URI указанного в webxml через mapped

        if (requestURI.equals("/api/v1/files") || requestURI.equals("/api/v1/files/")) {
            List<File> fileList = fileRepository.getAll();
            responseFromDB = fileList.toString();
        } else {
            String[] requestId = requestURI.split("/api/v1/files/");
            Integer fId = Integer.decode(requestId[1]);
            printWriter.println("Запросили ID = " + fId);
            responseFromDB = fileRepository.getId(fId).toString();
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

        // получим доступ к БД
        FileRepository fileRepository = new FileRepository();

        // Здесь мы обрабатываем запрос типа POST и передаем в слой репозиторий. Где идет работа ХИБЕРА.
        String requestURI = request.getRequestURI();
        printWriter.println(requestURI);

        Integer parametrId = Integer.decode(request.getParameter("id"));
        String parametrRef = request.getParameter("ref");
        String responseFromDB = null;

        File fileSave = fileRepository.save(new File(parametrId, parametrRef));

        LocalDateTime getTime = LocalDateTime.now();// запишем эту инфу в таблицу Events

        EventRepository eventRepository = new EventRepository();
        Event event = eventRepository.getId(parametrId);
        event.setCreated(getTime);
        eventRepository.save(event);

        responseFromDB = fileSave.toString();

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

        responseFromDB = fileList.toString();

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

        // получим доступ к БД
        FileRepository fileRepository = new FileRepository();

        // Здесь мы обрабатываем запрос типа POST и передаем в слой репозиторий. Где идет работа ХИБЕРА.
        String requestURI = request.getRequestURI();
        printWriter.println(requestURI);

        Integer parametrId = Integer.decode(request.getParameter("id"));
        String responseFromDB;

        // основной метод - удаляем ненужную запись
        fileRepository.deleteById(parametrId);

        LocalDateTime getDelete = LocalDateTime.now();// запишем эту инфу в таблицу Events

        EventRepository eventRepository = new EventRepository();
        Event event = eventRepository.getId(parametrId);
        event.setDeleted(getDelete);
        eventRepository.update(event);

        responseFromDB = fileRepository.getAll().toString();

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }
}
