/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.Cliente;
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
public class TestCliente extends Teste {
    @Test
    public void consultaCliente(){
        Cliente cliente1 = em.find(Cliente.class, 3L);
        assertNotNull(cliente1);
        assertEquals("Arthur", cliente1.getNome());
    }
    
    @Test
    public void inserirCliente(){
        Cliente cliente1 = criarCliente1();
        Cliente cliente2 = criarCliente2();
        
        em.persist(cliente1);
        em.persist(cliente2);
        em.flush();
        
        Cliente cAux1 = em.find(Cliente.class, 6L);
        assertNotNull(cAux1);
        assertEquals("Cliente 1", cAux1.getNome());
        assertEquals("cliente1@teste.com", cAux1.getEmail());
        assertEquals("00000000000", cAux1.getCpf());
        
        Cliente cAux2 = em.find(Cliente.class, 5L);
        assertNotNull(cAux2);
        assertEquals("Cliente 2", cAux2.getNome());
        assertEquals("cliente2@teste.com", cAux2.getEmail());
        assertEquals("11111111111", cAux2.getCpf());
    }

    private Cliente criarCliente1() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");
        cliente.setCpf("00000000000");
        Calendar c = Calendar.getInstance();
        c.set(2001, Calendar.JANUARY, 01);
        cliente.setDataNascimento(c.getTime());
        cliente.setEmail("cliente1@teste.com");
        cliente.setSenha("123");
        return cliente;
    }

    private Cliente criarCliente2() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 2");
        cliente.setCpf("11111111111");
        Calendar c = Calendar.getInstance();
        c.set(2001, Calendar.JANUARY, 01);
        cliente.setDataNascimento(c.getTime());
        cliente.setEmail("cliente2@teste.com");
        cliente.setSenha("123");
        return cliente;
    }
    
    @Test
    public void atualizarCliente() {
        logger.info("Executando atualizarCliente()");
        
        String novoEmail = "teste@gmail.com";
        String contato = "81985239632";
        
        Cliente cliente = em.find(Cliente.class, 3L);
        cliente.setEmail(novoEmail);
        cliente.addContato(contato);
        
        em.flush();
        
        String jpql = "SELECT c FROM Cliente c WHERE c.id = ?1";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 3L);
        cliente = query.getSingleResult();
        assertEquals("teste@gmail.com", cliente.getEmail());        
        assertTrue(cliente.getContatos().contains("81985239632"));
    }
    
    @Test
    public void atualizarClienteMerge() {
        logger.info("Executando atualizarClienteMerge()");
        
        String novoEmail = "teste@gmail.com";
        String telefone = "81985239632";
        
        Cliente cliente = em.find(Cliente.class, 3L);
        cliente.setEmail(novoEmail);
        cliente.addContato(telefone);
        
        em.clear();
        em.merge(cliente);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cliente = em.find(Cliente.class, 3L, properties);
        assertEquals("teste@gmail.com", cliente.getEmail());        
        assertTrue(cliente.getContatos().contains("81985239632"));
    }    
    
    @Test
    public void removerCliente() {
        logger.info("Executando removerCliente()");
        Cliente cliente = em.find(Cliente.class, 4L);
        em.remove(cliente);
        Usuario usuario = em.find(Usuario.class, 4L);
        assertNull(usuario);
    }
}
