package com.svn.gsheetsserviceaccount.service;

import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;

public interface GoogleConnectionService {

    public Credential getCredentials() throws IOException;
}
