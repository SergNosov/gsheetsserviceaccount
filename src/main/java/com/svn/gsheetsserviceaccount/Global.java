package com.svn.gsheetsserviceaccount;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
public class Global {

    public final static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public final static HttpTransport HTTP_TRANSPORT = createHttpTransport();

    private static HttpTransport createHttpTransport() {
        try {
            return GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException ex) {
            log.error("--- Exception in createHttpTransport: " + ex.getMessage());
            throw new RuntimeException("--- Exception in createHttpTransport.",ex);
        }
    }
}
