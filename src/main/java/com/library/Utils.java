package com.library;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class Utils {
    public static String getBaseURL(HttpEntity<String> entity) {
        HttpHeaders headers = entity.getHeaders();
        String scheme = headers.getFirst("X-Forwarded-Proto");
        if (scheme == null) {
            scheme = "http"; // Default to http if the scheme is not provided
        }
        String serverName = headers.getFirst("Host");
        int serverPort = UriComponentsBuilder.fromHttpUrl(scheme + "://" + serverName).build().getPort();
        String contextPath = headers.getFirst("X-Context-Path");

        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);
        if (contextPath != null) url.append(contextPath);
        if (url.toString().endsWith("/")) {
            url.append("/");
        }
        return url.toString();
    }
}