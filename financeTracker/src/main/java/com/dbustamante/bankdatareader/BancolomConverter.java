/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.Transaction;
import com.dbustamante.bankdatareader.model.PlainTransaction;
import com.dbustamante.bankdatareader.ifn.TransactionConverter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author daniel.bustamante
 */
public class BancolomConverter implements TransactionConverter{

    ThreadLocal<SimpleDateFormat> format = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy/MM/dd"));
    
    @Override
    public List<Transaction> apply(List<PlainTransaction> list) {
        return list.stream().map(this::convert).collect(Collectors.toList());
    }
    
    
    private Transaction convert(PlainTransaction plain){
        try { 
            Transaction t = new Transaction();
            t.setOficina(plain.getOficina());
            t.setDescripcion(plain.getDescripcion());
            t.setReferencia(plain.getReferencia());
            t.setValor(new BigDecimal(plain.getValor().replaceAll(",", "")));
            t.setFecha(format.get().parse(plain.getFecha()));
            return t;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
