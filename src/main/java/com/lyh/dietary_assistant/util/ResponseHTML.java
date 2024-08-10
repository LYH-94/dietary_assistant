package com.lyh.dietary_assistant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class ResponseHTML {
    @Autowired
    private ResourceLoader resourceLoader;

    public ResponseEntity<byte[]> getHTML(String html, HttpStatus httpStatus) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/pages/" + html + ".html");
        byte[] content = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(Files.probeContentType(resource.getFile().toPath())));
        headers.setContentLength(content.length);

        return new ResponseEntity<>(content, headers, httpStatus);
    }
}
