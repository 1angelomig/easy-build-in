/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "TB_NOTA_FISCAL")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Nota.consultaNotaPorCliente",
                    query = "SELECT n FROM NotaFiscal n LEFT JOIN n.tomadorServico t WHERE t.nome = :nome"
            ),
            @NamedQuery(
                    name = "Nota.consultaNotaPorPedreiro",
                    query = "SELECT n FROM NotaFiscal n LEFT JOIN n.prestadorServico p WHERE p.nome = :nome"
            ),
            @NamedQuery(
                    name = "Nota.consultaNotaPorObra",
                    query = "SELECT n FROM NotaFiscal n LEFT JOIN n.servicoRealizado o WHERE o.descricao = :descricao"
            )
        }
)
public class NotaFiscal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PRESTADOR_SERVICO", referencedColumnName = "ID")
    private Pedreiro prestadorServico;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TOMADOR_SERVICO", referencedColumnName = "ID")
    private Cliente tomadorServico;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_SERVICO_REALIZADO", referencedColumnName = "ID")
    private Obra servicoRealizado;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_EMISSAO", nullable = false)
    private Date dataEmissao;
    
    @Column(name = "FLOAT_VALOR", nullable = false)
    private float valorTotal;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Pedreiro getPrestadorServico() {
        return prestadorServico;
    }

    public void setPrestadorServico(Pedreiro prestadorServico) {
        this.prestadorServico = prestadorServico;
        this.prestadorServico.addNotaFiscal(this);
    }

    public Cliente getTomadorServico() {
        return tomadorServico;
    }

    public void setTomadorServico(Cliente tomadorServico) {
        this.tomadorServico = tomadorServico;
        this.tomadorServico.addNotaFiscal(this);
    }

    public Obra getServicoRealizado() {
        return servicoRealizado;
    }

    public void setServicoRealizado(Obra servicoRealizado) {
        this.servicoRealizado = servicoRealizado;
        this.servicoRealizado.setNotaFiscal(this);
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
