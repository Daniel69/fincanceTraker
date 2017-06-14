/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.Transaction;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author daniel.bustamante
 */
public class Readers {
    
    public Function<String, List<Transaction>> bancolombiaAhorrosReader(){
        return new BancolomConverter().compose(new BancolombiaReader());
    }
    
}
