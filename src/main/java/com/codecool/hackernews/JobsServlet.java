package com.codecool.hackernews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "jobsServlet", urlPatterns = {"/jobs"}, loadOnStartup = 4)
public class JobsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pageId = request.getParameter("page");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request1 = null;


        try {
            request1 = HttpRequest.newBuilder()
                    .uri(new URI("https://api.hnpwa.com/v0/jobs/"+pageId+".json"))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        var jsonResponseString = client.sendAsync(request1, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        Type listType = new TypeToken<ArrayList<Post>>(){}.getType();
        List<Post> postList = new Gson().fromJson(jsonResponseString, listType);

        StringBuffer buffer = new StringBuffer();

        postList.forEach(item -> {
                    buffer.append("<div class=\"grid-item\">");
                    buffer.append("Job title:<br><div class=\"title\">");
                    buffer.append(item.title);
                    buffer.append("</div><br>");
                    buffer.append("<div class=\"time-ago\">When: ");
                    buffer.append(item.time_ago);
                    buffer.append("</div>");
                    buffer.append("<div class=\"job-url\">When: ");
                    buffer.append(item.url);
                    buffer.append("</div>");
                    buffer.append("</div>");
                }
        );

        String previousButtonId = Integer.parseInt(pageId) == 1 ? "#" : Integer.toString(Integer.parseInt(pageId) - 1);
        String nextButtonId = Integer.parseInt(pageId) >= 1 ? Integer.toString(Integer.parseInt(pageId) + 1) : "#";

        String previousButton = previousButtonId.equals("#") ? "<a class=\"previous\">Previous</a>" :
                "<a href=\"jobs?page="+ previousButtonId +"\" class=\"previous\">Previous</a>";
        String nextButton = "<a href=\"jobs?page="+ nextButtonId +"\" class=\"next\">Next</a>";


        out.println(
                "<html>\n" +
                        "  <head>" +
                        "    <title>Best jobs ever right hyaaaa!</title>" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css' />" +
                        "  </head>\n" +
                        "<body>\n" +
                        "<ul class=\"navbar\">" +
                        "<li><a href=\"/\">Hackson news</a></li>" +
                        "<li><a href=\"/top?page=1\">Top News</a></li>" +
                        "<li><a href=\"/newest?page=1\">Newest</a></li>" +
                        "<li><a href=\"/jobs?page=1\">Jobs</a></li>" +
                        "</ul><br>" +
                        "<div class=\"button-container\">" + previousButton + nextButton +
                        "</div><br>" +
                        "<div class=\"grid-container\">" + buffer.toString() + "</div>" +
                        "  <br/>" +
                        "<footer class=\"footer\"><p>Ferenc Jancsár, late16@gmail.com</p></footer>" +
                        "    <script src='/static/js/main.js'></script>" +
                        "</body>" +
                        "</html>"
        );
    }
}
