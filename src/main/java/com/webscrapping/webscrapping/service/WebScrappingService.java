package com.webscrapping.webscrapping.service;

import com.webscrapping.webscrapping.client.WebScrappingClient;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebScrappingService {

    private final WebScrappingClient webScrappingClient;

    private final int TWITTER_SUBSTRING_METADATA = 8;

    private final int OPEN_GRAPH_SUBSTRING_METADATA = 3;

    private final String CONTENT = "content";

    private final String META = "meta";

    private final String NAME = "name";

    private final String PROPERTY = "property";

    private final String TWITTER = "twitter";

    public WebScrappingService(WebScrappingClient webScrappingClient) {
        this.webScrappingClient = webScrappingClient;
    }

    public Elements retrieveMetadata(String url) throws IOException {

        Document document = webScrappingClient.retrieveDocument(url);

        return document.getElementsByTag(META);
    }

    public Map<String, String> retrieveOpenGraphPropertyMetadata(String url) throws IOException{

        Elements metaTags = retrieveMetadata(url);
        Map<String, String> openGraphMetadataMap = new HashMap<>();

        for (Element metaTag : metaTags) {
            String openGraphProperty = metaTag.attr(PROPERTY);

            if (!StringUtil.isBlank(openGraphProperty) &&
                    !openGraphProperty.substring(OPEN_GRAPH_SUBSTRING_METADATA).contains(":")) {

                String value = metaTag.attr(CONTENT);
                openGraphMetadataMap.put(openGraphProperty.substring(OPEN_GRAPH_SUBSTRING_METADATA), value);
            }
        }

        return openGraphMetadataMap;
    }

    public Map<String, String> retrieveTwitterPropertyMetadata(String url) throws IOException{

        Elements metaTags = retrieveMetadata(url);
        Map<String, String> twitterMetadataMap = new HashMap<>();

        for (Element metaTag : metaTags) {
            String twitterMetadata = metaTag.attr(NAME);

            if (!StringUtil.isBlank(twitterMetadata) &&
                    twitterMetadata.contains(TWITTER)) {

                String value = metaTag.attr(CONTENT);
                twitterMetadataMap.put(twitterMetadata.substring(TWITTER_SUBSTRING_METADATA), value);
            }
        }

        return twitterMetadataMap;
    }
}

