/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.PlainTransaction;
import com.dbustamante.bankdatareader.model.Transaction;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author daniel.bustamante
 */
@RunWith(MockitoJUnitRunner.class)
public class BancolomConverterTest {
    
    
    BancolomConverter converter = new BancolomConverter();
    
    @Test
    public void shouldConvertPlainData() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(2016, Calendar.DECEMBER, 5, 0, 0, 0);
        
        List<Transaction> result = converter.apply(Arrays.asList(getPlainTx()));
        assertThat(result).isNotEmpty().first()
                .hasFieldOrPropertyWithValue("oficina", "Of")
                .hasFieldOrPropertyWithValue("descripcion", "desc")
                .hasFieldOrPropertyWithValue("referencia", "ref0092")
                .hasFieldOrPropertyWithValue("valor", new BigDecimal("-81440.00"))
                .hasFieldOrPropertyWithValue("fecha", c.getTime());
    }
    
    @Test
    public void shouldConvertBigNumber() {     
        List<Transaction> result = converter.apply(Arrays.asList(getPlainTxBigNumber()));
        assertThat(result.get(0).getValor()).isEqualByComparingTo(new BigDecimal(5025129));         
    }    
    
    
    private PlainTransaction getPlainTx(){
        return new PlainTransaction("-81,440.00", "2016/12/05", "Of", "desc", "ref0092");
    }
    
    private PlainTransaction getPlainTxBigNumber(){
        return new PlainTransaction("5,025,129.00", "2016/12/05", "Of", "desc", "ref0092");
    }    
}
