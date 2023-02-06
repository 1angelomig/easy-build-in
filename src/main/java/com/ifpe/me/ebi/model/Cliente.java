/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi.model;

import jakarta.persistence.CascadeType;
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
@Table(name = "TB_CLIENTE")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obra> obrasContratadas;

    @OneToMany(mappedBy = "tomadorServico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaFiscal> notasFiscaisRecebidas;

    public List<Obra> getObrasContratadas() {
        return obrasContratadas;
    }

    public void setObrasContratadas(List<Obra> obrasContratadas) {
        this.obrasContratadas = obrasContratadas;
    }
    
    public void addObra(Obra obra) {
        this.obrasContratadas.add(obra);
    }

    public List<NotaFiscal> getNotasFiscaisRecebidas() {
        return notasFiscaisRecebidas;
    }

    public void setNotasFiscaisRecebidas(List<NotaFiscal> notasFiscaisRecebidas) {
        this.notasFiscaisRecebidas = notasFiscaisRecebidas;
    }
    
    public void addNotaFiscal(NotaFiscal notaFiscal) {
        this.notasFiscaisRecebidas.add(notaFiscal);
    }

}
