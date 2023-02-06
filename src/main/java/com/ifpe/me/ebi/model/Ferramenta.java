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
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "TB_FERRAMENTA")
@NamedQuery(
        name = "Ferramenta.consultaPorDono",
        query = "SELECT f FROM Ferramenta f LEFT JOIN f.dono d WHERE d.nome = :nome"
)
public class Ferramenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_DONO", referencedColumnName = "ID")
    private Pedreiro dono;

    @Column(name = "TXT_NOME", nullable = false, length = 255)
    private String nome;

    @Column(name = "TXT_FUNCAO", nullable = false, length = 255)
    private String funcao;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Pedreiro getDono() {
        return dono;
    }

    public void setDono(Pedreiro dono) {
        this.dono = dono;
        this.dono.addFerramenta(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

}
