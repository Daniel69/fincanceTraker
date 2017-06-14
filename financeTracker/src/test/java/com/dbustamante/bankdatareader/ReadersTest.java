/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.Transaction;
import java.io.File;
import java.util.List;
import java.util.function.Function;
import org.assertj.core.util.Files;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author daniel.bustamante
 */
public class ReadersTest {
    
    @Test
    public void shouldConvertAllBancolombiaRawDataToProcesedData() {
        Function<String, List<Transaction>> bancolombiaReader = new Readers().bancolombiaAhorrosReader();
        String xml = Files.contentOf(new File(getClass().getClassLoader().getResource("movs.xml").getFile()), "ISO-8859-1");
        
        List<Transaction> transactions = bancolombiaReader.apply(xml);
        
        assertThat(transactions).hasSize(94);
        assertThat(transactions.stream().map(t -> t.getValor()).reduce((t1, t2) -> t1.add(t2))
                .get()).isEqualByComparingTo("4616139.91");
                
    }
    
}
