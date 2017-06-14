/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bank.financeTracker.rest;

import com.dbustamante.bank.financeTracker.rest.dto.MultipartFileUpload;
import com.dbustamante.bankdatareader.Readers;
import com.dbustamante.bankdatareader.model.Transaction;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 *
 * @author daniel.bustamante
 */
@Path("import")
public class DataImportService {
    
    @POST
    @Path("xml")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String importBankData(@MultipartForm MultipartFileUpload fileUpload) throws IOException{
        String file = IOUtils.toString(fileUpload.getFile(), Charset.forName("ISO-8859-1"));
        List<Transaction> transactions = new Readers().bancolombiaAhorrosReader().apply(file);
        
        StringBuilder sb = new StringBuilder();
        transactions.forEach(t -> sb.append(t.getValor().toPlainString()).append("<br> \n"));
        return sb.toString();
    }
    
}
