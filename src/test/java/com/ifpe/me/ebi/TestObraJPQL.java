/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import com.ifpe.me.ebi.model.Obra;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestObraJPQL extends Teste {

    @Test
    public void consultaTodasObras() {
        logger.info("Executado consultaTodasObras()");
        TypedQuery<Obra> query = em.createQuery("SELECT o FROM Obra o", Obra.class);
        List<Obra> obras = query.getResultList();

        obras.forEach(o -> assertEquals(2, obras.size()));
    }

    @Test
    public void consultaPorCliente() {
        logger.info("Executado consultaPorCliente()");
        TypedQuery<Obra> query = em.createNamedQuery("Obra.consultaObraPorCliente", Obra.class);
        query.setParameter("nome", "Arthur");
        List<Obra> obras = query.getResultList();
        obras.forEach(obra -> {
            assertTrue(obra.getCliente().getNome().equals("Arthur"));
        });

        assertEquals(1, obras.size());
    }
    
    @Test
    public void consultaPorPedreiro() {
        logger.info("Executado consultaPorPedreiro()");
        TypedQuery<Obra> query = em.createNamedQuery("Obra.consultaObraPorPedreiro", Obra.class);
        query.setParameter("nome", "Miguel");
        List<Obra> obras = query.getResultList();
        obras.forEach(obra -> {
            assertTrue(obra.getPedreiro().getNome().equals("Miguel"));
        });

        assertEquals(1, obras.size());
    }
    
    @Test
    public void consultaObraMaisCara(){
        logger.info("Executado consultaObraMaisCara()");
        Query query = em.createQuery("SELECT MAX(o.valor) FROM Obra o", Obra.class);
        float valor = (float) query.getSingleResult();
        assertEquals(60000.0, valor, 0);
    }
}
