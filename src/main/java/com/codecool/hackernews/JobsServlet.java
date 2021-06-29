package com.codecool.hackernews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String pageId = request.getParameter("page") != null ? request.getParameter("page") : "1";

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

        String jsonResponseString = client.sendAsync(request1, HttpResponse.BodyHandlers.ofString())
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
                    buffer.append("<div class=\"job-url\">URL: ");
                    buffer.append(item.url);
                    buffer.append("</div>");
                    buffer.append("</div>");
                }
        );

        out.println(
                "<html>\n" +
                        "  <head>" +
                        "    <title>Best jobs ever!</title>" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href='/static/css/site.css' />" +
                        "  </head>\n" +
                        "<body>\n" +
                        "<ul class=\"navbar\">" +
                        "<li><a id=\"pager\" href=\"/\" data-page=\"1\">Hackson news</a></li>" +
                        "<li><a href=\"/top\">Top News</a></li>" +
                        "<li><a href=\"/newest\">Newest</a></li>" +
                        "<li><a href=\"/jobs\">Jobs</a></li>" +
                        "</ul><br>" +
                        "<div class=\"button-container\">" + "<button class=\"previous\">Previous</button>" + "<button class=\"next\">Next</button>" +
                        "</div><br>" +
                        "<div class=\"grid-container\">" + buffer + "</div>" +
                        "  <br/>" +
                        "<footer class=\"footer\"><p>Ferenc Jancsár, late16@gmail.com</p></footer>" +
                        "    <script src='/static/js/main.js'></script>" +
                        "</body>" +
                        "</html>"
        );
    }
}
