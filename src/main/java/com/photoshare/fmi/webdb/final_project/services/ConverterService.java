/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project.services;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.photoshare.fmi.webdb.final_project.repository.UserRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author killer
 */
@Singleton
public class ConverterService {

    public byte[] convert(Part file) {

        File temp = null;
        File outputFile = null;
        OutputStream out = null;
        InputStream filecontent = null;
//        Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, "Attempting to create temp files");
        try {
            temp = File.createTempFile("temp-file-word", ".doc");
            out = new FileOutputStream(temp);
            filecontent = file.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            outputFile = File.createTempFile("temp-file-pdf", ".pdf");

        } catch (IOException ex) {
//            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, "Error attempting to read from generated pdf");
        }

// connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = null;
        try {
            connection = new SocketOpenOfficeConnection(8100);
            connection.connect();
        } catch (ConnectException ex) {
String test ="";
//            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, "Error attempting to connnect to LibreOffice");
        }

        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(temp, outputFile);

        connection.disconnect();

        byte outbytes[] = null;
        try {
            outbytes = Files.readAllBytes(outputFile.toPath());
            outputFile.delete();
            temp.delete();
        } catch (IOException ex) {
//            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, "Error attempting to read from generated pdf");
        }

        return outbytes;
    }
}
