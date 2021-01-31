package com.svn.gsheetsserviceaccount.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.svn.gsheetsserviceaccount.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Configuration
public class GoogleServicesConfig {

    private final Resource resource;
    private final String appName;

    public GoogleServicesConfig(@Qualifier("webApplicationContext")
                                           ResourceLoader resourceLoader,
                                   @Value("${google.file}")
                                           String secret,
                                   @Value("${google.app.name}")
                                           String appName) {
        this.resource = resourceLoader.getResource("classpath:" + secret);
        this.appName = appName;
    }

    @Bean
    public GoogleCredential googleCredentials() {
        try {
            return GoogleCredential.fromStream(
                    new FileInputStream(resource.getFile()),
                    Global.HTTP_TRANSPORT,
                    Global.JSON_FACTORY)
                    .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        } catch (IOException ioe) {
            log.error("---Не удалось получить учетные данные: " + ioe.getMessage());
            throw new RuntimeException("---Не удалось получить учетные данные: " + ioe.getMessage(), ioe);
        }
    }

    @Bean
    public Sheets googleSheets(GoogleCredential googleCredentials) {
        return new Sheets.Builder(
                Global.HTTP_TRANSPORT,
                Global.JSON_FACTORY,
                googleCredentials)
                .setApplicationName(appName).build();
    }
}
