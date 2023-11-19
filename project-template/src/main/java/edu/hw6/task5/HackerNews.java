package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    public HackerNews() {
    }

    public long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        long[] topStories = new long[0];
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            System.out.println(responseBody);
            String[] tokens = responseBody.substring(1, responseBody.length() - 1).split(",");
            topStories = new long[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                topStories[i] = Long.parseLong(tokens[i].trim());
            }
        }
        return topStories;
    }

    public String news(long id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .GET()
            .build();
        var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return extractTitleFromResponse(response.body());
    }

    private String extractTitleFromResponse(String responseBody) {
        Pattern pattern = Pattern.compile("\"title\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
