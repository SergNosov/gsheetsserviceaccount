package com.svn.gsheetsserviceaccount.service;

import java.io.IOException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<String>> readTable() throws IOException;
}
