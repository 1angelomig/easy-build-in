/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi;

import static com.ifpe.me.ebi.Teste.logger;
import com.ifpe.me.ebi.model.Cliente;
import com.ifpe.me.ebi.model.NotaFiscal;
import com.ifpe.me.ebi.model.Obra;
import com.ifpe.me.ebi.model.Pedreiro;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
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
public class TestObra extends Teste {

    @Test
    public void consultarObra() {
        Obra obra1 = em.find(Obra.class, 1L);
        assertNotNull(obra1);
        assertEquals("Construir Laje", obra1.getDescricao());
    }

    @Test
    public void inserirObra() {
        Obra obra1 = criarObra1();

        em.persist(obra1);
        em.flush();

        Obra aux1 = em.find(Obra.class, 3L);
        assertNotNull(aux1);
        assertEquals("Obra 1", aux1.getDescricao());
        assertEquals("Obra 1", aux1.getEndereco());
    }

    private Obra criarObra1() {
        Obra obra = new Obra();

        Pedreiro pedreiro = em.find(Pedreiro.class, 2L);
        Cliente cliente = em.find(Cliente.class, 3L);

        Calendar c1 = Calendar.getInstance();
        c1.set(2023, Calendar.FEBRUARY, 06);

        Calendar c2 = Calendar.getInstance();
        c2.set(2023, Calendar.JULY, 11);

        obra.setCliente(cliente);
        obra.setPedreiro(pedreiro);
        obra.setDataInicio(c1.getTime());
        obra.setDataFim(c2.getTime());
        obra.setValor(320000);
        obra.setDescricao("Obra 1");
        obra.setEndereco("Obra 1");
        obra.setEstadoObraTerminada(false);
        obra.setDescricaoEstadoObra("Obra 1 recém iniciada");

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPrestadorServico(pedreiro);
        notaFiscal.setTomadorServico(cliente);
        Date d = Calendar.getInstance().getTime();
        notaFiscal.setDataEmissao(d);
        notaFiscal.setValorTotal(obra.getValor());
        notaFiscal.setServicoRealizado(obra);

        return obra;
    }

//    @Test
//    public void atualizarObra() {
//        logger.info("Executando atualizarObra()");
//
//        String novaDescricao = "Teste Descricao";
//        String novoEndereco = "Teste Endereço";
//
//        Obra obra = em.find(Obra.class, 1L);
//        obra.setDescricao(novaDescricao);
//        obra.setEndereco(novoEndereco);
//
//        em.flush();
//        
//        String jpql = "SELECT o FROM Obra o WHERE o.id = ?2";
//        TypedQuery<Obra> query = em.createQuery(jpql, Obra.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(2, 1L);
//        obra = query.getSingleResult();
//        assertEquals("Teste Descricao", obra.getDescricao());
//        assertEquals("Teste Endereço", obra.getEndereco());
//    }
    
    @Test
    public void atualizarObraMerge() {
        logger.info("Executando atualizarObraMerge()");

        String novaDescricao = "Teste Descricao";
        String novoEndereco = "Teste Endereço";

        Obra obra = em.find(Obra.class, 1L);
        obra.setDescricao(novaDescricao);
        obra.setEndereco(novoEndereco);

        em.clear();
        em.merge(obra);

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        obra = em.find(Obra.class, 1L, properties);
        assertEquals("Teste Descricao", obra.getDescricao());
        assertEquals("Teste Endereço", obra.getEndereco());
    }

    @Test
    public void removerObra() {
        logger.info("Executando removerObra()");
        Obra obra = em.find(Obra.class, 1L);
        em.remove(obra);
        Obra aux = em.find(Obra.class, 1L);
        assertNull(aux);
    }
}
