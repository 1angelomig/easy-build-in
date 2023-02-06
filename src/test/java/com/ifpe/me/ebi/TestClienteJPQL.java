/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.Cliente;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author angel
 */
public class TestClienteJPQL extends Teste {
    @Test
    public void ordenacaoPorNomeDesc(){
        logger.info("Executando ordenacaoPorNome()");
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c ORDER BY c.nome DESC", Cliente.class);
        List<Cliente> clientes = query.getResultList();
        assertEquals(2, clientes.size());
        assertEquals("Matheus", clientes.get(0).getNome());
        assertEquals("Arthur", clientes.get(1).getNome());
    }
    
    @Test
    public void ordenacaoPorNomeAsc(){
        logger.info("Executando ordenacaoPorNome()");
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c ORDER BY c.nome ASC", Cliente.class);
        List<Cliente> clientes = query.getResultList();
        assertEquals(2, clientes.size());
        assertEquals("Arthur", clientes.get(0).getNome());
        assertEquals("Matheus", clientes.get(1).getNome());
    }
}
