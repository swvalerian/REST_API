package com.swvalerian.restapi.controller;

import com.swvalerian.restapi.model.File;
import com.swvalerian.restapi.repository.hibernate.FileRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileServlets extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");

        // получим доступ к БД
        FileRepository fileRepository = new FileRepository();
        List<File> fileList = fileRepository.getAll();

        //
        // Здесь будет реализованы: парсинг запроса и последующая проверка, что выводить
        String responseFromDB = fileList.toString();
        //

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String title = "HTTP simple example servlet request";
        String contentType = "<!DOCTYPE html>\n"; // стандартный заголовок HTML документа

        printWriter.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "<h1>" + responseFromDB + "</h1>"
        );

    }
}
