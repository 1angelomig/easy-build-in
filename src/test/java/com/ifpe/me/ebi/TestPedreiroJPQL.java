/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import com.ifpe.me.ebi.model.Pedreiro;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestPedreiroJPQL extends Teste {
    @Test
    public void ordenacaoPorNomeDesc(){
        logger.info("Executando ordenacaoPorNome()");
        TypedQuery<Pedreiro> query = em.createQuery("SELECT p FROM Pedreiro p ORDER BY p.nome DESC", Pedreiro.class);
        List<Pedreiro> pedreiros = query.getResultList();
        assertEquals(2, pedreiros.size());
        assertEquals("Miguel", pedreiros.get(0).getNome());
        assertEquals("Ayrton", pedreiros.get(1).getNome());
    }
    
    @Test
    public void ordenacaoPorNomeAsc(){
        logger.info("Executando ordenacaoPorNome()");
        TypedQuery<Pedreiro> query = em.createQuery("SELECT p FROM Pedreiro p ORDER BY p.nome ASC", Pedreiro.class);
        List<Pedreiro> pedreiros = query.getResultList();
        assertEquals(2, pedreiros.size());
        assertEquals("Ayrton", pedreiros.get(0).getNome());
        assertEquals("Miguel", pedreiros.get(1).getNome());
    }
}
