package com.svn.gsheetsserviceaccount.service;

import com.google.api.client.auth.oauth2.Credential;

public interface GoogleConnectionService {

    public Credential getCredentials();
}
