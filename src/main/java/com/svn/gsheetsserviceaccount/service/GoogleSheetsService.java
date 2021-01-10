package com.svn.gsheetsserviceaccount.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<Object>> readTable(GoogleConnectionService gc) throws IOException;
}
