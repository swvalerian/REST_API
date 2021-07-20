package com.swvalerian.restapi.controller;

import com.swvalerian.restapi.model.Event;
import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.model.User;
import com.swvalerian.restapi.repository.hibernate.EventRepository;
import com.swvalerian.restapi.repository.hibernate.FileRepository;
import com.swvalerian.restapi.repository.hibernate.UserRepository;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class UsersServlets extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        // получим доступ к БД
        UserRepository userRepository = new UserRepository();

        String requestURI = request.getRequestURI();
        String responseFromDB;

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();

        if (requestURI.equals("/api/v1/users") || requestURI.equals("/api/v1/users/")) {
            List<User> userList = userRepository.getAll();
            responseFromDB = mapper.writeValueAsString(userList);
        } else {
            String[] requestId = requestURI.split("/api/v1/users/");
            Integer fId = Integer.decode(requestId[1]);
            printWriter.println("Запросили ID = " + fId);
            responseFromDB = mapper.writeValueAsString(userRepository.getId(fId));
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

        // обработаем входные данные
        String parametrName = request.getParameter("name");

        UserRepository userRepository = new UserRepository();
        User userSave = new User(11, parametrName);
        userRepository.save(userSave);

//        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        String responseFromDB = mapper.writeValueAsString(userSave);

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

        Integer parametrId = Integer.decode(request.getParameter("id"));
        String parametrName = request.getParameter("name");

        UserRepository userRepository = new UserRepository();
        User userUpdate = userRepository.getId(parametrId);
        userUpdate.setName(parametrName);
        List<User> userList = userRepository.update(userUpdate);

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        String responseFromDB = mapper.writeValueAsString(userList);

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
        Integer userDeleteId = Integer.decode(request.getParameter("id"));
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getId(userDeleteId);
        user.setName(null);
        userRepository.update(user);

        //        JSON преображение
        ObjectMapper mapper = new ObjectMapper();
        String responseFromDB = mapper.writeValueAsString(userRepository.getAll());

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "\n<h1>" + responseFromDB + "</h1>"
        );
    }
}