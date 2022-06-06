package com.company;

import java.time.LocalDate;

public class FuncionarioAdminstrativo extends Funcionario {
    public FuncionarioAdminstrativo(String nome, LocalDate dataNascimento, String cpf) {
        super(nome, cpf);
        System.out.println("Funcionario adminstrativo adicionado!\n");
    }
}