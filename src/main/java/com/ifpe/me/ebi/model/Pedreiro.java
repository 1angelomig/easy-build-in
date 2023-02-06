/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "TB_PEDREIRO")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
public class Pedreiro extends Usuario {

    @OneToMany(mappedBy = "dono", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ferramenta> ferramentas;

    @OneToMany(mappedBy = "pedreiro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obrasFeitas;

    @OneToMany(mappedBy = "prestadorServico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaFiscal> notasFiscaisEmitidas;

    @Column(name = "INT_ANOS_EXPERIENCIA", nullable = false, length = 2)
    private int anosExperiencia;

    public List<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }
    
    public void addFerramenta(Ferramenta ferramenta) {
        this.ferramentas.add(ferramenta);
    }

    public List<Obra> getObrasFeitas() {
        return obrasFeitas;
    }

    public void setObrasFeitas(List<Obra> obrasFeitas) {
        this.obrasFeitas = obrasFeitas;
    }
    
    public void addObra(Obra obra) {
        this.obrasFeitas.add(obra);
    }

    public List<NotaFiscal> getNotasFiscaisEmitidas() {
        return notasFiscaisEmitidas;
    }

    public void setNotasFiscaisEmitidas(List<NotaFiscal> notasFiscaisEmitidas) {
        this.notasFiscaisEmitidas = notasFiscaisEmitidas;
    }
    
    public void addNotaFiscal(NotaFiscal notaFiscal) {
        this.notasFiscaisEmitidas.add(notaFiscal);
    }

    ////////////////////////////////////////////////
    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

}
