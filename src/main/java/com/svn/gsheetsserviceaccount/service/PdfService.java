package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;

import java.io.OutputStream;

public interface PdfService {

    public OutputStream generatePdf(Contact contact);
}
