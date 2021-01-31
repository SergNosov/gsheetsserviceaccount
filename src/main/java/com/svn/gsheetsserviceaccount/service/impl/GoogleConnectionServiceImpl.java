package com.svn.gsheetsserviceaccount.service.impl;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleConnectionServiceImpl implements GoogleConnectionService {

    private final GoogleCredential googleCredentials;

    public GoogleConnectionServiceImpl(GoogleCredential googleCredentials) {
        this.googleCredentials=googleCredentials;
    }

    @Override
    public Credential getCredentials() {
        return this.googleCredentials;
    }
}
