package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private final static String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";

    private final static String STORY_URL = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private final static int OK_STATUS = 200;

    private final static Pattern TITLE = Pattern.compile("\"title\"\\s*:\\s*\"(.*?)\"");

    public HackerNews() {
    }

    public long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(TOP_STORIES_URL))
            .GET()
            .build();
        /*try (var client = HttpClient.newBuilder()
            .build()) {
            try {
                var response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == OK_STATUS) {
                    return parseTopStories(response.body());
                }
            } catch (IOException | InterruptedException e) {
                return new long[] {};
            }
        }*/
        return new long[] {};
    }

    public String news(long id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(String.format(STORY_URL, id)))
            .GET()
            .build();
        /*try (var client = HttpClient.newBuilder()
            .build()) {
            try {
                var response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == OK_STATUS) {
                    return parseTitle(response.body());
                }
            } catch (IOException | InterruptedException e) {
                return "";
            }
        }*/
        return "";
    }

    private String parseTitle(String responseBody) {
        Matcher matcher = TITLE.matcher(responseBody);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private long[] parseTopStories(String responseBody) {
        String[] tokens = responseBody.substring(1, responseBody.length() - 1).split(",");
        long[] topStories = new long[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            topStories[i] = Long.parseLong(tokens[i].trim());
        }
        return topStories;
    }
}
