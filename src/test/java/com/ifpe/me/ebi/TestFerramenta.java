/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.Ferramenta;
import com.ifpe.me.ebi.model.Pedreiro;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestFerramenta extends Teste {
    @Test
    public void consultarFerramenta(){
        Ferramenta ferramenta1 = em.find(Ferramenta.class, 1L);
        assertNotNull(ferramenta1);
        assertEquals("Martelo", ferramenta1.getNome());
        assertEquals("Miguel", ferramenta1.getDono().getNome());
    }
    
    @Test
    public void inserirFerramenta(){
        Ferramenta ferramenta1 = criaFerramenta1();
        Ferramenta ferramenta2 = criaFerramenta2();
        
        em.persist(ferramenta1);
        em.persist(ferramenta2);
        em.flush();
        
        Ferramenta aux1 = em.find(Ferramenta.class, 6L);
        assertNotNull(aux1);
        assertEquals("Carro de mão", aux1.getNome());
        assertEquals("Ayrton", aux1.getDono().getNome());
        
        Ferramenta aux2 = em.find(Ferramenta.class, 5L);
        assertNotNull(aux2);
        assertEquals("Furadeira", aux2.getNome());
        assertEquals("Miguel", aux2.getDono().getNome());
    }

    private Ferramenta criaFerramenta1() {
        Ferramenta ferramenta = new Ferramenta();
        Pedreiro dono = em.find(Pedreiro.class, 1L);
        
        ferramenta.setDono(dono);
        ferramenta.setNome("Furadeira");
        ferramenta.setFuncao("Furar");
        
        return ferramenta;
    }

    private Ferramenta criaFerramenta2() {
        Ferramenta ferramenta = new Ferramenta();
        Pedreiro dono = em.find(Pedreiro.class, 2L);
        
        ferramenta.setDono(dono);
        ferramenta.setNome("Carro de mão");
        ferramenta.setFuncao("Ajuda a transportar objetos pesados");
        
        return ferramenta;
    }
    
//    @Test
//    public void atualizarFerramenta() {
//        logger.info("Executando atualizarFerramenta()");
//        
//        String novoNome = "Broca";
//        String novaFuncao = "Brocar";
//        
//        Ferramenta ferramenta = em.find(Ferramenta.class, 2L);
//        ferramenta.setNome(novoNome);
//        ferramenta.setFuncao(novaFuncao);
//        
//        em.flush();
//        
//        String jpql = "SELECT * FROM TB_FERRAMENTA WHERE ID = ?1";
//        TypedQuery<Ferramenta> query = em.createQuery(jpql, Ferramenta.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(1, 2L);
//        ferramenta = query.getSingleResult();
//        assertEquals("Broca", ferramenta.getNome());
//        assertEquals("Brocar", ferramenta.getFuncao());
//    }
    
    @Test
    public void atualizarFerramentaMerge() {
        logger.info("Executando atualizarFerramentaMerge()");
        
        String novoNome = "Broca";
        String novaFuncao = "Brocar";
        
        Ferramenta ferramenta = em.find(Ferramenta.class, 2L);
        ferramenta.setNome(novoNome);
        ferramenta.setFuncao(novaFuncao);
        
        em.clear();
        em.merge(ferramenta);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        ferramenta = em.find(Ferramenta.class, 2L, properties);
        assertEquals("Broca", ferramenta.getNome());
        assertEquals("Brocar", ferramenta.getFuncao());
    }    
    
    @Test
    public void removerFerramenta() {
        logger.info("Executando removerFerramenta()");
        Ferramenta ferramenta = em.find(Ferramenta.class, 3L);
        em.remove(ferramenta);
        Ferramenta aux = em.find(Ferramenta.class, 3L);
        assertNull(aux);
    }
}
