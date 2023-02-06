/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.me.ebi.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author angel
 */
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "DISC_USUARIO", 
        discriminatorType = DiscriminatorType.STRING, 
        length = 1
)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(name = "TB_CONTATOS", joinColumns = @JoinColumn(name = "ID_USUARIO", nullable = false))
    @Column(name = "TXT_NUM_CONTATO ", length = 20)
    private Collection<String> contatos;
    
    @Column(name = "TXT_NOME", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "TXT_CPF", nullable = false, length = 14, unique = true)
    private String cpf;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_NASCIMENTO", nullable = true)
    private Date dataNascimento;
    
    @Column(name = "TXT_EMAIL", nullable = false, length = 50)
    private String email;
    
    @Column(name = "TXT_SENHA", nullable = false, length = 50)
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<String> getContatos() {
        return contatos;
    }

    public void setContatos(Collection<String> contatos) {
        this.contatos = contatos;
    }
    
    public void addContato(String contato) {
        if (contatos == null) {
            contatos = new HashSet<>();
        }
        this.contatos.add(contato);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
