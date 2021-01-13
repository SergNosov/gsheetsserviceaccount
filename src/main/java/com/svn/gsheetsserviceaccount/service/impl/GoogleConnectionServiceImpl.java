package com.svn.gsheetsserviceaccount.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.svn.gsheetsserviceaccount.Global;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Service
public class GoogleConnectionServiceImpl implements GoogleConnectionService {

    private final ResourceLoader resourceLoader;
    private GoogleCredential googleCredentials = null;

    @Autowired
    public GoogleConnectionServiceImpl(@Qualifier("webApplicationContext")
                                                   ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Credential getCredentials() throws IOException {

        if (googleCredentials == null) { //todo обработать IOException
            Resource resource = resourceLoader.getResource("classpath:sincere-mission-299806-b5b976fd993f.json");

            googleCredentials = GoogleCredential.fromStream(
                    new FileInputStream(resource.getFile()),
                    Global.HTTP_TRANSPORT,
                    Global.JSON_FACTORY)
                    .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY))
            ;
        }
        return googleCredentials;
    }
}
