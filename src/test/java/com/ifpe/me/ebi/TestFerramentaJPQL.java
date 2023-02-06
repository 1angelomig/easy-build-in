/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.Ferramenta;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestFerramentaJPQL extends Teste {
    @Test
    public void consultaTodasFerramentas() {
        logger.info("Executado consultaTodasFerramentas()");
        TypedQuery<Ferramenta> query = em.createQuery("SELECT f FROM Ferramenta f", Ferramenta.class);
        List<Ferramenta> ferramentas = query.getResultList();

        ferramentas.forEach(o -> assertEquals(4, ferramentas.size()));
    }
    
    @Test
    public void consultaPorDono() {
        logger.info("Executado consultaPorDono()");
        TypedQuery<Ferramenta> query = em.createNamedQuery("Ferramenta.consultaPorDono", Ferramenta.class);
        query.setParameter("nome", "Miguel");
        List<Ferramenta> ferramentas = query.getResultList();
        ferramentas.forEach(ferramenta -> {
            assertTrue(ferramenta.getDono().getNome().equals("Miguel"));
        });

        assertEquals(2, ferramentas.size());
    }
}
