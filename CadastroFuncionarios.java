package com.company;

import java.util.LinkedList;


public class CadastroFuncionarios {
    private final LinkedList<Funcionario> funcionarios;

    public CadastroFuncionarios() {
        this.funcionarios = new LinkedList<>();
    }

    public void cadastrar(Funcionario funcionario){
        if(funcionario != null){
            this.funcionarios.add(funcionario);
        }
    }


    public void listarFuncionarios(){
        System.out.println("Funcionarios em ordem alfabética:");
        funcionarios.stream()
                .map(f -> f.getNome() +" CPF:"+ f.getCpf()+" | "+f.getClass().getName()+" | Ocupado: "+f.isOcupado())
                .sorted()
                .forEach(f -> System.out.println("\t"+f));
    }

    public Funcionario getByCPF(String cpf){
        return funcionarios.stream()
                .filter(fs -> fs.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public void listarMotoristasLivres(){
        funcionarios.stream()
                .filter(f -> f instanceof FuncionarioMotorista)
                .filter(fm -> !fm.isOcupado())
                .forEach(fmL -> {
                    FuncionarioMotorista temp = (FuncionarioMotorista)fmL;
                    System.out.println("Motorista Livre: "+fmL.getNome()+" CPF:"+fmL.getCpf()+" CNH Tipo:"+(temp.getCategoriaCNH())+"\n\tHabilitado para carga perigosa? "+temp.isCursoCargaPerigosa()+" | Habilitado para transporte de passageiros? "+temp.cursoTransportePassageiros());
                });

    }

}
