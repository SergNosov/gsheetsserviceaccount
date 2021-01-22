package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;

public interface PdfService {

    public byte[] generatePdf(Contact contact);
}
