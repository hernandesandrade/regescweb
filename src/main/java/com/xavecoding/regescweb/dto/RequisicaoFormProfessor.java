package com.xavecoding.regescweb.dto;

import com.xavecoding.regescweb.models.Professor;
import com.xavecoding.regescweb.models.StatusProfessor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class RequisicaoFormProfessor {

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal salario;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusProfessor statusProfessor;

    public void fromProfessor(Professor professor){
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        this.statusProfessor = professor.getStatusProfessor();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    public Professor toProfessor(){
        Professor p = new Professor();
        p.setNome(this.nome);
        p.setSalario(this.salario);
        p.setStatusProfessor(this.statusProfessor);
        return p;
    }

}
