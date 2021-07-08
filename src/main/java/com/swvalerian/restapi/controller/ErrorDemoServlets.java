package com.swvalerian.restapi.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorDemoServlets extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // настроим кодировку страницы
        response.setCharacterEncoding("UTF-8");

        // получим код исключения
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        // пишем свой обработчик на ошибку
        String title = "Ошибка!";
        String about = "Страница не найдена!";
        String docType = "<!DOCTYPE html>";

        writer.println(docType + "<html>" +
                "<head>" +
                "<title>" + title + "</title>" +
                "</head>" +
                "<body>");

        writer.println("<h1>Информация о возникшей ошибке:</h1>");
        writer.println("Code: " + code);
        if (code == 404) {
            writer.println("Значение кода ошибки: " + about);
        }

        writer.println("</body>");
        writer.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
