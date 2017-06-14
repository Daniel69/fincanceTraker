/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader;

import com.dbustamante.bankdatareader.model.PlainTransaction;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.util.Files;

/**
 *
 * @author daniel.bustamante
 */
public class BancolombiaReaderTest {
    
    BancolombiaReader reader = new BancolombiaReader();
    
    @Test
    public void shouldReadOneItemFile() throws JAXBException {
        String data = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<Movimientos>\n" +
                "    <Movimiento>\n" +
                "        <FECHA>2017/01/22</FECHA>\n" +
                "        <DOCUMENTO/>\n" +
                "        <OFICINA>VIVA LAURELES</OFICINA>\n" +
                "        <DESCRIPCIÓN>COMPRA PTO.VENTA</DESCRIPCIÓN>\n" +
                "        <REFERENCIA/>\n" +
                "        <VALOR>-43,400.00</VALOR>\n" +
                "    </Movimiento>\n" +
                "</Movimientos>";
        
        List<PlainTransaction> list = reader.readXml(data);
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getValor()).isEqualTo("-43,400.00");
        assertThat(list.get(0).getFecha()).isEqualTo("2017/01/22");
        assertThat(list.get(0).getOficina()).isEqualTo("VIVA LAURELES");
        assertThat(list.get(0).getDescripcion()).isEqualTo("COMPRA PTO.VENTA");
        assertThat(list.get(0).getReferencia()).isEmpty();                
    }
    
    @Test
    public void shouldReadTwoItemFile() throws JAXBException {
        String data = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<Movimientos>\n" +
                "    <Movimiento>\n" +
                "        <FECHA>2017/01/22</FECHA>\n" +
                "        <DOCUMENTO/>\n" +
                "        <OFICINA>VIVA LAURELES</OFICINA>\n" +
                "        <DESCRIPCIÓN>COMPRA PTO.VENTA</DESCRIPCIÓN>\n" +
                "        <REFERENCIA/>\n" +
                "        <VALOR>-43,400.00</VALOR>\n" +
                "    </Movimiento>\n" +
                "    <Movimiento>\n" +
                "        <FECHA>2017/01/22</FECHA>\n" +
                "        <DOCUMENTO/>\n" +
                "        <OFICINA>VIVA LAURELES</OFICINA>\n" +
                "        <DESCRIPCIÓN>COMPRA PTO.VENTA</DESCRIPCIÓN>\n" +
                "        <REFERENCIA>45435</REFERENCIA>\n" +
                "        <VALOR>-43,400.00</VALOR>\n" +
                "    </Movimiento>\n" +                
                "</Movimientos>";
        
        List<PlainTransaction> list = reader.readXml(data);
        assertThat(list).hasSize(2);
        assertThat(list.get(1).getReferencia()).isEqualTo("45435");              
    }    
    
    @Test
    public void shouldReadTwoItemFileWithFinalItem() throws JAXBException {
        String data = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<Movimientos>\n" +
                "    <Movimiento>\n" +
                "        <FECHA>2017/01/22</FECHA>\n" +
                "        <DOCUMENTO/>\n" +
                "        <OFICINA>VIVA LAURELES</OFICINA>\n" +
                "        <DESCRIPCIÓN>COMPRA PTO.VENTA</DESCRIPCIÓN>\n" +
                "        <REFERENCIA/>\n" +
                "        <VALOR>-43,400.00</VALOR>\n" +
                "    </Movimiento>\n" +
                "    <Movimiento>\n" +
                "        <FECHA>2017/01/22</FECHA>\n" +
                "        <DOCUMENTO/>\n" +
                "        <OFICINA>VIVA LAURELES</OFICINA>\n" +
                "        <DESCRIPCIÓN>COMPRA PTO.VENTA</DESCRIPCIÓN>\n" +
                "        <REFERENCIA>45435</REFERENCIA>\n" +
                "        <VALOR>-43,400.00</VALOR>\n" +
                "    </Movimiento>\n" +
                "    <Movimiento>Este extracto es copia del original</Movimiento>\n"+                
                "</Movimientos>";
        
        List<PlainTransaction> list = reader.readXml(data);
        assertThat(list).hasSize(2);             
    }     
    
    @Test
    public void shouldReadBigFileWithFinalItem() throws JAXBException {
        String xml = Files.contentOf(new File(getClass().getClassLoader().getResource("movs.xml").getFile()), "UTF-8");
        List<PlainTransaction> list = reader.readXml(xml);
        assertThat(list).hasSize(94);             
    }       
    
    
}
