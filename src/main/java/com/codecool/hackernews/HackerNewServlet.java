package com.codecool.hackernews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "hackerNewsServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HackerNewServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String title = "Michael Hackson news";
        out.println(
                "<html>\n" +
                        "<head>" +
                        "  <title>" + title + "</title>" +
                        "  <link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css' />" +
                        "</head>\n" +
                        "<body>\n" +
                        "<ul class=\"navbar\">" +
                        "<li><a href=\"/\">Hackson news</a></li>" +
                        "<li><a href=\"/top?page=1\">Top News</a></li>" +
                        "<li><a href=\"/newest?page=1\">Newest</a></li>" +
                        "<li><a href=\"/jobs?page=1\">Jobs</a></li>" +
                        "</ul>" +
                        "<br><div>" + "</div><br>" +
//                        "<div class='visit'>You may serve any static content from <span class='folder'>webapp/static</span> folder, like a css file.</div>" +
//                        "<div>Visit another servlet: <a href=\"/another\">Visit the other servlet</a></div>" +
//                        "<div>You can provide a json file as well: <a href=\"/json\">Visit Hacker News json data example</a></div>" +
                        "<footer class=\"footer\"><p>Ferenc Jancsár, late16@gmail.com</p></footer>" +
                        "</body></html>"
        );
    }
}
