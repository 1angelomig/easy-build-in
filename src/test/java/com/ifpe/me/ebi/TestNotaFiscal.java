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
public class TestNotaFiscal extends Teste {

    @Test
    public void consultaNotaFiscal() {
        NotaFiscal nota1 = em.find(NotaFiscal.class, 1L);
        assertNotNull(nota1);
        assertEquals("Mon Feb 06 00:00:00 BRST 2023", nota1.getDataEmissao().toString());
    }

    @Test
    public void inserirNotaFiscal() {
        NotaFiscal nota1 = criarNota1();

        em.persist(nota1);
        em.flush();

        NotaFiscal aux1 = em.find(NotaFiscal.class, 3L);
        assertNotNull(aux1);
        assertEquals("Miguel", aux1.getPrestadorServico().getNome());
        assertEquals("Obra 1", aux1.getServicoRealizado().getDescricao());
    }

    private NotaFiscal criarNota1() {
        NotaFiscal nota = new NotaFiscal();

        Date d = Calendar.getInstance().getTime();
        Pedreiro pedreiro = em.find(Pedreiro.class, 1L);
        Cliente cliente = em.find(Cliente.class, 3L);

        Calendar c1 = Calendar.getInstance();
        c1.set(2023, Calendar.FEBRUARY, 06);
        Calendar c2 = Calendar.getInstance();
        c2.set(2023, Calendar.JULY, 11);
        Obra obra = new Obra();
        obra.setDataInicio(c1.getTime());
        obra.setDataFim(c2.getTime());
        obra.setValor(320000);
        obra.setDescricao("Obra 1");
        obra.setEndereco("Obra 1");
        obra.setEstadoObraTerminada(false);
        obra.setDescricaoEstadoObra("Obra 2 recém iniciada");
        obra.setPedreiro(pedreiro);
        obra.setCliente(cliente);

        nota.setPrestadorServico(pedreiro);
        nota.setTomadorServico(cliente);
        nota.setServicoRealizado(obra);
        nota.setDataEmissao(d);
        nota.setValorTotal(obra.getValor());

        return nota;
    }

//    @Test
//    public void atualizarNotaFiscal() {
//        logger.info("Executando atualizarNotaFiscal()");
//
//        float novoValor = 15000f;
//
//        NotaFiscal notaFiscal = em.find(NotaFiscal.class, 1L);
//        notaFiscal.setValorTotal(novoValor);
//
//        em.flush();
//        
//        String jpql = "SELECT n FROM NotaFiscal n WHERE n.id = ?2";
//        TypedQuery<NotaFiscal> query = em.createQuery(jpql, NotaFiscal.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(2, 1L);
//        notaFiscal = query.getSingleResult();
//        assertEquals(15000f, notaFiscal.getValorTotal(), 0.0f);
//    }
    
    @Test
    public void atualizarNotaFiscalMerge() {
        logger.info("Executando atualizarNotaFiscalMerge()");
        
        float novoValor = 15000f;
        
        NotaFiscal notaFiscal = em.find(NotaFiscal.class, 1L);
        notaFiscal.setValorTotal(novoValor);
        
        em.clear();
        em.merge(notaFiscal);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        notaFiscal = em.find(NotaFiscal.class, 1L, properties);
        assertEquals(15000f, notaFiscal.getValorTotal(), 0.0f);
    }    

    @Test
    public void removerNotaFiscal() {
        logger.info("Executando removerNotaFiscal()");
        NotaFiscal notaFiscal = em.find(NotaFiscal.class, 2L);
        em.remove(notaFiscal);
        NotaFiscal aux = em.find(NotaFiscal.class, 2L);
        assertNull(aux);
    }
}
