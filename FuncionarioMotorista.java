package com.company;

import java.time.LocalDate;

public class FuncionarioMotorista extends Funcionario {
    private String numeroCNH;
    private String categoriaCNH;
    private LocalDate dataVencimentoCNH;
    private boolean cursoCargaPerigosa;
    private boolean cursoTransportePassageiros;

    public FuncionarioMotorista(String nome, LocalDate dataNascimento, String cpf, String numeroCNH, String categoriaCNH, LocalDate dataVencimentoCNH, boolean cursoCargaPerigosa, boolean cursoTransportePassageiros) {
        super(nome, cpf);
        this.numeroCNH = numeroCNH;
        this.categoriaCNH = categoriaCNH.toUpperCase();
        this.dataVencimentoCNH = dataVencimentoCNH;
        this.cursoCargaPerigosa = cursoCargaPerigosa;
        this.cursoTransportePassageiros = cursoTransportePassageiros;
        System.out.println("Funcionario Motorista adicionado!\n");
    }

    public String getNumeroCNH() {
        return numeroCNH;
    }

    public void setNumeroCNH(String numeroCNH) {
        this.numeroCNH = numeroCNH;
    }

    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public LocalDate getDataVencimentoCNH() {
        return dataVencimentoCNH;
    }

    public void setDataVencimentoCNH(LocalDate dataVencimentoCNH) {
        this.dataVencimentoCNH = dataVencimentoCNH;
    }

    public boolean isCursoCargaPerigosa() {
        return cursoCargaPerigosa;
    }

    public void setCursoCargaPerigosa(boolean cursoCargaPerigosa) {
        this.cursoCargaPerigosa = cursoCargaPerigosa;
    }

    public boolean cursoTransportePassageiros() {
        return cursoTransportePassageiros;
    }

    public void setCursoTransportePassageiros(boolean cursoTransportePassageiros) {
        this.cursoTransportePassageiros = cursoTransportePassageiros;
    }





}
