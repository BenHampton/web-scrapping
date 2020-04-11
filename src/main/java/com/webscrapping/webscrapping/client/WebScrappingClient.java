package com.webscrapping.webscrapping.client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebScrappingClient {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    public Document retrieveDocument(String URL) throws IOException {

        return Jsoup.connect(URL)
                .userAgent(USER_AGENT)
                .get();
    }
}
