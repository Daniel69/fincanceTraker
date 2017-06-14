/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bank.financeTracker.rest.dto;

import java.io.InputStream;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author daniel.bustamante
 */
public class MultipartFileUpload {
    
    
    @FormParam("file")
    @PartType(MediaType.TEXT_XML)
    private InputStream file;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

}
