package com.svn.gsheetsserviceaccount.service;

import com.itextpdf.text.DocumentException;
import com.svn.gsheetsserviceaccount.model.Contact;

import java.io.FileNotFoundException;

public interface PdfService {

    public void generatePdf(Contact contact) throws FileNotFoundException, DocumentException;
}
