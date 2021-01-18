package com.svn.gsheetsserviceaccount.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public void generatePdf(Contact contact) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("d:/temp/contact.pdf"));

        document.open();

       // BaseFont bf = BaseFont.createFont("C:\\WINXP\\Fonts\\ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = FontFactory.getFont(FontFactory.COURIER, "CP1251",16);
        font.setColor(BaseColor.BLACK);

        document.add(new Paragraph("_id: "+contact.getId().toString(),font));
        document.add(new Paragraph("userId: "+contact.getCode(),font));
        document.add(new Paragraph("name: "+contact.getName(),font));
        document.add(new Paragraph("phone: "+contact.getPhone(),font));
        document.add(new Paragraph("e-mail: "+contact.getEmail(),font));

//        Chunk chunk = new Chunk("_id: "+contact.getId().toString(), font);
//        document.add(chunk);

        document.close();
    }
}
