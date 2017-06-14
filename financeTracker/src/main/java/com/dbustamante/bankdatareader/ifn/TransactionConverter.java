/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader.ifn;

import com.dbustamante.bankdatareader.model.PlainTransaction;
import com.dbustamante.bankdatareader.model.Transaction;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author daniel.bustamante
 */
public interface TransactionConverter extends Function<List<PlainTransaction>, List<Transaction>>{
    
}
