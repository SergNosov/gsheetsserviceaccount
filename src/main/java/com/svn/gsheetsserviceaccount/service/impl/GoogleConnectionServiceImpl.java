package com.svn.gsheetsserviceaccount.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.svn.gsheetsserviceaccount.Global;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Service
@Scope("prototype")
public class GoogleConnectionServiceImpl implements GoogleConnectionService {

    private final Resource resource;
    private final String secret;
    private GoogleCredential googleCredentials;

    @Autowired
    public GoogleConnectionServiceImpl(@Qualifier("webApplicationContext")
                                               ResourceLoader resourceLoader,
                                       @Value("${google.secret-file}")
                                               String secret) {
        this.secret = secret;
        this.resource = resourceLoader.getResource("classpath:" + secret);
    }

    @PostConstruct
    private void init() {

        if (googleCredentials == null) {

            try {
                googleCredentials = GoogleCredential.fromStream(
                        new FileInputStream(resource.getFile()),
                        Global.HTTP_TRANSPORT,
                        Global.JSON_FACTORY)
                        .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
            } catch (IOException ioe) {
                log.error("---Не удалось получить учетные данные: " + ioe.getMessage());
                throw new RuntimeException("---Не удалось получить учетные данные: " + ioe.getMessage(), ioe);
            }

            log.info("--- GoogleConnectionServiceImpl postConstruct init credentials success.");
        }
    }

    @Override
    public Credential getCredentials() {
        return this.googleCredentials;
    }
}
