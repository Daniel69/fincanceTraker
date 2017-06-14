/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.PlainTransaction;
import com.dbustamante.bankdatareader.ifn.BankTransactionReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel.bustamante
 */

public class BancolombiaReader implements BankTransactionReader{

    public List<PlainTransaction> readXml(String data) throws JAXBException {
        return unmarshalXml(data)
                .stream().filter(t -> t.getValor() != null)
                .collect(Collectors.toList());
    }
    
    private List<PlainTransaction> unmarshalXml(String data) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Movimientos.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(data);
        return ((Movimientos) unmarshaller.unmarshal(reader)).getTransactions();
    }

    @Override
    public List<PlainTransaction> apply(String t) {
        try {
            return readXml(t);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}

@XmlRootElement(name = "Movimientos")
class Movimientos {
    
    @XmlElement(name = "Movimiento")
    private List<PlainTransaction> movimientos;

    public List<PlainTransaction> getTransactions() {
        return movimientos;
    }
    
    
}