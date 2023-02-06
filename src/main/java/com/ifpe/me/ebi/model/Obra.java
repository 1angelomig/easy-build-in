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
@Table(name = "TB_OBRA")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Obra.consultaObraPorCliente",
                    query = "SELECT o FROM Obra o LEFT JOIN o.cliente c WHERE c.nome = :nome"
            ),
            @NamedQuery(
                    name = "Obra.consultaObraPorPedreiro",
                    query = "SELECT o FROM Obra o LEFT JOIN o.pedreiro p WHERE p.nome = :nome"
            )
        }
)
public class Obra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PEDREIRO", referencedColumnName = "ID")
    private Pedreiro pedreiro;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "servicoRealizado", cascade = CascadeType.ALL)
    private NotaFiscal notaFiscal;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_INICIO", nullable = false)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_FIM", nullable = true)
    private Date dataFim;

    @Column(name = "FLOAT_VALOR", nullable = false)
    private float valor;

    @Column(name = "TXT_DESCRICAO", nullable = false, length = 255)
    private String descricao;

    @Column(name = "TXT_ENDERECO", nullable = false, length = 255)
    private String endereco;

    @Column(name = "BOL_ESTADO_OBRA_TERMINADA", nullable = false)
    private boolean estadoObraTerminada;

    @Column(name = "TXT_DESCRICAO_ESTADO_OBRA", nullable = false, length = 255)
    private String descricaoEstadoObra;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Pedreiro getPedreiro() {
        return pedreiro;
    }

    public void setPedreiro(Pedreiro pedreiro) {
        this.pedreiro = pedreiro;
        this.pedreiro.addObra(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.addObra(this);
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isEstadoObraTerminada() {
        return estadoObraTerminada;
    }

    public void setEstadoObraTerminada(boolean estadoObraTerminada) {
        this.estadoObraTerminada = estadoObraTerminada;
    }

    public String getDescricaoEstadoObra() {
        return descricaoEstadoObra;
    }

    public void setDescricaoEstadoObra(String descricaoEstadoObra) {
        this.descricaoEstadoObra = descricaoEstadoObra;
    }

}
