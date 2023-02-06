/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import com.ifpe.me.ebi.model.Pedreiro;
import com.ifpe.me.ebi.model.Usuario;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestPedreiro extends Teste {
    @Test
    public void consultarPedreiro() {
        Pedreiro pedreiro1 = em.find(Pedreiro.class, 1L);
        assertNotNull(pedreiro1);
        assertEquals("Miguel", pedreiro1.getNome());
        assertEquals(4, pedreiro1.getAnosExperiencia());
    }

    @Test
    public void inserirPedreiro() {
        Pedreiro pedreiro1 = criarPedreiro1();
        Pedreiro pedreiro2 = criarPedreiro2();

        em.persist(pedreiro1);
        em.persist(pedreiro2);
        em.flush();

        Pedreiro pAux1 = em.find(Pedreiro.class, 5L);
        assertNotNull(pAux1);
        assertEquals("Pedreiro 1", pAux1.getNome());
        assertEquals("pedreiro1@teste.com", pAux1.getEmail());
        assertEquals("00000000000", pAux1.getCpf());
        
        Pedreiro pAux2 = em.find(Pedreiro.class, 6L);
        assertNotNull(pAux2);
        assertEquals("Pedreiro 2", pAux2.getNome());
        assertEquals("pedreiro2@teste.com", pAux2.getEmail());
        assertEquals("11111111111", pAux2.getCpf());
    }

    public Pedreiro criarPedreiro1() {
        Pedreiro pedreiro = new Pedreiro();
        pedreiro.setNome("Pedreiro 1");
        pedreiro.setCpf("00000000000");
        Calendar c = Calendar.getInstance();
        c.set(2001, Calendar.JANUARY, 01);
        pedreiro.setDataNascimento(c.getTime());
        pedreiro.setEmail("pedreiro1@teste.com");
        pedreiro.setSenha("123");
        pedreiro.setAnosExperiencia(4);
        return pedreiro;
    }

    public Pedreiro criarPedreiro2() {
        Pedreiro pedreiro = new Pedreiro();
        pedreiro.setNome("Pedreiro 2");
        pedreiro.setCpf("11111111111");
        Calendar c = Calendar.getInstance();
        c.set(2001, Calendar.JANUARY, 01);
        pedreiro.setDataNascimento(c.getTime());
        pedreiro.setEmail("pedreiro2@teste.com");
        pedreiro.setSenha("123");
        pedreiro.setAnosExperiencia(4);
        return pedreiro;
    }
    
    @Test
    public void atualizarPedreiro() {
        logger.info("Executando atualizarPedreiro()");
        
        String novoEmail = "teste@gmail.com";
        String contato = "81985239632";
        
        Pedreiro pedreiro = em.find(Pedreiro.class, 1L);
        pedreiro.setEmail(novoEmail);
        pedreiro.addContato(contato);
        
        em.flush();
        
        String jpql = "SELECT p FROM Pedreiro p WHERE p.id = ?1";
        TypedQuery<Pedreiro> query = em.createQuery(jpql, Pedreiro.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 1L);
        pedreiro = query.getSingleResult();
        assertEquals("teste@gmail.com", pedreiro.getEmail());        
        assertTrue(pedreiro.getContatos().contains("81985239632"));
    }
    
    @Test
    public void atualizarPedreiroMerge() {
        logger.info("Executando atualizarPedreiroMerge()");
        
        String novoEmail = "teste@gmail.com";
        String telefone = "81985239632";
        
        Pedreiro pedreiro = em.find(Pedreiro.class, 1L);
        pedreiro.setEmail(novoEmail);
        pedreiro.addContato(telefone);
        
        em.clear();
        em.merge(pedreiro);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        pedreiro = em.find(Pedreiro.class, 1L, properties);
        assertEquals("teste@gmail.com", pedreiro.getEmail());        
        assertTrue(pedreiro.getContatos().contains("81985239632"));
    }    
    
    @Test
    public void removerPedreiro() {
        logger.info("Executando removerPedreiro()");
        Pedreiro pedreiro = em.find(Pedreiro.class, 2L);
        em.remove(pedreiro);
        Usuario usuario = em.find(Usuario.class, 2L);
        assertNull(usuario);
    }
}
