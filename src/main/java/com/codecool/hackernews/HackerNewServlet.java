package com.codecool.hackernews;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "hackerNewsServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HackerNewServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                        "<li><a href=\"/top\">Top News</a></li>" +
                        "<li><a href=\"/newest\">Newest</a></li>" +
                        "<li><a href=\"/jobs\">Jobs</a></li>" +
                        "</ul>" +
                        "<br><div>" + "</div><br>" +
                        "<footer class=\"footer\"><p>Ferenc Jancsár, late16@gmail.com</p></footer>" +
                        "</body></html>"
        );
    }
}
