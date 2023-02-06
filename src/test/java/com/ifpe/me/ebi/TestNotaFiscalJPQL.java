/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.NotaFiscal;
import com.ifpe.me.ebi.model.Obra;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestNotaFiscalJPQL extends Teste {
    @Test
    public void consultaTodasNotasFiscais() {
        logger.info("Executado consultaTodasNotasFiscais()");
        TypedQuery<NotaFiscal> query = em.createQuery("SELECT n FROM NotaFiscal n", NotaFiscal.class);
        List<NotaFiscal> notasFiscais = query.getResultList();

        notasFiscais.forEach(o -> assertEquals(2, notasFiscais.size()));
    }
    
    @Test
    public void consultaPorCliente() {
        logger.info("Executado consultaPorCliente()");
        TypedQuery<NotaFiscal> query = em.createNamedQuery("Nota.consultaNotaPorCliente", NotaFiscal.class);
        query.setParameter("nome", "Arthur");
        List<NotaFiscal> notasFiscais = query.getResultList();
        notasFiscais.forEach(nota -> {
            assertTrue(nota.getTomadorServico().getNome().equals("Arthur"));
        });

        assertEquals(1, notasFiscais.size());
    }
    
    @Test
    public void consultaPorPedreiro() {
        logger.info("Executado consultaPorPedreiro()");
        TypedQuery<NotaFiscal> query = em.createNamedQuery("Nota.consultaNotaPorPedreiro", NotaFiscal.class);
        query.setParameter("nome", "Miguel");
        List<NotaFiscal> notasFiscais = query.getResultList();
        notasFiscais.forEach(nota -> {
            assertTrue(nota.getPrestadorServico().getNome().equals("Miguel"));
        });

        assertEquals(1, notasFiscais.size());
    }
    
    @Test
    public void consultaNotaPorObra() {
        logger.info("Executado consultaNotaPorObra()");
        TypedQuery<NotaFiscal> query = em.createNamedQuery("Nota.consultaNotaPorObra", NotaFiscal.class);
        query.setParameter("descricao", "Construir Laje");
        List<NotaFiscal> notasFiscais = query.getResultList();
        notasFiscais.forEach(nota -> {
            assertTrue(nota.getServicoRealizado().getDescricao().equals("Construir Laje"));
        });

        assertEquals(1, notasFiscais.size());
    }
}
